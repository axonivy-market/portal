
def init() {
  configFileProvider(
      [configFile(fileId: 'properties-config', variable: 'PROPERTIES_CONFIG')]) {
      props = readProperties  file: "${env.PROPERTIES_CONFIG}"
  }
}

def stopAllElasticSearchs() {
  echo "====================Turn off Elastics Search port from 19200 to 19210===================="
  powershell '''
    for (($port = 19200); $port -lt 19210; ($port++)) {
      Get-NetTCPConnection -LocalPort $port -erroraction 'silentlycontinue'
      if ( $LastExitCode -eq 0 ) {
        Stop-Process -Id (Get-NetTCPConnection -LocalPort $port).OwningProcess -Force
      }
    }
  '''
  def elasticSearchServices = ['ivy-elasticsearch-master-cluster']
  for (elasticSearchService in elasticSearchServices) {
    stopWindowsService(elasticSearchService)
  }
}

def stopAllEngines() {
  echo "====================Stop engines===================="
  def engineServices = ['Axon.ivy Engine - LTS', 'Axon.ivyEngine8.0', 'Axon.ivyEngine10.0', 'Axon.ivyEngineTrunk', 'Axon.ivy-master-cluster-gui-test-1', 'Axon.ivy-master-cluster-gui-test-2', 'Axon.ivyEngineDashboard']
  for (engineService in engineServices) {
    stopWindowsService(engineService)
  }
  stopAllElasticSearchs()
}

def startWindowsService(String serviceName) {
  stopWindowsService(serviceName)
  echo "====================Start service ${serviceName}===================="
  bat """
    @echo off
    net start "${serviceName}"
    if "%ERRORLEVEL%"=="1" (
      timeout /t 30
      sc query "${serviceName}" | find /i "RUNNING"
    )
  """
}

def stopWindowsService(String serviceName) {
  echo "====================Stop service ${serviceName}===================="
  bat """
  @echo off
    sc query "${serviceName}" | find /i "RUNNING"
    if not "%ERRORLEVEL%"=="1" (net stop "${serviceName}")
    exit 0
  """
}

def remoteDesktop() {
  closeAllRemoteDesktopConnections()
  echo '====================Setup remote desktop connections to Portal slave===================='
  //e.g. remoteDesktopCmd=D:/tools/remote-desktop-plus/rdp.exe /v:portal02.server.ivy-cloud.com /domain:wawa.vn /u:wawa /p:SAMPLE_TEXT_1 /w:2560 /h:1440 /noclose
  bat "${props.remoteDesktopCmd}"
}

def closeAllRemoteDesktopConnections() {
  echo '====================Close all remote desktop connections===================='
  bat '''
    taskkill -im mstsc.exe -F
    exit 0
  '''
}

def killUnnecessaryProcessesToRunTest() {
  echo '====================Close unnecessary processes===================='
  bat '''
    taskkill -im dwm.exe -F
    taskkill -im Taskmgr.exe -F
    taskkill -im procexp64.exe -F
    taskkill -im IEDriverServer.exe -F
    taskkill -im iexplore.exe -F
    taskkill -im GeckoFireFoxDriver.exe -F
    taskkill -im firefox.exe -F
    exit 0
  '''
}

def extractEngine(String engineDir, String engineDownloadURL) {
  echo '====================Extract engine===================='
  bat """
    mvn clean compile -f AxonIvyPortal/portal-components/pom.xml -Divy.engine.directory=${engineDir} ${engineDownloadURL}
  """
}

def cleanDisk() {
  echo '====================Clean disk===================='
  bat """
    c:/tools/clean-disk.bat
    exit 0
  """
}

def getJenkinsMasterDomain() {
  return env.BUILD_URL.split('/')[2].split(':')[0]
}

def updatePortalDependenciesAsRangeVersion() {
  sh '''#!/bin/bash
    updatePortalVersion() {
      python3 - <<EOF
import xml.etree.ElementTree as ET
from pathlib import Path
import sys

def stripNamespace(element):
    # Iterate over all the elements and remove the namespace from the tag
    for elem in element.iter():
        # If the tag contains a namespace (indicated by '}')
        if '}' in elem.tag:
            elem.tag = elem.tag.split('}', 1)[1]  # Keep only the part after '}'
    return element

filePath = "$1"

try:
  tree = ET.parse(filePath)
  root = tree.getroot()
  root = stripNamespace(root) # By default, tags like <project> are changed to tag <ns0:project>, need removing namespace

  dependencies = root.find("dependencies")
  if dependencies is not None:
    for dependency in dependencies.findall("dependency"):
      groupId = dependency.find("groupId")
      if groupId is not None and groupId.text in [
        "com.axonivy.portal",
      ]:
        versionTag = dependency.find("version")
        if versionTag is not None:
          versionTag.text = "[" + versionTag.text + ",)"

  # Write changes back to the file
  tree.write(filePath, encoding="utf-8", xml_declaration=True)
except Exception as e:
  print(f"Failed to process {filePath}: {e}", file=sys.stderr)
EOF
    }

    filePatterns=("AxonIvyPortal/*/pom.xml" "Showcase/*/pom.xml" "AxonIvyPortal/portal-selenium-test/customized_pom.xml"
        "AxonIvyPortal/portal-selenium-test/document_screenshot_pom.xml" "Documentation/public-api/pom.xml")

    for pattern in "${filePatterns[@]}"; do
      for file in $pattern; do
        updatePortalVersion "$file"
      done
    done
  '''
}

return this
