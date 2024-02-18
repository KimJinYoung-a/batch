def reportSimple(String jandiUrl, String message) {
    def headers = [
        [name: "Accept", value: "application/vnd.tosslab.jandi-v2+json"],
        [name: "Content-Type", value: "application/json"]
        ]
    def body = """
{
    "body" : "${message}",
    "connectColor" : "#FAC11B",
    "connectInfo" : []
}
"""
    httpRequest url: jandiUrl,
                httpMode: 'POST',
                customHeaders: headers,
                requestBody: body
}

def reportSimpleCurl(String jandiUrl, String message) {
    sh "curl -X POST ${jandiUrl} " +
        "-H \"Accept: application/vnd.tosslab.jandi-v2+json\" " +
        "-H \"Content-Type: application/json\" " +
        "--data-binary '{\"body\":\"${message}\",\"connectColor\":\"\",\"connectInfo\":[]}'"
}

return this