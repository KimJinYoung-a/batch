//LFMALL QUE Making
@Library('tenbyten-library') _

import groovy.json.JsonSlurper
//4시간마다  실행

properties([buildDiscarder(logRotator(daysToKeepStr: '100', numToKeepStr: '100')),pipelineTriggers([cron('H H/4 * * *')])])

//QUE 와 교환 반품

node {    
    stage("SABANGNET CS") {
        try
        {
            Date yetday = new Date().plus(30)
            Date date = new Date().plus(30)
            def today = new Date()
            def yesterday = today - 1 
            //교환반품 수집
            def returncs ="http://110.93.128.100:8090/Sabangnet/CS/claim?st="+yesterday.format("yyyyMMdd")+"&ed="+today.format("yyyyMMdd")
            def response1 =httpRequest returncs            
        }
        catch(e){
            def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/6e1510e5775343308c2b0c98e61b6362"
            def message ="Fail SABANGNET CS "+e 
            def jandiReporter = new JandiReporter()
            jandiReporter.reportSimple(jandiUrl, message)
            throw e
        }
    } 
}