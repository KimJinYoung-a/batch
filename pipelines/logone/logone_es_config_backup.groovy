
@Library('tenbyten-library') _

// 매주 월요일 9시 30분
properties([pipelineTriggers([cron('30 9 * * 1')])])

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

    stage("alerting") {
		def out = sh(returnStdout: true, script: "curl -XPUT -H \"Content-type: application/json\" -d '{ \"indices\": \"*.opendistro-alerting-*\", \"ignore_unavailable\": false,  \"include_global_state\": false, \"partial\": false }' 'http://172.16.0.218:9200/_snapshot/esconfig/alerting_$formatDateString'")
		checkResult(out.trim())
    }

	stage("ism") {
		def out = sh(returnStdout: true, script: "curl -XPUT -H \"Content-type: application/json\" -d '{ \"indices\": \"*.opendistro-ism-*\", \"ignore_unavailable\": false,  \"include_global_state\": false, \"partial\": false }' 'http://172.16.0.218:9200/_snapshot/esconfig/ism_$formatDateString'")
		checkResult(out.trim())
	}

	stage("anomaly") {
		def out = sh(returnStdout: true, script: "curl -XPUT -H \"Content-type: application/json\" -d '{ \"indices\": \"*.opendistro-anomaly-*\", \"ignore_unavailable\": false,  \"include_global_state\": false, \"partial\": false }' 'http://172.16.0.218:9200/_snapshot/esconfig/anomaly_$formatDateString'")
		checkResult(out.trim())
	}
}

