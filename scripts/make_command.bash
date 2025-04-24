#!/bin/bash

COMMAND_NAME="tasktracker"
JAR_PATH="${1:-./dist/tasktracker.jar}"
JAR_LIB_DIR="$HOME/.local/lib/$COMMAND_NAME"
BIN_PATH="$HOME/.local/bin"

CURR_SHELL=""

# Accomodate for more shells later
if [[ "$SHELL" == */bash ]]; then
    CURR_SHELL="$HOME/.bashrc"
fi

# Check if path is added.
if ! [[ -d "$BIN_PATH" ]]; then
    mkdir -p "$BIN_PATH"
fi
if [[ ":$PATH:" != *"$BIN_PATH"* ]]; then
    echo 'Adding $BIN_PATH to PATH in your shell config...'
    echo -e '\n# Add local bin to PATH\nif [[ ":$PATH:" != *":$HOME/.local/bin:"* ]]; then\n  export PATH="$HOME/.local/bin:$PATH"\nfi' >> "$SHELL_RC"
    source "$CURR_SHELL"
fi

if [[ -f "$JAR_PATH" ]]; then
    if ! [[ -d "$JAR_LIB_DIR" ]]; then
        mkdir -p "$JAR_LIB_DIR"
    fi
    cp "$JAR_PATH" "$JAR_LIB_DIR/$COMMAND_NAME.jar"
    cat > "$BIN_PATH/$COMMAND_NAME" <<EOF
#!/bin/bash
java -jar "$JAR_LIB_DIR/$COMMAND_NAME.jar" "\$@"
EOF

    chmod +x "$BIN_PATH/$COMMAND_NAME"
fi
