=> put dependencies in lib/ AND test/
=> do not forget to edit jar/META-INF/MANIFEST.MF to
   add dependencies in the  Class-Path

*COMPILE*

cd src ;
javac.exe -classpath ..\lib\* -d ..\jar .\logToMongoDB\*

*JAR*

cd jar
jar cMf ../test/logToMongoDB.jar *


*TEST*

cd test
java -jar .\logToMongoDB.jar


*COMPACT LOOP*

cd src ; javac.exe -classpath ..\lib\* -d ..\jar .\logToMongoDB\* ; cd .. ; cd jar ; jar cMf ../test/logToMongoDB.jar * ; cd .. ; cd test ; java -jar .\logToMongoDB.jar ; cd ..
