# User Guide

## Introduction

**TASync** is a command-line application designed to help teaching assistants (TAs) and tutors efficiently manage their administrative tasks and personal workload. Whether it's tracking attendance, recording remarks, or planning tutorials, TASync provides a streamlined solution that automates time-consuming tasks, allowing users to focus on what truly matters – helping students succeed.

Optimized for use via a **Command Line Interface (CLI)**, TASync allows users to quickly execute commands without the overhead of graphical applications. For those who are comfortable typing, the CLI enables faster task execution compared to traditional GUI-based tools. By removing the need for a GUI, TASync offers a distraction-free environment, increasing productivity and reducing manual effort.

Designed specifically for TAs and tutors who manage both student-related administrative duties and personal responsibilities, TASync is the perfect tool to integrate seamlessly into their workflow and enhance efficiency.

- [Quick Start](#quick-start)
- [Features](#features-)
- [Tutorial Commands](#tutorial-commands)
  - [Create new tutorial: `NEWTUTORIAL -t`](#create-new-tutorial-newtutorial--t)
  - [Delete tutorial: `DELETE -t`](#delete-tutorial-delete--t)
  - [List upcoming tutorials: `LIST -t`](#list-existing-tutorials-list--t)
- [Student Commands](#student-commands)
  - [Adding a new student: `NEWSTUDENT -t`](#adding-a-new-student-newstudent--t)
  - [Deleting a student: `DELETESTUDENT -t`](#deleting-a-student-deletestudent--t)
  - [Find a student: `FIND -t`](#find-a-student-find--t)
  - [Change remark of a student: `CHANGEREMARK -t`](#change-remark-of-a-student-changeremark--t)
  - [Check remark of a student: `CHECKREMARK -t`](#check-the-remark-of-a-student-checkremark--t)
  - [List students in tutorial: `LISTSTUDENTS -t`](#list-students-in-tutorial-liststudents--t)
- [Marks Commands](#marks-commands)
  - [Adding new marks `NEWMARKS -m`](#adding-new-marks-for-a-student-newmarks--m)
  - [Deleting marks `DELETEMARKS -m`](#deleting-marks-for-a-student-deletemarks--m)
  - [List all marks for a student `LIST -m`](#list-all-marks-for-a-student-list--m)
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

1. Ensure that you have Java 17 or above installed.
2. Download the latest version of `TASync` from [here](https://github.com/AY2425S2-CS2113-T12-3/tp/releases).
3. Copy the file to the folder you want to use as the home folder for your TASync.
4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar TASync.jar command to run the application.
5. Type the command and press Enter to execute it.
6. Refer to the [Features](#features-) below for details of each command.

## Features 

### Tutorial Commands

- Things to note:
  - `<day_of_week>` represents a number from 1-7 that corresponds to each day of the week
    - Example:
      - 1 --> Monday, 2--> Tuesday, 3 --> Wednesday
  - `<start_time>` and `<end_time>` must be in `hh:mm` format
  - `<date>` must be formatted as `dd/MM/yyyy`

#### Create new tutorial: `NEWTUTORIAL -t`

Creates a new tutorial with the necessary parameters.

Format: `NEWTUTORIAL -t <tutorial_name>,<day_of_week>,<start_time>,<end_time>`

Example:
```
> NEWTUTORIAL -t T123,2,11:00,13:00
Tutorial "T123" successfully scheduled on TUESDAY from 11:00 to 13:00.
```

#### Delete tutorial: `DELETE -t`

Delete a specified tutorial class from the tutorial class list.

Format: `DELETE -t <tutorial_name>`

Examples:
- `DELETE -t T123`
- `DELETE -t T05`

#### List Existing Tutorials: `LIST -t`

Prints existing tutorial classes in the tutorial class list.

Format:`LIST -t`

### Student Commands

- Things to note:
  - `<matric_number>` must start with a valid capitalised english alphabet, followed by 7 digits and ending with another valid capitalised English alphabet.
  - `<contact>` follow the Singapore phone number format which has 8 digits
  - `<dob>` must be formatted as `dd/MM/yyyy`
  - Student commands only work on valid tutorial classes

#### Adding a new student: `NEWSTUDENT -t`

Adds a new person to a specified tutorial if the tutorial class already exists.

Format: `NEWSTUDENT -t <name>,<dob>,<gender>,<contact>,<matric_number>,<tutorial_class>`

Important Note: NEWSTUDENT will return an error message if the user attempts to create a student in an invalid tutorial class.

Example:
```
> NEWSTUDENT -t Mark Lim,20/03/2005,Male,97654344,A2387653D,T123
New student added to tutorial class T123: Mark Lim
```

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

#### List Students in Tutorial: `LISTSTUDENTS -t`

Lists all students in the specified tutorial.

Format: `LISTSTUDENTS -t <tutorial_name>`

Examples:
- `LISTSTUDENTS -t T123`

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


### Task Commands

#### Adding a todo: `TODO`

Adds a personal todo to the task list.

Format: `ADD -pt <todo name>`

Example:
```
> ADD -pt Read book
task added:
[T][ ] Read book
Now you have 1 tasks in the list.
```

#### Adding a deadline: `DEADLINE`

Adds a personal deadline to the task list.

Format: `ADD -pd <deadline name> /by <dd/MM/yyyy HH:mm>`

Example:
```
> ADD -pd Assignment submission /by 03/04/2025 23:59
task added:
[D][ ] Assignment submission (by: 2025-04-03T23:59)
Now you have 2 tasks in the list.
```

#### Adding an event: `EVENT`

Adds a personal event to the task list.

Format: `ADD -pe <event name> /from <dd/MM/yyyy HH:mm> /to <dd/MM/yyyy HH:mm>`

Example:
```
> ADD -pe Concert /from 03/04/2025 15:00 /to 03/04/2025 16:00
task added:
[E][ ] Concert (from: 2025-04-03T15:00 to: 2025-04-03T16:00)
Now you have 3 tasks in the list.
```

#### Adding a consultation: `CONSULTATION`

Adds a consultation with a student to the task list.

Format: `ADD -c <student_name> /from <dd/MM/yyyy HH:mm> /to <dd/MM/yyyy HH:mm>`

Example:
```
> ADD -c Kevin /from 05/04/2025 15:00 /to 05/04/2025 16:00
Task added:
[C][ ] Kevin (from: 2025-04-05T15:00 to: 2025-04-05T16:00)
Now you have 4 tasks in the list.
```

#### Listing the tasks: `LIST`

Displays all the tasks in the task list.

Format: `LIST -p`

Example:
```
> LIST -p
1.[T][ ] Study Java
2.[T][ ] Study Python
```

#### Deleting a task: `DELETE`

Delete a task from the task list.

Format: `DELETE -p <task_number>`

Example:
```
> DELETE -p 1
deleted task: 
[T][ ] Study Java
```

#### Marking a task: `MARK`

Marks a task as done.

Format: `MARK -p <task_number>`

Example:
```
> MARK -p 1
Study Python is marked
```

#### Unmarking a task: `UNMARK`

Marks a task as undone.

Format: `UNMARK -p <task_number>`

Example:
```
> UNMARK -p 1
Study Python is unmarked
```

#### Finding a task: `FIND`

Finds a task based on the keyword given.

Format: `FIND -p <keyword>`

Example:
```
> FIND -p java
1.[T][ ] Study Java
```

#### Renaming a task: `RENAME`

Renames a task based on the task number given.

Format: `RENAME -p <task_number> <new_name>`

Example:
```
> RENAME -p 1 Study Python
Study Java renamed to Study Python
```
### AttendanceList Commands

Note that Student names have to be in full for AttendanceListCommands.

#### View the attendance list: `LIST`

Lists out the attendance for a given tutorial name and week.

Format: `LIST -a <tutorial_name>,<Week_num>`

Example:
```
> LIST -a T01,1
Attendance List for tutorial T01 week1:
personname(matricnum1): Absent
End of list
```

#### Mark present a student: `MARK`

Marks a student present in a attendance list for a given tutorial name and week.

Format: `MARK -a <tutorial_name>,<Week_num>,<student_name>,<matricnum>`

Example:
```
> MARK -a T01,1,john,A001
Student marked Present
```

#### Mark absent a student: `UNMARK`

Marks a student absent in a attendance list for a given tutorial name and week.

Format: `UNMARK -a <tutorial_name>,<Week_num>,<student_name>,<matricnum>`

Example:
```
> UNMARK -a T01,1,john,A001
Student marked Absent
```

#### View comments for a student: `VIEWCOMMENT`

Lists out the comments for a student for a given tutorial name and week.

Format: `VIEWCOMMENT -a <tutorial_name>,<Week_num>,<student_name>,<matricnum>`

Example:
```
> VIEWCOMMENT -a T01,1,john,A001
list of comments:
1.john is studious
2.john is hardworking
End of list
```

#### add comments for a student: `COMMENT`

Adds comments to a student for a given tutorial name and week.More than one comment can be added at a time,ensure that they are seperated by ;

Format: `COMMENT -a <tutorial_name>,<Week_num>,<student_name>,<matricnum>//comment1;comment2`

Example:
```
> COMMENT -a T01,1,john,A001//john is studious;john does everything
added comment to john (A001) in tutorial T01  week 1
1.john is studious
2.john does everything
End of list

> VIEWCOMMENT -a T01,1,john,A001
list of comments:
1.john is studious
2.john does everything
End of list
```

#### Delete comment for a student: `DELETECOMMENT`

Deletes a comment for a student for a given tutorial name and week.

Format: `DELETECOMMENT -a <tutorial_name>,<Week_num>,<student_name>,<matricnum>//num_pos_to_delete`

Example:
```
> VIEWCOMMENT -a T01,1,john,A001
list of comments:
1.john is studious
2.john does everything
End of list

> DELETECOMMENT -a T01,1,john,A001//1
Comment deleted

> VIEWCOMMENT -a T01,1,john,A001
list of comments:
1.john does everything
End of list
```
#### Create an attendance list: `CREATE`

Creates an attendance List for a given tutorial name and week if it does not already exist.
Note that the tage is -at instead of -t this time.

Format: `CREATE -at <tutorial_name>,<Week_num>`

Example:
```
> CREATE -at T01,1
Attendance List created
```

### Bye Command: `BYE`

Exits the application.

Format: `BYE`

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

## Tutorial Commands

| CommandName               | Form                                                                   |               Example               |
|---------------------------|:-----------------------------------------------------------------------|:-----------------------------------:|
| Create Tutorial           | `NEWTUTORIAL -t <tutorial_name>,<day_of_week>,<start_time>,<end_time>` | `NEWTUTORIAL -t T123,2,11:00,13:00` |
| Delete Tutorial           | `DELETE -t <tutorial_name>`                                            |          `DELETE -t T123`           |
| List Students in Tutorial | `LISTSTUDENTS -t <tutorial_name>`                                      |       `LISTSTUDENTS -t T123`        |

# Student Commands

| CommandName             | Form                                                                             |                             Example                              |
|-------------------------|:---------------------------------------------------------------------------------|:----------------------------------------------------------------:|
| Add Student             | `NEWSTUDENT -t <name>,<dob>,<gender>,<contact>,<matric_number>,<tutorial_class>` | `NEWSTUDENT -t Mark Lim,20/03/2005,Male,97654344,A2387653D,T123` |
| Delete Student          | `DELETESTUDENT -t <tutorial_name>,<matric_number>`                               |                `DELETESTUDENT -t T123,A7656765W`                 |
| Find Student            | `FIND -t <keyword>`                                                              |           `FIND -t Charlie Song`  `FIND -t A7645342D`            |
| Change Student Remark   | `CHANGEREMARK -t <tutorial_name>,<matric_number>,<new_remark>`                   |     `CHANGEREMARK -t T123,A2345674W,Excellent job in class!`     |
| Check Student Remark    | `CHECKREMARKS -t <tutorial_name>,<matric_number>`                                |                 `CHECKREMARK -t T123,A2345674W`                  |
| List Existing Tutorials | `LIST -t `                                                                       |                            `LIST -t`                             |

## Marks Commands

| CommandName  | Form                                                                                         |                     Example                      |
|--------------|:---------------------------------------------------------------------------------------------|:------------------------------------------------:|
| Add Marks    | `NEWMARKS -m <tutorial_name>,<matric_number>,<assignment_name>,<marks_achieved>,<max_marks>` | `NEWMARKS -m T123,A1234567W,Midterm Exam,75,100` |
| Delete Marks | `DELETEMARKS -m <tutorial_name>,<matric_number>,<assignment_name>`                           |   `DELETEMARKS -m T123,A1234567W,Midterm Exam`   |
| List Marks   | `LIST -m <tutorial_name>,<matric_number>`                                                    |             `LIST -m T123,A1234567W`             |

## Task Commands

| CommandName       | Form                                                                    |                            Example                            |
|-------------------|:------------------------------------------------------------------------|:-------------------------------------------------------------:|
| Add To-Do         | `ADD -pt <todo_name>`                                                   |                      `ADD -pt Read book`                      |
| Add Deadline      | `ADD -pd <deadline_name> /by <dd/MM/yyyy HH:mm>`                        |     `ADD -pd Assignment submission /by 03/04/2025 23:59`      |
| Add Event         | `ADD -pe <event_name> /from <dd/MM/yyyy HH:mm> /to <dd/MM/yyyy HH:mm>`  | `ADD -pe Concert /from 03/04/2025 15:00 /to 03/04/2025 16:00` |
| Add Consultation  | `ADD -c <student_name> /from <dd/MM/yyyy HH:mm> /to <dd/MM/yyyy HH:mm>` |  `ADD -c Kevin /from 05/04/2025 15:00 /to 05/04/2025 16:00`   |
| List Tasks        | `LIST -p`                                                               |                           `LIST -p`                           |
| Delete Task       | `DELETE -p <task_number>`                                               |                         `DELETE -p 1`                         |
| Mark Task as Done | `MARK -p <task_number>`                                                 |                          `MARK -p 1`                          |
| Unmark Task       | `UNMARK -p <task_number>`                                               |                         `UNMARK -p 1`                         |
| Find Task         | `FIND -p <keyword>`                                                     |                        `FIND -p java`                         |
| Rename Task       | `RENAME -p <task_number> <new_name>`                                    |                  `RENAME -p 1 Study Python`                   |

## Attendance Commands

| CommandName            | Form                                                                                        |                               Example                               |
|------------------------|:--------------------------------------------------------------------------------------------|:-------------------------------------------------------------------:|
| View Attendance List   | `LIST -a <tutorial_name>,<Week_num>`                                                        |                           `LIST -a T01,1`                           |
| Mark Student Present   | `MARK -a <tutorial_name>,<Week_num>,<student_name>,<matricnum>`                             |                      `MARK -a T01,1,john,A001`                      |
| Mark Student Absent    | `UNMARK -a <tutorial_name>,<Week_num>,<student_name>,<matricnum>`                           |                     `UNMARK -a T01,1,john,A001`                     |
| View Comments          | `VIEWCOMMENT -a <tutorial_name>,<Week_num>,<student_name>,<matricnum>`                      |                  `VIEWCOMMENT -a T01,1,john,A001`                   |
| Add Comment            | `COMMENT -a <tutorial_name>,<Week_num>,<student_name>,<matricnum>//comment1;comment2`       | `COMMENT -a T01,1,john,A001//john is studious;john does everything` |
| Delete Comment         | `DELETECOMMENT -a <tutorial_name>,<Week_num>,<student_name>,<matricnum>//num_pos_to_delete` |                `DELETECOMMENT -a T01,1,john,A001//1`                |
| Create Attendance List | `CREATE -at <tutorial_name>,<Week_num>`                                                     |                         `CREATE -at T01,1`                          |


## Exit Command

| CommandName      | Form  | Example |
|------------------|:------|:-------:|
| Exit Application | `BYE` |  `BYE`  |

