//lf 몰 주문 정보 수집

@Library('tenbyten-library') _

import groovy.json.JsonSlurper
//5분마다 실행
properties([buildDiscarder(logRotator(daysToKeepStr: '100', numToKeepStr: '100')),pipelineTriggers([cron('35 8,10,13,16 * * *')])])

node {
    stage("LFMALL Order Collect") {
        try
        {
            Date yetday = new Date().plus(30)
            Date date = new Date().plus(30)

            def today = new Date()
            def yesterday = today - 5

            def lfsate ="http://110.93.128.100:8090/lfmall/order/ordermanage?startdate="+yesterday.format("yyyyMMdd")+"&enddate="+today.format("yyyyMMdd")+"&ordergubun=30"
            //송장업로드
            def songjang ="http://110.93.128.100:8090/lfmall/order/updateship"
            def response1 =httpRequest songjang

            def response =httpRequest lfsate//"http://xapi.10x10.co.kr:8080/qnacollect/cs/11getqna"
            def mem = response.content

            def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/6e1510e5775343308c2b0c98e61b6362"
            def message =  "lfmall order songjang Complete" //+mem// +"|"+mem1
            def jandiReporter = new JandiReporter()
            //jandiReporter.reportSimple(jandiUrl, message)
        }
        catch(e){
            def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/6e1510e5775343308c2b0c98e61b6362"
            def message ="Fail order cs "+e
            def jandiReporter = new JandiReporter()
            jandiReporter.reportSimple(jandiUrl, message)
            throw e
        }
    }
}