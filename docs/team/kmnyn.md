# kmnyn - Project Portfolio Page

## Overview
TASync is a command-line application designed to help teaching assistants and tutors manage administrative and personal tasks efficiently. It supports features like attendance tracking, remark recording, and tutorial planning, all accessible via a fast and distraction-free CLI. By streamlining routine processes, TASync enhances productivity and integrates seamlessly into the daily workflow of academic support staff.

## Summary of Contributions
### Code contributed
Please view my code contribution at:
- [Link to my code dashboard](#https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=kmnyn&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2025-02-21&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

### Enhancements implemented

#### 1. Task commands
- **Add a task:**

    - **Add Todo Command:**
        - **What it does:** Allows the user to add a new todo to their task list.

    - **Add Deadline Command:**
        - **What it does:** Allows the user to add a new deadline to their task list.

    - **Add Event Command:**
        - **What it does:** Allows the user to add a new event to their task list.

    - **Add Consultation Command:**
        - **What it does:** Allows the user to add a new consultation with student to their task list.
    - **Justification:** These four commands ensures that the user can efficiently manage different types of tasks. The **Add Todo command** allows users to capture general tasks or reminders without time constraints, while the **Add Deadline command** helps track important submission or grading dates. The **Add Event command** is useful for scheduling fixed-time activities like tutorials or meetings, and the **Add Consultation command** specifically supports managing student appointments. Together, these commands provide a flexible and structured way to organise both academic and personal tasks.

- **Delete a task:**
  - **What it does:** Allows the user to delete an existing task from their task list.
  - **Justification:** Enables users to remove outdated or completed tasks from their task list, keeping their schedule clean and focused only on relevant responsibilities.

- **List all tasks:**
    - **What it does:** Allows the user to list out all the existing tasks in their task list.
    - **Justification:** Provides an overview of all tasks in one place, helping users quickly assess their workload and prioritise their time effectively.

- **Mark a task:**
    - **What it does:** Allows the user to mark an existing task as done.
    - **Justification:** Allows users to keep track of completed tasks, giving a sense of progress and helping them distinguish between pending and finished items.

- **Unmark a task:**
    - **What it does:** Allows the user to mark an existing task as not done.
    - **Justification:** Lets users correct mistakes or reopen tasks that were prematurely marked as done, maintaining accurate task status.

- **Find a task:**
    - **What it does:** Allows the user to find an existing task by keyword.
    - **Justification:** Helps users locate specific tasks quickly using keywords, especially useful when managing long or complex task lists.

- **Rename a task:** 
    - **What it does:** Allows the user to rename an existing task with a new task name.
    - **Justification:** Offers flexibility to update task descriptions as plans change, ensuring that the task list remains clear, accurate, and relevant.

#### 2. Time Table/Task Reminder
- **Print daily schedule:**
    - **What it does:** Prints things of today to screen once the user opens the applications. This includes **Todos**, **Deadlines due today**, **Events starting today**, **Consultations starting today** and **Tutorial classes for today**.
    - **Justification:** Automatically shows the user their tasks and appointments for the day, helping them stay organised and prepared without needing to manually check each category.

#### 3. Test Coverage
- Created Junit tests for **taskcommands** classes and **task** classes.

### Contributions to the Project Management
- Crafted and released `v1.0`,`v1.0.1`,`v2.0` and `v2.1` on GitHub.
- Helped with creating and monitoring issue trackers for `v2.0` on GitHub.

### Contributions to the UG
- Wrote **Introduction** and **Quick Start**.
- Wrote documentation for all the **task commands**.

### Contributions to the DG
- Wrote implementation details for all the **task commands**.
- Created sequence diagrams for all the **task commands**.
- Created object diagrams for **task commands**.
- Created overall class diagram for **task commands**.
- Wrote **Product Scope**, including **Target User Profile** and **User Proposition**.

### Review/mentoring contributions
- Reviewed pull requests consistently for my team.
- Reviewed developer guide and made suggestions for other teams. 
- Reported bugs and made suggestions for other teams.