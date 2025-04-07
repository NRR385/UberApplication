pipeline { 
    agent any    // Defines where the pipeline runs (any available agent) 
    stages { 
        stage('Build') { 
            steps { 
                echo 'Building the application...' 
            } 
        } 
        stage('Test') { 
            steps { 
                echo 'Running tests...' 
            } 
        } 
        stage('Deploy') { 
            steps { 
                echo 'Deploying the application...' 
           } 
        } 
    } 