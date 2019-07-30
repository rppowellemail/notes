`groovyJsonPath20190730001.groovy`

```
@GrabResolver(name='maven', root='http://repo.maven.apache.org/')
// https://mvnrepository.com/artifact/io.rest-assured/rest-assured
@Grapes(
    @Grab(group='io.rest-assured', module='rest-assured', version='4.0.0', scope='test')
)

import io.restassured.path.json.JsonPath

jsonstring = """{"httpCode":"99","httpMessage":"TimeOut","moreInformation":"Backend Service RME54AAC Timed Out"}"""
println(jsonstring)

JsonPath jsonPath = new JsonPath(jsonstring)
println(jsonPath.getString("httpMessage"))
```

`groovy -Dgroovy.grape.report.downloads=true -Divy.message.logger.level=4`
