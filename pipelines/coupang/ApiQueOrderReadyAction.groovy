// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

//8~9시, 10~11시, 14~15시, 17~18시 시간동안 5분에 한번 실행
properties([pipelineTriggers([cron('H/5 08-09,10-11,14-15,17-18 * * *')])])

node {
    stage("OrderReady Action") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/coupang/xSiteOrderReady_Process.asp?sellsite=coupang&jenkinsBatchYn=Y",
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

    stage("OrderReady2 Action") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/coupang/xSiteOrderReady_Process.asp?sellsite=coupang&jenkinsBatchYn=Y",
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