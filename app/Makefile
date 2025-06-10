.PHONY: build

run-dist:
	./build/install/app/bin/app

build:
	./gradlew clean build

install:
	./gradlew installDist

test:
	./gradlew test

lint:
	./gradlew checkstyleMain checkstyleTest

report:
	./gradlew jacocoTestReport