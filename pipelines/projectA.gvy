pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                echo 'Hello World'
                println "This is a test stage"
            }
        }        
    }
}