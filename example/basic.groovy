// 전역 라이브러리 로드
@Library('tenbyten-library') _

// 5분에 한번 실행
properties([pipelineTriggers([cron('H/5 * * * *')])])

node {
    stage("Print Hello") {
        println("Hello")
    }
}