//대기 상품 체크
@Library('tenbyten-library') _

import groovy.json.JsonSlurper
//20분마다 실행

properties([buildDiscarder(logRotator(daysToKeepStr: '100', numToKeepStr: '100')),pipelineTriggers([cron('H/5 * * * *')])])

node {    
    stage("LFMALL QNA Collect") {
        try
        {
            def lfqnaget ="http://110.93.128.100:8090/lfmall/info/GETQNA"
            def lfqnaadd ="http://110.93.128.100:8090/lfmall/info/ADDQNA"
            def lfsonjang ="http://110.93.128.100:8090/lfmall/order/updateship"
            
            def response =httpRequest lfqnaget
            def response1 =httpRequest lfqnaadd
            def response2 =httpRequest lfsonjang

            def mem = response.content 
            def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/6e1510e5775343308c2b0c98e61b6362"
            def message =  "lfmall Complete" //+mem// +"|"+mem1  
            def jandiReporter = new JandiReporter()
            //jandiReporter.reportSimple(jandiUrl, message)
        }
        catch(e){
            def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/6e1510e5775343308c2b0c98e61b6362"
            def message ="Fail lfmall QNA "+e 
            def jandiReporter = new JandiReporter()
            jandiReporter.reportSimple(jandiUrl, message)
            throw e
        }
    } 
}