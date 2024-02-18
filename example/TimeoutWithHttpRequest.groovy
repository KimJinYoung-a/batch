


node {
    stage("timeout method") {
        try {
            // 2초 타임아웃
            timeout(time: 2, unit: 'SECONDS') {
                // 3초 작업
                httpRequest url: "http://localhost:8010/sleep?seconds=3",
                            httpMode: "GET",
                            validResponseCodes: "200:499"

            }
        } catch(e) {
            println("==========timeout exception==============")
            println(e)
        }
    }

    stage("httpRequest timeout options") {
        // timeout이 발생하면 408이 발생하므로 벨리드 코드를 408까지 포함하도록 함.
        // 벨리드 코드에 포함되지 않으면 exception이 발생함.
        httpRequest url: "http://localhost:8010/sleep?seconds=3",
            httpMode: "GET",
            timeout: 2,
            validResponseCodes: "200:499"

    }

    stage("after timeout") {
        println("after timeout!!!!!!")
    }
}