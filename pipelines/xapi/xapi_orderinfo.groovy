///주문정보

//위메프 qna
@Library('tenbyten-library') _

import groovy.json.JsonSlurper
//15분마다 실행

properties([buildDiscarder(logRotator(daysToKeepStr: '100', numToKeepStr: '100')),pipelineTriggers([cron('H/3 * * * *')])])

node {    
    stage("ORDER") {
        try
        {
            def response =httpRequest "http://xapi.10x10.co.kr:8080/api/common/ordercollect"            
            def mem = response.content
            def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/6e1510e5775343308c2b0c98e61b6362"
            def message =  "Order HeatBeat Complete"//"Radio Heatbeat : "+mem 
            def jandiReporter = new JandiReporter()
            //jandiReporter.reportSimple(jandiUrl, message)
        }
        catch(e){
            def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/6e1510e5775343308c2b0c98e61b6362"
            def message = "Fail Order Heatbeat "+e
            def jandiReporter = new JandiReporter()
            jandiReporter.reportSimple(jandiUrl, message)
            throw e
        }
    } 
}
