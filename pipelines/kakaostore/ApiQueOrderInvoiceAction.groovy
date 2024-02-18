// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

// 5분에 한번 실행
properties([pipelineTriggers([cron('H/5 * * * *')])])

node {
    stage("OrderInvoice Action") {
        try {
            def baseUrl = "https://webadmin.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/admin/etc/order/batchsongjangupload.asp?act=outmallSongJangIp&param1=kakaostore&redSsnKey=system",
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