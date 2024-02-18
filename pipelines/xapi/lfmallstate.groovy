//LFMALL 상태 업데이트
@Library('tenbyten-library') _

import groovy.json.JsonSlurper
//5분마다 실행
properties([buildDiscarder(logRotator(daysToKeepStr: '100', numToKeepStr: '100')),pipelineTriggers([cron('H/5 * * * *')])])



node {    
    stage("LFMALL STATE") {
        try
        {
            def lfsate ="http://110.93.128.100:8090/lfmall/jobschedule/state"
            
            def response =httpRequest lfsate//"http://xapi.10x10.co.kr:8080/qnacollect/cs/11getqna"
            def mem = response.content 
            def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/6e1510e5775343308c2b0c98e61b6362"
            def message =  "lfmall Complete" //+mem// +"|"+mem1  
            def jandiReporter = new JandiReporter()
            //jandiReporter.reportSimple(jandiUrl, message)
        }
        catch(e){
            def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/6e1510e5775343308c2b0c98e61b6362"
            def message ="LFMALL STATE "+e 
            def jandiReporter = new JandiReporter()
            jandiReporter.reportSimple(jandiUrl, message)
            throw e
        }
    } 
}