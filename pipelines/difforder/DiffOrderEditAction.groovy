// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

import groovy.json.JsonSlurperClassic

@NonCPS
def parseJsonToMap(String json) {
    final slurper = new JsonSlurperClassic()
    return new HashMap<>(slurper.parseText(json))
}

// 2시 5분 시작, 12시간 마다 반복
properties([pipelineTriggers([cron('5 2-23/12 * * *')])])

node {
    stage("DiffOrderEdit Action") {
        try {
            def baseUrl = "http://gateway.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/thirdparty/internal/difforder/read"
            def map = parseJsonToMap(response.content)
            if (map.result != ""){
                def jsonresultArr = map.result.split(",")
                for(i=0; i < jsonresultArr.length; i++){
                    def itemArr = jsonresultArr[i].split("##")[0]
                    def idxArr = jsonresultArr[i].split("##")[1]
                    def sellsiteArr = jsonresultArr[i].split("##")[2]
                    try {
                        def responseProc = httpRequest url: baseUrl + "/external/apis/product/update/batch?mallId="+sellsiteArr+"&action=EDIT&idx="+idxArr+"&itemId="+itemArr+"&user=difforder",validResponseCodes: "200:599"
                        if (responseProc.status == 200) {
                            println("Status: "+ responseProc.status)
                            println("Content: "+ responseProc.content)
                            println("itemArr: "+ itemArr)
                            println("idxArr: "+ idxArr)
                            println("sellsite: "+ sellsiteArr)
                        } else if (responseProc.status > 400) {
                            throw new Exception("api error")
                        }
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
