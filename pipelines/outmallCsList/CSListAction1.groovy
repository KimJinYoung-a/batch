// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

// 06:11에 트리거된 후 20:00 기간동안 1시간 마다 반복
properties([pipelineTriggers([cron('11 6-20/1 * * *')])])

node {
    stage("Auction CSList") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/auction/xSiteCSOrder_auction_Process.asp?mode=getxsitecslist&sellsite=auction1010&redSsnKey=system",
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
    stage("Gmarket CSList") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?sellsite=gmarket1010&mode=all&redSsnKey=system",
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
    stage("Nvstorefarm CSList") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?sellsite=nvstorefarm&mode=all&redSsnKey=system",
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
    stage("Mylittlewhoopee CSList") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?sellsite=Mylittlewhoopee&mode=all&redSsnKey=system",
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
    stage("NvstoreGift CSList") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?sellsite=nvstoregift&mode=all&redSsnKey=system",
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
    stage("Interpark CSList") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?sellsite=interpark&mode=all&redSsnKey=system",
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
    stage("CJMall ReturnList") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/cjmall/xSiteCSOrder_Cjmall_Process.asp?cmdparam=cjmallCsreg1&redSsnKey=system",
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
    stage("CJMall Cancel") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/cjmall/xSiteCSOrder_Cjmall_Process.asp?cmdparam=cjmallCsreg2&redSsnKey=system",
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
    stage("CJMall Change") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/cjmall/xSiteCSOrder_Cjmall_Process.asp?cmdparam=cjmallCsreg3&redSsnKey=system",
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
    stage("lfmall CSList") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Utf8Order_Ins_Process.asp?sellsite=lfmall&mode=all&redSsnKey=system",
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