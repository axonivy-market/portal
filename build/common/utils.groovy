
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
  echo "====================Turn off Elastic Search Windows services===================="
  def elasticSearchServices = ['ivy-elasticsearch-master-cluster']
  for (elasticSearchService in elasticSearchServices) {
    stopWindowsService(elasticSearchService)
  }
}

def stopAllEngines() {
  echo "====================Stop engines===================="
  def engineServices = ['Axon.ivy Engine - LTS', 'Axon.ivyEngine8.0', 'Axon.ivyEngineTrunk', 'Axon.ivy-master-cluster-1', 'Axon.ivy-master-cluster-2', 'Axon.ivyEngineDashboard']
  for (engineService in engineServices) {
    stopWindowsService(engineService)
  }
  stopAllElasticSearchs()
}

def startWindowsService(String serviceName) {
  stopWindowsService(serviceName)
  bat """
    net start "${serviceName}"
  """
}

def stopWindowsService(String serviceName) {
  bat """
    sc query "${serviceName}" | find /i "RUNNING"
    if not "%ERRORLEVEL%"=="1" ( net stop "${serviceName}")
  """
}

def remoteDesktop() {
  closeAllRemoteDesktopConnections()
  echo '====================Setup remote desktop connections to Portal slave===================='
  configFileProvider(
      [configFile(fileId: 'properties-config', variable: 'PROPERTIES_CONFIG')]) {
      props = readProperties  file: "${env.PROPERTIES_CONFIG}"
      //e.g. remoteDesktopCmd=D:/tools/remote-desktop-plus/rdp.exe /v:10.123.1.193 /domain:wawa.vn /u:wawa /p:W4w4@CH$ /w:2560 /h:1440 /noclose
      bat "${props.remoteDesktopCmd}"
  }
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
    mvn clean compile -f AxonIvyPortal/PortalTest/pom.xml -Divy.engine.directory=${engineDir} ${engineDownloadURL}
  """
}
return this