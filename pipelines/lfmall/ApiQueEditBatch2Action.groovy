@Library('tenbyten-library') _

properties([pipelineTriggers([cron('H/10 02-23 * * *')])])

node {
   stage("EDITBATCH1 Action") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/batch/batchproc.asp?mallid=lfmall&apiAction=EDITBATCH&topN=100",
                                    validResponseCodes: "200:599"
            def responseText = response.content
            if (responseText != ""){
                def responseTextArr = responseText.split(",")
                for(i=0; i < responseTextArr.length; i++){
                    def itemArr = responseTextArr[i].split("##")[0]
                    def idxArr = responseTextArr[i].split("##")[1]

                    def responseProc = httpRequest url: baseUrl + "/outmall/proc/lfmallProc.asp?redSsnKey=system&jenkinsBatchYn=Y&mallid=lfmall&action=EDIT&idx="+idxArr+"&itemid="+itemArr,
                                            validResponseCodes: "200:599"

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
   stage("EDITBATCH2 Action") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/batch/batchproc.asp?mallid=lfmall&apiAction=EDITBATCH&topN=100",
                                    validResponseCodes: "200:599"
            def responseText = response.content
            if (responseText != ""){
                def responseTextArr = responseText.split(",")
                for(i=0; i < responseTextArr.length; i++){
                    def itemArr = responseTextArr[i].split("##")[0]
                    def idxArr = responseTextArr[i].split("##")[1]

                    def responseProc = httpRequest url: baseUrl + "/outmall/proc/lfmallProc.asp?redSsnKey=system&jenkinsBatchYn=Y&mallid=lfmall&action=EDIT&idx="+idxArr+"&itemid="+itemArr,
                                            validResponseCodes: "200:599"

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