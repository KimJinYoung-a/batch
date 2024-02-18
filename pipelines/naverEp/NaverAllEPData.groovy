// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

// 매일 01:23분 1회 실행
properties([pipelineTriggers([cron('23 1 * * *')])])

node {
    stage("NaverAllEpData") {
        try {
            def baseUrl = "http://w2api.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/naverEP/makeDailyNewVerEP_File.asp",
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