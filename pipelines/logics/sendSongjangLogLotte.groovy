// 출고 주문 송장전송(롯데)
@Library('tenbyten-library') _

// 10-23시 25분 (월-토)
properties([pipelineTriggers([cron('25 10-23 * * 1-6')])])

node {
    stage("Send Songjang Action") {
        try {
            def response
            def baseUrl = "http://10.10.10.49:9502"

            for (int i = 0; i < 20; i++) {
                // 100개
                response = httpRequest url: baseUrl + "/csapi/v1/lotte/sendsongjanglog/",
                                    validResponseCodes: "200:399"
                println("Status: "+ response.status)
                println("Content: "+ response.content)

                if (response.status >= 400) {
                    throw new Exception("api error")
                }
            }
        }catch(e) {
            throw e
        }
    }
}