DDD Shop Application
====================

This repository contains a working progress backend for a shop using DDD methodology.

Its purpose is to learn the methodology and use this repository as source to test implementation options.


Backend
-------

### Start backend:

```bash
$ mvn clean spring-boot:run
```

It works with:
- Apache Maven 3.5.0
- Java version: 1.8.0_131

### Test backend:

```bash
$ mvn test
```



Source Files
-------------

Sources folder: `src/main/java`.
Test sources folder: `src/test/java`.



IntelliJ notes
--------------

To start with "IntelliJ IDEA":

- Select "Import Project"
- Select this project folder.
- Select "Import project from external model"
- Select "Maven"
- Next
- Next
- Next
- Next
- Finish

For speed: right click on "frontend/node_modules" "Mark directory as" > "Excluded"

Add Test All configuration to run:
- Go to the dropdown just before the play button
- Select "Edit Configurations..."
- Select "+"
- Select "JUnit"
- Write into Name: "Test All"
- Select in Test Kind: "All in Package"
- Write into Package: "com.drpicox.blog"

To test one test:
- Go to the test
- Locate the play button in the left
- Click to run the test

To get code coverage, close to the play button, there is a button that has a hint that says: "Run 'Test All' with Coverage".


Eclipse Notes
-------------

To open the project: 
- Menu "File" > "Open Projects from File System..."
- Select the directory
- Finish

To run:
- Right click in the Project name
- Select "Run As"
- Select "Maven Build..."
- Write into Goals: "spring-boot:run"
- Run

To Test:
- Right click `src/test/java` (or any individual package or test suite)
- Select "Run As"
- Select "JUnit Test"
- Results are in the JUnit tab.

To get Coverage (requires Eclipse plugin EclEmma installed):
- Right click `src/test/java` (or any individual package or test suite)
- Select "Coverage As"
- Select "JUnit Test"
- Results are in the JUnit tab.

EclEmma is available in eclipse marketplace, read more at:
- http://www.eclemma.org/installation.html
- http://marketplace.eclipse.org/content/eclemma-java-code-coverage
