# Learn Together 👨‍🎓👩‍🎓
Projekat rađen za **[*🚀 TO THE MOON - prvo online individualno srpsko takmičenje u programiranju*](https://dzimiks.com/contests)**.
<br>
<br>

> [*English Description*](https://github.com/SavaN222/Learn-Together/blob/main/README.md)

[![learnTogetherGif.gif](https://s3.gifyu.com/images/learnTogetherGif.gif)](https://gifyu.com/image/t1ZV)


## 🚩 Navigacija

- [O projektu](#-o-projektu)
- [Pokretanje](#-pokretanje--podesavanje)


## 👨‍💻 O projektu

**Learn Together** je socijalna mreža koja povezuje studente **svih univerziteta** i omogućava im brzu i lakšu razmenu znanja na razne zanimljive i kreativne načine.

Projekat je radjen u sledećim tehnologijama:

---
#### Back-End:
* **Java**
* **Spring Boot**
* **Spring Web(MVC, REST)**
* **Spring Data JPA**
* **Spring Security**
* **Lombok**

#### Baza podataka:
* **MySQL**

#### Front-End:
* **HTML - Thymeleaf**
* **CSS**
* **Bootstrap**
---
<br>

### 👦👧 Registracija

![registracija](https://i.ibb.co/f2L5Kmp/registracija.png)

<br>

### 📩 Potvrda mejl adrese i aktivacija naloga

![potvrda](https://i.ibb.co/85JDq1n/confirmmail.png)

<br>

### 🏠 Izgled početne stranice
* **Prikaz informacija o studentu.**
* **Naizmenični citati prilikom osveživanja stranice.**
* **Objave prijatelja.**

![homepage](https://i.ibb.co/VtxsQsW/homepage.png)

<br>

### 🎓 Study Field sekcija
* **Postavljanje pitanja u okviru studijskog smera, vidljivo svim studentima nezavisno od fakulteta.**
* **Komentarisanje i lajkovanje pitanja.**
* **Brisanje/Izmena komentara/pitanja.**
* **Obaveštenja.**

[![studyFieldGif470ae0e77426923b.gif](https://s3.gifyu.com/images/studyFieldGif470ae0e77426923b.gif)](https://gifyu.com/image/tOuP)

<br>

### 🏢 University sekcija
* **Postavljanje objava u okviru fakulteta vidljivo samo studentima fakulteta.**
* **Komentarisanje i lajkovanje objava.**
* **Brisanje/Izmena komentara/objava.**
* **Obaveštenja.**
* **Lista svih studenata sa fakulteta.**

[![universityGif.gif](https://s3.gifyu.com/images/universityGif.gif)](https://gifyu.com/image/tOQH)

<br>

### ❗❔ Quiz sekcija
* **Kreiranje kvizova od strane studenata.**
* **Brisanje kviza/pitanja.**
* **Igranje kviza.**

<br>

### Kreiranje Kviza
[[![createQuizGif.gif](https://s3.gifyu.com/images/createQuizGif.gif)](https://gifyu.com/image/tOQh)
* **Kreiranje numeričkih ili tekstualnih pitanja.**
* **Kreiranje 4 ponudjenih odgovora, izabrati koji je tačan.**

<br>

### Igranje Kviza
[![playQuiz.gif](https://s3.gifyu.com/images/playQuiz.gif)](https://gifyu.com/image/tAgN)
* **Prilikom pokretanja kviza ili osvežavanja stranice, pozicije ponudjenih odgovora su naizmenične(nisu na istom mestu).**

<br>

### 🐱‍🏍 Moj Profil sekcija
* **Prikaz informacija o studentu.**
* **Prikaz svih postavljenih pitanja u okviru 'STUDY FIELD' sekcije.**
* **Prikaz svih objava sa početne stranice.**
* **Prikaz i ukupni broj svih prijatelja.**
* **Izmena profila.**

<br>

![myProfile](https://i.ibb.co/vH1YWYQ/my-Profile.png)

<br>

### 👫 Dodavanje prijatelja
[![addFriendGif.gif](https://s3.gifyu.com/images/addFriendGif.gif)](https://gifyu.com/image/tOWP)

<br>

### 🏆 Favorites
* **Prikaz svih 'lajkovanih' pitanja iz sekcije 'STUDY FIELD'.**
* **Prikaz svih 'lajkovanih' objava sa fakulteta.**

![favorites](https://i.ibb.co/mD74nq6/favorites.png)


<br>

## 🔧 Pokretanje & Podešavanje

### Baza podataka

Potrebno je kreirati konekciju sa bazom podataka, u mom slučaju konekcija se zove `learn_together`.

Zatim je potrebno ubaciti sledeci [*SQL FILE*](https://github.com/SavaN222/Learn-Together/blob/main/learnTogether.sql)
### Podesavanje projekta
Otvoriti `application.yml` fajl i zameniti ga odgovarajućim podacima

putanja -> `lokacijaProjekta/src/main/resources/application.yml`

Fajl izgleda ovako:
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

### 🚨 Moguće greške
```yml
-  url: jdbc:mysql://localhost:3306/learn_together
````
**learn_together je naziv konekcije sa bazom, proverite vaš naziv ❗**

```yml
-  username: your_username
-  password: your_password
````
**Unesite vaš username i password sa kojim ostvarujete konekciju sa bazom ❗**

**🚧 U većini slučajeva je username:root a password prazan**

**🌠 Primeri konekcije:**
```yml
-  username: root
-  password: 

-  username: john
-  password: john123
````


**PODEŠAVANJE MEJL ADRESE ❗**

```yml
  # EMAIL
  mail:
    default-encoding: UTF-8
    host: smtp.gmail.com
    username: your@gmail.com
    password: your_email_password
````
**🌠 Primer:**
```yml
  # EMAIL
  mail:
    default-encoding: UTF-8
    host: smtp.gmail.com
    username: johndoe@gmail.com
    password: jdoe555333
````

**GREŠKA: Pristup nije dozvoljen ❓**
```sh
ERROR 11680 --- [nio-8080-exec-7] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is org.springframework.mail.MailAuthenticationException: Authentication failed; nested exception is javax.mail.AuthenticationFailedException: 535-5.7.8 Username and Password not accepted. Learn more at
535 5.7.8  https://support.google.com/mail/?p=BadCredentials u13sm377842ejj.16 - gsmtp
] with root cause

javax.mail.AuthenticationFailedException: 535-5.7.8 Username and Password not accepted. Learn more at
535 5.7.8  https://support.google.com/mail/?p=BadCredentials u13sm377842ejj.16 - gsmtp
```

**Dva najčešća razloga za pojavu ove greške su:**
* Nepravilan unos mejl adrese i šifre.
* Gugl ne dozvoljava pristup stranim aplikacijama.

Da uklonite ovu grešku potrebno je da:
* [*DOZVOLITE MANJE SIGURNIM APLIKACIJAMA PRISTUP VAšEM MEJLU*](https://myaccount.google.com/u/0/lesssecureapps?pli=1&rapt=AEjHL4MmQ2kpWEDZ1wfdbTM6updFNoZ2U3Uo7WNUHe9HpgL8KCzA1i1jncNJayP6ek3Uf2G8morP5YXshkpOkUzNRfa02CUMoQ)
-obratite pažnju koji je korisnik ulogovan na gugl nalog.
  ![guglSecure](https://i.ibb.co/4pYSHtT/Less-secure-app-access.png)
  ovo je potrebno da bi aplikacija preko vašeg mejla slala novo-registrovanim studentima link za potvrdu naloga.
  
### 📂 Putanja za čuvanje slika
U fajlu `StudentService` na putanji: `lokacijaProjekta/src/main/java/com/nakaradasava/learntogether/service/student/StudentService`

promeniti:
```sh 
Linija 82: String windowsPath = "C:\\Users\\Korisnik\\IdeaProjects\\Learn Together\\src\\main\\resources\\static";
```
sa vašom putanjom do static foldera.

📢Linux i Mac operativni sistemi koriste /`forward slash` dok windows koristi \ `backslash`

📢Windows putanja koristi duple znake `\\` zbog 'escape character', Linux i Max koriste normalnu putanju sa jednim znakom `/`

Istu promenu izvršiti u fajlu `UploadConfig` na lokaciji:
`lokacijaProjekta/src/main/java/com/nakaradasava/learntogether/config/UploadConfig`

```sh 
Linija 15: registry.addResourceHandler("/images/**").addResourceLocations("file:C:\\Users\\Korisnik\\IdeaProjects\\Learn Together\\src\\main\\resources\\static\\images\\");
```
#### 📢Linux i Mac = file:Vaša/Putanja/Do/Static
#### 📢Windows = file:C:\\\Vaša\\\Putanja\\\Do\\\Static



### ✅ Pokretanje projekta ✅

Pokretanje projekta mozete izvršiti na dva načina:

#### Pokretanje preko terminala:
Postavite vašu putanju u direktorijum projekta

``` sh
$ pwd
/c/Users/Korisnik/IdeaProjects/Learn Together
```

Ukoliko korsitite Windows operativni sistem pokrenite:
``` sh
$ mvnw spring-boot:run
```
Ukoliko korsitite Linux/Mac operativni sistem pokrenite:
``` sh
$ mvn spring-boot:run
```
Očekivani prikaz:
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
#### Pokretanje preko IDE, npr Intellij
Otvorite projekat u vašem Java radnom okruženju i kliknite na start dugme.
![idepokretanje](https://i.ibb.co/XsVVD5b/ide-Pokretanje.png)

### OSTALO

#### API koji su korišćeni
* **[*Random quote api*](https://github.com/lukePeavey/quotable)**

<br>

#### Nalozi za testiranje
* **Username: male_tester**
* **Username: female_tester**
* **Password: test123**