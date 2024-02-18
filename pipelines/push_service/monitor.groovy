

// 월 9시 15분
properties([pipelineTriggers([cron('15 9 * * 1')])])

node {
    stage("ansible") {
        // 환경변수로 정의한 값을 사용함.
        // cmd ansible-playbook 명령어 위치
        // host ansible host 파일 위치
        // monitor ansible 플레이북 스크립트 위치
        sh "${cmd} ${host} ${monitor}"
    }
}