// 제휴몰 교환/반품 어드민 등록상태 체크
@Library('tenbyten-library') _

// 매일 9-19시에 실행
properties([pipelineTriggers([cron('H 9-19 * * *')])])

node {
    stage("lotteimall SCM Admin Check") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?redSsnKey=system&sellsite=lotteimall&mode=matchcs",
                                    validResponseCodes: "200:499"

            println("Status: "+ response.status)
            println("Content: "+ response.content)
            if (response.status > 400) {
                throw new Exception("lotteimall Admin Check error")
            }
        }catch(e) {
            throw e
        }
    }

    stage("ezwel SCM Admin Check") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?redSsnKey=system&sellsite=ezwel&mode=matchcs",
                                    validResponseCodes: "200:499"

            println("Status: "+ response.status)
            println("Content: "+ response.content)
            if (response.status > 400) {
                throw new Exception("ezwel Admin Check error")
            }
        }catch(e) {
            throw e
        }
    }

    stage("kakaostore SCM Admin Check") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?redSsnKey=system&sellsite=kakaostore&mode=matchcs",
                                    validResponseCodes: "200:499"

            println("Status: "+ response.status)
            println("Content: "+ response.content)
            if (response.status > 400) {
                throw new Exception("kakaostore Admin Check error")
            }
        }catch(e) {
            throw e
        }
    }

    stage("nvstorefarm SCM Admin Check") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?redSsnKey=system&sellsite=nvstorefarm&mode=matchcs",
                                    validResponseCodes: "200:499"

            println("Status: "+ response.status)
            println("Content: "+ response.content)
            if (response.status > 400) {
                throw new Exception("nvstorefarm Admin Check error")
            }
        }catch(e) {
            throw e
        }
    }

    stage("nvstoregift SCM Admin Check") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?redSsnKey=system&sellsite=nvstoregift&mode=matchcs",
                                    validResponseCodes: "200:499"

            println("Status: "+ response.status)
            println("Content: "+ response.content)
            if (response.status > 400) {
                throw new Exception("nvstoregift Admin Check error")
            }
        }catch(e) {
            throw e
        }
    }

    stage("Mylittlewhoopee SCM Admin Check") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?redSsnKey=system&sellsite=Mylittlewhoopee&mode=matchcs",
                                    validResponseCodes: "200:499"

            println("Status: "+ response.status)
            println("Content: "+ response.content)
            if (response.status > 400) {
                throw new Exception("Mylittlewhoopee Admin Check error")
            }
        }catch(e) {
            throw e
        }
    }

    stage("auction1010 SCM Admin Check") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?redSsnKey=system&sellsite=auction1010&mode=matchcs",
                                    validResponseCodes: "200:499"

            println("Status: "+ response.status)
            println("Content: "+ response.content)
            if (response.status > 400) {
                throw new Exception("auction1010 Admin Check error")
            }
        }catch(e) {
            throw e
        }
    }

    stage("cjmall SCM Admin Check") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?redSsnKey=system&sellsite=cjmall&mode=matchcs",
                                    validResponseCodes: "200:499"

            println("Status: "+ response.status)
            println("Content: "+ response.content)
            if (response.status > 400) {
                throw new Exception("cjmall Admin Check error")
            }
        }catch(e) {
            throw e
        }
    }

    stage("ssg SCM Admin Check") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?redSsnKey=system&sellsite=ssg&mode=matchcs",
                                    validResponseCodes: "200:499"

            println("Status: "+ response.status)
            println("Content: "+ response.content)
            if (response.status > 400) {
                throw new Exception("ssg Admin Check error")
            }
        }catch(e) {
            throw e
        }
    }

    stage("gmarket1010 SCM Admin Check") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?redSsnKey=system&sellsite=gmarket1010&mode=matchcs",
                                    validResponseCodes: "200:499"

            println("Status: "+ response.status)
            println("Content: "+ response.content)
            if (response.status > 400) {
                throw new Exception("gmarket1010 Admin Check error")
            }
        }catch(e) {
            throw e
        }
    }

    stage("interpark SCM Admin Check") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?redSsnKey=system&sellsite=interpark&mode=matchcs",
                                    validResponseCodes: "200:499"

            println("Status: "+ response.status)
            println("Content: "+ response.content)
            if (response.status > 400) {
                throw new Exception("interpark Admin Check error")
            }
        }catch(e) {
            throw e
        }
    }

    stage("gseshop SCM Admin Check") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?redSsnKey=system&sellsite=gseshop&mode=matchcs",
                                    validResponseCodes: "200:499"

            println("Status: "+ response.status)
            println("Content: "+ response.content)
            if (response.status > 400) {
                throw new Exception("gseshop Admin Check error")
            }
        }catch(e) {
            throw e
        }
    }

    stage("halfclub SCM Admin Check") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?redSsnKey=system&sellsite=halfclub&mode=matchcs",
                                    validResponseCodes: "200:499"

            println("Status: "+ response.status)
            println("Content: "+ response.content)
            if (response.status > 400) {
                throw new Exception("halfclub Admin Check error")
            }
        }catch(e) {
            throw e
        }
    }

    stage("hmall1010 SCM Admin Check") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?redSsnKey=system&sellsite=hmall1010&mode=matchcs",
                                    validResponseCodes: "200:499"

            println("Status: "+ response.status)
            println("Content: "+ response.content)
            if (response.status > 400) {
                throw new Exception("hmall1010 Admin Check error")
            }
        }catch(e) {
            throw e
        }
    }

    stage("11st1010 SCM Admin Check") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?redSsnKey=system&sellsite=11st1010&mode=matchcs",
                                    validResponseCodes: "200:499"

            println("Status: "+ response.status)
            println("Content: "+ response.content)
            if (response.status > 400) {
                throw new Exception("11st1010 Admin Check error")
            }
        }catch(e) {
            throw e
        }
    }

    stage("WMP SCM Admin Check") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?redSsnKey=system&sellsite=WMP&mode=matchcs",
                                    validResponseCodes: "200:499"

            println("Status: "+ response.status)
            println("Content: "+ response.content)
            if (response.status > 400) {
                throw new Exception("WMP Admin Check error")
            }
        }catch(e) {
            throw e
        }
    }

    stage("shintvshopping SCM Admin Check") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?redSsnKey=system&sellsite=shintvshopping&mode=matchcs",
                                    validResponseCodes: "200:499"

            println("Status: "+ response.status)
            println("Content: "+ response.content)
            if (response.status > 400) {
                throw new Exception("shintvshopping Admin Check error")
            }
        }catch(e) {
            throw e
        }
    }

    stage("skstoa SCM Admin Check") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?redSsnKey=system&sellsite=skstoa&mode=matchcs",
                                    validResponseCodes: "200:499"

            println("Status: "+ response.status)
            println("Content: "+ response.content)
            if (response.status > 400) {
                throw new Exception("skstoa Admin Check error")
            }
        }catch(e) {
            throw e
        }
    }

    stage("lotteon SCM Admin Check") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?redSsnKey=system&sellsite=lotteon&mode=matchcs",
                                    validResponseCodes: "200:499"

            println("Status: "+ response.status)
            println("Content: "+ response.content)
            if (response.status > 400) {
                throw new Exception("lotteon Admin Check error")
            }
        }catch(e) {
            throw e
        }
    }

    stage("coupang SCM Admin Check") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?redSsnKey=system&sellsite=coupang&mode=matchcs",
                                    validResponseCodes: "200:499"

            println("Status: "+ response.status)
            println("Content: "+ response.content)
            if (response.status > 400) {
                throw new Exception("coupang Admin Check error")
            }
        }catch(e) {
            throw e
        }
    }
}
