// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

// 매일 01:23분 1회 실행
properties([pipelineTriggers([cron('30 16 * * *')])])

node {
    stage("KakaoGift Regitem") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/kakaogift/kakaogiftReg.asp",
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