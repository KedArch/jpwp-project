build-jar: build
	jar cvfm Mazedea.jar Manifest.txt -C bin .
build: clear
	javac -sourcepath src -d bin src/pl/kedar/mazedea/Mazedea.java
	cp -r resources/ bin/pl/kedar/mazedea/
build-docker: build-jar
	docker image rm -f pl.kedar.mazedea
	docker build -t pl.kedar.mazedea .
run-docker: disclaimer-docker
	@docker run -ti --rm --net=host pl.kedar.mazedea > /dev/null
run: build
	java -cp bin pl.kedar.mazedea.Mazedea
run-jar: build-jar
	java -jar Mazedea.jar
run-docker-new: build-docker disclaimer-docker
	@docker run -ti --rm --net=host pl.kedar.mazedea > /dev/null
clear:
	rm -rf bin Mazedea.jar
disclaimer-docker:
	@echo "To reduce image size the app is run in virtual framebuffer"
	@echo "without window management or compositing. Which means it's"
	@echo "window has no borders and there is nothing in visible background."
	@echo "To connect to it you need VNC client."
	@echo "Address is localhost:5900 or 127.0.0.1:5900. Password is 1234."
	@echo "Press Ctrl+C to kill app."
	
