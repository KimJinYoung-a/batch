// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

import groovy.json.JsonSlurperClassic

@NonCPS
def parseJsonToMap(String json) {
    final slurper = new JsonSlurperClassic()
    return new HashMap<>(slurper.parseText(json))
}

// 20분에 한번 실행
properties([pipelineTriggers([cron('H/20 * * * *')])])

node {
    stage("Soldout Action") {
        try {
            def baseUrl = "http://gateway.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/thirdparty/internal/queue/read?mallId=shopify&apiAction=SOLDOUT&topCount=100"
            def map = parseJsonToMap(response.content)
            if (map.result != ""){
                def jsonresultArr = map.result.split(",")
                for(i=0; i < jsonresultArr.length; i++){
                    def itemArr = jsonresultArr[i].split("##")[0]
                    def idxArr = jsonresultArr[i].split("##")[1]
                    try {
                        def responseProc = httpRequest url: baseUrl + "/external/apis/product/update/status/batch?mallId=shopify&action=SOLDOUT&idx="+idxArr+"&itemId="+itemArr
                    }catch(e) {
                        throw e
                    }
                }
            }
        }catch(e) {
            throw e
        }
    }
    stage("SellChg Action") {
        try {
            def baseUrl = "http://gateway.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/thirdparty/internal/queue/read?mallId=shopify&apiAction=EDIT2&topCount=80"
            def map = parseJsonToMap(response.content)
            if (map.result != ""){
                def jsonresultArr = map.result.split(",")
                for(i=0; i < jsonresultArr.length; i++){
                    def itemArr = jsonresultArr[i].split("##")[0]
                    def idxArr = jsonresultArr[i].split("##")[1]
                    try {
                        def responseProc = httpRequest url: baseUrl + "/external/apis/product/update/batch?mallId=shopify&action=EDIT&idx="+idxArr+"&itemId="+itemArr
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
            def response = httpRequest url: baseUrl + "/thirdparty/internal/queue/read?mallId=shopify&apiAction=EDIT&topCount=80"
            def map = parseJsonToMap(response.content)
            if (map.result != ""){
                def jsonresultArr = map.result.split(",")
                for(i=0; i < jsonresultArr.length; i++){
                    def itemArr = jsonresultArr[i].split("##")[0]
                    def idxArr = jsonresultArr[i].split("##")[1]
                    try {
                        def responseProc = httpRequest url: baseUrl + "/external/apis/product/update/batch?mallId=shopify&action=EDIT&idx="+idxArr+"&itemId="+itemArr
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

// // 상품 변경사항이 있을때 api que를 생성함.
// @Library('tenbyten-library') _

// // 5분에 한번 실행
// properties([pipelineTriggers([cron('H/5 * * * *')])])

// node {
//     stage("Edit Action") {
//         try {
//             def baseUrl = "http://local.10x10.co.kr:5000"
//             def response = httpRequest url: baseUrl + "/api/outmall/ShopifyApiQue/EditAction",
//                                     validResponseCodes: "200:499"
//             println("Status: "+ response.status)
//             println("Content: "+ response.content)

//             if (response.status > 400) {
//                 throw new Exception("api error")
//             }
//         } catch(e) {
//             def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/e22a7280497b36b0472934b78bdd1b6e"
//             def message = "Failure /api/outmall/ShopifyApiQue/EditAction"
//             def jandiReporter = new JandiReporter()
//             jandiReporter.reportSimple(jandiUrl, message)

//             throw e
//         }
//     }
    
// }