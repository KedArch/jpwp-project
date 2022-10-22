FROM openjdk:20-ea-17-jdk
COPY Mazedea.jar /opt/
CMD java -jar /opt/Mazedea.jar
