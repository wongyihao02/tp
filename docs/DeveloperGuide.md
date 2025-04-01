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
#### 1.ShowAttendanceListCommand
This is part of the Attendancelistcommands package,the function of this class is to show the user the attendance list of the tutorial and week the user asks for.

#### Implementation details
This class implements the Command<AttendanceList> interface.Its function is to take in the input from the user and extract the tutorial and week the user has given
and print the names and attendance status of the students in that tutorial,if it exists.

#### operations
- `ShowAttendanceListCommand.execute()`
- extracts tutorial name and week number from the given input.
- Exception thrown if no input or not enough input given.
- The AttendancFile is searched to find the relevant AttendanceList
- Exception is thrown and handled if none found.
- The hashMap containing the attendance status of the students will be printed.
- A different message will be printed if this hashMap is empty(signalling that the attendanceList has no students in it).
- An error message will be printed when an Exception is handled.
- 

#### 2.MarkStudentAttendanceCommand
This is part of the Attendancelistcommands package,the function of this class is to mark a student present for a specific tutorial

#### Implementation details
This class implements the Command<AttendanceList> interface.Its function is to take in the input from the user and extract the tutorial,week and the student name and matric number  that the user has given
and set the attendance status of the given student as Present in the attendanceList for the given tutorial name and week.

#### operations
- `MarkStudentAttendanceCommand.execute()`
- extracts tutorial name, week number and student name and matric number from the given input.
- Exception thrown if no inputs, not enough inputs or too much inputs are given.
- The AttendancFile is searched to find the relevant AttendanceList
- Exception is thrown and handled if none found.
- This AttendanceList is then checked is it has a student with the same name and matric number is found.
- Exception is thrown and handled if none found.
- The value for the student in the hashMap containing the attendance status of the students will be changed to Present.
- An error message will be printed when an Exception is handled.


#### 3.UnmarkStudentAttendanceCommand
This is part of the Attendancelistcommands package,the function of this class is to mark a student Absent for a specific tutorial

#### Implementation details
This class implements the Command<AttendanceList> interface.Its function is to take in the input from the user and extract the tutorial,week and the student that the user has given
and set the attendance status of the given student as Absent in the attendanceList for the given tutorial name and week.

#### operations
- `UnmarkStudentAttendanceCommand.execute()`
- extracts tutorial name, week number and student name and matric number from the given input.
- Exception thrown if no inputs, not enough inputs or too much inputs are given.
- The AttendancFile is searched to find the relevant AttendanceList
- Exception is thrown and handled if none found.
- This AttendanceList is then checked is it has a student with the same name and matric number is found.
- Exception is thrown and handled if none found.
- The value for the student in the hashMap containing the attendance status of the students will be changed to Absent.
- An error message will be printed when an Exception is handled.


#### 4.ViewStudentCommentsCommand
This is part of the Attendancelistcommands package,the function of this class is print out all the comments given for a student in the particular tutorial and week.

#### Implementation details
This class implements the Command<AttendanceList> interface.Its function is to take in the input from the user and extract the tutorial,week and the student name and matric number
that the user has given and print all comments for the given student in the attendanceList for the given tutorial name and week.

#### operations
- `ViewStudentCommentsCommand.execute()`
- extracts tutorial name, week number and student name and matric number from the given input.
- Exception thrown if no inputs, not enough inputs or too much inputs are given.
- The AttendancFile is searched to find the relevant AttendanceList
- Exception is thrown and handled if none found.
- This AttendanceList is then checked is it has a student with the same name and matric number is found.
- Exception is thrown and handled if none found.
- The elements of the ArrayList<String> associated with the student in the hashmap for students to comments is printed
- A special message will be printed if this ArrayList is empty.
- An error message will be printed when an Exception is handled.


#### 5.CommentOnStudentCommand
This is part of the Attendancelistcommands package,the function of this class is to add a comment(s) to a student in the particular tutorial and week.

#### Implementation details
This class implements the Command<AttendanceList> interface.Its function is to take in the input from the user and extract the tutorial,week and the student name and matric number
and their comments that the user has given and add all comments to the ArrayList containing all comments for a given student in the attendanceList for the given tutorial name and week.

#### operations
- `CommentOnStudentCommand.execute()`
- extracts tutorial name, week number , student name and matric number and the comments to be added from the given input.
- Exception thrown if no inputs, not enough inputs or too much inputs are given.
- The AttendancFile is searched to find the relevant AttendanceList
- Exception is thrown and handled if none found.
- This AttendanceList is then checked is it has a student with the same name and matric number is found.
- Exception is thrown and handled if none found.
- The ArrayList<String> associated with the student in the hashmap for student comments have the given comments added to them.
- An error message will be printed when an Exception is handled.


#### 6.DeleteStudentCommentCommand
This is part of the Attendancelistcommands package,the function of this class is to remove a comment to a student in the particular tutorial and week.

#### Implementation details
This class implements the Command<AttendanceList> interface.Its function is to take in the input from the user and extract the tutorial,week and the student name and matric number
and the number position of the comment to be removed that the user has given and remove the comment in the ArrayList containing all comments for a given student in the attendanceList for the given tutorial name and week.

#### operations
- `DeleteStudentCommentCommand.execute()`
- extracts tutorial name, week number , student name and matric number and the number position of the comment to be removed from the given input.
- Exception thrown if no inputs, not enough inputs or too much inputs are given.
- The AttendancFile is searched to find the relevant AttendanceList
- Exception is thrown and handled if none found.
- This AttendanceList is then checked is it has a student with the same name and matric number is found.
- Exception is thrown and handled if none found.
- The ArrayList<String> associated with the student that stores comments for the has the (num given - 1)th element removed.A message will then be printed stating that the comment was successfully deleted.
- Different messages will be printed if the student has no comments associated with them or if the num given is outside the boundaries of the comments ArrayList.
- An error message will be printed when an Exception is handled.


#### 7.CreateNewAttendanceList
This is part of the Attendancelistcommands package,the function of this class is to create an AttendanceList for a particular tutorial and week.

#### Implementation details
This class implements the Command<ArrayList<Object>> interface.Its function is to take in the input from the user and extract the tutorial,week that the 
user has given create a new AttendanceList from it to add to the attendanceFile.The ArrayList will have the TutorialClassList as the first element and the 
AttendanceFile as the second element.

#### operations
- `CreateNewAttendanceList.execute()`
- extracts tutorial name, week number for the new AttendanceList to be created from the given input.
- Exception thrown if no inputs, not enough inputs or too much inputs are given.
- The TutorialClassList is searched to find the relevant TutorialClass.
- Exception is thrown and handled if none found.
- The AttendanceFile is searched to find if it contains an AttendanceList for the given tutorial name and week.
- A new AttendanceList would be created for the given tutorial name and week if non found.
- Different message will be printed if the an AttendanceList was found.
- An error message will be printed when an Exception is handled.


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
