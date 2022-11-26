pipeline {
  agent any

  stages {

    stage ('Dependency Check Tool') {
      steps {
        dependencyCheck additionalArguments: '''--project 'demoAppTesi'
        --scan target/*.jar
        --format ALL''', odcInstallation: 'dependency-check'
      }
    }

    stage("Build & SonarQube analysis") {
      steps {
        withSonarQubeEnv('sonarqube-9.7.1') {
          bat 'mvn clean package sonar:sonar'
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