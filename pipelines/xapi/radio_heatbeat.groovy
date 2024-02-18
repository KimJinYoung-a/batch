//라디오 heatbeat

@Library('tenbyten-library') _

import groovy.json.JsonSlurper
//15분마다 실행

properties([buildDiscarder(logRotator(daysToKeepStr: '100', numToKeepStr: '100')),pipelineTriggers([cron('H/15 * * * *')])])


node {    
    stage("Radio Heatbeat") {
        try
        {
            def response =httpRequest "http://radio.10x10.co.kr/api/common"            
            def mem = response.content
            def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/6e1510e5775343308c2b0c98e61b6362"
            def message =  "Radio HeatBeat #1"//"Radio Heatbeat : "+mem 
            def jandiReporter = new JandiReporter()
            //jandiReporter.reportSimple(jandiUrl, message)
        }
        catch(e){
            def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/6e1510e5775343308c2b0c98e61b6362"
            def message = "Fail Radio Heatbeat"
            def jandiReporter = new JandiReporter()
            //jandiReporter.reportSimple(jandiUrl, message)
            throw e
        }
    } 
}
