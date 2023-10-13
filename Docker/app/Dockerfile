# Usar una imagen base de Java 17
FROM openjdk:17-jdk-alpine

#Establecer el directorio de trabajo en /app
workdir /app

# Copiar el archivo JAR de tu aplicación al contenedor
COPY target/ApiRestHelados-0.0.1-SNAPSHOT.jar ApiRestHelados-0.0.1-SNAPSHOT.jar

# Exponer el puerto en el que se ejecutara la aplicación
EXPOSE 8080

#Comando para ejecutar la aplicación
CMD ["java", "-jar", "ApiRestHelados-0.0.1-SNAPSHOT.jar"] 
