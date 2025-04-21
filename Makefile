BUILD_DIR = build

JAVA_SRCS := $(shell find src/ -type f -name '*.java')
JAVA_CLASSES := $(JAVA_SRCS:%.java=$(BUILD_DIR)/%.class)

all: tasktracker

tasktracker:
	@javac --source-path ./ $(JAVA_SRCS) -d $(BUILD_DIR)


.PHONY: clean

clean:
	@rm $(BUILD_DIR)/*.class 2> /dev/null
