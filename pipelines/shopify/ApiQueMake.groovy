@Library('tenbyten-library') _

// 여기서 트리거를 생성할수 있음.
// 5분에 한번 실행
properties([pipelineTriggers([cron('H/5 * * * *')])])

class GVar {
    static def exceptionMessage = ""
}

def request(apiPath) {
    def baseUrl = "http://local.10x10.co.kr:5000"
    def response = httpRequest url: baseUrl + apiPath,
                               validResponseCodes: "200:499"
    println("Status: "+ response.status)
    println("Content: "+ response.content)

    if (response.status > 400) {
        GVar.exceptionMessage = apiPath + " api error"
    }
    println("request >>>" + GVar.exceptionMessage)
    return GVar.exceptionMessage
}

node {
    // ApiQue Make 2개를 병렬로 처리함.
    stage("Run ApiQue Make") {
        parallel Par_EditMake: {
            GVar.exceptionMessage += "1"
            println("EditMake")
            request "/api/outmall/ShopifyApiQue/EditMake"
            println("Par1 >>>" + GVar.exceptionMessage)
        },
        Par_RegMake: {
            GVar.exceptionMessage += "2"
            println("RegMake")
            request "/api/outmall/ShopifyApiQue/RegMake"
            println("Par2 >>>" + GVar.exceptionMessage)
        }
    }

    stage("Repoter") {
        // 에러가 없으면 그냥 종료함.
        if (GVar.exceptionMessage != "") {
            def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/e22a7280497b36b0472934b78bdd1b6e"
            def jandiReporter = new JandiReporter()
            jandiReporter.reportSimple(jandiUrl, GVar.exceptionMessage)

            // project build를 실패로 처리하기 위한 throw exception
            throw new Exception(GVar.exceptionMessage)
        }

        println("reporter >>>" + GVar.exceptionMessage)
        println("Success")
    }
}
