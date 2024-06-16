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
                echo 'Building...'
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
        stage('Coverage') {
            steps {
                echo 'Create test report...'
                sh 'mvn jacoco:report'
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
    post {
        always {
            archiveArtifacts artifacts: 'target/site/jacoco/*', allowEmptyArchive: true
        }
    }
}