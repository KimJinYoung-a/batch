

// array partition
// https://stackoverflow.com/questions/2924395/how-to-split-a-list-into-equal-sized-lists-in-groovy
def partition(array, size) {
    def partitions = []
    int partitionCount = array.size() / size

    partitionCount.times { partitionNumber ->
        def start = partitionNumber * size
        def end = start + size - 1
        partitions << array[start..end]
    }

    if (array.size() % size) partitions << array[partitionCount * size..-1]
    return partitions
}

def task(array) {
    Random random = new Random()
    array.each { 
        println(it) 

        // 지정된 secodns만큼 sleep 한다.
        def s = random.nextInt(5) + 1
        httpRequest url: "http://localhost:8010/sleep?seconds=" + s,
                    httpMode: "GET",
                    validResponseCodes: "200:499"
    }
}

node {
    stage("parallel") {
        // array에 아이템 10개를 담는다.
        def array = []
        1.upto(10) {
            array.add(it)
        }

        // size 개수만큼 array를 쪼갠다.
        // [[1,2,3,4,5], [6,7,8,9,10]]
        def arrayParts = partition(array, Math.ceil(array.size() / 2))

        // array를 dict로 만든다.
        def dict = arrayParts.collectEntries {
            [it.hashCode(), { task(it) }]
        }

        // 병렬 처리를 한다.
        parallel dict
    }

    stage("after") {
        println("after")
    }
}