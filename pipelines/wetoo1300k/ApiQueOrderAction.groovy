// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

properties([pipelineTriggers([cron('39 8,10,13,16 * * *')])])

node {
    stage("OrderNewSelect Action") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSiteOrder_Ins_Process.asp?sellsite=wetoo1300k&redSsnKey=system",
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

    stage("OrderInput Action") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/shintvshopping/orderAutoInput.asp?sellsite=wetoo1300k",
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