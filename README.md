# scoring-query

Queries scoring-datastore of https://github.com/kalaboster/scoring-datastore

#### requrements.

Java 1.8.

#### build

1. *./gradlew bootJar*

#### execute/test

1. clone and bootRun https://github.com/kalaboster/scoring-datastore (defaults to 8888)

2. complete and follow steps of *End2End Scoring Post (Store)* from https://github.com/kalaboster/scoring-datastore/blob/master/README.md

3. build *./gradlew bootJar*

4. execute permutations of commands: 

    - *java -jar build/libs/scoring-query-0.1.0.jar*
    
    - *java -jar build/libs/scoring-query-0.1.0.jar -f=DATE:2014-04-02,STB:stb3*

    - *java -jar build/libs/scoring-query-0.1.0.jar -s=STB,PROVIDER,DATE*
    
    - *java -jar build/libs/scoring-query-0.1.0.jar -s=STB,PROVIDER,DATE -f=DATE:2014-04-02,STB:stb3*
