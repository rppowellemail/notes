```
//// https://mvnrepository.com/artifact/com.github.fge/json-schema-validator
//@Grapes(
//    @Grab(group='com.github.fge', module='json-schema-validator', version='2.2.6')
//)
//
//// http://json-schema-validator.herokuapp.com/

//// https://github.com/everit-org/json-schema
//@Grapes(
//    @Grab(group='com.github.everit-org.json-schema', module='org.everit.json.schema', version='1.11.1')
//)


// https://github.com/java-json-tools/json-schema-validator
@Grapes(
    @Grab(group='com.github.java-json-tools', module='json-schema-validator', version='2.2.10')
)

//final JsonSchema schema = factory.getJsonSchema("resource:/path/to/resource");
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;

//import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

String jsonSchema = """
{"properties":{"bar":{"type":"integer"},"foo":{"type":"string" }}}
"""

String jsonString = """
{"bar" : 2,"foo" : "test","extra" : 12}
"""

JsonNode schemaNode = JsonLoader.fromString(jsonSchema)
JsonNode data = JsonLoader.fromString(jsonString)

JsonSchemaFactory factory = JsonSchemaFactory.byDefault()
JsonSchema schema = factory.getJsonSchema(schemaNode)
ProcessingReport report = schema.validate(data)

println(report.isSuccess().toString())
```
