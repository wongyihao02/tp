# Cai Xubin - Project Portfolio Page

## Project: TASync

TASync is a tool designed to streamline the management of administrative tasks for tutors, including attendance tracking, remarks, and scheduling, with a focus on optimizing the user experience through a command-line interface (CLI).

---

## Overview

Below is a summary of my key contributions to TASync, focusing on the file persistence system, test coverage, and collaborative development.

---

### 👤 Author: Cai Xubin

- **Code Contributions:**  
(https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=xubin0&breakdown=true)

---

### 📦 Summary of Contributions

#### 1. File Persistence Architecture

- **New Features:**

  - **Comprehensive File Saving/Loading System:**
    - **What it does:** Implements a robust, interface-based hierarchy for saving and loading core data structures: `AttendanceList`, `StudentList`, `TaskList`, `TutorialClass`, and `TutorialClassList`.
    - **Justification:** Enables persistence across CLI sessions, allowing TAs to resume usage without data loss. Designed with maintainability and modularity in mind for future extensions.

  - **Classes Implemented:**
    - `StudentFileLoader` / `StudentFileSaver`
    - `StudentListFileLoader` / `StudentListFileSaver`
    - `AttendanceFileFileLoader` / `AttendanceFileFileSaver`
    - `AttendanceListFileLoader` / `AttendanceListFileSaver`
    - `TaskListFileLoader` / `TaskListFileSaver`
    - `TutorialClassFileLoader` / `TutorialClassFileSaver`
    - `TutorialClassListFileLoader` / `TutorialClassListFileSaver`
    - `FileCreator` (ensures directory and file setup)

  - **Interface Contracts:**
    - All loaders implement `FileLoader<T>`
    - All savers implement `FileSaver<T>`
    - Promotes clear contracts, reusability, and future scalability

  - **Design Highlights:**
    - **Layered Hierarchy:** Each loader/saver delegates to the next logical level
      - e.g., `TutorialClassListFileLoader` → `TutorialClassFileLoader` → `StudentListFileLoader` / `TaskListFileLoader`
    - **Centralized Control:** `AttendanceFileFileLoader` reads/writes all attendance data in a single file to reduce complexity
    - **SOLID Design:** Adheres to Single Responsibility and Open-Closed principles

  - **Impact:** 
    - Simplified file handling logic
    - Promoted code modularity and reduced redundancy
    - Improved testability and maintainability of file-related components

---

#### 2. Unit Testing

- **Test Classes Written:**
  - `CommandListPrinterTest`
  - `DateTimeFormatterUtilTest`
  - `IntegerCheckerTest`
  - `UITest`

- **What it does:** Verifies the correctness and robustness of critical utility and UI components.
- **Justification:** Ensures system stability and prevents regressions in frequently used components. Contributed to increased code reliability and maintainability.

---

#### 3. Edge Case Handling

- Implemented logic to:
  - Auto-create missing files/directories
  - Handle empty or malformed CSV rows gracefully
  - Maintain data consistency between runtime memory and persisted storage

---

#### 4. Developer Collaboration

- Worked closely with team members to:
  - Ensure seamless integration with CLI commands
  - Coordinate file naming conventions and data format consistency
  - Review pull requests and provide meaningful feedback

---

Let me know if you'd like a version optimized for resumes or career portfolios, or if you want to add visuals like class diagrams or testing screenshots!
