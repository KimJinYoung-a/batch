// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

// 06:13에 트리거된 후 20:00 기간동안 1시간 마다 반복
properties([pipelineTriggers([cron('56 7-19/1 * * *')])])

node {
    stage("NaverBookChgEpData") {
        try {
            def baseUrl = "http://w2api.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/naverEP/makeDailyBookEP.asp?epType=chg",
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