//11번가 QNA 가져오기 넣기 
@Library('tenbyten-library') _

import groovy.json.JsonSlurper
//3분마다 실행

properties([buildDiscarder(logRotator(daysToKeepStr: '100', numToKeepStr: '100')),pipelineTriggers([cron('H/3 * * * *')])])

node {    
    stage("Eleven QNA") {
        try
        {
            def eleget ="http://xapi.10x10.co.kr:8080/qnacollect/cs/11getqna"
            //def eleget ="http://xapi.10x10.co.kr:8080/api/Common/webhook"
            def eleadd ="http://xapi.10x10.co.kr:8080/qnacollect/cs/11addqna" 

            def response =httpRequest eleget//"http://xapi.10x10.co.kr:8080/qnacollect/cs/11getqna"
            def mem = response.content 
            def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/2fccb585ce3a66e5d0d440966513821b"

            def message =  "11 Get Complete" //+mem// +"|"+mem1

            def response1 =httpRequest eleadd//"http://xapi.10x10.co.kr:8080/qnacollect/cs/11getqna"
            def mem1 = response.content 
            message +=  "11 Add Complete" //+mem// +"|"+mem1

            def jandiReporter = new JandiReporter()
            //jandiReporter.reportSimple(jandiUrl, message)
        }
        catch(e){
            def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/6e1510e5775343308c2b0c98e61b6362"
            def message ="Fail 11st Heatbeat"+e 
            def jandiReporter = new JandiReporter()
            jandiReporter.reportSimple(jandiUrl, message)
            throw e
        }
    } 
}