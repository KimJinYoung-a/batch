// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

properties([pipelineTriggers([cron('25,55 7-19 * * *')])])

node {
    stage("NaverChgEpData") {
        try {
            def baseUrl = "http://w2api.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/naverEP/makeDailyNewVerEP_File.asp?epType=chg",
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