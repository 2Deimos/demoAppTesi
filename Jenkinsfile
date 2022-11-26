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

    stage("Build & SonarQube analysis") {
      steps {
        withSonarQubeEnv('sonarqube-9.7.1') {
          sh 'mvn clean package sonar:sonar'
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