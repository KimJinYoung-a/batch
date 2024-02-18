// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

// 7시~24시까지 1분에 한번 실행
properties([pipelineTriggers([cron('* 7-23 * * *')])])

node {
    stage("BaljuRefresh Action") {
        try {
            def baseUrl = "http://logics.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/autojob/actBaljuRefresh.asp",
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