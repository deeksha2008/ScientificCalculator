pipeline {
    agent any

    environment {
        // Your specific credentials and image details
        DOCKER_HUB_USER = 'deeksha2008' 
        IMAGE_NAME = 'scientific-calculator'
        IMAGE_TAG = "${env.BUILD_NUMBER}"
    }

    stages {
        stage('Unit Test') {
            steps {
                echo 'Step 1: Running Maven Unit Tests...'
                sh '/opt/homebrew/bin/mvn test'
            }
        }

        stage('Build executable') {
            steps {
                echo 'Step 2: Building Jar file (Maven Package)...'
                sh '/opt/homebrew/bin/mvn clean package'
            }
        }

        stage('Build & Tag Docker Image') {
            steps {
                echo 'Step 3: Creating Docker Image on MacBook...'
                sh "docker build -t ${DOCKER_HUB_USER}/${IMAGE_NAME}:${IMAGE_TAG} ."
                sh "docker tag ${DOCKER_HUB_USER}/${IMAGE_NAME}:${IMAGE_TAG} ${DOCKER_HUB_USER}/${IMAGE_NAME}:latest"
            }
        }

        stage('Push to Docker Hub') {
            steps {
                echo 'Step 4: Pushing to Docker Hub Repository...'
                // IMPORTANT: Ensure 'docker-hub-credentials' ID is created in Jenkins Credentials
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
            echo 'Sending Success Email to Deeksha...'
            mail to: 'deeksha.jain2008@gmail.com', 
                 subject: "SUCCESS: Build #${env.BUILD_NUMBER} - Scientific Calculator",
                 body: "Great! The build was successful. The Docker image is now available at: https://hub.docker.com/r/deeksha2008/scientific-calculator"
        }
        failure {
            echo 'Sending Failure Email to Deeksha...'
            mail to: 'deeksha.jain2008@gmail.com',
                 subject: "FAILURE: Build #${env.BUILD_NUMBER} - Scientific Calculator",
                 body: "Attention! The build or unit tests failed. Check Jenkins logs at: ${env.BUILD_URL}"
        }
    }
}

