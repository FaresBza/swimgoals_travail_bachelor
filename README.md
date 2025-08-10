# Travail de Bachelor : SWIMGOALS

# Projet SwimGoals

Bienvenue dans le projet **SwimGoals** !  
Ce document explique étape par étape comment configurer et lancer le projet complet (backend + frontend) sur votre machine.

## Sommaire

1. [Prérequis](#prérequis)
2. [1. Cloner le projet](#1-cloner-le-projet)
3. [2. Configuration de la base de données MySQL](#2-configuration-de-la-base-de-données-mysql)
4. [3. Configuration du backend (Spring Boot)](#3-configuration-du-backend-spring-boot)
5. [4. Configuration des tests (JUnit + H2)](#4-configuration-des-tests-junit--h2)
6. [5. Configuration du frontend (Next.js + React + TypeScript)](#5-configuration-du-frontend-nextjs--react--typescript)
7. [6. Remarques](#6-remarques)

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

## 1. Cloner le projet

Pour commencer, clonez le dépôt Git du projet SwimGoals :

```bash
git clone https://github.com/FaresBza/swimgoals_travail_bachelor.git
```

## 2. Configuration de la base de données MySQL

- Ouvrez votre outil de gestion MySQL (PhpMyAdmin, MySQL Workbench, etc.).
- Créez une base de données nommée swimgoals_bdd.
- Allez le dossier [swimgoals_database](https://github.com/FaresBza/swimgoals_travail_bachelor/tree/main/swimgoals_database),
- Ouvrez le fichier data.sql.
- Copiez tout le contenu SQL et collez-le dans un nouvel onglet SQL de votre outil, puis exécutez-le pour importer les tables et données nécessaires.

## 3. Configuration du backend (Spring Boot)

- Ouvrez le répertoire swimgoals_backend avec IntelliJ IDEA (recommandé).
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

## 4. Configuration des tests (JUnit + H2)

- Le projet utilise H2 pour les tests unitaires et d'intégration.
  Assurez-vous que le fichier src/test/resources/application-test.properties contient les bonnes configurations :

```bash
spring.datasource.url=jdbc:h2:mem:swimgoals_bdd_test;MODE=MYSQL
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.defer-datasource-initialization=true

spring.h2.console.enabled=true
spring.jpa.show-sql=true
spring.sql.init.mode=always
```

Comme pour la configuration du backend, vous pouvez adapter les paramètres de test/ressources/application.properties si nécessaires :

- Le spring.datasource.username (ex : root ou autre)
- Le spring.datasource.password

- Pour lancer les tests unitaires, ouvrez un terminal dans le dossier swimgoals_backend et exécutez :

```bash
./gradlew test
```

## 5. Configuration du frontend (Next.js + React + TypeScript)

- Ouvrez le répertoire swimgoals_frontend avec Visual Studio Code.
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

## Configuration Next.js avec TypeScript :

Si le projet ne contient pas encore les fichiers TypeScript, Next.js détecte automatiquement TypeScript à partir des fichiers .ts ou .tsx. Voici les étapes pour s’assurer que TypeScript est bien configuré :

- Créez un fichier tsconfig.json à la racine du projet (Next.js en génère un automatiquement si nécessaire).
- Assurez-vous que les dépendances TypeScript sont installées :

```bash
npm install --save-dev typescript @types/react @types/node
```

- Le fichier tsconfig.json minimal ressemble à ceci (généré automatiquement par Next.js, mais voici un exemple) :

```bash
{
  "compilerOptions": {
    "target": "ES2017",
    "lib": ["dom", "dom.iterable", "esnext"],
    "allowJs": true,
    "skipLibCheck": true,
    "strict": true,
    "noEmit": true,
    "esModuleInterop": true,
    "module": "esnext",
    "moduleResolution": "bundler",
    "resolveJsonModule": true,
    "isolatedModules": true,
    "jsx": "preserve",
    "incremental": true,
    "plugins": [
      {
        "name": "next"
      }
    ],
    "paths": {
      "@/*": ["./src/*"]
    }
  },
  "include": ["next-env.d.ts", "**/*.ts", "**/*.tsx", ".next/types/**/*.ts"],
  "exclude": ["node_modules"]
}

```

## 6. Remarques

- Merci de toujours utiliser la version Node recommandée (v18.18.0) pour éviter les problèmes de compatibilité.
- Le backend doit tourner sur le port 8080 (Spring Boot) et le frontend sur le port 3000 (Next.js).
- En cas d’erreur lors de la connexion à la base MySQL, vérifiez bien vos paramètres dans applications.properties.
