// Shopify에 등록할 상품이 있으면 ApiQue를 생성함.
@Library('tenbyten-library') _

// 5분에 한번 실행
properties([pipelineTriggers([cron('H/5 * * * *')])])

node {
    stage("Reg Make") {
        try {
            def baseUrl = "http://local.10x10.co.kr:5000"
            def response = httpRequest url: baseUrl + "/api/outmall/ShopifyApiQue/RegMake",
                                    validResponseCodes: "200:499"
            println("Status: "+ response.status)
            println("Content: "+ response.content)

            if (response.status > 400) {
                throw new Exception("api error")
            }
        } catch(e) {
            def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/e22a7280497b36b0472934b78bdd1b6e"
            def message = "Failure /api/outmall/ShopifyApiQue/RegMake"
            def jandiReporter = new JandiReporter()
            jandiReporter.reportSimple(jandiUrl, message)

            throw e
        }
    }
}