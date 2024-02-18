//업체 게시판 알림
@Library('tenbyten-library') _

import groovy.json.JsonSlurper
//5분마다 실행
properties([buildDiscarder(logRotator(daysToKeepStr: '100', numToKeepStr: '100')),pipelineTriggers([cron('H/10 * * * *')])])



node {    
    stage("SCM Upche") {
        try
        {
            def lfsate ="http://110.93.128.100:8090/scmapi/boards/upcheboard"
            
            def response =httpRequest lfsate//"http://xapi.10x10.co.kr:8080/qnacollect/cs/11getqna"
            def mem = response.content 
             
        }
        catch(e){
            def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/6e1510e5775343308c2b0c98e61b6362"
            def message ="SCM Upche "+e 
            def jandiReporter = new JandiReporter()
            jandiReporter.reportSimple(jandiUrl, message)
            throw e
        }
    } 
}