cd btsget\WEB-INF\src
javac -cp ..\lib\javax.servlet-api-3.1.0.jar;. -d ../classes Hello.java 
cd ..\..
jar cvf ..\btsget.war *
cd ..

