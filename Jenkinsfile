pipeline {
  agent any

  stages {
    stage('Download from SCM') {
      steps {
        git 'https://github.com/2Deimos/demoAppTesi.git'
      }
    }

    stage ('Dependency Check Tool') {
      steps {
        dependencyCheck additionalArguments: '''--project demoAppTesi
        --scan src/
        --format ALL''', odcInstallation: 'dependency-check'
      }
    }

    stage('SonarQube analysis') {
      withSonarQubeEnv('sonarqube-9.7.1') {
        bat 'mvn clean package sonar:sonar'
      } // submitted SonarQube taskId is automatically attached to the pipeline context
    }

    stage("Quality Gate"){
      timeout(time: 1, unit: 'HOURS') { // Just in case something goes wrong, pipeline will be killed after a timeout
        def qg = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv
        if (qg.status != 'OK') {
          error "Pipeline aborted due to quality gate failure: ${qg.status}"
        }
      }
    }
  }
}