# Portfolio 3 – Graph Algorithms for Course Scheduling

Third portfolio assignment for the **Software Development** course at **Roskilde University** (5th semester, autumn 2026).

The project uses graph algorithms to analyse a real scheduling problem: placing the third-semester subject module courses of a bachelor's programme into timeslots so that no student has two courses at the same time.

## The problem

In the third semester, each student takes courses from **two different subject modules**. There are **27 subject modules**, and there are not enough timeslots in a week to give each one its own slot. Modules that share no students can be taught at the same time, so the task is to find out which modules can share a timeslot.

This is modelled as a weighted, undirected graph:

| Graph concept | Meaning |
|---|---|
| Vertex | A subject module (and its third-semester course) |
| Edge between A and B | At least one student takes both A and B |
| Edge weight | Number of students with the combination A + B |
| Degree of a vertex | Number of other modules it shares students with |

Two modules connected by an edge **cannot** be scheduled in the same timeslot. In graph theory terms, a valid schedule is a **graph colouring**, where each colour is a timeslot.

## What the program does

1. **Reads and prints the graph** from `combi.txt`, storing each combination as a bidirectional edge.
2. **Traverses the graph** with depth-first search starting from `DATA`, and compares the number of visited modules with the total number of modules to check whether the graph is connected.
3. **Lists all subject modules sorted by their number of combinations** (the degree of each vertex), using `Collections.sort` with a lambda comparator.
4. **Checks a suggested schedule.** A proposal of 12 timeslots, each containing a group of modules, is stored as a two-dimensional array. The program checks every pair of modules within each group and reports whether the schedule is a solution, meaning no group contains two modules with overlapping students.
5. **Counts students in adjacent timeslots.** The 12 timeslots are spread over a five-day week. For each day, the program sums the number of students who have courses in two consecutive timeslots. The calculation is then repeated after randomly reordering the groups with `Collections.shuffle`, to compare how the ordering affects the result.

## Project structure

```
Portfolio3/
├── src/
│   ├── Portfolio3.java       # Main program – my own work
│   ├── GraphAlgorithms.java  # Provided by the course
│   └── Graphs.java           # Provided by the course
└── combi.txt                 # Input data: module combinations
```

### Authorship

- **`Portfolio3.java`** is my own work and contains the solutions to the assignment.
- **`Graphs.java`** and **`GraphAlgorithms.java`** were provided as part of the course material and are included unchanged so the project can run.

### Provided code overview

- `Graphs.java` defines `Vertex`, `Edge`, the `Graph` interface, the abstract class `AbstractGraph`, and four implementations: `EdgeGraph`, `AdjListGraph`, `AdjMapGraph` and `MatrixGraph`.
- `GraphAlgorithms.java` contains path length calculation, comparators and sorting helpers, breadth-first and depth-first traversal, Prim's minimum spanning tree, Dijkstra's shortest paths, and file input/output.

This project uses the `MatrixGraph` implementation, created with room for the 27 subject modules.

## Input format

`combi.txt` is a comma-separated file with one combination per line:

```
<module>, <module>, <number of students>
```

Each line is inserted into the graph in both directions.

## Requirements

- Java 21 or newer (developed with Temurin JDK 23)
- IntelliJ IDEA, or any Java IDE / the command line

## How to run

The program opens `combi.txt` using a relative path, so the file must be in the **working directory** when the program starts.

**In IntelliJ IDEA:** place `combi.txt` in the project root (next to the `src` folder), or set the working directory under *Run → Edit Configurations… → Working directory*. Then run `Portfolio3`.

**From the command line**, in the folder containing `combi.txt`:

```bash
javac -d out src/*.java
java -cp out Portfolio3
```

## Concepts practised

- Interfaces, abstract classes and polymorphism (`Graph`, `AbstractGraph` and its implementations)
- The Java Collections Framework: `Collection`, `List`, `Set`, `Map`, `ArrayList`, `HashSet`
- Sorting with `Collections.sort`, comparators and lambda expressions
- Graph traversal (depth-first and breadth-first search)
- Nested loops for pairwise checks, and multidimensional arrays (2D and 3D)
- Graph modelling of a real-world scheduling problem

## Author

Camilo Vergara Muñoz – B.Sc. Computer Science and Mathematics, Roskilde University
