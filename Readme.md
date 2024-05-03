# Guide d'exécution du projet (Psi_backend)

Ce guide fournit des instructions détaillées sur la façon d'exécuter ce projet Spring Boot sur votre machine locale.

## Prérequis

Avant de commencer, assurez-vous que votre environnement de développement répond aux critères suivants :

- Java JDK installé (version 8 ou supérieure)
- Xammp installé et lancé ou un environnement mysql lancé et sur le port 3306
- Git installé (facultatif, mais recommandé pour cloner le dépôt)

## Étapes d'exécution

Suivez ces étapes pour exécuter le projet Spring Boot sur votre machine :

### 1. Cloner le dépôt (facultatif)

Si vous n'avez pas encore cloné le dépôt, utilisez la commande suivante pour le cloner :

```bash
git clone https://github.com/DavePhil/project_psi.git
```

### 2. Se placer à la racine du projet dans un terminal
Ouvrez le fichier clonez ou alors le fichier dezippé
Si vous n'êtes pas à la racine du projet exécutez la commande :
```bash
cd project_psi 
```
ou alors fonction de ou vous situez dans votre terminal situez vous au bon emplacement.

### 3. Compiler le projet

Exécutez la commande Maven pour compiler le projet :

```bash
mvn clean install
```

### 4. Exécuter le projet   

Une fois la compilation terminée, exécutez le projet à l'aide de la commande suivante :

```bash
java -jar target/psi-backend.jar

```
### 5. Accéder à l'application

Une fois que le projet est en cours d'exécution, accédez à l'application dans votre navigateur Web en ouvrant l'URL suivante :
(Notez que vous ne pouvez pas avoir accès aux requêtes directement sans connexion sur un compte du système. On ne peut accéder ainsi qu'à la documentation)
le lien est: http://localhost:4000/api/auth/ (en local) et https://panafrican-space.net/api/auth/ (en production)

