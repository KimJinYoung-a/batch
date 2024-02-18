// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

// 3분에 한번 실행
properties([pipelineTriggers([cron('H/3 * * * *')])])

node {
    stage("Price Action") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/batch/batchproc.asp?mallid=nvstorefarm&apiAction=PRICE&topN=100",
                                    validResponseCodes: "200:499"
            def responseText = response.content
            if (responseText != ""){
                def responseTextArr = responseText.split(",")
                for(i=0; i < responseTextArr.length; i++){
                    def itemArr = responseTextArr[i].split("##")[0]
                    def idxArr = responseTextArr[i].split("##")[1]

                    def responseProc = httpRequest url: baseUrl + "/outmall/proc/NvstorefarmProc.asp?redSsnKey=system&jenkinsBatchYn=Y&mallid=nvstorefarm&action=PRICE&idx="+idxArr+"&itemid="+itemArr,
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
    // 2023-10-06 위 가격 큐가 많이 쌓여서 아래 일단 주석
    // stage("EDITBATCH Action") {
    //     try {
    //         def baseUrl = "http://wapi.10x10.co.kr"
    //         def response = httpRequest url: baseUrl + "/outmall/batch/batchproc.asp?mallid=nvstorefarm&apiAction=EDITBATCH&topN=100",
    //                                 validResponseCodes: "200:499"
    //         def responseText = response.content
    //         if (responseText != ""){
    //             def responseTextArr = responseText.split(",")
    //             for(i=0; i < responseTextArr.length; i++){
    //                 def itemArr = responseTextArr[i].split("##")[0]
    //                 def idxArr = responseTextArr[i].split("##")[1]

    //                 def responseProc = httpRequest url: baseUrl + "/outmall/proc/NvstorefarmProc.asp?redSsnKey=system&jenkinsBatchYn=Y&mallid=nvstorefarm&action=EDIT&idx="+idxArr+"&itemid="+itemArr,
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