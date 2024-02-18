@Library('tenbyten-library') _
// 1분에 한번 실행
properties([pipelineTriggers([cron('H/5 * * * *')])])

node {
    stage("Jandi Report") {
        
        //알림 https://wh.jandi.com/connect-api/webhook/15400820/6e1510e5775343308c2b0c98e61b6362

        def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/6e1510e5775343308c2b0c98e61b6362"
        def message = "call test1 2019!! 한글은 안되네여~"
        def jandiReporter = new JandiReporter()
        jandiReporter.reportSimple(jandiUrl, message)
    }
}
