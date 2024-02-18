@Library('tenbyten-library') _

// todo 테스트할때는 주석처리
// properties([pipelineTriggers([cron('H/5 * * * *')])])

// data가 null이면 안됨.
def requestAndLog(url, data) {
    // println("Request : " + url)
    // https://jenkins.io/doc/pipeline/steps/http_request/
    // objHttp.Open "POST", call_url, False
    // objHttp.setRequestHeader "Connection", "close"
    // objHttp.setRequestHeader "Content-Length", Len(sedata)
    // objHttp.setRequestHeader "Content-Type", "application/x-www-form-urlencoded"
    // objHttp.setTimeouts 5000,90000,90000,90000
    // objHttp.Send  sedata
    def response = httpRequest url: url,
                               httpMode: "POST",
                               customHeaders: [
                                   [
                                       "name": "Connection",
                                       "value": "close"
                                   ],
                                   [
                                       "name": "Content-Type",
                                       "value": "application/x-www-form-urlencoded"
                                   ]
                               ],
                               timeout: 90000,
                               requestBody: data,
                               // 200~399가 아닐경우 exception이 발생해서 200~599까지 범위를 늘림
                               validResponseCodes: "200:599"
    // println("Status: "+ response.status)
    // println("Content: "+ response.content)

    // '오류가 있거나 (오류가 없을경우 err.number가 0 값을 돌림) status 값이 200 (로딩 성공) 이 아닐경우
    // if err.number <> 0 or status <> 200 then
    //       if status = 404 then
    //             ret_txt = "[404]존재하지 않는 페이지 입니다."
    //       elseif status >= 401 and status < 402 then
    //             ret_txt = "[401]접근이 금지된 페이지 입니다."
    //       elseif status >= 500 and status <= 600 then
    //             ret_txt = "[500]내부 서버 오류 입니다."
    //       else
    //             ret_txt = "[err]서버가 다운되었거나 올바른 경로가 아닙니다."
    //       end if
    // '오류가 없음 (문서를 성공적으로 로딩함)
    // else
    //       ret_txt = objHttp.ResponseBody
    // end if
    switch(response.status) {
        case 404:
            println("[404]존재하지 않는 페이지 입니다.")
            break
        case 401:
            println("[401]접근이 금지된 페이지 입니다.")
            break
        case 500..600:
            println("[500]내부 서버 오류 입니다.")
            break
        // default: println("[err]서버가 다운되었거나 올바른 경로가 아닙니다.")
    }

    if (response.status >= 400) {
        // def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/e22a7280497b36b0472934b78bdd1b6e"
        // def jandiReporter = new JandiReporter()
        // jandiReporter.reportSimple(jandiUrl, "$BUILD_URL" + "\\n" + url + "\\n" + data + "\\n") 
        // throw new Exception("Url 호출 실패")
    }
}

def baseUrl = "http://local.10x10.co.kr:5000"

