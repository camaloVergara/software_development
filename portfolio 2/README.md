# Group Project Formation

A desktop application for forming project groups at university. Students register, sign up for study activities, and then either create a new project group or join an existing one for that activity.

Built with **JavaFX** for the user interface and **SQLite** for storage, following the **Model–View–Controller (MVC)** pattern.

Portfolio 2 for the Software Development course at Roskilde University (autumn 2026).

## Features

- **Register a student** by typing a name and pressing Enter.
- **Sign up for a study activity** (e.g. *Basic Project 1*, *Project Module 2*, *Bachelor Project*).
- **Show project groups** for a selected activity, listed in a text area and in a drop-down.
- **Join an existing group** in an activity the student is signed up for.
- **Create a new project group** with a title and a supervisor; the creator becomes its first member.
- **Input validation with pop-up alerts**, for example:
  - missing student name, activity, group, title or supervisor
  - signing up twice for the same activity
  - joining a group without being signed up for the activity
  - joining a second group in the same activity

## Architecture

The code is split into MVC parts so the user interface, the logic and the database are kept separate.

| Class | Role |
|---|---|
| `Launcher` | Entry point. Creates the View, Model and Controller and shows the window. |
| `View` | Builds the JavaFX layout (`GridPane`s inside a `BorderPane`) and holds the controls. Contains no logic or SQL. |
| `Controller` | Attaches the event listeners to the View's buttons and fields, validates input and calls the Model. |
| `Model` | Creates the database tables and contains all queries and inserts. |
| `MyDB` | Small JDBC helper for opening the SQLite connection and running commands and queries. Provided by the course (see Acknowledgements). |

## Database

The data is stored in an SQLite file (`identifier.sqlite`) in the project's root folder. On start-up, the application:

- creates the tables if they don't exist,
- inserts the six study activities into `studyActivity` (skipped if they are already there),
- turns on foreign key enforcement (`PRAGMA foreign_keys = ON`).

The activity drop-downs in the window are filled from the `studyActivity` table, so the database is the only place the activity names are defined.

| Table | Columns | Description |
|---|---|---|
| `student` | `name` (PK) | Registered students |
| `studyActivity` | `name` (PK) | Available study activities |
| `signUp` | `nameStud` (PK, FK), `nameAct` (PK, FK) | Which student is signed up for which activity |
| `projectGroup` | `title` (PK), `supervisor`, `activity` (FK) | Project groups and the activity each belongs to |
| `groupMember` | `nameStud` (PK, FK), `title` (PK, FK) | Which student is a member of which group |

`signUp` and `groupMember` are link tables for the many-to-many relationships between students and activities, and between students and groups. Each uses a composite primary key, so the same pair can't be stored twice.

## Requirements

- JDK 17 or newer (developed with Temurin 23)
- Maven (IntelliJ IDEA's bundled Maven works)
- Dependencies, downloaded automatically by Maven:
  - JavaFX 21 (`javafx-controls`, `javafx-fxml`)
  - SQLite JDBC driver (`org.xerial:sqlite-jdbc`)

## How to run

**In IntelliJ IDEA**

1. Clone the repository and open the project folder.
2. Let IntelliJ load the Maven project (or click *Reload* in the Maven panel).
3. Run `Launcher.java`.

**From the command line**

```bash
mvn clean javafx:run
```

## How to use

1. Type your name in **Student name** and press Enter to register.
2. Choose an activity under **Select Study Activity** and click **Sign Up**.
3. On the right, choose the same activity and click **Show groups**.
4. Either pick a group from the drop-down and click **Join**, or enter a **Title** and **Supervisor** and click **Create group**.

## Project structure

```
src/main/java/org/example/portfolio2x/
├── Launcher.java
├── View.java
├── Controller.java
├── Model.java
└── MyDB.java
```

## Acknowledgements

The `MyDB` class was provided as part of the Software Development course at Roskilde University and is not my own work. All other classes were written by me.

## Author

Camilo Vergara Muñoz, B.Sc. Computer Science and Mathematics, Roskilde University
