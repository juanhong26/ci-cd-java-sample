pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-creds') // Jenkins credential ID
        DOCKERHUB_REPO = "juanhong/ci-cd-java-sample"
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/juanhong26/ci-cd-java-sample.git', credentialsId: 'github-creds'
            }
        }

        stage('Build with Gradle') {
            steps {
                bat '.\\gradlew clean build'
            }
        }

        stage('Run Tests') {
            steps {
                bat '.\\gradlew test'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat "docker build -t %DOCKERHUB_REPO% ."
            }
        }

        stage('Push to Docker Hub') {
            steps {
                bat "echo %DOCKERHUB_CREDENTIALS_PSW% | docker login -u %DOCKERHUB_CREDENTIALS_USR% --password-stdin"
                bat "docker push %DOCKERHUB_REPO%"
            }
        }
    }

    post {
        success {
            echo '✅ Build, Test, and Deployment successful!'
        }
        failure {
            echo '❌ Build failed. Check logs.'
        }
    }
}
