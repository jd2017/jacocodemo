pipeline {
    agent any // 或者指定一个具体的 agent，比如 label 'maven-agent'

    stages {
        stage('Checkout') {
            steps {
                // 从版本控制系统中检出代码
                checkout scm
            }
        }

        stage('Build and Test with JaCoCo') {
            steps {
                // 使用 Maven 构建项目并运行测试，同时生成 JaCoCo 覆盖率报告
                script {
                    // 假设你的 pom.xml 已经配置了 JaCoCo 插件
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

                    // 如果你想要更详细地查看报告，可以考虑使用 JaCoCo CLI 工具或其他方法来解析和显示报告
                    // 但这通常不是流水线中直接做的事情，而是留给后续的报告查看或质量门槛检查阶段
                }
            }
            post {
                always {
                    // 归档测试报告（如果需要的话）
                    junit '**/target/surefire-reports/TEST-*.xml'
                    // 注意：JaCoCo 覆盖率报告的归档通常是通过 Jenkins 的 JaCoCo 插件自动处理的，而不是通过归档步骤
                    // 如果你想要归档 JaCoCo 的执行文件，可以这样做（但通常不需要，因为 JaCoCo 插件会处理它）：
                    // archiveArtifacts artifacts: '**/target/jacoco.exec', allowEmptyArchive: true
                }
                success {
                    echo 'Build and tests succeeded. JaCoCo coverage report generated.'
                }
                failure {
                    echo 'Build or tests failed.'
                }
            }
        }
    }

    post {
        always {
            // 清理工作区（可选）
            cleanWs()
        }
    }
}