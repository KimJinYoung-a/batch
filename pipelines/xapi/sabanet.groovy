//
@Library('tenbyten-library') _

import groovy.json.JsonSlurper
//4분마다


properties([buildDiscarder(logRotator(daysToKeepStr: '100', numToKeepStr: '100')),pipelineTriggers([cron('H/4 * * * *')])])

node {    
    stage("SABANET QNA") {
        try
        {
            def sabanget ="http://xapi.10x10.co.kr:8080/sabanet/cs"
            def sabanpost ="http://xapi.10x10.co.kr:8080/sabanet/cs"

            httpRequest sabanget//"http://xapi.10x10.co.kr:8080/qnacollect/cs/11getqna"
            //def mem = response.content              

            httpRequest url: sabanpost,httpMode: 'POST',timeout:6000
                       
            def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/6e1510e5775343308c2b0c98e61b6362"
            def message =  "사방넷 완료"
            def jandiReporter = new JandiReporter() 
            ///jandiReporter.reportSimpleCurl(jandiUrl, message)
        }
        catch(e){
            def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/6e1510e5775343308c2b0c98e61b6362"
            def message ="Sabanet Fail "+e 
            def jandiReporter = new JandiReporter()
            jandiReporter.reportSimple(jandiUrl, message)
            throw e
        }
    } 
}