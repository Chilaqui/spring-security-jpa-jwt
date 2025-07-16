# Usamos una imagen oficial de Java con JDK 17
FROM eclipse-temurin:17-jdk-alpine

# Creamos un directorio en el contenedor
WORKDIR /app

# Copiamos el archivo JAR construido por Maven
COPY target/app-0.0.1-SNAPSHOT.jar app.jar

# Comando para ejecutar el JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
