// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

//매일 05시33분	8시간반복 	9시간 기간
properties([pipelineTriggers([cron('33 5-14/8 * * *')])])

node {
    stage("DaumBest100EpData") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/daumEP/makeNewDailyEP.asp?epType=best100",
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