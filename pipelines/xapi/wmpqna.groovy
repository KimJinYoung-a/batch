//위메프 qna
@Library('tenbyten-library') _

import groovy.json.JsonSlurper
//15분마다 실행
properties([buildDiscarder(logRotator(daysToKeepStr: '100', numToKeepStr: '100')),pipelineTriggers([cron('H/3 * * * *')])])
 
node {    
    stage("WMP QNA") {
         try
        {
            def response =httpRequest "http://110.93.128.100:8090/wemake/cs/qna"
            def mem = response.content

            def response1 =httpRequest "http://110.93.128.100:8090/fwmp/cs/qna"
            def mem1 = response1.content

            def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/6e1510e5775343308c2b0c98e61b6362"
            def message =  "WMP HeatBeat #1"
            def jandiReporter = new JandiReporter()
            //jandiReporter.reportSimple(jandiUrl, message)
        }
        catch(e){
            def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/6e1510e5775343308c2b0c98e61b6362"
            def message = "Fail WMP QNA "+e
            def jandiReporter = new JandiReporter()
            jandiReporter.reportSimple(jandiUrl, message)
            throw e
        }
    } 
}