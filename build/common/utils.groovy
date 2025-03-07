
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

def generateBOM(def uploadDtrack) {
  def downloadBom = {
    maven cmd: '-f build/sbom/pom.xml clean package'
  }
  runMaven(downloadBom)

  def version = evaluateMavenProperty('ivy-version') // e.g. 13.1.0
  generateBOMFile(version, 'ch.ivyteam.ivy.designer.product/target/products/AxonIvyDesigner*Linux*.zip', 'designer-linux', uploadDtrack)
  generateBOMFile(version, 'ch.ivyteam.ivy.designer.product/target/products/AxonIvyDesigner*Windows*.zip', 'designer-windows', uploadDtrack)
  generateBOMFile(version, 'ch.ivyteam.ivy.designer.product/target/products/AxonIvyDesigner*macOS*.zip', 'designer-macos', uploadDtrack)
  generateBOMFile(version, 'ch.ivyteam.ivy.server.product/target/products/AxonIvyEngine*_Windows_*.zip', 'engine-windows', uploadDtrack)
  generateBOMFile(version, 'ch.ivyteam.ivy.server.product/target/products/AxonIvyEngine*_Slim_All*.zip', 'engine-slim', uploadDtrack)

  def fileName = sh (script: "ls workspace/ch.ivyteam.ivy.server.product/target/products/AxonIvyEngine*_Slim_All*.zip | xargs -n 1 basename", returnStdout: true)
  fileName = fileName.trim()
  sh "mv workspace/ch.ivyteam.ivy.server.product/target/products/$fileName /tmp/$fileName"

  generateBOMFile(version, 'ch.ivyteam.ivy.server.product/target/products/AxonIvyEngine*_All*.zip', 'engine-all', uploadDtrack)
  sh "mv /tmp/$fileName workspace/ch.ivyteam.ivy.server.product/target/products/$fileName"
}

def generateBOMFile(def version, def zip, def project, def uploadDtrack) {
  def currentDir = pwd()
  def file = sh (script: "ls workspace/$zip", returnStdout: true)
  file = file.trim()
  def fileName = sh (script: "ls workspace/$zip | xargs -n 1 basename", returnStdout: true)
  fileName = fileName.trim() + ".bom.json"
  sh "docker run -v $currentDir/$file:/product.zip anchore/syft scan /product.zip -o cyclonedx-json --exclude './**/pom.xml' > build/sbom/target/product.json"
  sh "docker run -v $currentDir/build/sbom:/sbom cyclonedx/cyclonedx-cli merge --input-files /sbom/target/product.json /sbom/target/sbom/neo-designer-bom.json /sbom/target/sbom/monaco-yaml-ivy-bom.json /sbom/target/sbom/swagger-ui-ivy-bom.json --output-file /sbom/$fileName"
  
  if(project.contains("windows")){
    sh "docker run -v $currentDir/build/sbom:/sbom cyclonedx/cyclonedx-cli merge --input-files /sbom/target/product.json /sbom/target/sbom/temurin-windows-bom.json --output-file /sbom/$fileName";
  }
  if(project.contains("macos")){
    sh "docker run -v $currentDir/build/sbom:/sbom cyclonedx/cyclonedx-cli merge --input-files /sbom/target/product.json /sbom/target/sbom/temurin-mac-bom.json --output-file /sbom/$fileName";
  }
  
  if (uploadDtrack) {
    uploadBOM(projectName: project, projectVersion: version, bomFile: "build/sbom/$fileName")
  }
}

return this
