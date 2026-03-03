pipeline {
    agent any

    environment {
        DOCKER_HUB_USER = 'your-dockerhub-username' // REPLACE THIS
        IMAGE_NAME = 'scientific-calculator'
        IMAGE_TAG = "${env.BUILD_NUMBER}"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/your-username/your-repo.git'
            }
        }

        stage('Unit Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Build executable') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Build & Tag Docker Image') {
            steps {
                sh "docker build -t ${DOCKER_HUB_USER}/${IMAGE_NAME}:${IMAGE_TAG} ."
                sh "docker tag ${DOCKER_HUB_USER}/${IMAGE_NAME}:${IMAGE_TAG} ${DOCKER_HUB_USER}/${IMAGE_NAME}:latest"
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', passwordVariable: 'DOCKER_PASS', usernameVariable: 'DOCKER_USER')]) {
                    sh "echo \$DOCKER_PASS | docker login -u \$DOCKER_USER --password-stdin"
                    sh "docker push ${DOCKER_HUB_USER}/${IMAGE_NAME}:${IMAGE_TAG}"
                    sh "docker push ${DOCKER_HUB_USER}/${IMAGE_NAME}:latest"
                }
            }
        }
    }

    post {
        always {
            echo 'Build Process Completed.'
        }
        success {
            mail to: 'developer@example.com',
                 subject: "Success: Build #${env.BUILD_NUMBER}",
                 body: "Great! The calculator build was successful and pushed to Docker Hub."
        }
        failure {
            mail to: 'developer@example.com',
                 subject: "Failure: Build #${env.BUILD_NUMBER}",
                 body: "Attention! The build or unit tests failed. Check Jenkins logs."
        }
    }
}
