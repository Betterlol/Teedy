pipeline {
    agent any

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    environment {
        MAVEN_OPTS = '-Dmaven.repo.local=.m2/repository'
    }

    stages {
        stage('Start') {
            steps {
                echo 'Pipeline started'
            }
        }

        stage('Checkout SCM') {
            steps {
                checkout scm
            }
        }

        stage('Clean') {
            steps {
                sh 'mvn -B clean'
            }
        }

        stage('Compile') {
            steps {
                sh 'mvn -B -DskipTests compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn -B test'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('PMD') {
            steps {
                sh 'mvn -B pmd:check'
            }
        }

        stage('JaCoCo') {
            steps {
                sh 'mvn -B jacoco:report'
            }
        }

        stage('Javadoc') {
            steps {
                sh 'mvn -B javadoc:aggregate || true'
            }
        }

        stage('Site') {
            steps {
                sh 'mvn -B site'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn -B -DskipTests package'
            }
        }

        stage('Pre Archive') {
            steps {
                sh 'echo "Preparing artifacts"'
            }
        }

        stage('End') {
            steps {
                echo 'Pipeline finished'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/*.jar, **/target/*.war, target/site/**, docs-*/target/site/**', allowEmptyArchive: true, fingerprint: true
        }
    }
}
