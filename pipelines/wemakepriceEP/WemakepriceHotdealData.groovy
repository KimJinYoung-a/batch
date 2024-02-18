// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

//매일 오전 02시 15분 트리거된 후 20:00기간 동안 8시간마다 반복
properties([pipelineTriggers([cron('15 2-20/8 * * *')])])

node {
    stage("WemakepriceHotdealData") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/wemakepriceEP/HotDealmakeXML.asp",
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