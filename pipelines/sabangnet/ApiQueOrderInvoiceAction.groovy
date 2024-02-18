// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

// 10분에 한번 실행
properties([pipelineTriggers([cron('H/10 * * * *')])])

node {
    stage("yes24 Invoice Action") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/autojob/outMallAutoJob_songjang.asp?act=outmallSongJangIp&param1=yes24&redSsnKey=system",
                                    validResponseCodes: "200:499"
            println("Status: "+ response.status)
            println("Content: "+ response.content)

            if (response.status > 400) {
                throw new Exception("api error")
            }

        }catch(e) {
            throw e
        }
    }

    stage("alphamall Invoice Action") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/autojob/outMallAutoJob_songjang.asp?act=outmallSongJangIp&param1=alphamall&redSsnKey=system",
                                    validResponseCodes: "200:499"
            println("Status: "+ response.status)
            println("Content: "+ response.content)

            if (response.status > 400) {
                throw new Exception("api error")
            }

        }catch(e) {
            throw e
        }
    }

    stage("ohou1010 Invoice Action") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/autojob/outMallAutoJob_songjang.asp?act=outmallSongJangIp&param1=ohou1010&redSsnKey=system",
                                    validResponseCodes: "200:499"
            println("Status: "+ response.status)
            println("Content: "+ response.content)

            if (response.status > 400) {
                throw new Exception("api error")
            }

        }catch(e) {
            throw e
        }
    }

    stage("wadsmartstore Invoice Action") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/autojob/outMallAutoJob_songjang.asp?act=outmallSongJangIp&param1=wadsmartstore&redSsnKey=system",
                                    validResponseCodes: "200:499"
            println("Status: "+ response.status)
            println("Content: "+ response.content)

            if (response.status > 400) {
                throw new Exception("api error")
            }

        }catch(e) {
            throw e
        }
    }

    stage("casamia Invoice Action") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/autojob/outMallAutoJob_songjang.asp?act=outmallSongJangIp&param1=casamia_good_com&redSsnKey=system",
                                    validResponseCodes: "200:499"
            println("Status: "+ response.status)
            println("Content: "+ response.content)

            if (response.status > 400) {
                throw new Exception("api error")
            }

        }catch(e) {
            throw e
        }
    }

    stage("Wconcept Invoice Action") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/autojob/outMallAutoJob_songjang.asp?act=outmallSongJangIp&param1=wconcept1010&redSsnKey=system",
                                    validResponseCodes: "200:499"
            println("Status: "+ response.status)
            println("Content: "+ response.content)

            if (response.status > 400) {
                throw new Exception("api error")
            }

        }catch(e) {
            throw e
        }
    }

    stage("withnature Invoice Action") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/autojob/outMallAutoJob_songjang.asp?act=outmallSongJangIp&param1=withnature1010&redSsnKey=system",
                                    validResponseCodes: "200:499"
            println("Status: "+ response.status)
            println("Content: "+ response.content)

            if (response.status > 400) {
                throw new Exception("api error")
            }

        }catch(e) {
            throw e
        }
    }

    stage("goodshop Invoice Action") {
        try {
            def baseUrl = "http://wapi.10x10.co.kr"
            def response = httpRequest url: baseUrl + "/outmall/autojob/outMallAutoJob_songjang.asp?act=outmallSongJangIp&param1=goodshop1010&redSsnKey=system",
                                    validResponseCodes: "200:499"
            println("Status: "+ response.status)
            println("Content: "+ response.content)

            if (response.status > 400) {
                throw new Exception("api error")
            }

        }catch(e) {
            throw e
        }
    }
}