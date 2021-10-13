# JUnit4 example

```groovy
JUnit4ExampleTests.groovy 
@Grab('junit:junit')

import org.junit.Test

import static groovy.test.GroovyAssert.shouldFail

class JUnit4ExampleTests {
    @Test
    void indexOutOfBoundsAccess() {
        def numbers = [1,2,3,4]
        shouldFail {
            numbers.get(4)
        }
    }
}
```
