//state 넣기 

//LFMALL QUE Making
@Library('tenbyten-library') _

import groovy.json.JsonSlurper
//4시간마다  실행

properties([buildDiscarder(logRotator(daysToKeepStr: '100', numToKeepStr: '100')),pipelineTriggers([cron('0 20 * * *')])])

node {    
    stage("LFMALL STATE Making") {
        try
        {
            ///lfmall/info/quemaking
            def lfsate ="http://110.93.128.100:8090/lfmall/jobschedule/stateadd?state=3"            
            def response =httpRequest lfsate//"http://xapi.10x10.co.kr:8080/qnacollect/cs/11getqna"
            def mem = response.content 

            def lfsate1 ="http://110.93.128.100:8090/lfmall/jobschedule/stateadd?state=7"            
            def response1 =httpRequest lfsate1//"http://xapi.10x10.co.kr:8080/qnacollect/cs/11getqna"
            def mem1 = response1.content 


            def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/6e1510e5775343308c2b0c98e61b6362"
            def message =  "lfmall STATE ADD" //+mem// +"|"+mem1  
            def jandiReporter = new JandiReporter()
            //jandiReporter.reportSimple(jandiUrl, message)
        }
        catch(e){
            def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/6e1510e5775343308c2b0c98e61b6362"
            def message ="Fail lfmall STATE ADD"+e 
            def jandiReporter = new JandiReporter()
            jandiReporter.reportSimple(jandiUrl, message)
            throw e
        }
    } 
}