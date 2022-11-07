build-jar: build
	jar cvfm Mazedea.jar Manifest.txt -C build .
build: clear
	javac -sourcepath src -d build src/pl/kedarch/mazedea/Main.java
	cp -r resources/ build/pl/kedarch/mazedea/
build-docker: build-jar
	docker image rm -f pl.kedarch.mazedea
	docker build -t pl.kedarch.mazedea .
run-docker: disclaimer-docker
	@docker run -ti --rm --net=host pl.kedarch.mazedea > /dev/null
run: build
	@echo Running build
	java -cp build pl.kedarch.mazedea.Main
run-jar: build-jar
	@echo Running jar build
	java -jar Mazedea.jar
run-cli: build
	@echo Running build
	java -cp build pl.kedarch.mazedea.Main -t
run-jar-cli: build-jar
	@echo Running jar build
	java -jar Mazedea.jar -t

run-docker-new: build-docker disclaimer-docker
	@docker run -ti --rm --net=host pl.kedarch.mazedea > /dev/null
clear:
	rm -rf build Mazedea.jar
disclaimer-docker:
	@echo "To reduce image size the app is run in virtual framebuffer"
	@echo "without window management or compositing. Which means it's"
	@echo "window has no borders and there is nothing in visible background."
	@echo "To connect to it you need VNC client."
	@echo "Address is localhost:5900 or 127.0.0.1:5900. Password is 1234."
	@echo "Press Ctrl+C to kill app."
	
build-docs: 
	rm -rf docs
	javadoc -d docs -sourcepath src -private pl.kedarch.mazedea
