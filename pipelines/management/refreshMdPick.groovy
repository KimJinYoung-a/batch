// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

import groovy.json.JsonSlurperClassic

@NonCPS
def parseJsonToMap(String json) {
    final slurper = new JsonSlurperClassic()
    return new HashMap<>(slurper.parseText(json))
}

// 09시~20시 사이 1시간에 한번 실행
//2023-07-28 김진영 수정..승현님 요청 시간 관계없이 10분마다로..
properties([pipelineTriggers([cron('H/10 * * * *')])])

node {
    stage("MD Pick refresh") {
        try {
            def baseUrl = "https://gateway.10x10.co.kr"
            // 토큰발행
            def response = httpRequest url: baseUrl + "/certificate/internal/certificate/token/throw/issue",
                                        httpMode : "POST",
                                        contentType: "APPLICATION_JSON",
                                        requestBody : '''{"userId": "system","userSeq": 0}''',
                                        validResponseCodes: "200:599"
            def map = parseJsonToMap(response.content)
            // 갱신요청
            if (map.result != ""){
                def accessToken = map.result.accessToken
                try {
                    def responseProc = httpRequest url: baseUrl + "/cache/apis/refresh/mdpick",
                                        httpMode : "POST",
                                        customHeaders : [[name : "Authorization", value: accessToken]],
                                        validResponseCodes: "200:599"
                    println("Status: "+ responseProc.status)
                    println("Content: "+ responseProc.content)
                }catch(e) {
                    throw e
                }
            }
        }catch(e) {
            throw e
        }
    }
}