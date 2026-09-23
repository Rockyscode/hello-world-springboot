# Hello World Spring Boot Application

A lightweight Spring Boot application that demonstrates basic REST endpoints and system IP address detection.

## Prerequisites
- **Java 21+** (JDK 21 LTS)
- **Maven 3.8+** (Installed in PATH)

## Project Location
```
C:\Users\om\Desktop\hello-world-springboot
```

## How to Run

1. Open PowerShell or Command Prompt.
2. Navigate to the project directory:
   ```powershell
   cd C:\Users\om\Desktop\hello-world-springboot
   ```
3. Run the Spring Boot application using Maven:
   ```powershell
   mvn spring-boot:run
   ```
4. Or build the JAR package and run:
   ```powershell
   mvn clean package
   java -jar target/hello-world-springboot-0.0.1-SNAPSHOT.jar
   ```

## Endpoints

| Method | Endpoint   | Description | Example Response |
|--------|------------|-------------|------------------|
| `GET`  | `/`        | Hello World JSON welcome message | `{"message":"Hello, World!","status":"UP","info":"..."}` |
| `GET`  | `/hello`   | Hello World plain text greeting with optional `?name=` query | `Hello, World!` |
| `GET`  | `/ip`      | **Current system IP address** and network interfaces info | JSON object with `systemIp`, `hostName`, `clientIp`, `networkInterfaces` |
| `GET`  | `/ip/raw`  | Raw IP string only | `192.168.1.15` |

### Sample `/ip` Output:
```json
{
  "systemIp": "192.168.1.15",
  "hostName": "MY-PC",
  "clientIp": "127.0.0.1",
  "networkInterfaces": {
    "Wi-Fi (wlan0)": [
      "192.168.1.15",
      "fe80::..."
    ]
  }
}
```
