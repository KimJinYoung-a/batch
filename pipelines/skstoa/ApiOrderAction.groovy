// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

properties([pipelineTriggers([cron('34 8,10,13,16 * * *')])])

node {
    stage("OrderNewSelect Action") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSiteOrder_Ins_Process.asp?sellsite=skstoa&gubunCode=1&redSsnKey=system",
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

    stage("OrderReady Action") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSiteOrder_Ins_Process.asp?sellsite=skstoa&gubunCode=2&redSsnKey=system",
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

    stage("OrderSelect Action") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSiteOrder_Ins_Process.asp?sellsite=skstoa&gubunCode=3&redSsnKey=system",
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
            def response = httpRequest url: baseUrl + "/outmall/shintvshopping/orderAutoInput.asp?sellsite=skstoa",
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