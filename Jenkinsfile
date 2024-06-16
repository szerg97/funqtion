pipeline {
    agent any
    tools {
        maven 'Maven3'
    }
    stages {
        stage('Prepare') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'mvn clean'
                sh 'mvn install -DskipTests'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh 'mvn test'
            }
        }
        stage('SonarQube analysis') {
            withSonarQubeEnv(credentialsId: 'f225455e-ea59-40fa-8af7-08176e86507a', installationName: '<sonarqubeInstallation>') { // You can override the credential to be used, If you have configured more than one global server connection, you can specify the corresponding SonarQube installation name configured in Jenkins
                sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.11.0.3922:sonar'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Create namespace...'
                sh 'kubectl create ns test'

                echo 'Installing chart...'
                sh 'helm install funqtion helm/funqtion -f helm/funqtion/values.yaml --set funqtion.operation=sub -n test'

                echo 'Uninstalling chart...'
                sh 'helm uninstall funqtion -n test'

                echo 'Delete namespace...'
                sh 'kubectl delete ns test'
            }
        }
    }
}