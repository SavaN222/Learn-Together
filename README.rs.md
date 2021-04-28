# Learn Together 👨‍🎓👩‍🎓
Projekat radjen za **[*🚀 TO THE MOON - prvo online individualno srpsko takmičenje u programiranju*](https://dzimiks.com/contests)**.
<br>
<br>

> [*English Description*](link ka eng opisu)

<[![learnTogetherGif.gif](https://s3.gifyu.com/images/learnTogetherGif.gif)](https://gifyu.com/image/t1ZV)


## 🚩 Navigacija

- [O projektu](#-o-projektu)
- [Pokretanje](#-pokretanje)


## 👨‍💻 O projektu

Learn Together je socijalna mreza koja povezuje studente **svih univerziteta** i omogucava im brzu i laksu razmenu znanja na razne zanimljive i kreatine nacine.

### Registracija

![registracija](https://i.ibb.co/f2L5Kmp/registracija.png)


### Potvrda mejl adrese i aktivacija naloga

![potvrda](https://i.ibb.co/85JDq1n/confirmmail.png)

### Izgled pocetne stranice
* **Prikaz Informacija o studentu.**
* **Naizmenicni citati prilikom osvezavanja stranice.**
* **Objave prijatelja.**

![homepage](https://i.ibb.co/VtxsQsW/homepage.png)

### Study Field sekcija
* **Postavljanje pitanja u okviru studijskom smera, vidljivo svim studentima nezavisno od fakulteta.**
* **Komentarisanje i lajkovanje pitanja.**
* **Brisanje/Editovanje komentara/pitanja.**
* **Obavestenja.**

[![studyFieldGif.gif](https://s3.gifyu.com/images/studyFieldGif.gif)](https://gifyu.com/image/tAc2)

### University sekcija
* **Postavljanje objava u okviru fakulteta vidljivo samo studentima fakulteta.**
* **Komentarisanje i lajkovanje objava.**
* **Brisanje/Editovanje komentara/objava.**
* **Obavestenja.**
* **Lista svih studenata sa fakulteta.**

[![university.gif](https://s3.gifyu.com/images/university.gif)](https://gifyu.com/image/tAct)

### Quiz sekcija
* **Kreiranje kvizova od strane studenata.**
* **Brisanje kviza/pitanja.**
* **Igranje kviza.**

### Kreiranje Kviza
[![create-quiz.gif](https://s3.gifyu.com/images/create-quiz.gif)](https://gifyu.com/image/tAgM)
* **Kreiranje numerickih ili tekstualnih pitanja.**
* **Kreitanje 4 ponudjenih odgovora, izabrati koji je tacan.**

### Igranje Kviza
[![playQuiz.gif](https://s3.gifyu.com/images/playQuiz.gif)](https://gifyu.com/image/tAgN)
* **Prilikom pokretanja kviza ili osvezivanje stranice, pozicije ponudjenih odgovora su naizmenicne(nisu na istom mestu).**

### Moj Profil sekcija
* **Prikaz informacija o studentu.**
* **Prikaz svih postavljenih pitanja u okviru 'STUDY FIELD' sekcije.**
* **Prikaz svih objava sa pocetne stranice.**
* **Prikaz i ukupni broj svih prijatelja.**
* **Izmena profila.**

![myProfile](https://i.ibb.co/vH1YWYQ/my-Profile.png)

### Dodavanje prijatelja
[![add-friend.gif](https://s3.gifyu.com/images/add-friend.gif)](https://gifyu.com/image/tA6M)

### Favorites
* **Prikaz svih 'lajkovanih' pitanja iz sekcije 'STUDY FIELD'.**
* **Prikaz svih 'lajkovanih' objava sa fakulteta.**


![favorites](https://i.ibb.co/mD74nq6/favorites.png)


## 🔧 Pokretanje

TOAST UI products are open source, so you can create a pull request(PR) after you fix issues. Run npm scripts and develop yourself with the following process.

### Setup

Fork `master` branch into your personal repository. Clone it to local computer. Install node modules. Before starting development, you should check to see if there are any errors.

```sh
$ git clone https://github.com/{your-personal-repo}/tui.editor.git
$ cd [project-name]
$ npm install
$ npm run setup:libs
$ cd ./apps/editor
$ npm install
$ npm run test
```

### Develop

You can see your code is reflected as soon as you saving the codes by running a server. Don't miss adding test cases and then make green rights.

#### Run webpack-dev-server

``` sh
$ npm run serve
```

#### Run karma

``` sh
$ npm run test
```

