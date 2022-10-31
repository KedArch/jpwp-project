FROM eclipse-temurin:17.0.4.1_1-jre
RUN apt-get update && apt-get install -y x11vnc xvfb && rm -rf /var/cache/apt/archives /var/lib/apt/lists/* && mkdir ~/.vnc && x11vnc -storepasswd 1234 ~/.vnc/passwd && echo "java -jar /opt/Mazedea.jar" > ~/.bashrc
COPY Mazedea.jar /opt/
CMD [ "x11vnc", "-forever", "-usepw", "-create", "-geometry", "1280x1024" ]
