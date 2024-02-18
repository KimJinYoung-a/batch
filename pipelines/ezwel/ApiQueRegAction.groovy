// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

import groovy.json.JsonSlurperClassic

@NonCPS
def parseJsonToMap(String json) {
    final slurper = new JsonSlurperClassic()
    return new HashMap<>(slurper.parseText(json))
}

// 2시~08시까지, 18시~23시까지 10분마다 실행
// properties([pipelineTriggers([cron('*/10 2-8,18-23 * * 1-5')])])
//properties([pipelineTriggers([cron('H/5 * * * *')])])

// 30분에 한번 실행
properties([pipelineTriggers([cron('H/30 02-23 * * *')])])
node {
    stage("REG Action") {
        try {
            def baseUrl = "http://gateway.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/thirdparty/internal/queue/read?mallId=ezwel&apiAction=REG&topCount=85"
            def map = parseJsonToMap(response.content)
            if (map.result != ""){
                def jsonresultArr = map.result.split(",")
                for(i=0; i < jsonresultArr.length; i++){
                    def itemArr = jsonresultArr[i].split("##")[0]
                    def idxArr = jsonresultArr[i].split("##")[1]
                    try {
                        def responseProc = httpRequest url: baseUrl + "/external/apis/product/create/batch?adminId=system&mallId=ezwel&action=REG&idx="+idxArr+"&itemId="+itemArr+"&user=batch",
                                            validResponseCodes: "200:599"
                        println("Status: "+ responseProc.status)
                        println("Content: "+ responseProc.content)
                    }catch(e) {
                        throw e
                    }
                }
            }
        }catch(e) {
            throw e
        }
    }
}