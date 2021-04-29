# Learn Together 👨‍🎓👩‍🎓
Project for **[*🚀 TO THE MOON - first online individual serbian programming competition*](https://dzimiks.com/contests)**.
<br>
<br>

> [*Opis na Srpskom*](https://github.com/SavaN222/Learn-Together/blob/main/README.rs.md)

[![learnTogetherGif.gif](https://s3.gifyu.com/images/learnTogetherGif.gif)](https://gifyu.com/image/t1ZV)


## 🚩 Navigation

- [About Project](#-about-project)
- [Starting and Configuration](#-starting--configuration)


## 👨‍💻 About Project

**Learn Together** is social network that connects students of all universities and enables them to quickly and easy exchange knowledge in various interesting and creative ways. 

Used technologies:

---
#### Back-End:
* **Java**
* **Spring Boot**
* **Spring Web(MVC, REST)**
* **Spring Data JPA**
* **Spring Security**
* **Lombok**

#### Database:
* **MySQL**

#### Front-End:
* **HTML - Thymeleaf**
* **CSS**
* **Bootstrap**
---
<br>

### 👦👧 Registration

![registracija](https://i.ibb.co/f2L5Kmp/registracija.png)

<br>

### 📩 Confirm mail address and account activation

![potvrda](https://i.ibb.co/85JDq1n/confirmmail.png)

<br>

### 🏠 HomePage
* **Information about the student.**
* **Random quote with every refresh.**
* **Friends posts**

![homepage](https://i.ibb.co/VtxsQsW/homepage.png)

<br>

### 🎓 Study Field section
* **Ask questions in study fields, visible to all students regardless of university.**
* **Comment and Like question.**
* **Delete/Edit comment/question.**
* **Notifications.**

[![studyFieldGif470ae0e77426923b.gif](https://s3.gifyu.com/images/studyFieldGif470ae0e77426923b.gif)](https://gifyu.com/image/tOuP)

<br>

### 🏢 University
* **Share posts with other students from your university.**
* **Comment/Like posts.**
* **Delete/Edit comment/post.**
* **Notification.**
* **List of all students from your university.**

[![universityGif.gif](https://s3.gifyu.com/images/universityGif.gif)](https://gifyu.com/image/tOQH)

<br>

### ❗❔ Quiz section
* **Student can create quiz with questions and answers.**
* **Student can delete quiz or question.**
* **Play quiz.**

<br>

### Creating Quiz
[[![createQuizGif.gif](https://s3.gifyu.com/images/createQuizGif.gif)](https://gifyu.com/image/tOQh)
* **Creating numeric or textual questions.**
* **Creating 4 possible answers, select one which is correct.**

<br>

### Playing Quiz
[![playQuiz.gif](https://s3.gifyu.com/images/playQuiz.gif)](https://gifyu.com/image/tAgN)
* **When page is refreshed or quiz is started, position of answers is changed(Not in the same place every time).**

<br>

### 🐱‍🏍 My Profile Section
* **Show information about the student.**
* **Show all posted questions in 'STUDY FIELD' section.**
* **Show all posts from news feed.**
* **Show all friends and total number of it.**
* **Update profile.**

<br>

![myProfile](https://i.ibb.co/vH1YWYQ/my-Profile.png)

<br>

### 👫 Add Friends
[![addFriendGif.gif](https://s3.gifyu.com/images/addFriendGif.gif)](https://gifyu.com/image/tOWP)

<br>

### 🏆 Favorites
* **Show all 'liked' questions from 'STUDY FIELD'.**
* **Show all 'liked' posts from university.**

![favorites](https://i.ibb.co/mD74nq6/favorites.png)


<br>

## 🔧 Starting & Configuration

### Database

First you need to create database connection, in my case name of connection is `learn_together`.

After creating connection you must import data from this [*SQL FILE*](https://github.com/SavaN222/Learn-Together/blob/main/learnTogether.sql)
### Project Configuration
Open `application.yml` file and change it with right information.

path -> `projectLocation/src/main/resources/application.yml`

File looking something like this:
```yml
spring:
  # DATABASE
  datasource:
    url: jdbc:mysql://localhost:3306/learn_together
    username: your_username
    password: your_password
  jpa:
    hibernate.ddl-auto: update
    generate-ddl: true
    show-sql: true

  # EMAIL
  mail:
    default-encoding: UTF-8
    host: smtp.gmail.com
    username: your@gmail.com
    password: your_email_password
    port: 587
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
    protocol: smtp
    test-connection: false

  servlet:
    multipart:
      max-file-size: 15MB
      max-request-size: 15MB

  mvc:
    hiddenmethod:
      filter:
        enabled: true
```

### 🚨 Possible mistakes
```yml
-  url: jdbc:mysql://localhost:3306/learn_together
````
**learn_together is database connection name, check what is your database connection name ❗**

```yml
-  username: your_username
-  password: your_password
````
**Override your_username and your_password with your credentials for connection to database ❗**

**🚧 In most cases username is root, password is empty**

**🌠 Example:**
```yml
-  username: root
-  password: 

-  username: john
-  password: john123
````


**CONFIGURE E-MAIL ADDRESS ❗**

```yml
  # EMAIL
  mail:
    default-encoding: UTF-8
    host: smtp.gmail.com
    username: your@gmail.com
    password: your_email_password
````
**🌠 Example:**
```yml
  # EMAIL
  mail:
    default-encoding: UTF-8
    host: smtp.gmail.com
    username: johndoe@gmail.com
    password: jdoe555333
````

**ERROR: Access is not allowed ❓**
```sh
ERROR 11680 --- [nio-8080-exec-7] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is org.springframework.mail.MailAuthenticationException: Authentication failed; nested exception is javax.mail.AuthenticationFailedException: 535-5.7.8 Username and Password not accepted. Learn more at
535 5.7.8  https://support.google.com/mail/?p=BadCredentials u13sm377842ejj.16 - gsmtp
] with root cause

javax.mail.AuthenticationFailedException: 535-5.7.8 Username and Password not accepted. Learn more at
535 5.7.8  https://support.google.com/mail/?p=BadCredentials u13sm377842ejj.16 - gsmtp
```

**Two of the most cases for this error is:**
* Bad credentials.
* Google does not allow access to foreign applications.

For fix this error you must:
* [*ENABLE LESS SECURE APPS ACCESS TO GOOGLE ACCOUNT*](https://myaccount.google.com/u/0/lesssecureapps?pli=1&rapt=AEjHL4MmQ2kpWEDZ1wfdbTM6updFNoZ2U3Uo7WNUHe9HpgL8KCzA1i1jncNJayP6ek3Uf2G8morP5YXshkpOkUzNRfa02CUMoQ)
  -pay attention to which user is logged in to the google account.
  ![guglSecure](https://i.ibb.co/4pYSHtT/Less-secure-app-access.png)
  this is necessary for the application to send a link to confirm the account to newly-registered students via your e-mail

### 📂 Path for saving images
In file `StudentService` on path: `projectLocation/src/main/java/com/nakaradasava/learntogether/service/student/StudentService`

change:
```sh 
Linija 82: String windowsPath = "C:\\Users\\Korisnik\\IdeaProjects\\Learn Together\\src\\main\\resources\\static";
```
with your path to static folder.

📢Linux and Mac uses /`forward slash` while windows use \ `backslash`

📢Windows path use double backslashes `\\` because 'escape character', Linux and Mac uses normal path with single forward slash `/`

Same change applied in `UploadConfig` on path:
`lokacijaProjekta/src/main/java/com/nakaradasava/learntogether/config/UploadConfig`

```sh 
Linija 15: registry.addResourceHandler("/images/**").addResourceLocations("file:C:\\Users\\Korisnik\\IdeaProjects\\Learn Together\\src\\main\\resources\\static\\images\\");
```
#### 📢Linux and Mac = file:Your/Path/To/Static
#### 📢Windows = file:C:\\\Your\\\Path\\\To\\\Static



### ✅ Starting project ✅

You can run project in two ways:

#### Running from terminal:
Set your path in project folder

``` sh
$ pwd
/c/Users/Korisnik/IdeaProjects/Learn Together
```

On windows:
``` sh
$ mvnw spring-boot:run
```
On Linux/Mac:
``` sh
$ mvn spring-boot:run
```
Expected output:
``` java
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------< com.nakaradasava:learn-together >-------------------
[INFO] Building Learn Together 0.0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] >>> spring-boot-maven-plugin:2.4.4:run (default-cli) > test-compile @ learn-together >>>
[INFO]
[INFO] --- maven-resources-plugin:3.2.0:resources (default-resources) @ learn-together ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Using 'UTF-8' encoding to copy filtered properties files.
[INFO] Copying 1 resource
[INFO] Copying 30 resources
[INFO]
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ learn-together ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] --- maven-resources-plugin:3.2.0:testResources (default-testResources) @ learn-together ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Using 'UTF-8' encoding to copy filtered properties files.
[INFO] skip non existing resourceDirectory C:\Users\Korisnik\IdeaProjects\Learn Together\src\test\resources
[INFO]
[INFO] --- maven-compiler-plugin:3.8.1:testCompile (default-testCompile) @ learn-together ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] <<< spring-boot-maven-plugin:2.4.4:run (default-cli) < test-compile @ learn-together <<<
[INFO]
[INFO]
[INFO] --- spring-boot-maven-plugin:2.4.4:run (default-cli) @ learn-together ---
[INFO] Attaching agents: []

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.4.4)

2021-04-28 22:21:54.117  INFO 5476 --- [           main] c.n.l.LearnTogetherApplication           : Starting LearnTogetherApplication using Java 11.0.8 on DESKTOP-DK1Q5BN with PID 5476 (C:\Users\Korisnik\IdeaProjects\Learn Together\target\classes started by Sava in C:\Users\Korisnik\IdeaProjects\Learn Together)
2021-04-28 22:21:54.121  INFO 5476 --- [           main] c.n.l.LearnTogetherApplication           : No active profile set, falling back to default profiles: default
2021-04-28 22:21:55.134  INFO 5476 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2021-04-28 22:21:55.327  INFO 5476 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 182 ms. Found 18 JPA repository interfaces.
2021-04-28 22:21:56.254  INFO 5476 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2021-04-28 22:21:56.267  INFO 5476 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2021-04-28 22:21:56.267  INFO 5476 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.44]
2021-04-28 22:21:56.394  INFO 5476 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2021-04-28 22:21:56.394  INFO 5476 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 2209 ms
2021-04-28 22:21:56.627  INFO 5476 --- [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2021-04-28 22:21:56.686  INFO 5476 --- [           main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 5.4.29.Final
2021-04-28 22:21:56.863  INFO 5476 --- [           main] o.hibernate.annotations.common.Version   : HCANN000001: Hibernate Commons Annotations {5.1.2.Final}
2021-04-28 22:21:56.991  INFO 5476 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2021-04-28 22:21:57.383  INFO 5476 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2021-04-28 22:21:57.407  INFO 5476 --- [           main] org.hibernate.dialect.Dialect            : HHH000400: Using dialect: org.hibernate.dialect.MySQL8Dialect
2021-04-28 22:21:59.039  INFO 5476 --- [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
2021-04-28 22:21:59.050  INFO 5476 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2021-04-28 22:21:59.586  WARN 5476 --- [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2021-04-28 22:22:00.501  INFO 5476 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2021-04-28 22:22:00.607  INFO 5476 --- [           main] o.s.b.a.w.s.WelcomePageHandlerMapping    : Adding welcome page template: index
2021-04-28 22:22:00.882  INFO 5476 --- [           main] o.s.s.web.DefaultSecurityFilterChain     : Will secure any request with [org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter@5006a697, org.springframework.security.web.context.SecurityContextPersistenceFilter@11b74ecb, org.springframework.security.web.header.HeaderWriterFilter@73476e2d, org.springframework.security.web.csrf.CsrfFilter@29ebaf2f, org.springframework.security.web.authentication.logout.LogoutFilter@d8751de, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter@938e54a, org.springframework.security.web.savedrequest.RequestCacheAwareFilter@25093079, org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter@26a004ed, org.springframework.security.web.authentication.AnonymousAuthenticationFilter@193792e6, org.springframework.security.web.session.SessionManagementFilter@6ab6ec33, org.springframework.security.web.access.ExceptionTranslationFilter@193c810, org.springframework.security.web.access.intercept.FilterSecurityInterceptor@61d4155b]
2021-04-28 22:22:00.950  INFO 5476 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2021-04-28 22:22:00.962  INFO 5476 --- [           main] c.n.l.LearnTogetherApplication           : Started LearnTogetherApplication in 7.457 seconds (JVM running for 8.057)

```
#### Starting with IDE, example: Intellij
Open the project in your IDE, and run it.
![idepokretanje](https://i.ibb.co/XsVVD5b/ide-Pokretanje.png)

### OTHER

#### API which used
* **[*Random quote api*](https://github.com/lukePeavey/quotable)**

<br>

#### Accounts for testing
* **Username: male_tester**
* **Username: female_tester**
* **Password: test123**