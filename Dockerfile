FROM java:8
COPY  ./deploy/target/officedict.deploy-1.0-SNAPSHOT.jar /usr/share

EXPOSE 8081
ENTRYPOINT java -jar /usr/share/officedict.deploy-1.0-SNAPSHOT.jar
