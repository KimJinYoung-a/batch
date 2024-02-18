node {
    stage("timeout test") {
        // defaults to minutes.
        // NANOSECONDS, MICROSECONDS, MILLISECONDS, SECONDS, MINUTES, HOURS, DAYS
        // If the time limit is reached, an exception (org.jenkinsci.plugins.workflow.steps.FlowInterruptedException) is thrown
        try {
            timeout(time: 2, unit: 'SECONDS') {
                sleep(3)
            }
        } catch(e) {
            println("catch")
            println(e)
        }
    }

    // timeout을 try catch하지 않으면 위에서 에러가 발생하고 더이상 진행되지 않음.
    stage("after timeout") {
        println("after timeout!!!!!!")
    }
}