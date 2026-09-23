# Spring Boot Hello World & System IP Application

A production-ready Spring Boot 3 application created on your desktop that exposes Hello World endpoints and dynamic system IP address resolution endpoints.

---

## 📌 Project Overview

- **Project Path:** `C:\Users\om\Desktop\hello-world-springboot`
- **Build System:** Apache Maven (with included Maven Wrapper `./mvnw`)
- **Java Version:** Java 22 (`22.0.1`)
- **Framework:** Spring Boot `3.3.4` (Spring Web MVC)
- **Port:** `8080` (configured in `application.properties`)

---

## 📁 Project Architecture & Components

```
C:\Users\om\Desktop\hello-world-springboot\
│
├── pom.xml                                                 # Maven dependencies & build settings
├── mvnw / mvnw.cmd                                         # Self-contained Maven wrapper scripts
├── read.md / README.md                                     # Project documentation
│
└── src/
    ├── main/
    │   ├── java/com/example/helloworld/
    │   │   ├── HelloWorldApplication.java                 # Main application entry point
    │   │   ├── controller/
    │   │   │   ├── HelloController.java                   # Hello World endpoints (/ and /hello)
    │   │   │   └── IpController.java                      # System IP endpoints (/ip and /ip/raw)
    │   │   ├── dto/
    │   │   │   └── IpResponse.java                        # DTO record for IP response payload
    │   │   └── service/
    │   │       └── IpService.java                         # Network detection & IP resolution logic
    │   └── resources/
    │       └── application.properties                     # Server port and app configuration
    │
    └── test/java/com/example/helloworld/
        └── HelloWorldApplicationTests.java                # Integration test suite (MockMvc)
```

### Component Breakdown

1. **[`HelloWorldApplication.java`](file:///C:/Users/om/Desktop/hello-world-springboot/src/main/java/com/example/helloworld/HelloWorldApplication.java)**
   - Annotated with `@SpringBootApplication`.
   - Bootstraps and launches the embedded Tomcat server on port 8080.

2. **[`IpService.java`](file:///C:/Users/om/Desktop/hello-world-springboot/src/main/java/com/example/helloworld/service/IpService.java)**
   - Resolves the machine's real IPv4 address using `NetworkInterface.getNetworkInterfaces()`.
   - Filters out down and loopback interfaces to identify active adapters (e.g. Wi-Fi or Ethernet).
   - Falls back gracefully to `InetAddress.getLocalHost()`.
   - Enumerates all network interfaces and host machine details.

3. **[`IpController.java`](file:///C:/Users/om/Desktop/hello-world-springboot/src/main/java/com/example/helloworld/controller/IpController.java)**
   - Exposes `GET /ip` returning full JSON details: system IP, hostname, client IP, and active network interfaces.
   - Exposes `GET /ip/raw` returning plain text system IP address.
   - Extracts caller IP address from `HttpServletRequest` and supports `X-Forwarded-For`.

4. **[`HelloController.java`](file:///C:/Users/om/Desktop/hello-world-springboot/src/main/java/com/example/helloworld/controller/HelloController.java)**
   - Exposes `GET /` returning JSON status and greeting.
   - Exposes `GET /hello` returning plain text greeting with optional `?name=` parameter.

5. **[`IpResponse.java`](file:///C:/Users/om/Desktop/hello-world-springboot/src/main/java/com/example/helloworld/dto/IpResponse.java)**
   - Modern Java 22 record serving as an immutable data transfer object.

6. **[`HelloWorldApplicationTests.java`](file:///C:/Users/om/Desktop/hello-world-springboot/src/test/java/com/example/helloworld/HelloWorldApplicationTests.java)**
   - Integration tests using `@SpringBootTest` and `MockMvc` validating the context and endpoints.

---

## 🌐 API Reference

### 1. Welcome / Status Endpoint
- **URL:** `http://localhost:8080/`
- **Method:** `GET`
- **Response (`application/json`):**
  ```json
  {
    "status": "UP",
    "message": "Hello, World!",
    "info": "Spring Boot Application is running on your system"
  }
  ```

### 2. Personalized Greeting Endpoint
- **URL:** `http://localhost:8080/hello`
- **Method:** `GET`
- **Query Params:** `name` *(optional, defaults to "World")*
- **Example:** `http://localhost:8080/hello?name=Om`
- **Response (`text/plain`):**
  ```
  Hello, Om!
  ```

### 3. Current System IP Endpoint (Detailed JSON)
- **URL:** `http://localhost:8080/ip`
- **Method:** `GET`
- **Response (`application/json`):**
  ```json
  {
    "systemIp": "192.168.1.35",
    "hostName": "DESKTOP-2PNMF5L",
    "clientIp": "127.0.0.1",
    "networkInterfaces": {
      "Realtek 8821CE Wireless LAN 802.11ac PCI-E NIC (wireless_32768)": [
        "192.168.1.35"
      ]
    }
  }
  ```

### 4. Raw System IP Endpoint (Plain Text)
- **URL:** `http://localhost:8080/ip/raw`
- **Method:** `GET`
- **Response (`text/plain`):**
  ```
  192.168.1.35
  ```

---

## 🚀 How to Run the Application

Open PowerShell and navigate to the project directory:

```powershell
cd C:\Users\om\Desktop\hello-world-springboot
```

### Option A: Run directly with Maven
```powershell
mvn spring-boot:run
```
*(Or with the wrapper: `.\mvnw spring-boot:run`)*

### Option B: Run the Pre-built JAR
```powershell
java -jar target/hello-world-springboot-0.0.1-SNAPSHOT.jar
```

### Option C: Run Automated Tests
```powershell
mvn test
```

---

## 🧪 Testing the Endpoints

Once the application is running, open a browser or new PowerShell window:

```powershell
# Welcome JSON
curl.exe http://localhost:8080/

# Hello greeting
curl.exe http://localhost:8080/hello?name=Om

# System IP JSON
curl.exe http://localhost:8080/ip

# Raw System IP
curl.exe http://localhost:8080/ip/raw
```
