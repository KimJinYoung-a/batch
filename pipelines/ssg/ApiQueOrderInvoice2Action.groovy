// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

// 10분에 한번 실행
properties([pipelineTriggers([cron('H/10 * * * *')])])

node {
    stage("OrderInvoice_BeasongDate_DESC Action") {
        try {
            def reqParam = "act=outmallSongJangIp&param1=ssg&param2=30"
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/autojob/outmallAutoJob_ssg.asp?" + reqParam,
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
    stage("OrderInvoice_BeasongDate_ASC Action") {
        try {
            def reqParam = "act=outmallSongJangIp&param1=ssg&param2=30&param3=1"
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/autojob/outmallAutoJob_ssg.asp?" + reqParam,
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
    stage("ChulgoCompleteTarget Action1") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/autojob/outmallAutoJob_ssg.asp?act=chulgotarget&param1=ssg",
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

    stage("ChulgoCompleteProc Action1") {
        try {
            def reqParam = "act=outmallChulgoProc&param1=ssg&param2=60"
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/autojob/outmallAutoJob_ssg.asp?" + reqParam,
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

    stage("ChulgoCompleteTarget Action2") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/autojob/outmallAutoJob_ssg.asp?act=chulgotarget&param1=ssg",
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

    stage("ChulgoCompleteProc Action2") {
        try {
            def reqParam = "act=outmallChulgoProc&param1=ssg&param2=60"
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/autojob/outmallAutoJob_ssg.asp?" + reqParam,
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

    stage("CS List Action2") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/ssg/xSiteCsOrder_ssg_Process.asp",
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