`groovySqliteJson.groovy`

```
//@GrabResolver(name='restlet', root='http://maven.restlet.org/')
//@GrabConfig(systemClassLoader=true)
@Grapes([
 @GrabConfig(systemClassLoader=true),
 @Grab(group='org.xerial',module='sqlite-jdbc',version='3.27.2.1')
])
 
import java.sql.*
import groovy.sql.Sql
 
//Class.forName("org.sqlite.JDBC")
 
//def sql = Sql.newInstance("jdbc:sqlite:sample.db", "org.sqlite.JDBC")
def sql = Sql.newInstance("jdbc:sqlite:memory", "org.sqlite.JDBC")
 
sql.execute("drop table if exists person")
sql.execute("create table person (id integer, name string)")

 
def people = sql.dataSet("person")
people.add(id:1, name:"leo")
people.add(id:2,name:'yui')
 
sql.eachRow("select * from person") {  
  println("id=${it.id}, name= ${it.name}") 
}

sql.eachRow("PRAGMA compile_options;") {  
  println(it)
}

sql.eachRow("select json(' { \"this\" : \"is\", \"a\": [ \"test\" ] } ')") {
  println(it)
}
```

```
groovy -Dgroovy.grape.report.downloads=true -Divy.message.logger.level=4 groovySqliteJson.groovy
```
