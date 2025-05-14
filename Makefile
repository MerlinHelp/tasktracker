BUILD_DIR = build
SRC_DIR = src
TEST_DIR = src/lib/test

JAVA_SRCS := $(shell find $(SRC_DIR) -type f -path $(TEST_DIR) -prune -false -o -name '*.java')
JAVA_CLASSES := $(JAVA_SRCS:%.java=$(BUILD_DIR)/%.class)

JAVA_TEST_SRCS := $(shell find $(TEST_DIR) -type f -name '*.java')
JAVA_TEST_CLASSES := $(JAVA_TEST_SRCS:%.java=$(BUILD_DIR)/test/%.class)

JAVA_FLAGS := --source-path . -d $(BUILD_DIR) -J-Xmx2G -J-XX:+UseParallelGC

all: tasktracker


$(BUILD_DIR)/%.class: %.java
	javac $(JAVA_FLAGS) $<

tasktracker: $(JAVA_CLASSES)

test: tasktracker $(JAVA_TEST_SRCS)
	@java -ea --class-path build src.lib.test.RunTests build/src/test/RunTests.class -C build src
	@rm -rf build/$(TEST_DIR)

jar: tasktracker
	jar -cfe tasktracker.jar src.main.TaskTracker build/src/main/TaskTracker.class -C build src
	@mv tasktracker.jar dist/

cli: jar
	@bash scripts/make_command.bash


.PHONY: clean tasktracker jar cli

clean:
	@rm -rf $(BUILD_DIR)/*
