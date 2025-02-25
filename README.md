# Travail de Bachelor : SWIMGOALS

## 1. Présentation du projet

### Description du projet

SwimGoals est une application web spécifiquement dédiée aux clubs de natation, conçue pour optimiser et numériser la gestion des séances d’entraînement et des performances des nageurs.

Les entraîneurs créent leurs groupes d’entraînement et les nageurs rejoignent leur groupe respectif. L’entraîneur peut également de personnaliser les programmes d’entraînement en fonction des besoins spécifiques du nageur renforçant ainsi la relation entraîneur-nageur grâce à un suivi continu et optimisé.

Une fois chaque objectif atteint, l’entraîneur enregistre la performance du nageur via un chronomètre numérique, générant automatiquement un histogramme pour suivre les progrès.

### Technologies utilisées

- **Front-end :** NextJS + TypeScript
- **Back-end :** Spring Boor (version Java 21)
- **Base de données :** MySQL

### Dépendances utilisées

- **Back-end :**
  - `Spring Data JPA`
  - `Spring Web`
  - `Spring Security`
  - `MySQL Driver`

### Répartition des différentes couches 3-tiers

- **/swimgoals_backend:** contient le back-end de l'application et la configuration
- **/swimgoals_frontend:** contient le front-end de l'application et la configuration
- **/swimgoals_database:** contient le script SQL pour la création de la base de données
