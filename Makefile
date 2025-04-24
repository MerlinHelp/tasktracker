BUILD_DIR = build
SRC_DIR = src

JAVA_SRCS := $(shell find $(SRC_DIR) -type f -name '*.java')
JAVA_CLASSES := $(JAVA_SRCS:%.java=$(BUILD_DIR)/%.class)

JAVA_FLAGS := --source-path . -d $(BUILD_DIR) -J-Xmx2G -J-XX:+UseParallelGC

all: tasktracker


$(BUILD_DIR)/%.class: %.java
	javac $(JAVA_FLAGS) $<

tasktracker: $(JAVA_CLASSES)

jar: 
	jar -cfe tasktracker.jar src.main.TaskTracker build/src/main/TaskTracker.class -C build src
	@mv tasktracker.jar dist/

cli: jar
	@bash scripts/make_command.bash


.PHONY: clean tasktracker jar cli

clean:
	@rm -rf $(BUILD_DIR)/*
