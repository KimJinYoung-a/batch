// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

// 06:13에 트리거된 후 20:00 기간동안 1시간 마다 반복
properties([pipelineTriggers([cron('13 6-20/1 * * *')])])

node {
    stage("KakaoStore CSList") {
        try {
            def baseUrl = "https://webadmin.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/admin/etc/order/xSiteCSOrder_Ins_Process.asp?sellsite=kakaostore&mode=all",
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
    stage("Ezwel CSList") {
        try {
            def baseUrl = "https://webadmin.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/admin/etc/order/xSiteCSOrder_Ins_Process.asp?sellsite=ezwel&mode=all",
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
    stage("Boribori CSList") {
        try {
            def baseUrl = "https://webadmin.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/admin/etc/order/xSiteCSOrder_Ins_Process.asp?sellsite=boribori1010&mode=all",
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
    stage("Benepia CSList") {
        try {
            def baseUrl = "https://webadmin.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/admin/etc/order/xSiteCSOrder_Ins_Process.asp?sellsite=benepia1010&mode=all",
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