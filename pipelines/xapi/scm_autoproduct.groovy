//상품 자동승인및 수정 승인
@Library('tenbyten-library') _

import groovy.json.JsonSlurper

properties([buildDiscarder(logRotator(daysToKeepStr: '100', numToKeepStr: '100')),pipelineTriggers([cron('10 8,17 * * *')])])

node {    
    stage("SCM AutoApproval") {
        try
        {
            def app1 ="http://110.93.128.100:8090/scmapi/nsqmessage/brandreg?maekrid=cannotsend"
            //def app2 ="http://110.93.128.100:8090/scmapi/nsqmessage/brandreg?maekrid=hplusmall12"
            def app3 ="http://110.93.128.100:8090/scmapi/nsqmessage/brandreg?maekrid=buyest"
            def app4 ="http://110.93.128.100:8090/scmapi/nsqmessage/brandreg?maekrid=delilary"
            def app5 ="http://110.93.128.100:8090/scmapi/nsqmessage/brandreg?maekrid=strawberrynet"

            def container ="http://110.93.128.100:8090/scmapi/nsqmessage/containcollect"

            def updateapproval ="http://110.93.128.100:8090/scmapi/product/updateapproval?makerid=strawberrynet"
            def noti ="http://110.93.128.100:8090/scmapi/nsqmessage/proregreport"
            
            def response =httpRequest app1 
            //def response2 =httpRequest app2
            def response3 =httpRequest app3
            def response4 =httpRequest app4
            def response5 =httpRequest app5
            def response6 =httpRequest container
            def response7 =httpRequest updateapproval
            def response8 =httpRequest noti 
        }
        catch(e){
            def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/6e1510e5775343308c2b0c98e61b6362"
            def message ="SCM Auto "+e 
            def jandiReporter = new JandiReporter()
            jandiReporter.reportSimple(jandiUrl, message)
            throw e
        }
    } 
}