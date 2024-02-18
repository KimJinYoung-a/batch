// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

import groovy.json.JsonSlurperClassic

@NonCPS
def parseJsonToMap(String json) {
    final slurper = new JsonSlurperClassic()
    return new HashMap<>(slurper.parseText(json))
}

def nowLocalDateTime = new Date().format("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

// 15분에 한번 실행
properties([pipelineTriggers([cron('H/10 02-23 * * *')])])

node {
    stage("REG Action1") {
        try {
            def baseUrl = "http://gateway.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/thirdparty/internal/queue/read?mallId=boribori1010&apiAction=REG&topCount=100"
            def map = parseJsonToMap(response.content)
            if (map.result != ""){
                def jsonresultArr = map.result.split(",")
                for(i=0; i < jsonresultArr.length; i++){
                    def itemArr = jsonresultArr[i].split("##")[0]
                    def idxArr = jsonresultArr[i].split("##")[1]
                    try {
                        def responseProc = httpRequest url: baseUrl + "/external/apis/product/create/batch?adminId=system&mallId=boribori1010&action=REG&idx="+idxArr+"&itemId="+itemArr+"&user=batch",
                                            validResponseCodes: "200:599"
                        println("Status: "+ responseProc.status)
                        println("Content: "+ responseProc.content)
                        if (responseProc.status > 400) {
                            def failbody = """
                            {
                                "itemId": $itemArr,
                                "lastErrMsg": "BATCH ERR",
                                "mallId": "boribori1010"
                                }
                            """

                            def queuebody = """
                            {
                                "apiAction": "REG",
                                "findDate": "$nowLocalDateTime",
                                "itemId": $itemArr,
                                "lastErrMsg": "BATCH ERR",
                                "lastUserId": "system",
                                "mallId": "boribori1010",
                                "priority": 0,
                                "readDate": "$nowLocalDateTime",
                                "regDate": "$nowLocalDateTime",
                                "resultCode": "ERR"
                            }
                            """
                            
                            def responseProc3 = httpRequest url: baseUrl + "/thirdparty/internal/item/fail", httpMode : "POST", contentType: "APPLICATION_JSON", requestBody : failbody, validResponseCodes: "200:599"
                            def responseProc2 = httpRequest url: baseUrl + "/thirdparty/internal/queue/result", httpMode : "POST", contentType: "APPLICATION_JSON", requestBody : queuebody, validResponseCodes: "200:599"
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
    stage("REG Action2") {
        try {
            def baseUrl = "http://gateway.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/thirdparty/internal/queue/read?mallId=boribori1010&apiAction=REG&topCount=100"
            def map = parseJsonToMap(response.content)
            if (map.result != ""){
                def jsonresultArr = map.result.split(",")
                for(i=0; i < jsonresultArr.length; i++){
                    def itemArr = jsonresultArr[i].split("##")[0]
                    def idxArr = jsonresultArr[i].split("##")[1]
                    try {
                        def responseProc = httpRequest url: baseUrl + "/external/apis/product/create/batch?adminId=system&mallId=boribori1010&action=REG&idx="+idxArr+"&itemId="+itemArr+"&user=batch",
                                            validResponseCodes: "200:599"
                        println("Status: "+ responseProc.status)
                        println("Content: "+ responseProc.content)
                        if (responseProc.status > 400) {
                            def failbody = """
                            {
                                "itemId": $itemArr,
                                "lastErrMsg": "BATCH ERR",
                                "mallId": "boribori1010"
                                }
                            """

                            def queuebody = """
                            {
                                "apiAction": "REG",
                                "findDate": "$nowLocalDateTime",
                                "itemId": $itemArr,
                                "lastErrMsg": "BATCH ERR",
                                "lastUserId": "system",
                                "mallId": "boribori1010",
                                "priority": 0,
                                "readDate": "$nowLocalDateTime",
                                "regDate": "$nowLocalDateTime",
                                "resultCode": "ERR"
                            }
                            """
                            
                            def responseProc3 = httpRequest url: baseUrl + "/thirdparty/internal/item/fail", httpMode : "POST", contentType: "APPLICATION_JSON", requestBody : failbody, validResponseCodes: "200:599"
                            def responseProc2 = httpRequest url: baseUrl + "/thirdparty/internal/queue/result", httpMode : "POST", contentType: "APPLICATION_JSON", requestBody : queuebody, validResponseCodes: "200:599"
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
    stage("REG Action3") {
        try {
            def baseUrl = "http://gateway.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/thirdparty/internal/queue/read?mallId=boribori1010&apiAction=REG&topCount=100"
            def map = parseJsonToMap(response.content)
            if (map.result != ""){
                def jsonresultArr = map.result.split(",")
                for(i=0; i < jsonresultArr.length; i++){
                    def itemArr = jsonresultArr[i].split("##")[0]
                    def idxArr = jsonresultArr[i].split("##")[1]
                    try {
                        def responseProc = httpRequest url: baseUrl + "/external/apis/product/create/batch?adminId=system&mallId=boribori1010&action=REG&idx="+idxArr+"&itemId="+itemArr+"&user=batch",
                                            validResponseCodes: "200:599"
                        println("Status: "+ responseProc.status)
                        println("Content: "+ responseProc.content)
                        if (responseProc.status > 400) {
                            def failbody = """
                            {
                                "itemId": $itemArr,
                                "lastErrMsg": "BATCH ERR",
                                "mallId": "boribori1010"
                                }
                            """

                            def queuebody = """
                            {
                                "apiAction": "REG",
                                "findDate": "$nowLocalDateTime",
                                "itemId": $itemArr,
                                "lastErrMsg": "BATCH ERR",
                                "lastUserId": "system",
                                "mallId": "boribori1010",
                                "priority": 0,
                                "readDate": "$nowLocalDateTime",
                                "regDate": "$nowLocalDateTime",
                                "resultCode": "ERR"
                            }
                            """
                            
                            def responseProc3 = httpRequest url: baseUrl + "/thirdparty/internal/item/fail", httpMode : "POST", contentType: "APPLICATION_JSON", requestBody : failbody, validResponseCodes: "200:599"
                            def responseProc2 = httpRequest url: baseUrl + "/thirdparty/internal/queue/result", httpMode : "POST", contentType: "APPLICATION_JSON", requestBody : queuebody, validResponseCodes: "200:599"
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