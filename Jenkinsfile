pipeline {
  agent any

  stages {

    stage("Build") {
      steps {
          bat 'mvn clean package'
        }
    }

    stage ('Analisi delle dipendenze (OWASP DEPENDENCY CHECK)') {
      steps {
        dependencyCheck additionalArguments: '''
        --project 'demoAppTesi'
        --scan target/*.jar
        --out target/dependency-check-report.xml
        --format XML
        --prettyPrint
        ''', odcInstallation: 'dependency-check'
        
        dependencyCheckPublisher failedTotalCritical: 1, 
        pattern: 'target/dependency-check-report.xml', 
        stopBuild: true
      }
    }

    stage("Analisi SonarQube") {
      steps {
        withSonarQubeEnv('sonarqube-9.7.1') {
          bat 'mvn sonar:sonar'
          }
        }
    }

    stage("Quality Gate") {
      steps {
        timeout(time: 1, unit: 'HOURS') {
          waitForQualityGate abortPipeline: true
        }
      }
    }
  }
}