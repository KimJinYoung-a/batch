// 전역 라이브러리 로드
@Library('tenbyten-library') _

properties([pipelineTriggers([cron('H/2 * * * *')])])

// trigger 사용 예제
def trigger = new Trigger()
// 2019-11-27 10:15분 부터 시작
trigger.startTrigger(new Date(), "2019-11-27 10:15")
// 2019-11-27 10:30분에 종료
trigger.endTrigger(new Date(), "2019-11-27 10:30", "batch_sample1")

node {
    def baseUrl = "http://local.10x10.co.kr:5000"
    stage("Edit Action") {
        // https://jenkins.io/doc/pipeline/steps/http_request/
        def response = httpRequest baseUrl + "/api/outmall/ShopifyApiQue/EditAction"
        println("Status: "+ response.status)
        println("Content: "+ response.content)
    }
}