FROM 20240312-windowsservercore-ltsc2022
EXPOSE 8081
ADD target/psi-backend.jar psi-backend.jar
ENTRYPOINT ["java","-jar","/psi-backend.jar"]