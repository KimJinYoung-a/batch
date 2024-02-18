// COUPANG 교환/반품 어드민 등록상태 체크 및 제휴몰 처리상태 체크
@Library('tenbyten-library') _

// 매일 9-19시에 실행
properties([pipelineTriggers([cron('H 9-19 * * *')])])

node {
    stage("COUPANG Ext Admin Check") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/order/xSite_CS_Order_Ins_Process.asp?redSsnKey=system&sellsite=coupang&mode=chkextcs",
                                    validResponseCodes: "200:499"

            println("Status: "+ response.status)
            println("Content: "+ response.content)
            if (response.status > 400) {
                throw new Exception("COUPANG Admin Check error")
            }
        }catch(e) {
            throw e
        }
    }
}
