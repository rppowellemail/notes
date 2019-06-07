Some notes experimenting with mock-server.com

http://mock-server.com/proxy/getting_started.html#start_mockserver

Service

`groovyRESTServer.groovy`

```
import com.sun.net.httpserver.HttpServer
import java.time.LocalDateTime
import groovy.json.JsonOutput
int PORT = 8081
HttpServer.create(new InetSocketAddress(PORT), /*max backlog*/ 0).with {
    println "Server is listening on ${PORT}, hit Ctrl+C to exit."    
    createContext("/helloworld") { http ->
        http.responseHeaders.add("Content-type", "text/plain")
        http.sendResponseHeaders(200, 0)
        http.responseBody.withWriter { out ->
            out << "Hello ${http.remoteAddress.hostName}!\n"
        }
        println "Hit from Host: ${http.remoteAddress.hostName} on port: ${http.remoteAddress.holder.port} for /helloworld"
    }
    createContext("/test") { http ->
        http.responseHeaders.add("Content-type", "application/json")
        http.sendResponseHeaders(200, 0)

        http.responseBody.withWriter { out ->
            //out << "Hello ${http.remoteAddress.hostName}!\n"
            out << JsonOutput.toJson([message:"Hello ${http.remoteAddress.hostName}!", timedatestamp: LocalDateTime.now().toString()])
        }
        println "Hit from Host: ${http.remoteAddress.hostName} on port: ${http.remoteAddress.holder.port} for /test"
    }
    start()
}
```

Running the mock-server (command line)

```
java -jar mockserver-netty-5.5.4-jar-with-dependencies.jar -serverPort 1080 -proxyRemotePort 8081 -logLevel DEBUG
```

```
curl -s 127.0.0.1:8081
{"message":"Hello 127.0.0.1!","timedatestamp":"2019-06-06T17:34:42.200814600"}
```

Adding expectations

```
curl -v -X PUT "http://localhost:8080/mockserver/expectation" -d '{
  "httpRequest" : {
    "path" : "/test2"
  },
  "httpResponse" : {
    "body" : "some_response_body"
  }
}'
```

```
curl -v -X PUT "http://localhost:1080/mockserver/expectation" -d '{
    "httpRequest": {
        "path": "/some/path"
    },
    "httpResponseTemplate": {
        "template": "return { statusCode: 200, cookies: { session: request.headers[\"Session-Id\"] && request.headers[\"Session-Id\"][0] }, headers: { Date: [ Date() ] }, body: JSON.stringify({method: request.method, path: request.path, body: request.body}) };",
        "templateType": "JAVASCRIPT"
    }
}'
```


Retrieve expectations/logs

```
curl -v -X PUT "http://localhost:1080/mockserver/retrieve?type=REQUESTS"
```

```
curl -v -X PUT "http://localhost:1080/mockserver/retrieve?type=ACTIVE_EXPECTATIONS"
```


Clear logs for expectation

```
curl -v -X PUT "http://localhost:1080/mockserver/clear?type=LOGS" -d '{ "path": "/test2" }'
```

Reset everything

```
curl -v -X PUT "http://localhost:1080/mockserver/reset
```

http://mock-server.com/proxy/getting_started.html#start_mockserver
