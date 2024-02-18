// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

// 06:13에 트리거된 후 20:00 기간동안 1시간 마다 반복
properties([pipelineTriggers([cron('13 6-20/1 * * *')])])

node {
    stage("Coupang CSList") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?sellsite=coupang&mode=all&redSsnKey=system",
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
    stage("Hmall CSList") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?sellsite=hmall1010&mode=all&redSsnKey=system",
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
    stage("Lotteimall CSList") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/ltimall/xSiteCSOrder_lotteimall_Process.asp?sellsite=lotteimall&mode=getxsitecslist&redSsnKey=system",
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
    stage("11st CSList") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?sellsite=11st1010&mode=all&redSsnKey=system",
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
    stage("WMP CSList") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?sellsite=WMP&mode=all&redSsnKey=system",
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
    // stage("Wmpfashion CSList") {
    //     try {
    //         def baseUrl = "http://wapi.10x10.co.kr"
    //         def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?sellsite=wmpfashion&mode=all&redSsnKey=system",
    //                                 validResponseCodes: "200:499"
    //         println("Status: "+ response.status)
    //         println("Content: "+ response.content)

    //         if (response.status > 400) {
    //             throw new Exception("api error")
    //         }

    //     }catch(e) {
    //         throw e
    //     }
    // }
    stage("SSG CSList") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/ssg/xSiteCsOrder_ssg_Process.asp?redSsnKey=system",
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
    stage("GSShop Return") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSiteOrder_NoChkSession_Ins_Process.asp?sellsite=gseshopNew&redSsnKey=system",
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
    stage("GSShop Cancel") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?sellsite=gseshop&mode=orderNewcancel&redSsnKey=system",
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