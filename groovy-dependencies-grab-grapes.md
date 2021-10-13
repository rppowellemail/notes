Groovy Grapes

Running with dependency handling debugging turned on:

```
groovy -Dgroovy.grape.report.downloads=true -Divy.message.logger.level=4 Script.groovy
```

---

## Groovy grape Examples

* http://docs.groovy-lang.org/latest/html/documentation/grape.html

```groovy
/** 
 * Reference: 
 *
 * http://docs.groovy-lang.org/latest/html/documentation/grape.html
 * 
 * run with:
 * 
 * groovy -Dgroovy.grape.report.downloads=true -Divy.message.logger.level=4 ApacheCommonsCollections.groovy
 *
*/

@Grab('commons-primitives:commons-primitives:1.0')
//@Grab(group='commons-primitives', module='commons-primitives', version='1.0')

import org.apache.commons.collections.primitives.ArrayIntList

def createEmptyInts() { new ArrayIntList() }

def ints = createEmptyInts()
ints.add(0, 42)
assert ints.size() == 1
assert ints.get(0) == 42

println(ArrayIntList.class.getProtectionDomain().getCodeSource().getLocation().getPath())
println(ArrayIntList.class.getPackage().getSpecificationTitle())
println(ArrayIntList.class.getPackage().getSpecificationVersion())
println(ArrayIntList.class.getPackage().getImplementationTitle())
println(ArrayIntList.class.getPackage().getImplementationVersion())
```
