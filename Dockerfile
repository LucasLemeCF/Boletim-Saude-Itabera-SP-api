# Usa a imagem base do OpenJDK 17 com o Maven pré-instalado
FROM maven:3.8.5-openjdk-17-slim AS build

# Copia os arquivos do seu projeto para a imagem
COPY pom.xml ./
COPY src/ ./src/

# Executa o Maven para construir o projeto
RUN mvn clean package

EXPOSE 8080

# Comando para iniciar a aplicação
CMD ["java", "-jar", "target/boletim-medico-0.0.1.jar"]