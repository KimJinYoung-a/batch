// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

// 02~23시까지 6분에 한번 실행
properties([pipelineTriggers([cron('H/6 02-23 * * *')])])

node {
    stage("Reg Action") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/batch/batchproc.asp?mallid=shintvshopping&apiAction=REGBATCH&topN=100",
                                    validResponseCodes: "200:499"
            def responseText = response.content
            if (responseText != ""){
                def responseTextArr = responseText.split(",")
                for(i=0; i < responseTextArr.length; i++){
                    def itemArr = responseTextArr[i].split("##")[0]
                    def idxArr = responseTextArr[i].split("##")[1]

                    def responseProc = httpRequest url: baseUrl + "/outmall/proc/shintvshoppingProc.asp?redSsnKey=system&jenkinsBatchYn=Y&mallid=shintvshopping&action=REGL4&idx="+idxArr+"&itemid="+itemArr,
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
    stage("Reg Action2") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/batch/batchproc.asp?mallid=shintvshopping&apiAction=REGBATCH&topN=100",
                                    validResponseCodes: "200:499"
            def responseText = response.content
            if (responseText != ""){
                def responseTextArr = responseText.split(",")
                for(i=0; i < responseTextArr.length; i++){
                    def itemArr = responseTextArr[i].split("##")[0]
                    def idxArr = responseTextArr[i].split("##")[1]

                    def responseProc = httpRequest url: baseUrl + "/outmall/proc/shintvshoppingProc.asp?redSsnKey=system&jenkinsBatchYn=Y&mallid=shintvshopping&action=REGL4&idx="+idxArr+"&itemid="+itemArr,
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
}