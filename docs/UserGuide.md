# User Guide

## Introduction

{Give a product intro}

- [Features](#features-)
  - [Student Commands](#student-commands)
    - [Adding a new student: `NEWSTUDENT -t`](#adding-a-new-student-newstudent--t)
    - [Deleting a student: `DELETESTUDENT -t`](#deleting-a-student-deletestudent--t)
    - [Find a student: `FIND -t`](#find-a-student-find--t)
    - [Change remark of a student: `CHANGEREMARK -t`](#change-remark-of-a-student-changeremark--t)
    - [Check remark of a student: `CHECKREMARK -t`](#check-the-remark-of-a-student-checkremark--t)
  - [Tutorial Commands](#tutorial-commands)
    - [Create new tutorial: `NEWTUTORIAL -t`](#create-new-tutorial-newtutorial--t)
    - [Delete tutorial: `DELETETUT -t`](#delete-tutorial-deletetutorial--t)
    - [List students in tutorial: `LISTSTUDENTS -t`](#list-students-in-tutorial-liststudents--t)
    - [List upcoming tutorials: `LIST -t`](#list-upcoming-tutorials-list--t)
  - [Task Commands](#task-commands)
    - [Adding a todo: `TODO`](#adding-a-todo-todo)
    - [Adding a deadline: `DEADLINE`](#adding-a-deadline-deadline)
    - [Adding an event: `EVENT`](#adding-an-event-event)
    - [Adding a consultation: `CONSULTATION`](#adding-a-consultation-consultation)
    - [Listing the tasks: `LIST`](#listing-the-tasks-list)
    - [Deleting a task: `DELETE`](#deleting-a-task-delete)
    - [Marking a task: `MARK`](#marking-a-task-mark)
    - [Unmarking a task: `UNMARK`](#unmarking-a-task-unmark)
    - [Finding a task: `FIND`](#finding-a-task-find)
    - [Renaming a task: `RENAME`](#renaming-a-task-rename)
  - [Bye Command](#bye-command-bye)
- [FAQ](#faq)
- [Command Summary](#Command-Summary)
## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 17 or above installed.
1. Down the latest version of `TASync` from [here](http://link.to/duke).

## Features 

### Student Commands

- Things to note:
  - `<matric_number>` must start with a valid english alphabet, followed by 7 digits and ending with another valid English alphabet.
  - `<contact>` follow the Singapore phone number format which has 8 digits
  - `<dob>` must be formatted as `dd/MM/yyyy`
  - Student commands only work on valid tutorial classes

#### Adding a new student: `NEWSTUDENT -t`

Adds a new person to a specified tutorial if the tutorial class already exists.

Format: `NEWSTUDENT -t <name>,<dob>,<gender>,<contact>,<matric_number>,<tutorial_class>`

Important Note: NEWSTUDENT will return an error message if the user attempts to create a student in an invalid tutorial class.

Examples:
- `NEWSTUDENT -t Mark Lim,20/03/2005,Male,97654344,A2387653D,T123`

#### Deleting a student: `DELETESTUDENT -t`

Deletes a student from a valid tutorial.

Format: `DELETESTUDENT -t <tutorial_name>,<matric_number>`

Examples:
- `DELETESTUDENT -t T123,A7656765W`

#### Find a student: `FIND -t`

Finds a student based on partial matching of name or matriculation number.

Format: `FIND -t <keyword>`

Examples: 
- `FIND -t Charlie Song`
- `FIND -t A7645342D`

#### Change remark of a student: `CHANGEREMARK -t`

Changes the remark of a student in a specified tutorial.

Format: `CHANGEREMARK -t <tutorial_name>,<matric_number>,<new_remark>`

Examples:
- `CHANGEREMARK -t T123,A2345674W,Excellent job in class!`

#### Check the remark of a student: `CHECKREMARK -t`

Check the remarks given to a student for a specified tutorial class. 

Format: `CHECKREMARK -t <tutorial_name>,<matric_number>`

Examples:
- `CHECKREMARK -t T123,A2345674W`

### Tutorial Commands

- Things to note:
  - `<day_of_week>` represents a number from 1-7 that corresponds to each day of the week
    - Example:
      - 1 --> Monday, 2--> Tuesday, 3 --> Wednesday
  - `<start_time>` and `<end_time>` must be in `hh:mm` format
  - `<date>` must be formatted as `dd/MM/yyyy`

#### Create new tutorial: `NEWTUTORIAL -t`

Creates a new tutorial with the necessary parameters. 

Format: `NEWTUTORIAL -t <tutorial_name>,<day_of_week>,<start_time> <end_time>`

Examples:
- `NEWTUTORIAL -t T123,2,11:00,13:00`

#### Delete tutorial: `DELETETUTORIAL -t`

Delete a specified tutorial class from the tutorial class list.

Format: `DELETETUT -t <tutorial_name>`

Examples:
- `DELETETUT -t T123`

#### List Students in Tutorial: `LISTSTUDENTS -t`

Lists all students in the specified tutorial.

Format: `LISTSTUDENTS -t <tutorial_name>`

Examples:
- `LISTSTUDENTS -t T123`

#### List Upcoming Tutorials: `LIST -t`

Prints tutorial classes from today to the given date in weekly blocks.

Format:`LIST -t <date>`

Examples:
- `LIST -t 12/03/2025 `

### Task Commands

#### Adding a todo: `TODO`

Adds a personal todo to the task list.

Format: `add -pt <todo name>`

Examples:
- `add -pt Task A `
- `add -pt Write the rest of the User Guide`

#### Adding a deadline: `DEADLINE`

Adds a personal deadline to the task list.

Format: `add -pd <deadline name> /by <dd/MM/yyyy HH:mm>`

Examples:
- `add -pd Deadline A /by 03/04/2025 23:59`
- `add -pd Update DG /by 06/04/2025 23:59`

#### Adding an event: `EVENT`

Adds a personal event to the task list.

Format: `add -pe <event name> /from <dd/MM/yyyy HH:mm> /to <dd/MM/yyyy HH:mm>`

Examples:
- `add -pe Event A /from 03/04/2025 16:00 /to 03/04/2025 17:00`
- `add -pe Concert /from 05/04/2025 18:00 /to 05/04/2025 20:00`

#### Adding a consultation: `CONSULTATION`

Adds a consultation with a student to the task list.

Format: `add -c <student_name> /from <dd/MM/yyyy HH:mm> /to <dd/MM/yyyy HH:mm>`

Examples:
- `add -c Student A /from 03/04/2025 12:00 /to 03/04/2025 13:00`
- `add -c Kevin /from 05/04/2025 14:00 /to 05/04/2025 14:30`

#### Listing the tasks: `LIST`

Displays all the tasks in the task list.

Format: `list -p`

Examples:
- `list -p` returns: `1.[T][ ] Study Java`

#### Deleting a task: `DELETE`

Delete a task from the task list.

Format: `delete -p <task_number>`

Examples:
- Original:
  - `1.[T][ ] Study Java`
  - `2.[C][ ] Study Python`
- `delete -p 1`
- Output:
  - `1.[C][ ] Study Python`

#### Marking a task: `MARK`

Marks a task as done.

Format: `mark -p <task_number>`

Examples:
- Original:
  - `1.[T][ ] Study Java`
- `mark -p 1`
- Output:
  - `1.[T][X] Study Java`

#### Unmarking a task: `UNMARK`

Marks a task as undone.

Format: `unmark -p <task_number>`

Examples:
- Original:
  - `1.[T][X] Study Java`
- `unmark -p 1`
- Output:
  - `1.[T][ ] Study Java`

#### Finding a task: `FIND`

Finds a task based on the keyword given.

Format: `find -p <keyword>`

Examples:
- Original:
  - `1.[T][X] Study Java`
  - `2.[T][X] Study Python`
- `find -p java`
- Output:
  - `1.[T][X] Study Java`

#### Renaming a task: `RENAME`

Renames a task based on the task number given.

Format: `rename -p <task_number> <new_name>`

Examples:
- Original:
  - `1.[T][X] Study Java`
- `rename -p 1 Study Python`
- Output:
  - `1.[T][X] Study Python`

### Bye Command: `BYE`

Exits the application.

Format: `bye`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of taskcommands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
