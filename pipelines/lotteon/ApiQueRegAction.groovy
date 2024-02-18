// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

// 20분에 한번 실행
//properties([pipelineTriggers([cron('H/20 * * * *')])])
properties([pipelineTriggers([cron('H/20 02-23 * * *')])])

node {
    stage("REG Action") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/batch/batchproc.asp?mallid=lotteon&apiAction=REG&topN=100",
                                    validResponseCodes: "200:499"
            def responseText = response.content
            if (responseText != ""){
                def responseTextArr = responseText.split(",")
                for(i=0; i < responseTextArr.length; i++){
                    def itemArr = responseTextArr[i].split("##")[0]
                    def idxArr = responseTextArr[i].split("##")[1]

                    def responseProc = httpRequest url: baseUrl + "/outmall/proc/LotteonProc.asp?redSsnKey=system&jenkinsBatchYn=Y&mallid=lotteon&action=REG&idx="+idxArr+"&itemid="+itemArr,
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