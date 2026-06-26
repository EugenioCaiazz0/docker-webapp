FROM eclipse-temurin:21-jdk 
#usiamo l'immagine di java21
WORKDIR /app
#crea la cartella app nel container e ci lavora dentro
COPY target/*.jar app.jar
#copia il file jar creato da maven
ENTRYPOINT ["java","-jar","app.jar"]
#avvia Spring Boot