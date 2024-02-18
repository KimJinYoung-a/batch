//LFMALL QUE Making
@Library('tenbyten-library') _

import groovy.json.JsonSlurper
//2시간마다  실행


properties([buildDiscarder(logRotator(daysToKeepStr: '100', numToKeepStr: '100')),pipelineTriggers([cron('H H/2 * * *')])])

//properties([pipelineTriggers([cron('30 2,3,4,5,6,7,8,19,20,21,22 * * *')])])

node {    
    stage("LFMALL ProductReg") {
        try
        {
            ///lfmall/info/quemaking
            def lfsate ="http://110.93.128.100:8090/lfmall/jobschedule/reg"
            
            def response =httpRequest lfsate//"http://xapi.10x10.co.kr:8080/qnacollect/cs/11getqna"
            def mem = response.content 
            def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/6e1510e5775343308c2b0c98e61b6362"
            def message =  "lfmall QUE Making" //+mem// +"|"+mem1  
            def jandiReporter = new JandiReporter()
            //jandiReporter.reportSimple(jandiUrl, message)
        }
        catch(e){
            def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/6e1510e5775343308c2b0c98e61b6362"
            def message ="Fail lfmall ProductReg "+e 
            
            def jandiReporter = new JandiReporter()
            jandiReporter.reportSimple(jandiUrl, message)
            throw e
        }
    } 
}