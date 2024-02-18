//LFMALL QUE Making
@Library('tenbyten-library') _

import groovy.json.JsonSlurper
//2시간마다  실행

properties([buildDiscarder(logRotator(daysToKeepStr: '100', numToKeepStr: '100')),pipelineTriggers([cron('H H/2 * * *')])])

//QUE 와 교환 반품

node {    
    stage("LFMALL QUE Making") {
        try
        {
            Date yetday = new Date().plus(30)
            Date date = new Date().plus(30)

            def today = new Date()
            def yesterday = today - 1 

            ///lfmall/info/quemaking
            def lfsate ="http://110.93.128.100:8090/lfmall/info/quemaking"
            

            //교환반품 알림  /lfmall/order/ordercsmanager
            def returncs ="http://110.93.128.100:8090/lfmall/order/ordercsmanager?startdate="+yesterday.format("yyyyMMdd")+"&enddate="+today.format("yyyyMMdd")
            def response1 =httpRequest returncs

            def response =httpRequest lfsate//"http://xapi.10x10.co.kr:8080/qnacollect/cs/11getqna"
            def mem = response.content 
            def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/6e1510e5775343308c2b0c98e61b6362"
            def message =  "lfmall QUE Making exchange return" //+mem// +"|"+mem1  
            def jandiReporter = new JandiReporter()
            //jandiReporter.reportSimple(jandiUrl, message)
        }
        catch(e){
            def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/6e1510e5775343308c2b0c98e61b6362"
            def message ="Fail lfmall QUE RE EX "+e 
            def jandiReporter = new JandiReporter()
            jandiReporter.reportSimple(jandiUrl, message)
            throw e
        }
    } 
}