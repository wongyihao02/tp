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
  - [AttendanceList Commands](#attendancelist-commands)
    - [View the attendance list: `LIST`](#view-the-attendance-list-list)
    - [Mark present a student: `MARK`](#mark-present-a-student-mark)
    - [Mark absent a student: `UNMARK`](#mark-absent-a-student-unmark)
    - [View comments for a student: `VIEWCOMMENT`](#view-comments-for-a-student-viewcomment)
    - [Add comments for a student: `COMMENT`](#add-comments-for-a-student-comment)
    - [delete comments for a student: `DELETECOMMENT`](#delete-comment-for-a-student-deletecomment)
    - [Create a new attendancelist`CREATE`](#create-an-attendance-list-create)
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

#### Adding a todo: `TODO`

Adds a personal todo to the task list.

Format: `add -pt <todo name>`

Example:
```
> add -pt Read book
task added:
[T][ ] Read book
Now you have 1 tasks in the list.
```

#### Adding a deadline: `DEADLINE`

Adds a personal deadline to the task list.

Format: `add -pd <deadline name> /by <dd/MM/yyyy HH:mm>`

Example:
```
> add -pd Assignment submission /by 03/04/2025 23:59
task added:
[D][ ] Assignment submission (by: 2025-04-03T23:59)
Now you have 2 tasks in the list.
```

#### Adding an event: `EVENT`

Adds a personal event to the task list.

Format: `add -pe <event name> /from <dd/MM/yyyy HH:mm> /to <dd/MM/yyyy HH:mm>`

Example:
```
> add -pe Concert /from 03/04/2025 15:00 /to 03/04/2025 16:00
task added:
[E][ ] Concert (from: 2025-04-03T15:00 to: 2025-04-03T16:00)
Now you have 3 tasks in the list.
```

#### Adding a consultation: `CONSULTATION`

Adds a consultation with a student to the task list.

Format: `add -c <student_name> /from <dd/MM/yyyy HH:mm> /to <dd/MM/yyyy HH:mm>`

Example:
```
> add -c Kevin /from 05/04/2025 15:00 /to 05/04/2025 16:00
Task added:
[C][ ] Kevin (from: 2025-04-05T15:00 to: 2025-04-05T16:00)
Now you have 4 tasks in the list.
```

#### Listing the tasks: `LIST`

Displays all the tasks in the task list.

Format: `list -p`

Example:
```
> list -p
1.[T][ ] Study Java
2.[T][ ] Study Python
```

#### Deleting a task: `DELETE`

Delete a task from the task list.

Format: `delete -p <task_number>`

Example:
```
> delete -p 1
deleted task: 
[T][ ] Study Java
```

#### Marking a task: `MARK`

Marks a task as done.

Format: `mark -p <task_number>`

Example:
```
> mark -p 1
Study Python is marked
```

#### Unmarking a task: `UNMARK`

Marks a task as undone.

Format: `unmark -p <task_number>`

Example:
```
> unmark -p 1
Study Python is unmarked
```

#### Finding a task: `FIND`

Finds a task based on the keyword given.

Format: `find -p <keyword>`

Example:
```
> find -p java
1.[T][ ] Study Java
```

#### Renaming a task: `RENAME`

Renames a task based on the task number given.

Format: `rename -p <task_number> <new_name>`

Example:
```
> rename -p 1 Study Python
Study Java renamed to Study Python
```
### AttendanceList Commands

#### View the attendance list: `LIST`

Lists out the attendance for a given tutorial name and week

Format: `list -a <Tut_name>,<Week_num>`

Example:
```
> list -a T01,1
Attendance List for tutorial T01 week1:
personname(matricnum1): Absent
end of list
```

#### Mark present a student: `MARK`

marks a student present in a attendance list for a given tutorial name and week

Format: `mark -a <Tut_name>,<Week_num>,<student_name>,<matricnum>`

Example:
```
> mark -a T01,1,john,A001
```

#### Mark absent a student: `UNMARK`

marks a student absent in a attendance list for a given tutorial name and week

Format: `unmark -a <Tut_name>,<Week_num>,<student_name>,<matricnum>`

Example:
```
> mark -a T01,1,john,A001
```

#### View comments for a student: `VIEWCOMMENT`

Lists out the comments for a student for a given tutorial name and week

Format: `viewcomment -a <Tut_name>,<Week_num>,<student_name>,<matricnum>`

Example:
```
> viewcomment -a T01,1,john,A001
list of comments:
1.john is studious
2.john is hardworking
```

#### add comments for a student: `COMMENT`

adds comments to a student for a given tutorial name and week

Format: `comment -a <Tut_name>,<Week_num>,<student_name>,<matricnum>//comment1;comment2`

Example:
```
> comment -a T01,1,john,A001//john is studious;john does everything

> viewcomment -a T01,1,john,A001
list of comments:
1.john is studious
2.john does everything
```

#### Delete comment for a student: `DELETECOMMENT`

deletes a comment for a student for a given tutorial name and week

Format: `deletecomment -a <Tut_name>,<Week_num>,<student_name>,<matricnum>//num_pos_to_delete`

Example:
```
> viewcomment -a T01,1,john,A001
list of comments:
1.john is studious
2.john does everything

> deletecomment -a T01,1,john,A001//1

> viewcomment -a T01,1,john,A001
list of comments:
1.john does everything
```
#### Create an attendance list: `CREATE`

Creates an attendance List for a given tutorial name and week if it does not already exist.
Note that the tage is -at instead of -t this time.

Format: `create -at <Tut_name>,<Week_num>`

Example:
```
> create -at T01,1
Attendance List created
```

### Bye Command: `BYE`

Exits the application.

Format: `bye`

Example:
```
> bye
Bye. Hope to see you again soon!
👋 Goodbye! Have a productive day!
All data saved successfully!
```

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Transfer over the csv files for attendance file and tutorial class list and the marks and tasklist and textfiles into the data file into the other
computer

## Command Summary

# Student Commands

| CommandName           | Form                                                                             |                             Example                             |
|-----------------------|:---------------------------------------------------------------------------------|:---------------------------------------------------------------:|
| Add Student           | `newstudent -t <name>,<dob>,<gender>,<contact>,<matric_number>,<tutorial_class>` | `newstudent -t kim dokja,15-02-2000,Male,99224421 806,A002,T01` |
| Delete Student        | `deletestudent -t <tutorial_name>,<matric_number>`                               |                `DELETESTUDENT -t T123,A7656765W`                |
| Find Student          | `find -t <keyword>`                                                              |           `FIND -t Charlie Song`  `FIND -t A7645342D`           |
| Change Student Remark | `changeremark -t <tutorial_name>,<matric_number>,<new_remark>`                   |    `CHANGEREMARK -t T123,A2345674W,Excellent job in class!`     |
| Check Student Remark  | `checkremarks -t <tutorial_name>,<matric_number>`                                |                 `CHECKREMARK -t T123,A2345674W`                 |

## Marks Commands

| CommandName  | Form                                                                                         |                     Example                      |
|--------------|:---------------------------------------------------------------------------------------------|:------------------------------------------------:|
| Add Marks    | `newmarks -m <tutorial_name>,<matric_number>,<assignment_name>,<marks_achieved>,<max_marks>` | `NEWMARKS -m T123,A1234567W,Midterm Exam,75,100` |
| Delete Marks | `deletemarks -m <tutorial_name>,<matric_number>,<assignment_name>`                           |   `DELETEMARKS -m T123,A1234567W,Midterm Exam`   |
| List Marks   | `list -m <tutorial_name>,<matric_number>`                                                    |             `LIST -m T123,A1234567W`             |

## Tutorial Commands

| CommandName               | Form                                                                   |               Example               |
|---------------------------|:-----------------------------------------------------------------------|:-----------------------------------:|
| Create Tutorial           | `newtutorial -t <tutorial_name>,<day_of_week>,<start_time>,<end_time>` | `NEWTUTORIAL -t T123,2,11:00,13:00` |
| Delete Tutorial           | `deletetut -t <tutorial_name>`                                         |         `DELETETUT -t T123`         |
| List Students in Tutorial | `listsstudents -t <tutorial_name>`                                     |       `LISTSTUDENTS -t T123`        |
| List Upcoming Tutorials   | `list -t <date>`                                                       |        `LIST -t 12/03/2025`         |

## Task Commands

| CommandName       | Form                                                                    |                            Example                            |
|-------------------|:------------------------------------------------------------------------|:-------------------------------------------------------------:|
| Add To-Do         | `add -pt <todo_name>`                                                   |                      `add -pt Read book`                      |
| Add Deadline      | `add -pd <deadline_name> /by <dd/MM/yyyy HH:mm>`                        |     `add -pd Assignment submission /by 03/04/2025 23:59`      |
| Add Event         | `add -pe <event_name> /from <dd/MM/yyyy HH:mm> /to <dd/MM/yyyy HH:mm>`  | `add -pe Concert /from 03/04/2025 15:00 /to 03/04/2025 16:00` |
| Add Consultation  | `add -c <student_name> /from <dd/MM/yyyy HH:mm> /to <dd/MM/yyyy HH:mm>` |  `add -c Kevin /from 05/04/2025 15:00 /to 05/04/2025 16:00`   |
| List Tasks        | `list -p`                                                               |                           `list -p`                           |
| Delete Task       | `delete -p <task_number>`                                               |                         `delete -p 1`                         |
| Mark Task as Done | `mark -p <task_number>`                                                 |                          `mark -p 1`                          |
| Unmark Task       | `unmark -p <task_number>`                                               |                         `unmark -p 1`                         |
| Find Task         | `find -p <keyword>`                                                     |                        `find -p java`                         |
| Rename Task       | `rename -p <task_number> <new_name>`                                    |                  `rename -p 1 Study Python`                   |

## Attendance Commands

| CommandName            | Form                                                                                   |                               Example                               |
|------------------------|:---------------------------------------------------------------------------------------|:-------------------------------------------------------------------:|
| View Attendance List   | `list -a <Tut_name>,<Week_num>`                                                        |                           `list -a T01,1`                           |
| Mark Student Present   | `mark -a <Tut_name>,<Week_num>,<student_name>,<matricnum>`                             |                      `mark -a T01,1,john,A001`                      |
| Mark Student Absent    | `unmark -a <Tut_name>,<Week_num>,<student_name>,<matricnum>`                           |                     `unmark -a T01,1,john,A001`                     |
| View Comments          | `viewcomment -a <Tut_name>,<Week_num>,<student_name>,<matricnum>`                      |                  `viewcomment -a T01,1,john,A001`                   |
| Add Comment            | `comment -a <Tut_name>,<Week_num>,<student_name>,<matricnum>//comment1;comment2`       | `comment -a T01,1,john,A001//john is studious;john does everything` |
| Delete Comment         | `deletecomment -a <Tut_name>,<Week_num>,<student_name>,<matricnum>//num_pos_to_delete` |                `deletecomment -a T01,1,john,A001//1`                |
| Create Attendance List | `create -at <Tut_name>,<Week_num>`                                                     |                         `create -at T01,1`                          |

## Exit Command
| CommandName      | Form  | Example |
|------------------|:------|:-------:|
| Exit Application | `bye` |  `bye`  |

