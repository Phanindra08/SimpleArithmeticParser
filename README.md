# 🚀 Parser Project
Welcome to the Simple Arithmetic Parser Project. This guide will help you to set up, build and run the Parser application.

## 🔧 Prerequisites

Before you start, make sure you have the appropriate tools installed on your system:

| 🛠 Tools            | 🔢 Version | 🔗 Link                                                                      |
|---------------------|------------|------------------------------------------------------------------------------|
| ☕ **Java**          | `21`       | [Java Downloads](https://www.oracle.com/java/technologies/downloads/#java21) |
| 🛠 **Apache Maven** | `3.9.9+`   | [Apache Maven](https://maven.apache.org/download.cgi)                        |
| **IntelliJ IDEA**   | `Latest`   | [IntelliJ IDEA Download](https://www.jetbrains.com/idea/download/)           |

🔍 **You can verify your Java and Maven Versions using the below commands:**
```sh
java -version
mvn -version
```
## Maven Dependencies

Please find below the list of important maven dependencies used in this project.
| 🏷 Dependency    | 🔢 Version |
|------------------|------------|
| **ANTLR**        | `4.13.1`   |
| **Spring Boot**  | `3.5.6`    |
| **Spring Batch** | `5.2.3`    |
| **Lombok**       | `1.18.36`  |

## IDE Plugins

For a better development experience while using IntelliJ IDEA, please install these plugins: 

| Plugin Name  | Description                                   |
|--------------|-----------------------------------------------|
| **ANTLR v4** | Provides the support for ANTLR grammar files. |
| **Lombok**   | Provides the support for Lombok annotations.  |

## 📦️Build
If you make any code changes, follow the below commands to rebuild the project and generate a new package.
```sh
mvn clean package
```

## Running the Application

After a successful build, the generated JAR file will be located in the `target` directory. Move this JAR file into your designated `Package` directory for execution.
Before running the JAR file, edit the <b>dl-output</b> key in the `application.yml` file to specify your desired output location for the generated files.
After placing the JAR package in the Package folder. You can run the application using this command:
```sh
java -jar SimpleArithmeticParser-*.jar --spring.config.location=application.yml --input.file=<Input File path> --job.name=<Any Job Name from the below list>
```

Example command for running the application:
```sh
java -jar SimpleArithmeticParser-0.0.1-SNAPSHOT.jar --spring.config.location=application.yml --input.file=C:\Users\skothur1\Downloads\SimpleArithmeticParser\Inputs\example1 --job.name=SIMPLE_ARITHMETIC_PARSE_TREE_GENERATION
```

## Available Job Names:
Choose from the following job names based on your desired operation:

| Job Name                                | Description                                            |
|-----------------------------------------|--------------------------------------------------------|
| SIMPLE_ARITHMETIC_PARSE_TREE_GENERATION | Generating Parse Tree for Simple Arithmetic Input File |
| SIMPLE_ARITHMETIC_AST_GENERATION        | Generating AST for Simple Arithmetic Input File        |

## 📝 Notes
- <b>Sample Input and Output Files: </b> When referring to the sample input and output files, check out the [Inputs](./SampleExamples/Inputs) and [Outputs](./SampleExamples/Outputs) folders. Keep in mind that if you use the sample input files to generate the output, the resulting output file will be identical.