# Travail de Bachelor : SWIMOALS - Couche Backend

## Technologies Utilisées

- **Spring Boot** : Framework Java pour créer des applications web et backend.
- **Spring Security** : Pour la gestion de la sécurité (authentification et autorisation).
- **Spring Data JPA** : Pour interagir avec la base de données.
- **Spring Boot Starter Web** : Pour exposer des services web.
- **Spring Boot Starter Data REST** : Pour exposer automatiquement des APIs REST à partir des entités JPA.

## Prérequis

- **Java 21** :
  Véerifiez la version de Java installée sur votre machine en exécutant la commande suivante dans un terminal :
  - `java -version`
- Gradle pour la gestion des dépendances.
- MySQL comme la base de données relationnelles.

## Installation

Clonez le dépôt et installez les dépendances :

```bash
git clone <https://github.com/FaresBza/swimgoals_travail_bachelor.git>
cd swimgoals_backend
./gradlew build
./gradlew bootRun
```

- L'API REST est accessible à l'adresse suivante : `http://localhost:8080`
- La documentation Swagger sera accessible à l'adresse : `http://localhost:8080/swagger-ui/index.html#/`
