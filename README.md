#  BudgetNah

![Android](https://img.shields.io/badge/Platform-Android-green)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-purple)
![Architecture](https://img.shields.io/badge/Architecture-MVVM-blue)
![Database](https://img.shields.io/badge/Database-Room-orange)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

#  BudgetNah – Smart Expense Manager

BudgetNah est une application mobile Android développée en Kotlin avec Jetpack Compose, permettant de gérer ses dépenses de manière intelligente, rapide et intuitive.

---

##  Fonctionnalités principales

###  Gestion des dépenses
- Ajouter une dépense (montant, catégorie, date, note)
- Modifier / supprimer une dépense
- Liste dynamique avec RecyclerView (Compose LazyColumn)

###  Catégories personnalisées
- Création de catégories utilisateur
- Catégories par défaut :
  - Food 
  - Transport 
  - Shopping 
  - Bills 

### Statistiques avancées
- Dépenses par catégorie
- Analyse :
  - Journalière
  - Hebdomadaire
  - Mensuelle
  - Trimestrielle
  - Annuelle
- Graphiques (Pie Chart + Trend Chart)

###  Objectifs financiers
- Définir des goals (objectifs)
- Suivi de progression
- Visualisation avec ProgressBar

###  Fonctionnalités intelligentes
-  OCR (scan facture avec ML Kit)
-  Voice Input (ajout via la voix)

###  Synchronisation Cloud
- Authentification utilisateur (Supabase)
- Synchronisation des données
- Mode offline-first (Room + SyncManager)

###  Export des données
- Export PDF (rapport des dépenses)
- Export Excel (analyse avancée)
- Partage de fichiers intégré

###  Expérience utilisateur
- UI moderne avec Jetpack Compose
- Dark Mode 
- Multidevise :
  - XAF (Franc CFA)
  - USD ($)
  - EUR (€)
- UX optimisée (animations, feedback)

---

##  Architecture

BudgetNah est construit avec une architecture moderne inspirée de **Clean Architecture** et du pattern **MVVM (Model-View-ViewModel)**, garantissant une application :

-  Modulaire
-  Performante
-  Scalable
-  Testable

---

##  Vue globale

L’application est divisée en deux grandes couches :


---

##  Couche Data

```
data/
├── local/
│   ├── dao/           → interfaces Room (ExpenseDao, CategoryDao...)
│   ├── entities/      → modèles de base de données
│   ├── database/      → configuration Room DB
│
├── remote/
│   ├── SupabaseClient.kt → configuration API Supabase
│
├── repository/
│   ├── ExpenseRepositoryImpl.kt
│   ├── AuthRepositoryImpl.kt
│   → source unique de données (Single Source of Truth)
│
├── sync/
│   ├── SyncManager.kt → gestion synchronisation offline-first
│
├── export/
│   ├── PdfExporter.kt
│   ├── ExcelExporter.kt
│
├── ocr/
│   ├── OcrManager.kt → traitement OCR (ML Kit)
│
├── voice/
│   ├── VoiceManager.kt → reconnaissance vocale
```

###  Rôle de la couche Data
- Gestion des données locales (Room)
- Synchronisation cloud (Supabase)
- Export (PDF / Excel)
- OCR & Voice processing
- Logique métier centralisée

---

##  Couche Presentation

```
presentation/
├── core/
│   ├── navigation/    → NavGraph (navigation app)
│   ├── theme/         → couleurs, typography, dark mode
│   ├── components/    → composants UI réutilisables
│   └── utils/         → helpers (parsers OCR, voice, fichiers)
│
├── features/
│   ├── auth/
│   │   ├── AuthScreen.kt
│   │   ├── AuthViewModel.kt
│   │
│   ├── dashboard/
│   │   ├── DashboardScreen.kt
│   │   ├── DashboardViewModel.kt
│   │   └── components/
│   │        ├── BalanceCard.kt
│   │        ├── PieChart.kt
│   │        ├── TrendChart.kt
│   │
│   ├── expenses/
│   │   ├── ExpenseScreen.kt
│   │   ├── AddExpenseScreen.kt
│   │   ├── ExpenseViewModel.kt
│   │   └── components/
│   │
│   ├── categories/
│   │   ├── CategoriesScreen.kt
│   │   ├── CategoriesViewModel.kt
│   │
│   ├── statistics/
│   │   ├── StatisticsScreen.kt
│   │   └── components/
│   │
│   ├── goals/
│   │   ├── GoalsScreen.kt
│   │   ├── GoalViewModel.kt
│   │   └── components/
│   │
│   ├── settings/
│   │   ├── SettingsScreen.kt
│   │   ├── SettingsViewModel.kt
```

---

##  Flux de données

```
UI (Compose)
   ↓
ViewModel
   ↓
Repository
   ↓
Room (local) + Supabase (cloud)
```

###  Principe clé
 **Single Source of Truth = Repository**

---

## Concepts clés utilisés

-  **MVVM** → séparation UI / logique
-  **StateFlow / Compose State** → UI réactive
-  **Offline-first** → fonctionne sans internet
##  Concepts clés utilisés

-  **MVVM** → séparation UI / logique
-  **StateFlow / Compose State** → UI réactive
- **Offline-first** → fonctionne sans internet
-  **SyncManager** → synchronisation intelligente
-  **Auth Supabase** → gestion utilisateur
-  **Modularisation par feature**

---

## Avantages de cette architecture

-  Facile à maintenir
-  Prête pour scaling (web + backend)
-  Testable
-  Réutilisable
-  Structure utilisée en entreprise

---


##  Technologies utilisées

- Kotlin
- Jetpack Compose
- Room Database
- Hilt (Dependency Injection)
- Supabase (Auth + Cloud Sync)
- ML Kit (OCR)
- SpeechRecognizer (Voice Input)
- Apache POI (Excel)
- iText (PDF)
- CameraX

---

##  Performance & Best Practices

- Coroutines + Dispatchers.IO
- Offline-first architecture
- Lazy loading (Compose)
- Separation of concerns
- Reusable components

---

## Installation

###  Cloner le repository

```bash
git clone https://github.com/Haouabarkallah/BudgetNahPro_0.git
```

###  Ouvrir le projet

Ouvrir le projet dans **Android Studio**.

### Synchroniser les dépendances

Android Studio synchronisera automatiquement les dépendances Gradle.

###  Lancer l'application

Exécuter l’application sur :

* un **émulateur Android**
* ou un **appareil physique**

---

#  Améliorations futures

*  Clipboard (copier-coller rapide)
* Parsing automatique (montant + catégorie)
* Gestion multi-utilisateurs (famille)
* AI catégorisation automatique
* Notifications intelligentes
* Analytics avancés
  

---

#  Contribution

Les contributions sont les bienvenues.

1. Fork le projet
2. Créer une branche

```
git checkout -b feature/nouvelle-fonctionnalite
```

3. Commit les modifications

```
git commit -m "Ajout d'une nouvelle fonctionnalité"
```

4. Push la branche

```
git push origin feature/nouvelle-fonctionnalite
```

5. Ouvrir une **Pull Request**

---

#  Licence

Ce projet est distribué sous licence **MIT**.

---

# Auteur

Projet développé dans le cadre d’un apprentissage du **développement d’applications Android**.

---
