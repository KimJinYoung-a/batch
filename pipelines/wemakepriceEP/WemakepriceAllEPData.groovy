// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

// 매일 오전 1시 1분
properties([pipelineTriggers([cron('1 1 * * *')])])

node {
    stage("WemakepriceAllEPData") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/wemakepriceEP/makeDailyEP.asp",
                                    timeout:6000,
                                    validResponseCodes: "200:599"
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