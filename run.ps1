# Set JAVA_HOME to the specific JDK version
$env:JAVA_HOME = "C:\Program Files\Java\jdk-21.0.12.1"

# Prepend the JDK's bin directory to PATH so java/javac commands are found
$env:Path = "$env:JAVA_HOME\bin;$env:Path"

# Run the Spring Boot application using the Maven wrapper in the background or open browser after a short delay
Start-Job -ScriptBlock {
    Start-Sleep -Seconds 5
    Start-Process "http://localhost:8080"
}

# Run the Spring Boot application using the Maven wrapper
.\mvnw.cmd spring-boot:run