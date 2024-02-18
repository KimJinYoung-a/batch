// ��ǰ���� ����(�Ե�)
@Library('tenbyten-library') _

// 17-23�� 33�� (��-��)
properties([pipelineTriggers([cron('33 17-23 * * 1-6')])])

node {
    stage("Send Songjang Action") {
        try {
            def response
            def baseUrl = "http://10.10.10.49:9502"

            // 100��
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
