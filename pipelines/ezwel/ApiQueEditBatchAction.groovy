// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

import groovy.json.JsonSlurperClassic

@NonCPS
def parseJsonToMap(String json) {
    final slurper = new JsonSlurperClassic()
    return new HashMap<>(slurper.parseText(json))
}

// 5분에 한번 실행
properties([pipelineTriggers([cron('H/6 * * * *')])])

node {
    stage("EditBatch Action") {
        try {
            def baseUrl = "http://gateway.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/thirdparty/internal/queue/read?mallId=ezwel&apiAction=EDITBATCH&topCount=100"
            def map = parseJsonToMap(response.content)
            if (map.result != ""){
                def jsonresultArr = map.result.split(",")
                for(i=0; i < jsonresultArr.length; i++){
                    def itemArr = jsonresultArr[i].split("##")[0]
                    def idxArr = jsonresultArr[i].split("##")[1]
                    try {
                        def responseProc = httpRequest url: baseUrl + "/external/apis/product/update/batch?mallId=ezwel&action=EDIT&idx="+idxArr+"&itemId="+itemArr+"&user=batch",
                                            validResponseCodes: "200:599"
                        println("Status: "+ responseProc.status)
                        println("Content: "+ responseProc.content)
                        if (responseProc.status > 400) {
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
    stage("EditBatch2 Action") {
        try {
            def baseUrl = "http://gateway.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/thirdparty/internal/queue/read?mallId=ezwel&apiAction=EDITBATCH&topCount=100"
            def map = parseJsonToMap(response.content)
            if (map.result != ""){
                def jsonresultArr = map.result.split(",")
                for(i=0; i < jsonresultArr.length; i++){
                    def itemArr = jsonresultArr[i].split("##")[0]
                    def idxArr = jsonresultArr[i].split("##")[1]
                    try {
                        def responseProc = httpRequest url: baseUrl + "/external/apis/product/update/batch?mallId=ezwel&action=EDIT&idx="+idxArr+"&itemId="+itemArr+"&user=batch",
                                            validResponseCodes: "200:599"
                        println("Status: "+ responseProc.status)
                        println("Content: "+ responseProc.content)
                        if (responseProc.status > 400) {
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
    //  stage("EditBatch Action") {
    //     try {
    //         def baseUrl = "http://wapi.10x10.co.kr"
    //         def response = httpRequest url: baseUrl + "/outmall/batch/batchproc.asp?mallid=ezwel&apiAction=EDITBATCH&topN=100",
    //                                 validResponseCodes: "200:499"
    //         def responseText = response.content
    //         if (responseText != ""){
    //             def responseTextArr = responseText.split(",")
    //             for(i=0; i < responseTextArr.length; i++){
    //                 def itemArr = responseTextArr[i].split("##")[0]
    //                 def idxArr = responseTextArr[i].split("##")[1]

    //                 def responseProc = httpRequest url: baseUrl + "/outmall/proc/EzwelProc.asp?redSsnKey=system&jenkinsBatchYn=Y&mallid=ezwel&action=EDIT&idx="+idxArr+"&itemid="+itemArr,
    //                                         validResponseCodes: "200:499"

    //                 println("Status: "+ responseProc.status)
    //                 println("Content: "+ responseProc.content)
    //                 if (responseProc.status > 400) {
    //                     throw new Exception("api error")
    //                 }
    //             }
    //         }
    //     }catch(e) {
    //         throw e
    //     }
    // }
}