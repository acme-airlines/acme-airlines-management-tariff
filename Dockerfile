# Usa una imagen base de JDK para ejecutar el JAR
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR generado al contenedor
COPY target/fee-0.0.1-SNAPSHOT.jar fee-ms.jar

EXPOSE 8003

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "fee-ms.jar"]