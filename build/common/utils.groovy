
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

def generateBOM() {
  generateBOMFile('AxonIvyPortal/portal-components')
  generateBOMFile('AxonIvyPortal/portal')
  generateBOMFile('Showcase/portal-user-examples')
  generateBOMFile('Showcase/portal-developer-examples')
  generateBOMFile('Showcase/portal-components-examples')
}

def generateBOMFile(def moduleDir) {
  def currentDir = pwd()
  def iarFile = sh (script: "ls ${currentDir}/${moduleDir}/target/*.iar", returnStdout: true).trim()
  if (iarFile) {
    def zipFile = iarFile.replace('.iar', '.zip')
    sh "mv ${iarFile} ${zipFile}"
    echo "Renamed ${iarFile} to ${zipFile}"
    def inputFile = sh (script: "ls ${zipFile} | xargs -n 1 basename", returnStdout: true).trim()
    def outputFile = inputFile.replace('.zip', '.bom.json')
    sh "docker run -v ${zipFile}:/portal.zip anchore/syft scan /portal.zip -o cyclonedx-json --exclude './**/pom.xml' > ${currentDir}/${moduleDir}/target/$outputFile"
  } else {
    echo "File not found: ${iarFile}"
  }
}

def mergeBOMFiles() {
  def currentDir = pwd()
  def targetDir = "${currentDir}/build/sbom/target"
  sh """
      if [ -d ${targetDir} ]; then
        echo "Directory exists, emptying it..."
        rm -rf ${targetDir}/*   # Remove all contents of the directory
      else
        echo "Directory does not exist, creating it..."
        mkdir -p ${targetDir}   # Create the directory
      fi
     """
  def bomFiles = sh (script: "find ${currentDir} -type d -name 'target' -exec find {} -type f -name '*.bom.json' \\;", returnStdout: true).trim()
  if(bomFiles) {
    def bomFileList = bomFiles.split("\n")
    bomFileList.each { file ->
                          sh "cp ${file} ${targetDir}/"
                          echo "Copied file: ${file} to ${targetDir}/"
                      }
  }
  def bomFileNames = sh(script: "find ${targetDir} -type f -name '*.bom.json' -exec basename {} \\;", returnStdout: true).trim().replace("\n", " ")
  bomFileNames = bomFileNames.split(" ").collect { "/sbom/${it}" }.join(" ")
  sh "docker run -v ${targetDir}:/sbom cyclonedx/cyclonedx-cli merge --input-files ${bomFileNames} --output-file /sbom/portal.bom.json"
}

def uploadBOM(def projectName, def projectVersion, def bomFile) {
  withCredentials([string(credentialsId: 'dependency-track', variable: 'API_KEY')]) {
    sh 'curl -v --fail -X POST http://portal01.server.ivy-cloud.com:8081/api/v1/bom \
        -H "Content-Type: multipart/form-data" \
        -H "X-API-Key: ' + API_KEY + '" \
        -F "autoCreate=true" \
        -F "projectName=' + projectName + '" \
        -F "projectVersion=' + projectVersion + '" \
        -F "bom=@' + bomFile + '"'
  }
}

return this
