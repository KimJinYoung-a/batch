import groovy.text.GStringTemplateEngine
import groovy.json.*

def getPort() {
    // https://stackoverflow.com/questions/30781016/groovy-string-tourl-is-deprecated-why-and-what-should-we-use-instead
    def jsonString = new URL("http://localhost:8500/v1/agent/health/service/name/spring-nomad-blue").text

    // https://groovy-lang.org/json.html
    def jsonSlurper = new JsonSlurper()
    def jsonObj = jsonSlurper.parseText(jsonString)
    def serviceObj = jsonObj[0]["Service"]

    def port = serviceObj["Port"] == 8081 ? "8082" : "8081"

    return port
}

node {
    stage("scm") {
        git url: "http://qpark99@bitbucket.tenbyten.kr:7990/scm/prot/proto-spring-web.git",
            branch: "h2",
            credentialsId: "qpark99"
    }

    stage("build") {
        // todo 되살리기
        //sh "GRADLE_USER_HOME=./.gradle ./gradlew bootJar"
        sh "cp build/libs/demo1-2.jar build/libs/demo1-${build_number}.jar"
    }

    stage("upload Jar file") {
        s3Upload consoleLogLevel: 'INFO',
                dontWaitForConcurrentBuildCompletion: false,
                entries: [[bucket: 'tenbyten-deploy',
                    excludedFile: '',
                    flatten: false,
                    gzipFiles: false,
                    keepForever: false,
                    managedArtifacts: false,
                    noUploadOnFailure: false,
                    selectedRegion: 'ap-northeast-2',
                    showDirectlyInBrowser: false,
                    sourceFile: 'build/libs/demo1-${build_number}.jar',
                    storageClass: 'STANDARD',
                    uploadFromSlave: false,
                    useServerSideEncryption: false]],
                pluginFailureResultConstraint: 'FAILURE',
                profileName: 'tenbyten-deploy',
                userMetadata: []
    }

    stage("nomad job run java.nomad") {
        // load port
        def port = getPort()

        // jenkins에 저장된 nomad 파일을 로드함.
        withCredentials([file(credentialsId: 'tenbyten-deplooy-java-nomad', variable: 'NOMAD_FILE')]) {
            // nomad template 파일을 빌드함.
            def javaNomadFile = readFile "${NOMAD_FILE}"
            def bindMap = [jarName: "demo1-${build_number}.jar", portNumber: port]
            def javaNomadText = new GStringTemplateEngine().createTemplate(javaNomadFile).make(bindMap).toString()
            // 빌드한 파일을 로컬에 저장함.
            writeFile file: 'java.nomad', text: javaNomadText

            // 빌드한 파일을 실행함.
            sh "/usr/local/bin/nomad job run java.nomad"
            sh "rm java.nomad"
        }
    }

    stage("lb setting") {
        // todo 아직 안함...
    }
}