// 반품접수 전송(롯데)
@Library('tenbyten-library') _

// 매일 7-23시 13분/43분
properties([pipelineTriggers([cron('13,43 7-23 * * *')])])

node {
    stage("Receive Return Info Action") {
        try {
            def response
            def baseUrl = "http://10.10.10.49:9502"

            // 100개
            response = httpRequest url: baseUrl + "/csapi/v1/lotte/sendsongjanglogreturn/receive/",
                                validResponseCodes: "200:399"
            println("Status: "+ response.status)
            println("Content: "+ response.content)

            if (response.status >= 400) {
                throw new Exception("api error")
            }
        }catch(e) {
            throw e
        }
    }
}
