// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

//8~9시, 10~11시, 14~15시, 17~18시 시간동안 30분에 한번 실행
properties([pipelineTriggers([cron('H/30 08-09,10-11,14-15,17-18,22-23 * * *')])])

node {
    stage("OrderGetList Action") {
        try {
            def baseUrl = "https://webadmin.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/admin/etc/order/xSiteOrder_Ins_Process.asp?sellsite=wconcept1010&gubunCode=1",
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

    stage("OrderDetail Action") {
        try {
            def baseUrl = "https://webadmin.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/admin/etc/order/xSiteOrder_Ins_Process.asp?sellsite=wconcept1010&gubunCode=2",
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

    stage("OrderConfirm Action") {
        try {
            def baseUrl = "https://webadmin.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/admin/etc/order/xSiteOrder_Ins_Process.asp?sellsite=wconcept1010&gubunCode=3",
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
            def response = httpRequest url: baseUrl + "/outmall/shintvshopping/orderAutoInput.asp?sellsite=wconcept1010",
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