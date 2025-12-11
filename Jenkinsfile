pipeline {
    agent any // Define que el Pipeline puede ejecutarse en cualquier agente disponible

    environment {
        // Define la variable global para el modo Headless (CI siempre debe ser true)
        HEADLESS_MODE = 'true'
        // Ruta para los reportes XML de Surefire
        REPORTS_PATH = 'target/surefire-reports/**/*.xml'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/DaniOchoAlj/SauceDemo_Headless.git'
            }
        }

        stage('Parallel Browser Tests') {
            parallel {
                // --- Etapa 1: Chrome Headless ---
                stage('Chrome Tests') {
                    steps {
                        echo "Starting Chrome tests in Headless mode..."
                        bat "mvn clean install -Dbrowser=chrome -Dheadless=${HEADLESS_MODE}"
                    }
                }

                // --- Etapa 2: Edge Headless ---
                stage('Edge Tests') {
                    steps {
                        echo "Starting Edge tests in Headless mode..."
                        bat "mvn clean install -Dbrowser=edge -Dheadless=${HEADLESS_MODE}"
                    }
                }
            }
        }
    }

    // --- Post-Acciones para Reportes ---
    post {
        always {
            // Publica los resultados de todos los tests ejecutados
            junit REPORTS_PATH
            // Esto se ejecuta al final, sin importar el resultado del test (SUCCESS o FAILED)
        }
    }
}