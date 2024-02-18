// sandbox 체크박스를 해제하고 사용해야 쓸수 있음.
// https://github.com/jenkinsci/jenkins-scripts/blob/master/scriptler/disableEnableJobsMatchingPattern.groovy
import jenkins.model.*

// 오전 9시 정각에 실행함.
properties([pipelineTriggers([cron('0 9 * * *')])])

// Trigger을 생성하고, 마지막에 아래 코드를 실행하면 특정시간에 한번만 실행되는 배치를 실행할수 있음.
node {
    stage("Job Disable") {
        def matchedJobs = Jenkins.instance.items.findAll { job ->
            job.name == "Pipeline_Test1"
        }

        matchedJobs.each { job ->
            job.setDisabled(true)
        }
    }
}