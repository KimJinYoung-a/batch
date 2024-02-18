// 전역 라이브러리 로드
@Library('tenbyten-library') _

// 5분에 한번 실행
properties([pipelineTriggers([cron('H/5 * * * *')])])

node {
    def baseUrl = "http://local.10x10.co.kr:5000"
    stage("Edit Action") {
        // https://jenkins.io/doc/pipeline/steps/http_request/
        def response = httpRequest baseUrl + "/api/outmall/ShopifyApiQue/EditAction"
        println("Status: "+ response.status)
        println("Content: "+ response.content)
    }

    stage("Edit Make") {
        // https://jenkins.io/doc/pipeline/steps/http_request/
        def response = httpRequest baseUrl + "/api/outmall/ShopifyApiQue/EditMake"
        println("Status: "+ response.status)
        println("Content: "+ response.content)
    }
}