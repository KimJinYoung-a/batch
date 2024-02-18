@Library('tenbyten-library') _

import groovy.json.JsonSlurper
//15분마다 실행
properties([pipelineTriggers([cron('H/15 * * * *')])])

node {    
    stage("Radio Heatbeat") {
        try
        {
            def response =httpRequest "http://radio.10x10.co.kr/api/common"            
            def mem = response.content
            def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/6e1510e5775343308c2b0c98e61b6362"
            def message =  "Radio HeatBeat Complete"//"Radio Heatbeat : "+mem 
            def jandiReporter = new JandiReporter()
            jandiReporter.reportSimple(jandiUrl, message)
        }
        catch(e){
            def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/6e1510e5775343308c2b0c98e61b6362"
            def message = "Fail Radio Heatbeat"
            def jandiReporter = new JandiReporter()
            jandiReporter.reportSimple(jandiUrl, message)
            throw e
        }
    } 
}