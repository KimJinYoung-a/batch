// 포맷이 다를경우 에러남.
// yyyy-MM-dd hh:mm
// 2019-11-11 01:10
// 지정한 시간보다 현재 시간이 크면 실행 가능해짐.
void startTrigger(Date now, String dateTimeString) {
    def triggerDate = Date.parse("yyyy-MM-dd hh:mm", dateTimeString)

    if (now < triggerDate) {
        currentBuild.rawBuild.result = Result.ABORTED
        println("now : " + now.format("yyyy-MM-dd hh:mm"))
        println("start date : " + triggerDate.format("yyyy-MM-dd hh:mm"))
        throw new hudson.AbortException('now < startDate 아직 실행될때가 아닙니다.')
    }
}

// 현재시간(now)이 지정한 시간(dateTimeString)보다 크면 프로젝트를 disable함.
// jobName의 경우 실행중인 프로젝트명과 동일해야 함.
// jobName이 프로젝트명과 일치하지 않으면 project disable는 안되지만 작업이 더이상 진행되진 않음.
void endTrigger(Date now, String dateTimeString, String jobName) {
    def triggerDate = Date.parse("yyyy-MM-dd hh:mm", dateTimeString)

    if (now > triggerDate) {
        currentBuild.rawBuild.result = Result.ABORTED
        println("now : " + now.format("yyyy-MM-dd hh:mm"))
        println("end date : " + triggerDate.format("yyyy-MM-dd hh:mm"))
        println(jobName + " Project Disalbe")

        def matchedJobs = Jenkins.instance.items.findAll { job ->
            job.name == jobName
        }

        matchedJobs.each { job ->
            job.setDisabled(true)
        }

        throw new hudson.AbortException('now > endDate 이제 실행되면 안됩니다.')
    }
}

return this