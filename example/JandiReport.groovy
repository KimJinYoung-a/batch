// 텐바이텐 글로벌 라이브러리 로드
@Library('tenbyten-library') _

// 5분에 한번 실행
properties([pipelineTriggers([cron('H/5 * * * *')])])

node {
    stage("Jandi Report") {
        def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/e22a7280497b36b0472934b78bdd1b6e"
        def message = "Jandi Report"
        def jandiReporter = new JandiReporter()
        jandiReporter.reportSimple(jandiUrl, message)
    }
}