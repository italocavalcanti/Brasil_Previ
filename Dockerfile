FROM openjdk:8

VOLUME /tmp

COPY target/brasilprev-0.0.1-SNAPSHOT.jar /brasilprev.jar

RUN sh -c 'touch /brasilprev.jar' 

ENTRYPOINT ["sh", "-c", "java -jar /brasilprev.jar"]