pipeline {
  agent any
  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build and Test with JaCoCo') {
      post {
        always {
          junit '**/target/surefire-reports/TEST-*.xml'
        }

        success {
          echo 'Build and tests succeeded. JaCoCo coverage report generated.'
        }

        failure {
          echo 'Build or tests failed.'
        }

      }
      steps {
        script {
          def mvnHome = tool 'MAVEN_HOME' // Maven 安装的名称，确保在 Jenkins 中已配置
          sh "${mvnHome}/bin/mvn clean verify"

          // 检查 JaCoCo 报告文件是否存在
          def jacocoExecFile = sh(script: 'find . -name jacoco.exec', returnStdout: true).trim()
          if (jacocoExecFile.isEmpty()) {
            error 'JaCoCo execution file not found!'
          } else {
            echo "JaCoCo execution file found: ${jacocoExecFile}"
          }

          // 输出 JaCoCo 覆盖率报告（这一步通常是在 Jenkins 的后续处理中完成的，比如通过 JaCoCo 插件）
          // 但为了演示，我们可以简单地打印出报告文件的位置，并在 Jenkins 控制台中查看
          // 注意：这里不会直接显示覆盖率百分比，而是报告文件的位置
          def targetDir = sh(script: 'echo $(dirname ${jacocoExecFile})/../target', returnStdout: true).trim()
          echo "JaCoCo report files are located in: ${targetDir}/site/jacoco"
        }

      }
    }

  }
  post {
    always {
      cleanWs()
    }

  }
}