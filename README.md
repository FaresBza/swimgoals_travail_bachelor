# Travail de Bachelor : SWIMGOALS

# Projet SwimGoals

Bienvenue dans le projet **SwimGoals** !  
Ce document explique étape par étape comment configurer et lancer le projet complet (backend + frontend) sur votre machine.

## Prérequis

Avant de commencer, merci de vérifier que vous disposez bien des outils suivants installés et à jour :

- **Node.js** (v18.18.0 recommandée via NVM)
- **NPM** (gestionnaire de paquets de Node.js : v9.8.1)
- **NVM** (Node Version Manager)
- **Java JDK** (version compatible avec Gradle / Spring Boot, JDK 21 recommandé)
- **MySQL** (avec accès à PhpMyAdmin, Workbench ou autre interface de gestion)

### Comment vérifier vos versions ?

Dans un terminal, vous pouvez vérifier les versions avec ces commandes :

```bash
node -v       # Version de Node.js
npm -v        # Version de npm
nvm --version # Version de NVM
java -version # Version de Java
```

## Cloner le dépôt

Pour commencer, clonez le dépôt Git du projet SwimGoals :

```bash
git clone https://github.com/FaresBza/swimgoals_travail_bachelor.git
```

## Configuration de la base de données MySQL

- Ouvrez votre outil de gestion MySQL (PhpMyAdmin, MySQL Workbench, etc.).
- Créez une base de données nommée swimgoals_bdd.
- Allez le dossier [swimgoals_database](https://github.com/FaresBza/swimgoals_travail_bachelor/tree/main/swimgoals_database),
- Ouvrez le fichier data.sql.
- Copiez tout le contenu SQL et collez-le dans un nouvel onglet SQL de votre outil, puis exécutez-le pour importer les tables et données nécessaires.

## Configuration du backend (Spring Boot)

- Ouvrez le répertoire swimgoals_backend avec IntelliJ IDEA ou votre IDE Java préféré.
- Allez dans le fichier src/main/java/com/swimgoals/ressources/applications.properties.
- Modifiez les paramètres de connexion à la base de données selon votre configuration MySQL :

```bash
spring.application.name=swimgoals

spring.datasource.url=jdbc:mysql://localhost:8889/swimgoals_bdd
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.defer-datasource-initialization=true
spring.jpa.show-sql=true
```

- Pensez à adapter :

  - Le port dans spring.datasource.url (ex : 8889 ou 3306 selon votre installation)
  - Le spring.datasource.username (ex : root ou autre)
  - Le spring.datasource.password

- Ouvrez un terminal dans le dossier swimgoals_backend et lancez les commandes suivantes une par une :

```bash
./gradlew build
./gradlew bootRun
```

- Si tout est correct, vous pourrez accéder à la documentation Swagger via cette URL dans votre navigateur :
  http://localhost:8080/swagger-ui/index.html

## Configuration du frontend (Next.js + React)

- Ouvrez le répertoire swimgoals_frontend avec Visual Studio Code (ou votre éditeur préféré).
- Ouvrez un terminal dans ce dossier et exécutez ces commandes dans l’ordre :

```bash
npm install                           # Installe les dépendances du projet
nvm install v18.18.0                  # Installe la version Node recommandée via NVM
nvm use v18.18.0                      # Utilise cette version Node localement
npm install -D sass                   # Installe sass pour le style
npm install chart.js react-chartjs-2  # Installe les librairies graphiques
npm run dev                           # Lance le serveur de développement Next.js
```

- Une fois lancé, ouvrez votre navigateur à l’adresse : http://localhost:3000
