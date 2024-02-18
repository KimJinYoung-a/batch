// 반품접수 전송(롯데)
@Library('tenbyten-library') _

// 17-23시 33분 (월-토)
properties([pipelineTriggers([cron('33 17-23 * * 1-6')])])

node {
    stage("Send Songjang Action") {
        try {
            def response
            def baseUrl = "http://10.10.10.49:9502"

            // 100개
            response = httpRequest url: baseUrl + "/csapi/v1/lotte/sendsongjanglogreturn/",
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
