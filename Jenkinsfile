pipeline {
  agent any

  stages {

    stage("Build") {
      steps {
          bat 'mvn clean package'
        }
    }

    stage ('Dependency Check Tool') {
      steps {
        dependencyCheck additionalArguments: '''
        --project 'demoAppTesi'
        --scan target/*.jar
        --out target/
        --format ALL
        ''', odcInstallation: 'dependency-check'

        dependencyCheckPublisher pattern: 'target/dependency-check-report.xml'
      }
    }

    stage("Build & SonarQube analysis") {
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