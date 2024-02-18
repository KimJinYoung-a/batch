import jenkins.model.*

def disableJob(String jobName) {
    def matchedJobs = Jenkins.instance.items.findAll { job ->
        job.name == jobName
    }

    matchedJobs.each { job ->
        job.setDisabled(true)
    }
}

return this