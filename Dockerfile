#inicia con la imagen base que contiene Java runtime
FROM openjdk:17-jdk-slim as build

# se agregar el jar del microservicio al contenedor
COPY target/InventoryControl-0.0.1-SNAPSHOT.jar InventoryControl-0.0.1-SNAPSHOT.jar

#se ejecuta el microservicio
ENTRYPOINT ["java","-jar","/InventoryControl-0.0.1-SNAPSHOT.jar"]