// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

// 10분에 한번 실행
properties([pipelineTriggers([cron('30 8 * * *')])])

node {
    stage("NaverNewVerSellEpData") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/naverEP/makeDailyNewVerSellEP.asp",
                                    validResponseCodes: "200:499"
            println("Status: "+ response.status)
            println("Content: "+ response.content)

            if (response.status > 400) {
                throw new Exception("api error")
            }

        }catch(e) {
            throw e
        }
    }

    stage("NaverOldVerSellEpData") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/naverEP/makeDailySellEP.asp",
                                    validResponseCodes: "200:499"
            println("Status: "+ response.status)
            println("Content: "+ response.content)

            if (response.status > 400) {
                throw new Exception("api error")
            }

        }catch(e) {
            throw e
        }
    }
}