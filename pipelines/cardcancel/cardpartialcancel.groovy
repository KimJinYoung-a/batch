// ���� ���̺귯�� �ε�
@Library('tenbyten-library') _

import groovy.json.JsonSlurper

// �� 10�и��� ����
properties([pipelineTriggers([cron('5,15,25,35,45,55 * * * *')])])

pipeline {
    environment {
		baseUrl = "http://scm.10x10.co.kr"
        doError = '0'	// ���ڿ��� �ؾ߸� �۵��Ѵ�.
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
							println("����: ${json.content}")
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
						sh 'echo "����!!"; exit 1'
					}
				}
			}
        }
    }

    post {
		changed {
			emailext body: "������� : ${currentBuild.currentResult}\nJOB ${env.JOB_NAME} ���� ${env.BUILD_NUMBER}\n\n\nȮ���ϱ�: ${env.BUILD_URL}",
                to: 'skyer9@gmail.com',
                from: 'admin@10x10.co.kr',
                subject: "��Ų�� ���� ${currentBuild.currentResult}: ${env.JOB_NAME}"
        }

		/*
        always {
            echo '�׻� ����'
        }

        success {
            echo '������ ��� ����'
        }

        changed {
            echo '���º���� �ѹ���'
            echo '��� �����ϴٰ� ������ ��� ��.'
        }

		failure {
			emailext body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}",
                to: 'skyer9@gmail.com',
                from: 'admin@10x10.co.kr',
                subject: "��Ų�� ���� ${currentBuild.currentResult}: Job ${env.JOB_NAME}"
        }
		 */
    }
}
