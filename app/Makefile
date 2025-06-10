.PHONY: build clean rebuild

run-dist:
	./build/install/app/bin/app

build:
	./gradlew build

clean:
	./gradlew clean

rebuild:
	clean build

test:
	./gradlew test

lint:
	./gradlew checkstyleMain checkstyleTest

report:
	./gradlew jacocoTestReport