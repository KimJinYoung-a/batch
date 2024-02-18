// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

// 10분에 한번 실행
properties([pipelineTriggers([cron('H/10 * * * *')])])

node {
    stage("OrderInvoice Action") {
        try {
            for(i=0; i < 10; i++){
                def reqParam = "act=outmallSongJangIp&param1=ssg&param2=6"
                if (i % 2 == 0) {
                    reqParam = reqParam + "&param3=1"
                }
                def baseUrl = "http://wapi.10x10.co.kr"
                def response = httpRequest url: baseUrl + "/outmall/autojob/outmallAutoJob_ssg.asp?" + reqParam,
                                        validResponseCodes: "200:499"
                println("Status: "+ response.status)
                println("Content: "+ response.content)
                if (response.status > 400) {
                    throw new Exception("api error")
                }
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
            for(i=0; i < 10; i++){
                def reqParam = "act=outmallChulgoProc&param1=ssg&param2=6"
                def baseUrl = "http://wapi.10x10.co.kr"
                def response = httpRequest url: baseUrl + "/outmall/autojob/outmallAutoJob_ssg.asp?" + reqParam,
                                        validResponseCodes: "200:499"
                println("Status: "+ response.status)
                println("Content: "+ response.content)
                if (response.status > 400) {
                    throw new Exception("api error")
                }
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
            for(i=0; i < 10; i++){
                def reqParam = "act=outmallChulgoProc&param1=ssg"
                def baseUrl = "http://wapi.10x10.co.kr"
                def response = httpRequest url: baseUrl + "/outmall/autojob/outmallAutoJob_ssg.asp?" + reqParam,
                                        validResponseCodes: "200:499"
                println("Status: "+ response.status)
                println("Content: "+ response.content)
                if (response.status > 400) {
                    throw new Exception("api error")
                }
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