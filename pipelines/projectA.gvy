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

        stage('Run Docker Image') {
            steps {
                dir('cypress-docker-setup') {
                    // bat 'docker run --rm my-cypress-image'
                    bat ''' 
                        if not exist "%CD%\\cypress-reports" mkdir "%CD%\\cypress-reports"
                        if not exist "%CD%\\cypress-dashboard" mkdir "%CD%\\cypress-dashboard"
                        docker run --rm -v "%CD%\\cypress-dashboard:/docker-container-setup/cypress/dashboard" -v "%CD%\\cypress-reports:/docker-container-setup/cypress/reports" my-cypress-image
                    '''
                }
            }
        }        
    }
    // post {
    //     always {
    //         publishHTML(
    //             target: [
    //                 allowMissing: false,
    //                 alwaysLinkToLastBuild: false,
    //                 keepAll: true,
    //                 reportDir: 'cypress-docker-setup/cypress-dashboard/',
    //                 reportFiles: 'dummy.html',
    //                 reportName: 'Cypress-Test-Report'
    //             ]               
    //         )
    //     }
    // }
}