# Li XiaoNa - Project Portfolio Page

## Project: TASync

TASync is a tool designed to streamline the management of administrative tasks for tutors, including attendance tracking,
remarks, and scheduling, with a focus on optimizing the user experience through a command-line interface (CLI).

## Overview

Given below are my contributions to the project.

### Summary of Contributions

#### 1. Student Commands

- **New Features:**

  - **Add/Delete Student:**
    - **What it does:** Allows the user to easily add or delete a new student to an existing tutorial class. This command enables the TA to maintain an up-to-date list of students for each tutorial class.
    - **Justification:** This feature helps TAs efficiently manage student records, ensuring that the class roster is always current. It also simplifies administrative tasks by enabling easy removal or addition of students.

  - **Find Student:**
    - **What it does:** Enables the user to find an existing student from any tutorial class by performing a search that matches part of the student's matriculation number or name.
    - **Justification:** Given that TAs may handle multiple tutorial groups with students who may share similar names, this feature allows them to quickly locate a specific student based on partial information, streamlining the management of large student populations.

  - **Change Student Remark:**
    - **What it does:** Allows the user to modify or update a student's remark within their record. This could include updating feedback, performance reviews, or other notes relevant to the student’s progress.
    - **Justification:** This feature allows TAs to maintain accurate, up-to-date records of student performance and behavior. It helps to track individual progress and provides a mechanism for timely feedback or notes that may be needed for assessments or communication with students.

  - **Check Student Remark:**
    - **What it does:** Allows the user to view the current remarks associated with a student in any given tutorial class. This feature can help TAs quickly access the student's history of feedback and performance.
    - **Justification:** This command aids in quickly retrieving specific details about a student’s past performance, providing a useful reference for the TA during interactions with the student or for planning future tutorial sessions. It ensures that the TA has all relevant information on hand

#### 2. Tutorial Commands

- **New Features:**

  - **Add/Delete Tutorial:**
    - **What it does:** Allows the user to easily add or delete a tutorial from the existing tutorial class list.
    - **Justification:** This feature enables the TA to effectively manage their tutorial schedule by adding or removing classes as needed, ensuring that the list of tutorials is always up-to-date with the current teaching plan.

  - **List students in tutorial:**
    - **What it does:** Allows the user to list all students in a specified tutorial class, with personal information such as name, date of birth, gender, contact number, and matriculation number listed out as well.
    - **Justification:** This command helps TAs efficiently access and review student details within a specific tutorial class. By listing all relevant personal information, it provides a comprehensive overview of the students, aiding in attendance tracking, communication, and any other tutorial-related management tasks.

  - **List upcoming tutorials:**
    - **What it does:** Allows the user to check the upcoming tutorials in weekly tutorials up till a specified date in dd/MM/yyyy format.
    - **Justification:** This feature provides TAs with an easy way to track and plan for upcoming tutorial sessions. By specifying a date range, it helps the TA stay organized and ensures they are prepared for all upcoming classes in advance.

#### 3. Help Command

- Helped to create new CommandListPrinter class in order to produce a much clearer help guide for users

- **Code contributed**
  - https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=samstt&breakdown=true

- **Project management**
  - Managed releases `v1.0`,`v2.0` and `v2.1` on GitHub
    - Set up a potential roadmap to further extensions through brainstorming ideas for `v3.0`
    - Set up issue tracker for `v1.0` and allocated issues to groupmates
  - Monitored issue tracker consistently and helped to close or create new issues based on team progress
  - Tasked as overall project manager and led the team in brainstorming user stories, new features and implementation details
  - In charge of setting deadlines and meetings between groupmates and task allocation in order to deliver final project deliverables punctually
  - Monitored code quality consistently in order to ensure compliance of coding conventions
- **Enhancements to existing features:**
  - Wrote additional tests for student commands and tutorial commands
- **Documentation**
  - User guide:
    - Developed the layout for User Guide for implementation by groupmates
    - Added documentation for all student commands and tutorial commands
  - Developer Guide:
    - Developed the layout for Developer Guide for implementation by groupmates
    - Added implementation details for all student commands and tutorial commands
    - Created sequence diagrams for commands
    - Completed section on user stories
- **Community:**
  - Reported bugs and suggestions for other teams in CS2113
  - Regularly reviewed PRs for my team (with non-trivial review comments)