// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

properties([pipelineTriggers([cron('H * * * *')])])

node {
    stage("PreOrder Action") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/11st/xSitePreOrder_11st1010_Process.asp?redSsnKey=system",
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