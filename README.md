# Guide de survie sur Manek

Bienvenue dans le guide de survie sur Manek ! Cette application en ligne de commande est conçue pour aider les voyageurs à naviguer, se préparer et survivre dans l'environnement unique de Manek.

## 📋 Table des matières

- [Description](#description)
- [Fonctionnalités](#fonctionnalités)
- [Architecture](#architecture)
- [Installation](#installation)
- [Utilisation](#utilisation)
- [Structure du projet](#structure-du-projet)
- [Technologies](#technologies)

## 📖 Description

"Guide de survie sur Manek" est une application console Java qui fournit des outils essentiels pour survivre et s'épanouir dans un nouvel environnement. L'application inclut un catalogue d'équipement, une carte interactive en terminal, des conseils pratiques et un système d'événements.

## ✨ Fonctionnalités

- 🗺️ **Carte** : Visualisez les lieux importants et les chemins dans le terminal
- 📦 **Catalogue d'équipement** : Consultez les vêtements, nourriture et autres équipements essentiels
- 🛍️ **Boutique** : Achetez des articles
- 🎉 **Événements** : Découvrez les événements en cours et à venir
- 👤 **Profil utilisateur** : Gérez votre profil, vos véhicules et vos capsules de secours
- 🎽 **Gestion des tenues** : Composez des tenues adaptées à différentes situations
- ⌨️ **Interface interactive** : Menu de navigation simple et intuitif

## 🏗️ Architecture

L'application suit une architecture modulaire basée sur des modèles métier :

```
├── Contrôleurs / Gestionnaires
│   ├── Page_controleur.java       # Routage et menu principal
│   ├── Gestionnaire_Events.java   # Gestion des événements
│   └── Runner.java                # Point d'entrée
├── Modèles
│   ├── Utilisateur.java           # Profil utilisateur
│   ├── Lieu.java                  # Lieux de la carte
│   ├── Vetement.java              # Articles vestimentaires
│   ├── Nourriture.java            # Articles alimentaires
│   ├── Vehicules.java             # Moyens de transport
│   ├── Capsule.java               # Capsules de secours
│   ├── Evenement.java             # Événements
│   ├── Tenue.java                 # Combinaisons de vêtements
│   ├── Catalogue.java             # Catalogue d'articles
│   ├── Carte.java                 # Gestion de la carte
│   ├── Boutique.java              # Gestion de la boutique
│   ├── Stockables.java            # Interface pour articles stockables
│   ├── Conseils.java              # Système de conseils
│   ├── Chemin.java                # Chemins entre lieux
│   ├── Coordonnees.java           # Coordonnées géographiques
│   ├── Type_Evenement.java        # Types d'événements
│   └── TYPE_VETEMENT.java         # Énumération des types de vêtements
└── Resources
    └── application.properties      # Configuration
```

## 🚀 Installation

### Prérequis

- Java 21+
- Maven 3.6+

### Étapes

1. **Clonez le dépôt**
```bash
git clone https://github.com/Elgoogko/HackatonJr2025.git
cd HackatonJr2025/hackatonjr
```

2. **Compilez le projet**
```bash
mvn clean install
```

3. **Lancez l'application**
```bash
mvn exec:java -Dexec.mainClass="com.main.hackatonjr.Runner"
```

Ou directement avec Java :
```bash
java -cp target/classes:target/lib/* com.main.hackatonjr.Runner
```

## 📱 Utilisation

### Menu principal

L'application propose un menu interactif avec les options suivantes :

```
╔═══════════════════════════════════════╗
║   Guide de Survie sur Manek          ║
╚═══════════════════════════════════════╝

1. Consulter la Carte
2. Explorer le Catalogue
3. Accéder à la Boutique
4. Lire les Conseils
5. Gérer mon Profil
6. Voir les Événements
7. Composer une Tenue
8. Quitter
```

### Exemple d'utilisation

```bash
$ java -cp target/classes:target/lib/* com.main.hackatonjr.Runner

```

## 📁 Structure du projet

```
hackatonjr/
├── src/
│   ├── main/
│   │   ├── java/com/main/hackatonjr/
│   │   │   ├── Modèles (*.java)
│   │   │   ├── Page_controleur.java
│   │   │   ├── Runner.java
│   │   │   ├── Test.java
│   │   │   └── HackatonjrApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/                    # Tests unitaires
├── pom.xml                          # Dépendances Maven
└── target/                          # Fichiers compilés

```

## 🛠️ Technologies

- **Backend** : Java 21
- **Framework** : Spring Boot 3.2.0 (optionnel pour configuration)
- **Build** : Maven
- **Interface** : Terminal/Console

## 📦 Dépendances principales

```xml
<!-- Spring Boot Starters (optionnel) -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

## 🎯 Fonctionnement des modules clés

### Utilisateur
Gère le profil, l'inventaire et l'état de chaque voyageur. Sauvegarde et charge les données.

### Catalogue & Boutique
Système complet de gestion d'articles et de transactions commerciales affichées dans le terminal.

### Carte & Chemin
Navigateur spatial avec affichage ASCII art et calcul de trajets entre lieux.

### Événements
Système d'événements dynamiques qui affectent la survie et s'affichent en temps réel.

### Tenues
Assemblage personnalisé de vêtements pour différentes situations, affichable dans le profil.

## 🎨 Affichage terminal

L'application utilise des caractères spéciaux pour un meilleur affichage :

```
✓ Symboles d'état
✗ Erreurs/Avertissements
► Menu et navigation
║ Lignes
═ Séparations
╔ ╗ ╚ ╝ Cadres
```

## 🤝 Contribution

Pour contribuer au projet :
1. Forkez le dépôt
2. Créez une branche pour votre fonctionnalité (`git checkout -b feature/AmazingFeature`)
3. Committez vos changements (`git commit -m 'Add some AmazingFeature'`)
4. Poussez vers la branche (`git push origin feature/AmazingFeature`)
5. Ouvrez une Pull Request

## 📝 Licence

Ce projet a été créé dans le cadre du HackatonJr 2025.

## 👥 Auteurs

- Équipe HackatonJr 2025 

## 📞 Support

Pour toute question ou problème, veuillez ne pas ouvrir d'issue sur le dépôt GitHub.

---

**Bon voyage sur Manek ! Que la survie soit avec vous ! 🌍**
