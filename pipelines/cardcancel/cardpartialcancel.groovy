// 전역 라이브러리 로드
@Library('tenbyten-library') _

import groovy.json.JsonSlurper

// 매 10분마다 실행
properties([pipelineTriggers([cron('5,15,25,35,45,55 * * * *')])])

pipeline {
    environment {
		baseUrl = "http://scm.10x10.co.kr"
        doError = '0'	// 문자열로 해야만 작동한다.
    }

	agent any

	stages {
        stage('Card Partial Cancel') {
			steps {
				script {
					// https://jenkins.io/doc/pipeline/steps/http_request/
					for (i = 0; i < 5; i++) {
						def response = httpRequest baseUrl + "/admin/apps/dailyAutoJob.asp?act=cardPartialCancel&param1=1"
						def json = new JsonSlurper().parseText(response.content)
						if (json.status != "0000") {
							doError = '1'
							println("에러: ${json.content}")
						}
					}

					if (doError == '0') {
						echo 'OK'
					}
				}
			}
        }

        stage('Report') {
			steps {
				script {
					if (doError == '1') {
						sh 'echo "에러!!"; exit 1'
					}
				}
			}
        }
    }

    post {
		changed {
			emailext body: "현재상태 : ${currentBuild.currentResult}\nJOB ${env.JOB_NAME} 빌드 ${env.BUILD_NUMBER}\n\n\n확인하기: ${env.BUILD_URL}",
                to: 'skyer9@gmail.com',
                from: 'admin@10x10.co.kr',
                subject: "젠킨스 빌드 ${currentBuild.currentResult}: ${env.JOB_NAME}"
        }

		/*
        always {
            echo '항상 실행'
        }

        success {
            echo '성공한 경우 실행'
        }

        changed {
            echo '상태변경시 한번만'
            echo '계속 성공하다가 실패한 경우 등.'
        }

		failure {
			emailext body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}",
                to: 'skyer9@gmail.com',
                from: 'admin@10x10.co.kr',
                subject: "젠킨스 빌드 ${currentBuild.currentResult}: Job ${env.JOB_NAME}"
        }
		 */
    }
}
