// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

// 매일 12시53분
properties([pipelineTriggers([cron('53 0 * * *')])])

node {
    stage("DaumAllEpData") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/daumEP/makeNewDailyEP.asp",
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