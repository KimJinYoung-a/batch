// 전역 라이브러리 로드
@Library('tenbyten-library') _

import groovy.json.JsonSlurper

// 매 10분마다 실행
properties([pipelineTriggers([cron('H/2 * * * *')])])

pipeline {
    environment {
		baseUrl = "http://wapi.10x10.co.kr"
        doError = '0'	// 문자열로 해야만 작동한다.
    }

	agent any

	stages {
        stage('Get Stock Info') {
			steps {
				script {
					// https://jenkins.io/doc/pipeline/steps/http_request/
					def response = httpRequest baseUrl + "/agv/api.asp?mode=updagvstockchange"
					def json = new JsonSlurper().parseText(response.content)
					if (json.resultCode != "200") {
						doError = '1'
						println("에러: ${json.resultMessage}")
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
    }
}
