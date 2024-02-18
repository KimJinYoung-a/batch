@Library('tenbyten-library') _

//08:00 ~ 23:00 기간동안 3시간 마다 반복
properties([pipelineTriggers([cron('H H(8-23)/3 * * *')])])

node {
    stage("getAmplitude Action") {
        try {
            def baseUrl = "http://gateway.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/v1/site/internal/manage/amplitude",
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