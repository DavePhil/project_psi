FROM mcr.microsoft.com/windows/servercore:ltsc2019
EXPOSE 8081
ADD target/psi-backend.jar psi-backend.jar
ENTRYPOINT ["java","-jar","/psi-backend.jar"]