// 전역 라이브러리 로드
@Library('tenbyten-library') _

node {
    // git clone을 하게되면 현재 위치가 루트 폴더가 됨.
    // credentialsId의 경우 미리 등록해둔 git 계정을 넣으면 됨.
    stage("git") {
        git url: "http://deployer@bitbucket.tenbyten.kr:7990/scm/prot/proto-spring-batch.git",
            branch: "master",
            credentialsId: "deployer"
    }

    stage("build") {
        // clean 이 있는건과 없는것에 시간 차이가 큼.
        // 없을때 5s 있을때 14s
        // sh "GRADLE_USER_HOME=/var/lib/jenkins/workspace/hello-spring-batch/.gradle ./gradlew clean"
        // GRADLE_USER_HOME을 셋팅하지 않으면 퍼미션이 없는곳에 쓰기 때문에 에러가 발생함.
        // 현재 폴더를 지정하면 퍼미션이 있기 때문에 에러가 발생 안함.
        sh "GRADLE_USER_HOME=./.gradle ./gradlew build"
        // readlink로 실행하기 위한 application.jar 심볼릭 링크 생성
        sh "ln -s \$(pwd)/build/libs/batchs.0.1.0.jar \$(pwd)/build/application.jar"
    }
}