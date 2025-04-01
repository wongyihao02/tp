# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## **Design & implementation**

This section describes some important details on how certain features in TASync are implemented.

### Command Package Structure

The `command` package contains the following sub-packages:

- `attendancelistcommands` - Handles commands related to attendance lists.
- `commandhandler` - Manages the processing and execution of various commands
- `student commands` - Implements commands specific to student-related operations
- `taskcommands` - Contains commands for managing tasks
- `tutorialcommands` - Handles tutorial-related commands


### Attendance List Commands

### Command Handler 

### **Student Commands**

The `studentcommands` package includes commands that handle student-related functionalities in TASync. 
These commands interact with the tutorial classes and the student list, allowing users to manage student records 
effectively. 


#### 1. NewStudentCommand

The NewStudentCommand is part of the `studentcommands` package and is responsible for adding a new student to a
specific tutorial class.

#### Implementation Details

The NewStudentCommand class implements the Command<TutorialClassList> interface. It is responsible for parsing the input to
extract the student’s details, validating them, and adding the student to the appropriate tutorial class.
#### Operations

The class implements the following main operation:

- `NewStudentCommand#execute()`
- Parses the input to extract the student’s name, date of birth (DOB), gender, contact number, matriculation number, and tutorial class code. 
- Validates the student details to ensure they are correctly formatted and do not contain any errors. 
- Retrieves the specified tutorial class based on the provided class code. 
- Checks if the matriculation number already exists in the class. If it does, an error message is shown. 
- Creates a new Student object and adds it to the student list of the tutorial class. 
- Logs and displays a success message if the student is successfully added to the tutorial class. 
- Handles any exceptions by displaying relevant error messages if validation fails or the tutorial class is not found.

#### 2. DeleteStudentCommand

The `DeleteStudentCommand` is part of the `studentcommands` package and is responsible for removing a 
student from a specific tutorial class.

#### Implementation Details

The `DeleteStudentCommand` class implements the Command<TutorialClassList> interface. It is responsible for
parsing the input to extract the tutorial class code and matriculation number, validating them, and removing 
the student from the appropriate tutorial class.

#### Operations

The class implements the following main operation:

- `DeleteStudentCommand#execute()`
- Parses the input to extract the tutorial class code and matriculation number. 
- Validates the input to ensure it follows the expected format and does not contain any errors. 
- Retrieves the specified tutorial class based on the provided class code. 
- Checks if the tutorial class exists. If it does not, an error message is shown. 
- Retrieves the student list from the tutorial class. 
- Checks if the student with the given matriculation number exists in the class. 
- If the student exists, removes them from the student list and displays a success message. 
- If the student does not exist, an error message is shown indicating that no such student was found. 
- Handles any exceptions by displaying relevant error messages if validation fails or the tutorial class is not found.

#### 3. ChangeRemarkCommand

The `ChangeRemarkCommand` is part of the `studentcommands` package and is responsible for modifying the remark of a
student in a specific tutorial class.

#### Implementation Details

The `ChangeRemarkCommand` class implements the `Command<TutorialClassList>` interface and modifies a student's remark based 
on the provided tutorial class code and matriculation number.

#### Operations

The class implements the following main operation:

- `ChangeRemarkCommand#execute()`
  - Parses the input to extract the tutorial class code, matriculation number and new remark.
  - Retrieves the specified tutorial class and student.
  - Updates the student's remark and displays a confirmation message.
  - If the tutorial class or student is not found, an error message is shown.

This command is executed as part of the TASync system when a user provides a properly formatted input string.


#### 4. CheckRemarkCommand

The `CheckRemarkCommand` is part of the `studentcommands` package and is responsible for retrieving and displaying
a student’s remark in a specific tutorial class.

#### Implementation Details

The `CheckRemarkCommand` class implements the `Command<TutorialClassList>` interface. It is responsible for parsing the input
to extract the tutorial class code and matriculation number, validating them, and retrieving the remark of the specified student.

#### Operations

The class implements the following main operation:

- `CheckRemarkCommand#execute()`
- Parses the input to extract the tutorial class code and matriculation number. 
- Validates the input to ensure it follows the expected format and does not contain any errors. 
- Retrieves the specified tutorial class based on the provided class code. 
- Checks if the tutorial class exists. If it does not, an error message is shown. 
- Retrieves the student list from the tutorial class. 
- Checks if the student with the given matriculation number exists in the class. 
- If the student exists, retrieves their remark. 
- If the student has a remark, displays it. Otherwise, indicates that no remarks were found. 
- If the student does not exist, an error message is shown indicating that no such student was found. 
- Handles any exceptions by displaying relevant error messages if validation fails or the tutorial class is not found.

#### 5. FindStudentCommand

The `FindStudentCommand` is part of the `studentcommands` package and is responsible for searching for students by partial 
matching of their name or matriculation number across all tutorial classes.

#### Implementation Details

The `FindStudentCommand` class implements the `Command<TutorialClassList> interface`. It is responsible for parsing the input to 
extract the search keyword, validating it, and searching for students who match the keyword in any tutorial class.

#### Operations

The class implements the following main operation:

- `FindStudentCommand#execute()`
- Parses the input to extract the search keyword. 
- Validates the keyword to ensure it is not empty. If the input is invalid, an error message is displayed. 
- Iterates through each tutorial class in the tutorial class list. 
- Retrieves the list of students in each tutorial class. 
- Checks if any student's name or matriculation number partially matches the keyword (case-insensitive). 
- If a match is found, prints the tutorial class name and the matching students. 
- If no matching students are found in any tutorial class, prints a message indicating that no results were found. 
- Handles any exceptions by displaying relevant error messages if validation fails.


### Task Commands

#### 1. DeleteTaskCommand

The `DeleteTaskCommand` handles the removal of tasks from the system. It searches for the specified task by its number and removes it from the task list if found. If the task is not found, an error message is displayed.

#### Implementation Details

The `DeleteTaskCommand` class implements the `Command<TaskList>` interface and interacts with the `TaskList` to remove tasks.

#### Operations

- `DeleteTaskCommand#execute()`
  - Retrieves the task to be deleted based on user input.
  - Removes the task from the `TaskList`.
  - Displays a confirmation message upon successful removal.
  - If the task is not found, an error message is shown.

### Tutorial Commands


## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
