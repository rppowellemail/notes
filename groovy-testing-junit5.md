JUnit5 Example

Reference:

* https://groovy-lang.org/testing.html#_junit_5

`JUnit5ExampleTests.groovy`

```
//@Grab('org.junit.jupiter:junit-jupiter')
@Grab("org.junit.jupiter:junit-jupiter:5.5.0")
//@Grab('org.junit.jupiter:junit-jupiter:[5.5.0,)')
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.junit.platform.launcher.listeners.LoggingListener


import java.util.logging.ConsoleHandler
import java.util.logging.Level
import java.util.logging.Logger
import java.util.stream.Stream

import static org.junit.jupiter.api.Assertions.assertTrue
import static org.junit.jupiter.api.DynamicTest.dynamicTest

class MyTest {

  @BeforeAll
  static void init() {
    def logger = Logger.getLogger(LoggingListener.name)
    logger.level = Level.FINE
    logger.addHandler(new ConsoleHandler(level: Level.FINE))
  }

  @Test
  void streamSum() {
    assertTrue(Stream.of(1, 2, 3)
      .mapToInt(i -> i)
      .sum() > 5, () -> "Sum should be greater than 5")
  }

  @RepeatedTest(value=2, name = "{displayName} {currentRepetition}/{totalRepetitions}")
  void streamSumRepeated() {
    assert Stream.of(1, 2, 3).mapToInt(i -> i).sum() == 6
  }

  private boolean isPalindrome(s) { s == s.reverse()  }

  @ParameterizedTest                                                              
  @ValueSource(strings = [ "racecar", "radar", "able was I ere I saw elba" ])
  void palindromes(String candidate) {
    //assert isPalindrome(candidate)
  }

  @TestFactory
  def dynamicTestCollection() {[
    dynamicTest("Add test") { -> assert 1 + 1 == 2 },
    dynamicTest("Multiply Test", () -> { assert 2 * 3 == 6 })
  ]}
}
```
