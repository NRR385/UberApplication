pipeline {
    agent any

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/dhaunshd11/LibraryManagementSystem.git'
            }
        }

        stage('Compile Code') {
            steps {
                script {
                    bat '''
                    @echo off
                    if not exist bin mkdir bin
                    rem Compile main classes
                    for /R src\\main %%f in (*.java) do javac -d bin -cp "junit-4.13.2.jar;hamcrest-core-1.3.jar" "%%f"
                    rem Compile test classes
                    for /R src\\test %%f in (*.java) do javac -d bin -cp "bin;junit-4.13.1.jar;hamcrest-core-1.1.jar" "%%f"
                    '''
                }
            }
        }

        stage('Check Compiled Classes') {
            steps {
                script {
                    bat 'dir /s /b bin'
                }
            }
        }

        stage('Run JUnit Tests') {
            steps {
                script {
                    bat '''
                    @echo off
                    rem Auto-discover test classes
                    for %%f in (bin\\*Test.class) do (
                        setlocal
                        set TEST_CLASS=%%~nf
                        echo Running: !TEST_CLASS!
                        java -cp "bin;junit-4.13.2.jar;hamcrest-core-1.3.jar" org.junit.runner.JUnitCore !TEST_CLASS!
                        endlocal
                    )
                    '''
                }
            }
        }
    }
}
