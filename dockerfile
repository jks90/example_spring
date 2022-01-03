
FROM arm32v7/openjdk
WORKDIR /
ENV MACHINE=k3s
ADD target/testHw-0.0.1-SNAPSHOT.jar mihouse.jar
EXPOSE 8080
CMD ["java", "-jar", "mihouse.jar"]