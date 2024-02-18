// 상품 변경사항이 있을때 api que를 생성함.
@Library('tenbyten-library') _

properties([pipelineTriggers([cron('35 8,10,13,16 * * *')])])

node {
    stage("Order D-4 Action") {
        try {

            def today = new Date()
            def yesterday = today - 4

            def baseUrl = "http://110.93.128.100:8090"
            def response = httpRequest url: baseUrl + "/lfmall/order/ordermanage?startdate="+yesterday.format("yyyyMMdd")+"&enddate="+yesterday.format("yyyyMMdd")+"&ordergubun=30",
                                    timeout:6000,
                                    validResponseCodes: "200:599"
            println("Status: "+ response.status)
            println("Content: "+ response.content)

            if (response.status > 400) {
                throw new Exception("api error")
            }
        }catch(e) {
            throw e
        }
    }

    stage("Order D-3 Action") {
        try {

            def today = new Date()
            def yesterday = today - 3

            def baseUrl = "http://110.93.128.100:8090"
            def response = httpRequest url: baseUrl + "/lfmall/order/ordermanage?startdate="+yesterday.format("yyyyMMdd")+"&enddate="+yesterday.format("yyyyMMdd")+"&ordergubun=30",
                                    timeout:6000,
                                    validResponseCodes: "200:599"
            println("Status: "+ response.status)
            println("Content: "+ response.content)

            if (response.status > 400) {
                throw new Exception("api error")
            }
        }catch(e) {
            throw e
        }
    }

    stage("Order D-2 Action") {
        try {

            def today = new Date()
            def yesterday = today - 2

            def baseUrl = "http://110.93.128.100:8090"
            def response = httpRequest url: baseUrl + "/lfmall/order/ordermanage?startdate="+yesterday.format("yyyyMMdd")+"&enddate="+yesterday.format("yyyyMMdd")+"&ordergubun=30",
                                    timeout:6000,
                                    validResponseCodes: "200:599"
            println("Status: "+ response.status)
            println("Content: "+ response.content)

            if (response.status > 400) {
                throw new Exception("api error")
            }
        }catch(e) {
            throw e
        }
    }

    stage("Order D-1 Action") {
        try {

            def today = new Date()
            def yesterday = today - 1

            def baseUrl = "http://110.93.128.100:8090"
            def response = httpRequest url: baseUrl + "/lfmall/order/ordermanage?startdate="+yesterday.format("yyyyMMdd")+"&enddate="+yesterday.format("yyyyMMdd")+"&ordergubun=30",
                                    timeout:6000,
                                    validResponseCodes: "200:599"
            println("Status: "+ response.status)
            println("Content: "+ response.content)

            if (response.status > 400) {
                throw new Exception("api error")
            }
        }catch(e) {
            throw e
        }
    }

    stage("Order D-DAY Action") {
        try {

            def today = new Date()

            def baseUrl = "http://110.93.128.100:8090"
            def response = httpRequest url: baseUrl + "/lfmall/order/ordermanage?startdate="+today.format("yyyyMMdd")+"&enddate="+today.format("yyyyMMdd")+"&ordergubun=30",
                                    timeout:6000,
                                    validResponseCodes: "200:599"
            println("Status: "+ response.status)
            println("Content: "+ response.content)

            if (response.status > 400) {
                throw new Exception("api error")
            }
        }catch(e) {
            throw e
        }
    }
}