node {
    stage("stage 1") {
        // 각각의 작업은 비동기로 동작한다
        // Timeout1에서 exception이 발생해도 Timeout2는 작업을 실행한다.
        // 한개라도 작업에서 오류가 발생하면 전체 테스크가 실패한다.
        parallel Timeout1: {
            timeout(time:1, unit: 'SECONDS') {
                println("time1 start")
                sleep(2)
            }
        },
        Timeout2: {
            sleep(2)
            timeout(time:2, unit: 'SECONDS') {
                println("time2 start")
                sleep(1)
                println("time2 end")
            }
        }
    }

    stage("after") {
        println("after")
    }
}