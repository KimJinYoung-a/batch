//대기 상품 체크
@Library('tenbyten-library') _

import groovy.json.JsonSlurper
//20분마다 실행

properties([buildDiscarder(logRotator(daysToKeepStr: '100', numToKeepStr: '100')),pipelineTriggers([cron('H/20 * * * *')])])

node {    
    stage("LFMALL CS Collect") {
        try
        {
            def lfsate ="http://110.93.128.100:8090/lfmall/info/updatestate/3"
            //송장업로드
            //def lfsonjang ="http://110.93.128.100:8090/lfmall/order/updateship"

            def response =httpRequest lfsate//"http://xapi.10x10.co.kr:8080/qnacollect/cs/11getqna"
            def mem = response.content 

            //def response1 =httpRequest lfsonjang//"http://xapi.10x10.co.kr:8080/qnacollect/cs/11getqna"
            //def mem1 = response1.content  

            def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/6e1510e5775343308c2b0c98e61b6362"
            def message =  "lfmall Complete" //+mem// +"|"+mem1  
            def jandiReporter = new JandiReporter()
            //jandiReporter.reportSimple(jandiUrl, message)
        }
        catch(e){
            def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/6e1510e5775343308c2b0c98e61b6362"
            def message ="Fail lfmall cs "+e 
            def jandiReporter = new JandiReporter()
            jandiReporter.reportSimple(jandiUrl, message)
            throw e
        }
    } 
}