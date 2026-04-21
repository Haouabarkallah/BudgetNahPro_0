#  BudgetNah – Smart Expense Manager

BudgetNah est une application mobile Android moderne développée en Kotlin avec Jetpack Compose, permettant de gérer ses dépenses de manière intelligente, rapide et intuitive.

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

##  Concepts clés utilisés

-  **MVVM** → séparation UI / logique
-  **StateFlow / Compose State** → UI réactive
- **Offline-first** → fonctionne sans internet
-  **SyncManager** → synchronisation intelligente
-  **Auth Supabase** → gestion utilisateur
-  **Modularisation par feature**

---

##  Avantages de cette architecture

-  Facile à maintenir
-  Prête pour scaling (web + backend)
-  Testable
-  Réutilisable
-  Structure utilisée en entreprise

---

##  Évolution possible

-  Web App (React + Supabase)
-  AI catégorisation automatique
-  Notifications intelligentes
- Analytics avancés
-  Clipboard (copier-coller rapide)
- Parsing automatique (montant + catégorie)

