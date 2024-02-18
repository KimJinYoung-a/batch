node {
    stage("run") {
        sh "${JAVA_11} -jar \$(readlink ${TENBYTEN_BATCH_PATH}) --job.name=SimpleJob"
    }
}