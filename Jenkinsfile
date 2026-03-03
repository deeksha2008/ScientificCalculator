pipeline {
    agent any

    environment {
        DOCKER_HUB_USER = 'deeksha2008' 
        IMAGE_NAME = 'scientific-calculator'
        IMAGE_TAG = "${env.BUILD_NUMBER}"
        MAVEN_PATH = '/opt/homebrew/bin/mvn'
        DOCKER_PATH = '/usr/local/bin/docker'
        // This helps bypass Mac credential helper issues
        DOCKER_CONFIG = "${WORKSPACE}/.docker"
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
                // Create a temporary docker config directory to avoid Mac Keychain errors
                sh "mkdir -p ${DOCKER_CONFIG}"
                sh "${DOCKER_PATH} build -t ${DOCKER_HUB_USER}/${IMAGE_NAME}:${IMAGE_TAG} ."
                sh "${DOCKER_PATH} tag ${DOCKER_HUB_USER}/${IMAGE_NAME}:${IMAGE_TAG} ${DOCKER_HUB_USER}/${IMAGE_NAME}:latest"
            }
        }

        stage('Push to Docker Hub') {
            steps {
                echo 'Step 4: Pushing to Docker Hub...'
                withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', passwordVariable: 'DOCKER_HUB_PASSWORD', usernameVariable: 'DOCKER_HUB_USER_VAR')]) {
                    sh "echo ${DOCKER_HUB_PASSWORD} | ${DOCKER_PATH} login -u ${DOCKER_HUB_USER_VAR} --password-stdin"
                    sh "${DOCKER_PATH} push ${DOCKER_HUB_USER}/${IMAGE_NAME}:${IMAGE_TAG}"
                    sh "${DOCKER_PATH} push ${DOCKER_HUB_USER}/${IMAGE_NAME}:latest"
                }
            }
        }

        stage('Deploy with Ansible') {
            steps {
                echo 'Step 5: Deploying to Localhost via Ansible...'
                // This runs the playbook we created earlier
                sh "ansible-playbook deploy.yml"
            }
        }
    }

    post {
        always {
            echo 'Build Process Completed.'
        }
        success {
            echo 'Pipeline Success! Image is on Docker Hub and Deployed.'
        }
        failure {
            echo 'Pipeline Failed. Check logs for errors.'
        }
    }
}

