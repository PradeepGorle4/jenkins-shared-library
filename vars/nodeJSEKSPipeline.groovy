def call(Map configMap){
    pipeline {
        agent { label 'AGENT-1'}
        environment {
            greeting = configMap.get('greeting')
    // When we call this default call function, we assign configMap variable  a value, so assign that value to the greeting.
        }
        options {
            disableConcurrentBuilds ()
            timeout(time: 30, unit: 'MINUTES')
        }
        parameters {
            booleanParam(name: 'deploy', defaultValue: false, description: 'Toggle this value')
        }

        stages {
            stage('Print Greeting') {
                steps {
                    script{
                        echo "App Version is : $greeting"
                    }
                }
            }
            
        }
            
        post {
            always {
                echo "I will always say hello-again"
                deleteDir()
            }
            failure {
                echo "I will run when pipeline fails"
            }
            success {
                echo "I will run when pipeline succeeds"
            }
        }
    }
}