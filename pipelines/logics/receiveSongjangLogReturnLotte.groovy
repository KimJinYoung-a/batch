// ��ǰ���� ����(�Ե�)
@Library('tenbyten-library') _

// ���� 7-23�� 13��/43��
properties([pipelineTriggers([cron('13,43 7-23 * * *')])])

node {
    stage("Receive Return Info Action") {
        try {
            def response
            def baseUrl = "http://10.10.10.49:9502"

            // 100��
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
