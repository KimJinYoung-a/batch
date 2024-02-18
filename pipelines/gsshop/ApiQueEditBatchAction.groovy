// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

// 3분에 한번 실행
properties([pipelineTriggers([cron('H/3 02-23 * * *')])])

node {
    stage("EDITBATCH Action") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/batch/batchproc.asp?mallid=gsshop&apiAction=EDITBATCH&topN=100",
                                    validResponseCodes: "200:499"
            def responseText = response.content
            if (responseText != ""){
                def responseTextArr = responseText.split(",")
                for(i=0; i < responseTextArr.length; i++){
                    def itemArr = responseTextArr[i].split("##")[0]
                    def idxArr = responseTextArr[i].split("##")[1]
                    def responseProc = httpRequest url: baseUrl + "/outmall/proc/GSShopProc.asp?redSsnKey=system&jenkinsBatchYn=Y&mallid=gsshop&action=EDIT&idx="+idxArr+"&itemid="+itemArr,
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

    // //수정할꺼 좀 많아서 배치를 수정으로 쓰자;..나중에 배치처리할 때 이하 주석처리하고 위 주석처리 해제하기
    // stage("EDITBATCH Action") {
    //     try {
    //         def baseUrl = "http://wapi.10x10.co.kr"
    //         def response = httpRequest url: baseUrl + "/outmall/batch/batchproc.asp?mallid=gsshop&apiAction=EDIT&topN=100",
    //                                 validResponseCodes: "200:499"
    //         def responseText = response.content
    //         if (responseText != ""){
    //             def responseTextArr = responseText.split(",")
    //             for(i=0; i < responseTextArr.length; i++){
    //                 def itemArr = responseTextArr[i].split("##")[0]
    //                 def idxArr = responseTextArr[i].split("##")[1]
    //                 def responseProc = httpRequest url: baseUrl + "/outmall/proc/GSShopProc.asp?redSsnKey=system&jenkinsBatchYn=Y&mallid=gsshop&action=EDIT&idx="+idxArr+"&itemid="+itemArr,
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