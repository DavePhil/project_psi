FROM openjdk:8
EXPOSE 8081
ADD target/psi-backend.jar psi-backend.jar
ENTRYPOINT ["java","-jar","/psi-backend.jar"]