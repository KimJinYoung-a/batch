// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

// 10분에 한번 실행
properties([pipelineTriggers([cron('H/10 * * * *')])])

node {

    stage("SOLDOUT Action") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/batch/batchAddPriceproc.asp?mallid=gsshop&apiAction=SOLDOUT&topN=150",
                                    validResponseCodes: "200:499"
            def responseText = response.content
            if (responseText != ""){
                def responseTextArr = responseText.split(",")
                for(i=0; i < responseTextArr.length; i++){
                    def midxArr = responseTextArr[i].split("##")[0]
                    def qidxArr = responseTextArr[i].split("##")[1]

                    def responseProc = httpRequest url: baseUrl + "/outmall/proc/GSShopOptProc.asp?redSsnKey=system&jenkinsBatchYn=Y&mallid=gsshop&action=SOLDOUT&qidx="+qidxArr+"&idx="+midxArr,
                                            validResponseCodes: "200:499"

                    println("Status: "+ responseProc.status)
                    println("Content: "+ responseProc.content)
                    if (responseProc.status > 400) {
                        throw new Exception("api error")
                    }
                }
            }
        }catch(e) {
            throw e
        }
    }

    // stage("Price Action") {
    //     try {
    //         def baseUrl = "http://wapi.10x10.co.kr"
    //         def response = httpRequest url: baseUrl + "/outmall/batch/batchAddPriceproc.asp?mallid=gsshop&apiAction=PRICE&topN=50",
    //                                 validResponseCodes: "200:499"
    //         def responseText = response.content
    //         if (responseText != ""){
    //             def responseTextArr = responseText.split(",")
    //             for(i=0; i < responseTextArr.length; i++){
    //                 def midxArr = responseTextArr[i].split("##")[0]
    //                 def qidxArr = responseTextArr[i].split("##")[1]

    //                 def responseProc = httpRequest url: baseUrl + "/outmall/proc/GSShopOptProc.asp?redSsnKey=system&jenkinsBatchYn=Y&mallid=gsshop&action=PRICE&qidx="+qidxArr+"&idx="+midxArr,
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

    // stage("Edit Action") {
    //     try {
    //         def baseUrl = "http://wapi.10x10.co.kr"
    //         def response = httpRequest url: baseUrl + "/outmall/batch/batchAddPriceproc.asp?mallid=gsshop&apiAction=EDIT&topN=40",
    //                                 validResponseCodes: "200:499"
    //         def responseText = response.content
    //         if (responseText != ""){
    //             def responseTextArr = responseText.split(",")
    //             for(i=0; i < responseTextArr.length; i++){
    //                 def midxArr = responseTextArr[i].split("##")[0]
    //                 def qidxArr = responseTextArr[i].split("##")[1]

    //                 def responseProc = httpRequest url: baseUrl + "/outmall/proc/GSShopOptProc.asp?redSsnKey=system&jenkinsBatchYn=Y&mallid=gsshop&action=EDIT&qidx="+qidxArr+"&idx="+midxArr,
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