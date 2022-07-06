FROM openjdk:18
ADD target/Licencjat-0.0.1-SNAPSHOT.jar .
CMD java -jar Licencjat-0.0.1-SNAPSHOT.jar