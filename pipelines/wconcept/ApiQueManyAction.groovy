// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

import groovy.json.JsonSlurperClassic

@NonCPS
def parseJsonToMap(String json) {
    final slurper = new JsonSlurperClassic()
    return new HashMap<>(slurper.parseText(json))
}

// 20분에 한번 실행
properties([pipelineTriggers([cron('H/15 * * * *')])])

node {
    stage("SOLDOUT Action") {
        try {
            def baseUrl = "http://gateway.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/thirdparty/internal/queue/read?mallId=wconcept1010&apiAction=SOLDOUT&topCount=50"
            def map = parseJsonToMap(response.content)
            if (map.result != ""){
                def jsonresultArr = map.result.split(",")
                for(i=0; i < jsonresultArr.length; i++){
                    def itemArr = jsonresultArr[i].split("##")[0]
                    def idxArr = jsonresultArr[i].split("##")[1]
                    try {
                        def responseProc = httpRequest url: baseUrl + "/external/apis/product/update/status/batch?mallId=wconcept1010&action=SOLDOUT&status=N&idx="+idxArr+"&itemId="+itemArr+"&user=batch",
                                            validResponseCodes: "200:599"
                        println("Status: "+ responseProc.status)
                        println("Content: "+ responseProc.content)
                        // if (responseProc.status > 400) {
                        //     throw new Exception("api error")
                        // }
                    }catch(e) {
                        throw e
                    }
                }
            }
        }catch(e) {
            throw e
        }
    }

    stage("Price Action") {
        try {
            def baseUrl = "http://gateway.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/thirdparty/internal/queue/read?mallId=wconcept1010&apiAction=PRICE&topCount=100"
            def map = parseJsonToMap(response.content)
            if (map.result != ""){
                def jsonresultArr = map.result.split(",")
                for(i=0; i < jsonresultArr.length; i++){
                    def itemArr = jsonresultArr[i].split("##")[0]
                    def idxArr = jsonresultArr[i].split("##")[1]
                    try {
                        def responseProc = httpRequest url: baseUrl + "/external/apis/product/update/batch?mallId=wconcept1010&action=PRICE&idx="+idxArr+"&itemId="+itemArr+"&user=batch",
                                            validResponseCodes: "200:599"
                        println("Status: "+ responseProc.status)
                        println("Content: "+ responseProc.content)
                        // if (responseProc.status > 400) {
                        //     throw new Exception("api error")
                        // }
                    }catch(e) {
                        throw e
                    }
                }
            }
        }catch(e) {
            throw e
        }
    }

    stage("Price2 Action") {
        try {
            def baseUrl = "http://gateway.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/thirdparty/internal/queue/read?mallId=wconcept1010&apiAction=PRICE&topCount=100"
            def map = parseJsonToMap(response.content)
            if (map.result != ""){
                def jsonresultArr = map.result.split(",")
                for(i=0; i < jsonresultArr.length; i++){
                    def itemArr = jsonresultArr[i].split("##")[0]
                    def idxArr = jsonresultArr[i].split("##")[1]
                    try {
                        def responseProc = httpRequest url: baseUrl + "/external/apis/product/update/batch?mallId=wconcept1010&action=PRICE&idx="+idxArr+"&itemId="+itemArr+"&user=batch",
                                            validResponseCodes: "200:599"
                        println("Status: "+ responseProc.status)
                        println("Content: "+ responseProc.content)
                        // if (responseProc.status > 400) {
                        //     throw new Exception("api error")
                        // }
                    }catch(e) {
                        throw e
                    }
                }
            }
        }catch(e) {
            throw e
        }
    }

    stage("Edit Action") {
        try {
            def baseUrl = "http://gateway.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/thirdparty/internal/queue/read?mallId=wconcept1010&apiAction=EDIT&topCount=100"
            def map = parseJsonToMap(response.content)
            if (map.result != ""){
                def jsonresultArr = map.result.split(",")
                for(i=0; i < jsonresultArr.length; i++){
                    def itemArr = jsonresultArr[i].split("##")[0]
                    def idxArr = jsonresultArr[i].split("##")[1]
                    try {
                        def responseProc = httpRequest url: baseUrl + "/external/apis/product/update/batch?mallId=wconcept1010&action=EDIT&idx="+idxArr+"&itemId="+itemArr+"&user=batch",
                                            validResponseCodes: "200:599"
                        println("Status: "+ responseProc.status)
                        println("Content: "+ responseProc.content)
                        // if (responseProc.status > 400) {
                        //     throw new Exception("api error")
                        // }
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