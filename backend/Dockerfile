# Bước 1: Image Maven để build ứng dụng
FROM maven:3.9.4-eclipse-temurin-21 AS build

# Bước 2: Thiết lập thư mục làm việc và copy mã nguồn
WORKDIR /app
COPY . .

# Bước 3: Build ứng dụng với Maven
RUN mvn clean package -DskipTests

# Bước 4: Image OpenJDK để chạy ứng dụng
FROM openjdk:21-slim

# Bước 5: Copy file JAR từ container build sang container runtime
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Bước 6: Mở port và chạy ứng dụng
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
