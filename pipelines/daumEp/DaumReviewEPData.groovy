// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

// 매일 12시50분
// 매일1시 25분으로 수정..2020-11-05 김진영
properties([pipelineTriggers([cron('25 1 * * *')])])

node {
    stage("DaumReviewEpData") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/daumEP/makeNewDailyEP.asp?epType=Newreview",
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
