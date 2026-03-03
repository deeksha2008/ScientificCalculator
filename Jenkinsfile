pipeline {
    agent any

    environment {
        DOCKER_HUB_USER = 'deeksha2008' 
        IMAGE_NAME = 'scientific-calculator'
        IMAGE_TAG = "${env.BUILD_NUMBER}"
        // Absolute paths for MacBook Air compatibility
        MAVEN_PATH = '/opt/homebrew/bin/mvn'
        DOCKER_PATH = '/usr/local/bin/docker' 
    }

    stages {
        stage('Unit Test') {
            steps {
                echo 'Step 1: Running Maven Unit Tests...'
                sh "${MAVEN_PATH} test"
            }
        }

        stage('Build executable') {
            steps {
                echo 'Step 2: Building Jar file...'
                sh "${MAVEN_PATH} clean package"
            }
        }

        stage('Build & Tag Docker Image') {
            steps {
                echo 'Step 3: Creating Docker Image...'
                sh "${DOCKER_PATH} build -t ${DOCKER_HUB_USER}/${IMAGE_NAME}:${IMAGE_TAG} ."
                sh "${DOCKER_PATH} tag ${DOCKER_HUB_USER}/${IMAGE_NAME}:${IMAGE_TAG} ${DOCKER_HUB_USER}/${IMAGE_NAME}:latest"
            }
        }

        stage('Push to Docker Hub') {
            steps {
                echo 'Step 4: Pushing to Docker Hub...'
                withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', passwordVariable: 'DOCKER_PASS', usernameVariable: 'DOCKER_USER')]) {
                    sh "echo \$DOCKER_PASS | ${DOCKER_PATH} login -u \$DOCKER_USER --password-stdin"
                    sh "${DOCKER_PATH} push ${DOCKER_HUB_USER}/${IMAGE_NAME}:${IMAGE_TAG}"
                    sh "${DOCKER_PATH} push ${DOCKER_HUB_USER}/${IMAGE_NAME}:latest"
                }
            }
        }
    }

    post {
        always {
            echo 'Build Process Completed.'
        }
        success {
            echo 'Sending Success Email...'
            mail to: 'deeksha.jain2008@gmail.com', 
                 subject: "SUCCESS: Build #${env.BUILD_NUMBER}",
                 body: "Project build successful! Image: deeksha2008/scientific-calculator"
        }
        failure {
            echo 'Sending Failure Email...'
            mail to: 'deeksha.jain2008@gmail.com',
                 subject: "FAILURE: Build #${env.BUILD_NUMBER}",
                 body: "Build failed. Check Jenkins logs."
        }
    }
}
