# Setting up a basic groovy project in IntelliJ

Reference
* https://www.jetbrains.com/help/idea/getting-started-with-groovy-java-9-project.html#2197e4ec


# Step 1 - Create the Project

Create Groovy Project

    File -> New -> Project...

In `New Project` dialog:

* Select `Groovy`

Then click `Next`

Project name: `BasicGroovyProject`

# Step 2 - add first groovy script

Right click on `src`, select `New`, and then `Groovy script`

Add/save the following:

    println("Hello World")
    
Press `Ctrl+Shift+F10` or Right click and select `Run`, to build run, will have this error message:

```
Groovyc: Internal groovyc error: code 1
```

# Step 3 - Modify the Run Configuration

Edit the Run Configuration and add the following code code to the VM options field:

```
--add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/java.lang.invoke=ALL-UNNAMED
```

# Step 4 - Add dependencies

Add dependencies and rerun the script

Press `Ctrl+Alt+Shift+S` to open the Project Structure dialog

Select `Modules`

Under `Dependencies`

Click `+` and select `JARs or Directories`.

Locate `extras-jaxb` directory that the Groovy SDK comes with and add it as a dependency

Example:

For `C:\tools\groovy-3.0.7\bin`, the location is:

    C:\tools\groovy-3.0.7\lib\extras-jaxb
    
