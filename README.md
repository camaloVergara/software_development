# Software Development – Portfolio Projects

**Roskilde University · Autumn 2026**

This repository contains my three portfolio projects for the *Software Development* course, part of my B.Sc. in Computer Science and Mathematics at Roskilde University (RUC). The projects go from object-oriented design in Java, to a full desktop application with a database, to classic graph algorithms.

## Projects

| # | Project | What it covers | Tech |
|---|---------|----------------|------|
| 1 | [Portfolio 1 – Object-Oriented Programming](portfolio-1/) | Class design, encapsulation, inheritance and polymorphism | Java |
| 2 | [Portfolio 2 – JavaFX Desktop App](portfolio-2/) | GUI application backed by a relational database | Java, JavaFX, SQLite, JDBC, Maven |
| 3 | [Portfolio 3 – Graph Algorithms](portfolio-3/) | BFS, DFS, minimum spanning tree and Dijkstra's shortest path | Java |

Each folder has its own README with a description of the project and instructions for running it.

### Portfolio 1 – Object-Oriented Programming
A Java program built around object-oriented principles: modelling a problem with classes and interfaces, keeping responsibilities separated, and writing code that is easy to extend.

### Portfolio 2 – JavaFX Desktop App
A desktop application with a JavaFX user interface and an SQLite database, connected through JDBC. The project is built and run with Maven.

### Portfolio 3 – Graph Algorithms
Implementations of fundamental graph algorithms on weighted and unweighted graphs:
- **Breadth-first search (BFS)** and **depth-first search (DFS)** for traversal
- **Minimum spanning tree (MST)**
- **Dijkstra's algorithm** for shortest paths

## Repository structure

```
.
├── README.md
├── portfolio-1/    # Object-oriented programming in Java
├── portfolio-2/    # JavaFX + SQLite desktop app (Maven project)
└── portfolio-3/    # Graph algorithms
```

## Requirements

- **Java** (JDK 17 or newer)
- **Maven** (for Portfolio 2)

## Getting started

Clone the repository:

```bash
git clone https://github.com/camaloVergara/software_development.git
cd software_development
```

Run Portfolio 2 (JavaFX app):

```bash
cd portfolio-2
mvn clean javafx:run
```

Portfolios 1 and 3 can be opened and run directly in IntelliJ IDEA (or any Java IDE) from their `Main` class.

## Author

**Camilo Vergara Muñoz**
B.Sc. student in Computer Science and Mathematics, Roskilde University
[LinkedIn](https://www.linkedin.com/in/cvergaramunoz) · [GitHub](https://github.com/camaloVergara)
