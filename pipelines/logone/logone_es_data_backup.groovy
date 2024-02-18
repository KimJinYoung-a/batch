
@Library('tenbyten-library') _

// 매일(월-금) 9시 30분
properties([pipelineTriggers([cron('30 9 * * 1-5')])])

def checkResult(out) {
	if (out.contains('"accepted":true')) {
		print("Success")
		print(out)
	} else {
		print("Fail")
		print(out)
		throw new Exception("backup failed")
	}
}

node {
	def now = new Date()
	def formatDateString = now.format("yyyyMMdd", TimeZone.getTimeZone('UTC'))

    stage("v8 data backup") {
		def out = sh(returnStdout: true, script: "curl -XPUT -H \"Content-type: application/json\" -d '{ \"indices\": \"*-v8-*\", \"ignore_unavailable\": false,  \"include_global_state\": false, \"partial\": false }' 'http://172.16.0.218:9200/_snapshot/logonedata/backup_$formatDateString'")
		checkResult(out.trim())
    }	
}

