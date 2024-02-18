// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

//매일 오전 07시 50분 트리거된 후 12시간 기간동안 2시간마다 반복
properties([pipelineTriggers([cron('50 7-19/2 * * *')])])

node {
    stage("WemakepriceChgEpData") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/wemakepriceEP/makeDailyEP.asp?epType=chg",
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