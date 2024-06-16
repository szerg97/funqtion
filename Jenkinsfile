pipeline {
    agent any
    tools {
        maven 'Maven3'
    }
    environment {
        SONARQUBE_SERVER_URL = 'http://<SONARQUBE_SERVER_IP>:9000'
        SONARQUBE_SCANNER_IMAGE = 'sonarsource/sonar-scanner-cli'
        SONARQUBE_PROJECT_KEY = 'your_project_key'
        SONARQUBE_PROJECT_NAME = 'Your Project Name'
        SONARQUBE_PROJECT_VERSION = '1.0'
        SONARQUBE_LOGIN = '<SONARQUBE_TOKEN>'
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
            steps {
                script {
                    // Use Docker to run SonarQube scanner
                    docker.image(env.SONARQUBE_SCANNER_IMAGE).inside {
                        withSonarQubeEnv('SonarQube') {
                            sh """
                            sonar-scanner \
                                -Dsonar.projectKey=${env.SONARQUBE_PROJECT_KEY} \
                                -Dsonar.projectName=${env.SONARQUBE_PROJECT_NAME} \
                                -Dsonar.projectVersion=${env.SONARQUBE_PROJECT_VERSION} \
                                -Dsonar.sources=. \
                                -Dsonar.host.url=${env.SONARQUBE_SERVER_URL} \
                                -Dsonar.login=${env.SONARQUBE_LOGIN}
                            """
                        }
                    }
                }
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