node {
    // 이 방식으로 하면 로직을 좀더 잘 짤수 있음.
    // def stageJobs = [
    //     [
    //         name: "승인내역 가져오기 INI",
    //         url: baseUrl + "/",
    //         data: "mode=getonpgdata&redSsnKey=system"
    //     ],
    //     [
    //         name: "승인내역 가져오기 INI HP(PREV MONTH)",
    //         url: baseUrl + "/",
    //         data: "mode=getonpgdatahp&redSsnKey=system"
    //     ]
    // ]

    // https://stackoverflow.com/questions/48471101/set-a-stage-status-in-jenkins-pipelines/48472468
    // SUCCESS, FAILURE, NOT_EXECUTED, ABORTED
    // stageJobs.each {
    //     stage(it.name) {
    //         // try..catch는 error가 발생했을때의 후속처리가 가능함.
    //         try {
    //             requestAndLog(it.url, it.data)
    //         } catch(Exception e) {
    //             def jandiUrl = "https://wh.jandi.com/connect-api/webhook/15400820/e22a7280497b36b0472934b78bdd1b6e"
    //             def jandiReporter = new JandiReporter()
    //             jandiReporter.reportSimple(jandiUrl, it.name + "\\n" + "$BUILD_URL" + "\\n" + it.url + "\\n" + it.data + "\\n") 

    //             unstable "unstable~~"
    //         }
    //         // catchError은 error이 발생했을때의 후속 처리가 어려움.
    //         // catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
    //         // }
    //     }
    // }


    // 이 방식은 많은 중복이 발생하고, 좋은 코드를 만들기가 좀더 어려움.
    stage("승인내역 가져오기 INI") {
        // SendReqPost("http://scm.10x10.co.kr/admin/maechul/pgdata/pgdata_process.asp","mode=getonpgdatahp&redSsnKey=system")
        requestAndLog(baseUrl + "/", "mode=getonpgdata&redSsnKey=system")
    }

    stage("승인내역 가져오기 INI HP") {
        // SendReqPost("http://scm.10x10.co.kr/admin/maechul/pgdata/pgdata_process.asp","mode=getonpgdatahp&redSsnKey=system")
        requestAndLog(baseUrl + "/", "mode=getonpgdatahp&redSsnKey=system")
    }

    stage("승인내역 가져오기 INI HP(PREV MONTH)") {
        def now = new Date()
        if (now.date == 2 || now.date == 3) {
            // SendReqPost("http://scm.10x10.co.kr/admin/maechul/pgdata/pgdata_process.asp","mode=getonpgdatahppre&redSsnKey=system")
            requestAndLog(baseUrl + "/", "mode=getonpgdatahppre&redSsnKey=system")
            // SendReqPost("http://scm.10x10.co.kr/admin/maechul/pgdata/pgdata_process.asp","mode=getonpgdatahp&redSsnKey=system&gubun=prevmonth")
            requestAndLog(baseUrl + "/", "mode=getonpgdatahp&redSsnKey=system&gubun=prevmonth")
        }
        else {
            println("Pass")
        }
    }

    stage("승인내역 가져오기 UPLUS") {
        // SendReqPost("http://scm.10x10.co.kr/admin/maechul/pgdata/pgdata_process.asp","mode=getonpgdatauplus&redSsnKey=system")
        requestAndLog(baseUrl + "/", "mode=getonpgdatauplus&redSsnKey=system")
    }

    stage("승인내역 가져오기 NAVERPAY") {
        // SendReqPost("http://wapi.10x10.co.kr/nPay/jungsanReceive.asp","mode=npayget&redSsnKey=system")
        requestAndLog(baseUrl + "/", "mode=npayget&redSsnKey=system")
    }

    stage("승인내역 가져오기 payco") {
        // SendReqPost("http://scm.10x10.co.kr/admin/maechul/pgdata/pgdata_process.asp","mode=getpaycoT&redSsnKey=system")
        requestAndLog(baseUrl + "/", "mode=getpaycoT&redSsnKey=system")
    }

    stage("정산내역 가져오기 payco") {
        // SendReqPost("http://scm.10x10.co.kr/admin/maechul/pgdata/pgdata_process.asp","mode=getpaycoS&redSsnKey=system")
        requestAndLog(baseUrl + "/", "mode=getpaycoS&redSsnKey=system")
    }

    stage("승인내역 가져오기 newkakaopay") {
        // SendReqPost("http://scm.10x10.co.kr/admin/maechul/pgdata/pgdata_process.asp","mode=getonpgdatanewkakaopayT&redSsnKey=system")
        requestAndLog(baseUrl + "/", "mode=getonpgdatanewkakaopayT&redSsnKey=system")
    }

    stage("정산내역 가져오기 newkakaopay") {
        // SendReqPost("http://scm.10x10.co.kr/admin/maechul/pgdata/pgdata_process.asp","mode=getonpgdatanewkakaopayS&redSsnKey=system")
        requestAndLog(baseUrl + "/", "mode=getonpgdatanewkakaopayS&redSsnKey=system")
    }
    stage("승인내역 가져오기 TOSS") {
        // SendReqPost("http://wapi.10x10.co.kr/toss/api.asp","mode=settle&redSsnKey=system")
        requestAndLog(baseUrl + "/", "mode=settle&redSsnKey=system")
    }

    stage("리포트") {
        // Call MailCDO("aaaaa@10x10.co.kr;bbbbb@10x10.co.kr;ccccc@10x10.co.kr;ddddd@gmail.com","admin@10x10.co.kr","승인내역 가져오기 작업보고" & Now(),replace(pLogAll,vbcrlf,"<br>"))
        // requestAndLog(baseUrl + "/")
        // todo 구현하시오
        println("MailCDO")
    }
}