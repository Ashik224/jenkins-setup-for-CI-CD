pipeline {
    agent any

     environment {
        IMAGE_NAME = "cypress-tests"
        CONTAINER_NAME = "cypress-runner"
        CYPRESS_REPO = "https://github.com/Ashik224/cypress-docker-setup.git"
        CYPRESS_DIR = "cypress-docker-setup"
    }
    stages {
        stage('Checkout Jenkins Repo') {
            steps {
                checkout scm
            }
        }

        stage('Clone Cypress Repo') {
            steps {
                    echo "WORKSPACE = ${env.WORKSPACE}"
                    bat """
                        if exist "%CYPRESS_DIR%" rmdir /S /Q "%CYPRESS_DIR%"
                        git clone %CYPRESS_REPO% %CYPRESS_DIR%
                    """
            }
        }
        stage('Build Docker Image') {
            steps {
                dir('cypress-docker-setup') {
                     bat 'docker build -t my-cypress-image .'
                }
            }
        }        
    }
}