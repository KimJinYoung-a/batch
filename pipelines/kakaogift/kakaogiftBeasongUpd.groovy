// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

// 05:11에 트리거된 후 20:00 기간동안 12시간 마다 반복
properties([pipelineTriggers([cron('11 5-20/12 * * *')])])

node {
    stage("KakaoGift BeasongUpd") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/kakaogift/kakaogiftBeasongUpd.asp",
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