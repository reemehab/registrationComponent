FROM openjdk:15
COPY . /app

# Add the commons-lang3 library .jar file to the container
ADD https://repo1.maven.org/maven2/org/apache/commons/commons-lang3/3.0.1/commons-lang3-3.0.1.jar /app/lib

# Add the OpenCSV library .jar file to the container
ADD https://repo1.maven.org/maven2/com/opencsv/opencsv/5.5.2/opencsv-5.5.2.jar /app/lib

# Set the classpath to include the OpenCSV library
ENV CLASSPATH="/app:/app/lib/commons-lang3-3.0.1.jar:/app/lib/opencsv-5.5.2.jar"

# Set the app as a working directory
WORKDIR /app

COPY /src/Main.java /app

# Compile the Java application
RUN javac Main.java

# Enable interactive mode
ENV TERM xterm
ENV DEBIAN_FRONTEND noninteractive

# Set the container to run in interactive mode
ENV JAVA_TOOL_OPTIONS="-Djava.awt.headless=true"

# Set the command to run the Java application
CMD ["java", "-cp", "/app:/app/lib/commons-lang3-3.0.1.jar:/app/lib/opencsv-5.5.2.jar", "Main"]