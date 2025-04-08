# Li XiaoNa - Project Portfolio Page

## Project: TASync
TASync is a tool designed to streamline the management of administrative tasks for tutors, including attendance tracking,
remarks, and scheduling, with a focus on optimizing the user experience through a command-line interface (CLI).

## Overview
Given below are my contributions to the project.

## Summary of Code Contributions
### 1. Student Commands

#### New Features

- **Add/Delete Student**
  - **What it does:** Allows the user to easily add or delete a student from an existing tutorial class.
  - **Justification:** Helps TAs efficiently manage student records, ensuring an up-to-date and accurate class roster.
  - **Depth of work:** Required implementing checks to ensure students are not duplicated or accidentally deleted.
  - **Challenge:** Involved designing defensive parsing and robust error handling to avoid data corruption.

- **Find Student**
  - **What it does:** Enables users to search for a student using partial names or matric numbers.
  - **Justification:** Especially useful in large classes where students may share similar names.
  - **Depth of work:** Implemented substring matching and case-insensitive search.
  - **Challenge:** Optimizing for accuracy while keeping the search responsive and informative.

- **Change Student Remark**
  - **What it does:** Allows the user to update or overwrite remarks in a student’s record.
  - **Justification:** Helps TAs track academic progress and behavior over time.

- **Check Student Remark**
  - **What it does:** Allows the user to view existing remarks for a student.
  - **Justification:** Enables quick access to historical records during tutorials or consultations.

- **List Students in Tutorial**
  - **What it does:** Lists all students in a given tutorial, with full personal information.
  - **Justification:** Gives a comprehensive snapshot for attendance, contact, and planning.


### 2. Tutorial Commands

#### New Features

- **Add/Delete Tutorial**
  - **What it does:** Lets users add or remove tutorials from the master list.
  - **Justification:** Maintains an accurate reflection of the TA’s real teaching schedule.
  
- **List Existing Tutorials**
  - **What it does:** Displays all created tutorials sorted by day and time.
  - **Justification:** Helps the TA stay organized and track tutorial sessions.


### 3. Help Command

- **Improvement:** Created a new `CommandListPrinter` class to modularize and improve the Help Guide.
- **Justification:** Improves maintainability and clarity of the help feature for users.


### 4. Others

- **Code Cleanup (v1.0 & v2.0):**
  - **What it does:** Resolved all Checkstyle errors across the codebase.
  - **Justification:** Ensured consistency, readability, and maintainability for the entire team.

- **Code contributed**
  - https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=samstt&breakdown=true

### Summary of other Project Contributions

- **Project management (Contributions to Team-based Tasks)**
  - Managed releases `v1.0`,`v2.0` and `v2.1` on GitHub
    - Set up a potential roadmap to further extensions through brainstorming ideas for `v3.0`
    - Set up issue tracker for `v1.0` and allocated issues to groupmates
  - Monitored issue tracker consistently and helped to close or create new issues based on team progress
  - Tasked as overall project manager and led the team in brainstorming user stories, new features and implementation details
  - In charge of setting deadlines and meetings between groupmates and task allocation in order to deliver final project deliverables punctually
  - Monitored code quality consistently in order to ensure compliance with coding conventions
- **Enhancements to existing features:**
  - Wrote additional tests for student commands 
- **Documentation**
  - User guide:
    - Developed the layout for User Guide for implementation by groupmates
    - Added documentation for all student commands and tutorial commands
- Developer Guide:
    - Developed the layout for Developer Guide for implementation by groupmates
    - Added implementation details for all student commands and tutorial commands
    - Created all sequence diagrams and class diagrams for tutorial commands and student commands
    - Created all class diagrams for the design section of the Developer guide
    - Completed section on user stories
- **Review/Mentoring Contributions:**
  - Regularly reviewed PRs for my team (with non-trivial review comments):
    - Gave suggestions on possible improvements on various features of TASync
- **Contributions beyond the project team**
  - Reported bugs and suggestions for other teams in CS2113
    - Provided a bug report of 11 bugs of varying severity levels during the PE-D
