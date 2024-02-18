// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

//한시간에 한번 실행
properties([pipelineTriggers([cron('H/20 * * * *')])])

node {
    stage("CSRequestQnA Action") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/csqna/xSiteQnA_Ins_Process.asp?sellsite=skstoa&mode=reqQnA&redSsnKey=system",
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

    stage("CSResponseQnA Action") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/csqna/xSiteQnA_Ins_Process.asp?sellsite=skstoa&mode=resQnA&redSsnKey=system",
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