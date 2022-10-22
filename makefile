build-jar: build
	jar cvfm Mazedea.jar Manifest.txt -C bin .
build: clear
	javac -sourcepath src -d bin src/pl/kedar/mazedea/Mazedea.java
docker: build-jar
	docker build -t pl.kedar.mazedea .
run: build
	java -cp bin pl.kedar.mazedea.Mazedea
run-jar: build-jar
	java -jar Mazedea.jar
run-docker: docker
	docker run pl.kedar.mazedea
clear:
	rm -rf bin Mazedea.jar
