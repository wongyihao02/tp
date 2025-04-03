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
  - [Marks Commands](#marks-commands)
    - [Adding new marks `NEWMARKS -m`](#adding-new-marks-for-a-student-newmarks--m)
    - [Deleting marks `DELETEMARKS -m`](#deleting-marks-for-a-student-deletemarks--m)
    - [List all marks for a student `LIST -m`](#list-all-marks-for-a-student-list--m)
  - [Tutorial Commands](#tutorial-commands)
    - [Create new tutorial: `NEWTUTORIAL -t`](#create-new-tutorial-newtutorial--t)
    - [Delete tutorial: `DELETETUT -t`](#delete-tutorial-deletetutorial--t)
    - [List students in tutorial: `LISTSTUDENTS -t`](#list-students-in-tutorial-liststudents--t)
    - [List upcoming tutorials: `LIST -t`](#list-upcoming-tutorials-list--t)
- [FAQ](#faq)
- [Command Summary](#Command-Summary)
## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 17 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

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

### Marks Commands

#### Adding new marks for a student `NEWMARKS -m`

Adds marks with the given details to the student's marks list.

Format: `NEWMARKS -m <tutorial_name>,<matric_number>,<assignment_name>,<marks_achieved>,<max_marks>`
* `marks_achieved` and `max_marks` must be entered in numerical format.
* `marks_achieved` and `max_marks` must be non-negative, with `marks_achieved` <= `max_marks`.
* Marks will not be added if there already exists marks with the given `assignment_name` in the student's marks list.

Examples: 
- `NEWMARKS -m T123,A1234567W,Midterm Exam,75,100` 

#### Deleting marks for a student `DELETEMARKS -m`

Deletes marks of the given assignment name from the student's marks list.

Format: `DELETEMARKS -m <tutorial_name>,<matric_number>,<assignment_name>`

Examples: 
- `DELETEMARKS -m T123,A1234567W,Midterm Exam`

#### List all marks for a student `LIST -m`

Prints a list of all marks for the student.

Format: `LIST -m <tutorial_name>,<matric_number>`

Examples: 
- `LIST -m T123,A1234567W`

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

### Adding a todo: `todo`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of taskcommands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
