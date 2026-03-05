
pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "deeksha2008/scientific-calculator"
        DOCKER_HUB_CREDENTIALS_ID = 'docker-hub-credentials'
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
                echo 'Step 2: Building Jar file...'
                sh '/opt/homebrew/bin/mvn clean package'
            }
        }

        stage('Build & Tag Docker Image') {
            steps {
                echo 'Step 3: Creating Docker Image...'
                sh "mkdir -p ${WORKSPACE}/.docker"
                sh "/usr/local/bin/docker build -t ${DOCKER_IMAGE}:${env.BUILD_NUMBER} ."
                sh "/usr/local/bin/docker tag ${DOCKER_IMAGE}:${env.BUILD_NUMBER} ${DOCKER_IMAGE}:latest"
            }
        }

        stage('Push to Docker Hub') {
            steps {
                echo 'Step 4: Pushing to Docker Hub...'
                withCredentials([usernamePassword(credentialsId: "${DOCKER_HUB_CREDENTIALS_ID}", passwordVariable: 'DOCKER_HUB_PASSWORD', usernameVariable: 'DOCKER_HUB_USER')]) {
                    sh "echo \$DOCKER_HUB_PASSWORD | /usr/local/bin/docker login -u \$DOCKER_HUB_USER --password-stdin"
                    sh "/usr/local/bin/docker push ${DOCKER_IMAGE}:${env.BUILD_NUMBER}"
                    sh "/usr/local/bin/docker push ${DOCKER_IMAGE}:latest"
                }
            }
        }

        stage('Deploy with Ansible') {
            steps {
                echo 'Step 5: Deploying to Localhost via Ansible...'
                sh '/opt/homebrew/bin/ansible-playbook -i inventory deploy.yml'
            }
        }
    }

    post {
        success {
            mail to: 'deeksha.jain2008@gmail.com',
                 subject: "✅ Application Deployment SUCCESS: Build ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "Hi Deeksha,\n\nYour Scientific Calculator Application has been built, containerized, and deployed successfully!\n\nBuild Details: ${env.BUILD_URL}"
        }
        failure {
            mail to: 'deeksha.jain2008@gmail.com',
                 subject: "❌ Application Deployment FAILURE: Build ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "Hi Deeksha,\n\nThe deployment failed. Please check the Jenkins console logs to troubleshoot the issue.\n\nLogs: ${env.BUILD_URL}console"
        }
        always {
            echo 'Build Process Completed. Cleaning workspace...'
            cleanWs()
        }
    }
}
