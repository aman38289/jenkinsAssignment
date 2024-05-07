pipeline {
    agent any
 
    tools {
        // Ensures Maven is available for building and testing the project
        maven 'default' // Ensure Maven is named and configured in Jenkins' Global Tool Configuration
    }
 
   
 
    stages {
        stage('Initialize') {
            steps {
                git 'https://github.com/aman38289/jenkinsAssignment.git'
                echo 'Starting build and test process'
            }
        }
        stage('Build') {
            steps {
                // Clean the project and install dependencies without running tests
                bat 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                // Run unit tests and Cucumber tests
                bat 'mvn test'
            }
        }
    }
 
    post {
        always {
            // This will always execute at the end of the pipeline execution
            echo 'Pipeline execution complete!'
        }
        success {
            // Actions to take if the entire pipeline runs successfully
            echo 'Build and tests completed successfully.'
        }
        failure {
            // Actions to take if the pipeline fails at any stage
            echo 'An error occurred during pipeline execution.'
        }
    }
}
