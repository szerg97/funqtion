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
        stage('Deploy') {
            steps {
                echo 'Create namespace...'
                sh 'kubectl create ns test'
            }
            steps {
                echo 'Installing chart...'
                sh 'helm install funqtion helm/funqtion -f helm/funqtion/values.yaml --set funqtion.operation=sub -n test'
            }
            steps {
                echo 'Uninstalling chart...'
                sh 'helm uninstall funqtion -n test'
            }
        }
    }
}