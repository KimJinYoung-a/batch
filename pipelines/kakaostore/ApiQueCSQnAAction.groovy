// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

// 20분에 한번 실행
properties([pipelineTriggers([cron('H/20 * * * *')])])

node {
    stage("CSRequestQnA Action") {
        try {
            def baseUrl = "https://webadmin.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/admin/etc/csqna/xSiteQna_Ins_Process.asp?sellsite=kakaostore&mode=reqQnA",
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

    stage("CSResponseQnA Action") {
        try {
            def baseUrl = "https://webadmin.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/admin/etc/csqna/xSiteQna_Ins_Process.asp?sellsite=kakaostore&mode=resQnA",
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