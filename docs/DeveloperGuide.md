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


### Command Handler

#### 1. CommandParser

The `CommandParser` is responsible for breaking down user input into a **command keyword** and its corresponding **arguments**.

#### Implementation Details

- Extracts the first word as the command keyword (e.g., `addstudent`, `deletetask`) and treats the remainder as arguments.
- Trims and sanitizes input to ensure compatibility across all command handlers.
- Throws relevant exceptions if input is malformed or empty.

#### Operations

- `CommandParser.parse(String input)`
  - Parses the keyword and arguments from user input.
  - Returns both as a `ParsedInput` object to the `CommandHandler`.
  - Handles edge cases like extra whitespace and missing commands.

---

#### 2. CommandFactory

The `CommandFactory` is responsible for returning the correct command object for execution.

#### Implementation Details

- Implements the **Factory Design Pattern** to instantiate command classes without exposing their construction logic to external classes.
- Maps each supported command keyword (e.g., `addstudent`, `listtasks`, `newtutorial`) to its corresponding command class.
- Throws descriptive exceptions if an unrecognized command is provided.

#### Operations

- `CommandFactory.getCommand(String keyword)`
  - Returns a new instance of the corresponding command class that implements `Command<T>`.
  - Commands are registered in the factory using a `switch-case` or a command map for maintainability.
  - Supports all domains: `studentcommands`, `taskcommands`, `tutorialcommands`, `attendancelistcommands`, `markscommands`.

---

#### 3. CommandHandler

The `CommandHandler` orchestrates the end-to-end command processing — from parsing to execution.

#### Implementation Details

- Acts as the main entry point when a command is entered via CLI.
- Delegates parsing to `CommandParser`, command resolution to `CommandFactory`, and execution to the returned `Command` instance.
- Accepts relevant data structures such as `TaskList`, `TutorialClassList`, and `AttendanceFile` as input to command execution.
- Handles exception logging and displays error messages to the user if any part of the command flow fails.

#### Operations

- `CommandHandler.handleCommand(String fullInput)`
  - Calls `CommandParser` to break down the input.
  - Delegates command creation to `CommandFactory`.
  - Executes the command using the appropriate data object.
  - Displays output or error messages based on the outcome.

---

#### Benefits of the Design

- **Modular & Extensible:** Commands can be added or removed without changing the handler logic.
- **Polymorphic Execution:** All commands implement a common interface `Command<T>`, simplifying invocation logic.
- **Robust Error Handling:** Clearly separates exceptions thrown during parsing, command creation, and execution.
- **Testable:** Each component (`Parser`, `Factory`, `Handler`) can be unit tested independently.

### Attendance List Commands
#### 1.ShowAttendanceListCommand
This is part of the Attendancelistcommands package,the function of this class is to show the user the attendance list of the tutorial and week the user asks for.

#### Implementation details
This class implements the Command<AttendanceList> interface.Its function is to take in the input from the user and extract the tutorial and week the user has given
and print the names and attendance status of the students in that tutorial,if it exists.

#### Operations
- `ShowAttendanceListCommand.execute()`
![ShowAttendanceListCommand.png](diagrams/attendancelistcommands/ShowAttendanceListCommand.png)
  - Extracts tutorial name and week number from the given input(parts) and stores it in partsArray.
  - Exception thrown if no input or not enough input given.
  - The AttendanceFile is searched to find the relevant AttendanceList
  - Exception is thrown and handled if none found.
  - The hashMap containing the attendance status of the students will be printed.
  - A different message will be printed if this hashMap is empty(signalling that the attendanceList has no students in it).
  - An error message will be printed when an Exception is handled.
![findStudentInTutorial.png](diagrams/attendancelistcommands/findStudentInTutorial.png)
`Reference Block`
#### 2.MarkStudentAttendanceCommand
This is part of the Attendancelistcommands package,the function of this class is to mark a student present for a specific tutorial

#### Implementation details
This class implements the Command<AttendanceList> interface.Its function is to take in the input from the user and extract the tutorial,week and the student name and matric number  that the user has given
and set the attendance status of the given student as Present in the attendanceList for the given tutorial name and week.

#### Operations
- `MarkStudentAttendanceCommand.execute()`
  - Extracts tutorial name, week number and student name and matric number from the given input.
  - Exception thrown if no inputs, not enough inputs or too many inputs are given.
  - The AttendanceFile is searched to find the relevant AttendanceList
  - Exception is thrown and handled if none found.
  - This AttendanceList is then checked is it has a student with the same name and matric number is found.
  - Exception is thrown and handled if none found.
  - The value for the student in the hashMap containing the attendance status of the students will be changed to Present.
  - An error message will be printed when an Exception is handled.
  - sequence diagram is similar to 1 but instead at the end the student is marked present in the attendanceList and nothing is printed if there are no exceptions


#### 3.UnmarkStudentAttendanceCommand
This is part of the Attendancelistcommands package,the function of this class is to mark a student Absent for a specific tutorial

#### Implementation details
This class implements the Command<AttendanceList> interface.Its function is to take in the input from the user and extract the tutorial,week and the student that the user has given
and set the attendance status of the given student as Absent in the attendanceList for the given tutorial name and week.

#### Operations
- `UnmarkStudentAttendanceCommand.execute()`
  - Extracts tutorial name, week number and student name and matric number from the given input.
  - Exception thrown if no inputs, not enough inputs or too many inputs are given.
  - The AttendancFile is searched to find the relevant AttendanceList
  - Exception is thrown and handled if none found.
  - This AttendanceList is then checked is it has a student with the same name and matric number is found.
  - Exception is thrown and handled if none found.
  - The value for the student in the hashMap containing the attendance status of the students will be changed to Absent.
  - An error message will be printed when an Exception is handled.
- sequence diagram is similar to 1 but instead at the end the student is marked Absent in the attendanceList and nothing is printed if there are no exceptions

#### 4.ViewStudentCommentsCommand
This is part of the Attendancelistcommands package,the function of this class is print out all the comments given for a student in the particular tutorial and week.

#### Implementation details
This class implements the Command<AttendanceList> interface.Its function is to take in the input from the user and extract the tutorial,week and the student name and matric number
that the user has given and print all comments for the given student in the attendanceList for the given tutorial name and week.

#### Operations
- `ViewStudentCommentsCommand.execute()`
  - Extracts tutorial name, week number and student name and matric number from the given input.
  - Exception thrown if no inputs, not enough inputs or too many inputs are given.
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

#### Operations

- `CommentOnStudentCommand.execute()`
![CommentOnStudentCommandSequenceDiagram.png](diagrams/attendancelistcommands/CommentOnStudentCommandSequenceDiagram.png)

  - As seen in the diagram,the input from parts is split between data used to find the student and the comments to be added to the student and placed into a string array.
  - The relevant data is then extracted from each index and put into arrays.For the comments they are put into an ArrayList<String>
  - The attendanceFile is searched for an AttendanceList with the given Tutorial name and week number.
  - This AttendanceList is then searched for the student with the name and matric number.
  - The above 2 are located in the reference block highlighted in red.
  - The ArrayList<String> associated with the student in the hashmap for student comments have the given comments added to them.
  - As seen at the bottom,if an exception is thrown, an error message will be printed when an Exception is handled.
  - TASYNC exception will be thrown if the inputs do not follow the set form or has invalid inputs.It will also be thrown if the attendanceList cannot be found or the student cannot be found.
![findStudentInTutorial.png](diagrams/attendancelistcommands/findStudentInTutorial.png)
`find student in tutorial reference diagram`
#### 6.DeleteStudentCommentCommand
This is part of the Attendancelistcommands package,the function of this class is to remove a comment to a student in the particular tutorial and week.

#### Implementation details
This class implements the Command<AttendanceList> interface.Its function is to take in the input from the user and extract the tutorial,week and the student name and matric number
and the number position of the comment to be removed that the user has given and remove the comment in the ArrayList containing all comments for a given student in the attendanceList for the given tutorial name and week.

#### Operations
- `DeleteStudentCommentCommand.execute()`
  - Extracts tutorial name, week number , student name and matric number and the number position of the comment to be removed from the given input.
  - Exception thrown if no inputs, not enough inputs or too many inputs are given.
  - The AttendancFile is searched to find the relevant AttendanceList
  - Exception is thrown and handled if none found.
  - This AttendanceList is then checked is it has a student with the same name and matric number is found.
  - Exception is thrown and handled if none found.
  - The ArrayList<String> associated with the student that stores comments for the has the (num given - 1)th element removed.A message will then be printed stating that the comment was successfully deleted.
  - Different messages will be printed if the student has no comments associated with them or if the num given is outside the boundaries of the comments ArrayList.
  - An error message will be printed when an Exception is handled.
  - sequence diagram is similar to 5 but instead at the end the comment is deleted and a message stating that it is deleted is printed

#### 7.CreateNewAttendanceList
This is part of the Attendancelistcommands package,the function of this class is to create an AttendanceList for a particular tutorial and week.

#### Implementation details
This class implements the Command<ArrayList<Object>> interface.Its function is to take in the input from the user and extract the tutorial,week that the 
user has given create a new AttendanceList from it to add to the attendanceFile.The ArrayList will have the TutorialClassList as the first element and the 
AttendanceFile as the second element.

#### Operations
- `CreateNewAttendanceList.execute()`
  - Extracts tutorial name, week number for the new AttendanceList to be created from the given input.
  - Exception thrown if no inputs, not enough inputs or too many inputs are given.
  - The TutorialClassList is searched to find the relevant TutorialClass.
  - Exception is thrown and handled if none found.
  - The AttendanceFile is searched to find if it contains an AttendanceList for the given tutorial name and week.
  - A new AttendanceList would be created for the given tutorial name and week if non found.
  - Different message will be printed if the AttendanceList was found.
  - An error message will be printed when an Exception is handled.
![CreateNewAttendanceListCommand.png](diagrams/attendancelistcommands/CreateNewAttendanceListCommand.png)
  - Sequence diagram

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
  - **Parses the input** to extract the student’s name, date of birth (DOB), gender, contact number, matriculation number, and tutorial class code. 
  - **Validates the student details** to ensure they are correctly formatted and do not contain any errors. 
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
  - **Parses the input** to extract the tutorial class code and matriculation number. 
  - **Validates the input** to ensure it follows the expected format and does not contain any errors. 
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
  - **Parses the input** to extract the tutorial class code, matriculation number and new remark.
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
  - **Parses the input** to extract the tutorial class code and matriculation number. 
  - **Validates the input** to ensure it follows the expected format and does not contain any errors. 
  - **Retrieves the specified tutorial class** based on the provided class code. 
  - **Checks if the tutorial class exists**
    - If it does not, an error message is shown. 
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
  - **Parses the input** to extract the search keyword. 
  - **Validates the keyword** to ensure it is not empty. If the input is invalid, an error message is displayed. 
  - Iterates through each tutorial class in the tutorial class list. 
  - Retrieves the list of students in each tutorial class. 
  - Checks if any student's name or matriculation number partially matches the keyword (case-insensitive). 
  - If a match is found, prints the tutorial class name and the matching students. 
  - If no matching students are found in any tutorial class, prints a message indicating that no results were found. 
  - Handles any exceptions by displaying relevant error messages if validation fails.

### Tutorial Commands

#### 1. NewTutorialCommand

The `NewTutorialCommand` is part of the `tutorialcommands` package and is responsible for adding a new tutorial class 
to the tutorial class list. This operation ensures that the tutorial class is valid, does not duplicate an 
existing class, and has the correct details.

#### Implementation Details

The `NewTutorialCommand` class implements the `Command<TutorialClassList>` interface. It is responsible for parsing 
the input, validating the tutorial details, and adding the new tutorial to the list.

#### Operations

The class implements the following main operation:

- `NewTutorialCommand#execute()`
  - **Parses the input** to extract the tutorial details: name, day of the week, start time, and end time.
  - **Validates the input** to ensure:
    - The input is not empty or invalid.
    - The day of the week is a valid integer between 1 and 7.
    - The start and end times are in a valid format.
    - The tutorial is not a duplicate of an existing tutorial in the list.
  - **Checks if a duplicate tutorial exists** by comparing the tutorial name, day of the week, start time, and end time.
  - If a duplicate is found, throws a `TASyncException.duplicateTutorial()` exception.
  - If the tutorial details are valid and no duplicates are found, creates a new `TutorialClass` object.
  - Adds the new tutorial to the tutorial class list.
  - Prints a success message upon successful addition of the tutorial.
  - If the input or any other data is invalid, catches and handles exceptions and displays appropriate error messages.

#### 2. DeleteTutorialCommand

The `DeleteTutorialCommand` is part of the `tutorialcommands` package and is responsible for removing a tutorial class 
from the tutorial class list by its name. This operation ensures that a tutorial class is deleted if it exists, 
and provides appropriate feedback if the class does not exist in the list.

#### Implementation Details

The `DeleteTutorialCommand` class implements the `Command<TutorialClassList>` interface. It is responsible for parsing 
the input, validating it, and deleting the corresponding tutorial class from the list.

#### Operations

The class implements the following main operation:

- `DeleteTutorialCommand#execute()`
  - **Validates the input** to ensure that the tutorial class code is not empty or invalid.
  - **Searches for the tutorial class** by its name (code) in the tutorial class list.
  - If the tutorial class is found, it is removed from the list.
  - **Handles cases where no tutorial class is found** with the specified code and displays a message indicating that the tutorial class does not exist.
  - **Error handling**: Catches any exceptions that occur during the execution and displays the error message.


#### 3. ListTutorialStudentsCommand

The `ListTutorialStudentsCommand` class implements the `Command<TutorialClassList>` interface. It is responsible for
parsing the input, validating the tutorial name, and listing the students enrolled in the specified tutorial class.

#### Implementation Details

The `ListTutorialStudentsCommand` class implements the `Command<TutorialClassList>` interface. It is responsible for 
parsing the input, validating the tutorial name, and listing the students enrolled in the specified tutorial class.

#### Operations

The class implements the following main operation:

- `ListTutorialStudentsCommand#execute()`
  - **Validates the input** to ensure the tutorial name is not empty or invalid.
  - **Searches for the tutorial class** by its name within the list of tutorial classes.
  - If the tutorial class is found, it retrieves the list of students enrolled in the tutorial class.
  - If there are no students enrolled, a message is displayed indicating that the tutorial has no students.
  - If students are found, their details are printed.
  - **Error handling**: If the tutorial name does not match any existing tutorial class or the input is invalid, 
  an exception is thrown, and an error message is displayed.

#### 4. ListUpcomingTutorialsCommand

The `ListUpcomingTutorialsCommand` is part of the `tutorialcommands` package and is responsible for listing all upcoming
tutorial sessions from the current date until a given end date. The tutorials are displayed with their names, dates, and times. 
If the input is invalid or any exceptions occur, appropriate error messages are displayed.

#### Implementation Details

The `ListUpcomingTutorialsCommand` class implements the `Command<TutorialClassList>` interface. It handles parsing the input, 
validating the date, and listing the upcoming tutorials that fall within the specified date range.

#### Operations

The class implements the following main operation:

- `ListUpcomingTutorialsCommand#execute()`
  - **Validates the input**: Ensures the end date string is not null or empty.
  - **Parses the input date**: Converts the input end date string into a `LocalDate` format using `DateTimeFormatterUtil.parseDate()`.
  - **Calculates the first upcoming tutorial date**: Determines the next occurrence of the first tutorial class based on the current date and the day of the week.
  - **Lists tutorials**: Iterates through the tutorial classes, printing their names, dates, start times, and end times for each tutorial class that happens before the end date.
  - **Error handling**: If any exceptions occur during parsing or processing, the relevant error messages are displayed.

### Marks Commands

#### 1. AddMarksCommand

The `AddMarksCommand` handles the addition of new marks to a given student's marksList.

#### Implementation Details

The `AddMarksCommand` class implements the `Command<TutorialClassList>` interface. It is responsible for parsing the input to 
extract the tutorial class code, student matriculation number, assignment name, marks achieved and maximum marks, validating them,
and instantiating and saving a Marks object with the details to the given student's marksList.

#### Operations

`AddMarksCommand#execute()`
- Validates the input to ensure it follows the expected format and is not missing arguments.
- Parses the input to extract the tutorial class code, matric number, assignment name, marks and maximum marks.
- Validates parsed inputs marks and maximum marks to make sure they are valid, non-negative integers with maximum marks >= marks.
- Retrieves the `tutorialClass` with the given tutorial class code.
- Checks if the `tutorialClass` retrieved exists, prints an error message otherwise.
- Retrieves the student with the given matric number from the retrieved `tutorialClass` student list.
- Checks if the student exists, prints an error message otherwise.
- Checks if the student's `marksList` already contains a marks object with the same assignment name, prints an error message if so.
- Instantiates the `marks` object with given assignment name, marks and max marks, and adds it to `marksList`.

### 2. DeleteMarksCommand

The `DeleteMarksCommand` handles the removal of a student's marks entry for a specified assignment.

### Implementation Details

The `DeleteMarksCommand` class implements the `Command<TutorialClassList>` interface. It is responsible for parsing the input  
to extract the tutorial class code, student matriculation number, and assignment name, validating them, and removing the  
corresponding `Marks` object from the student's `marksList`.

### Operations

#### `DeleteMarksCommand#execute()`
- Validates the input to ensure it follows the expected format and is not missing arguments.
- Parses the input to extract the tutorial class code, matric number, and assignment name.
- Retrieves the `TutorialClass` with the given tutorial class code.
- Checks if the `TutorialClass` exists, prints an error message otherwise.
- Retrieves the student with the given matric number from the retrieved `TutorialClass` student list.
- Checks if the student exists, prints an error message otherwise.
- Checks if the student's `marksList` contains a `Marks` object with the given assignment name, prints an error message if not.
- Removes the corresponding `Marks` object from the student's `marksList` and prints a success message.

### 3. ListMarksCommand

The `ListMarksCommand` handles retrieving and displaying all marks for a specified student.

### Implementation Details

The `ListMarksCommand` class implements the `Command<TutorialClassList>` interface. It is responsible for parsing the input  
to extract the tutorial class code and student matriculation number, validating them, and printing all marks recorded for  
the student.

### Operations

#### `ListMarksCommand#execute()`
- Validates the input to ensure it follows the expected format and is not missing arguments.
- Parses the input to extract the tutorial class code and matric number.
- Retrieves the `TutorialClass` with the given tutorial class code.
- Checks if the `TutorialClass` exists, prints an error message otherwise.
- Retrieves the student with the given matric number from the retrieved `TutorialClass` student list.
- Checks if the student exists, prints an error message otherwise.
- Prints the student's name followed by all marks recorded in their `marksList`.


### Task Commands

#### 1. DeleteTaskCommand

The `DeleteTaskCommand` handles the removal of tasks from the system. It searches for the specified task by its number and removes it from the task list if found. If the task is not found, an error message is displayed.

#### Implementation Details

The `DeleteTaskCommand` class implements the `Command<TaskList>` interface and interacts with the `TaskList` to remove tasks.

#### Operations

`DeleteTaskCommand#execute()`
  - Retrieves the task to be deleted based on user input.
  - Removes the task from the `TaskList`.
  - Displays a confirmation message upon successful removal.
  - If the task is not found, an error message is shown.

#### 2. ConsultationCommand
The `ConsultationCommand` handles the creation of a consultation task with specific timings. It requires the student's name along with `/from` and `/to` tags to specify the start and end times.

#### Implementation Details

The `ConsultationCommand` class implements the `Command<TaskList>` interface and interacts with the `TaskList` to add a new consultation task.

#### Operations

`ConsultationCommand#execute(String parts, TaskList taskList)`

- Parses the student name, start time, and end time from the user input.
- Ensures that both `/from` and `/to` tags are provided in the input.
- Validates the datetime format using `DateTimeFormatterUtil`.
- Creates a `Consultation` task and adds it to the `TaskList`.
- If the input format is incorrect, prompts the user to re-enter the command.
- Displays an error message if:
  - The `/from` or `/to` tags are missing.
  - The datetime format is incorrect.
  - Any input is missing.

#### 3. DeadlineCommand

The `DeadlineCommand` handles the creation of a task with a deadline. It requires a task description and a `/by` tag to specify the deadline.

#### Implementation Details

The `DeadlineCommand` class implements the `Command<TaskList>` interface and interacts with the `TaskList` to add a new deadline task.

#### Operations

`DeadlineCommand#execute(String parts, TaskList taskList)`

- Parses the task description and deadline from the user input.
- Ensures that the `/by` tag is provided in the input.
- Validates the deadline format using `DateTimeFormatterUtil`.
- Creates a `Deadline` task and adds it to the `TaskList`.
- If the input format is incorrect, prompts the user to re-enter the command.
- Displays an error message if:
  - The `/by` tag is missing.
  - The deadline format is incorrect.
  - Any input is missing.

#### 4. EventCommand

The `EventCommand` handles the creation of a task with event timings. It requires a task description along with `/from` and `/to` tags to specify the start and end times.

#### Implementation Details

The `EventCommand` class implements the `Command<TaskList>` interface and interacts with the `TaskList` to add a new event task.

#### Operations

`EventCommand#execute(String parts, TaskList taskList)`

- Parses the task description, start time, and end time from the user input.
- Ensures that both `/from` and `/to` tags are provided in the input.
- Validates the datetime format using `DateTimeFormatterUtil`.
- Creates an `Event` task and adds it to the `TaskList`.
- If the input format is incorrect, prompts the user to re-enter the command.
- Displays an error message if:
  - The `/from` or `/to` tags are missing.
  - The datetime format is incorrect.
  - Any input is missing.

#### 5. TodoCommand

The `TodoCommand` handles the creation of a new todo task. It requires a task description as input.

#### Implementation Details

The `TodoCommand` class implements the `Command<TaskList>` interface and interacts with the `TaskList` to add a new todo task.

#### Operations

`TodoCommand#execute(String parts, TaskList taskList)`

- Parses the task description from the user input.
- Ensures that a non-empty description is provided.
- Creates a `Todo` task and adds it to the `TaskList`.
- Displays an error message if:
  - The task description is missing or empty.

#### 6. FindTaskCommand

The `FindTaskCommand` handles searching for tasks that contain a specified keyword. It retrieves and displays all matching tasks from the task list.

#### Implementation Details

The `FindTaskCommand` class implements the `Command<TaskList>` interface and interacts with the `TaskList` to search for tasks based on the given keyword.

#### Operations

`FindTaskCommand#execute(String parts, TaskList taskList)`

- Searches for tasks that contain the specified keyword.
- Retrieves a list of matching tasks using `taskList.getTaskListWithKeyWord(parts)`.
- If matching tasks are found, displays them to the user.
- Displays an error message if:
  - The keyword is missing or empty.
  - No matching tasks are found.

#### 7. ListTaskCommand

The `ListTaskCommand` handles displaying all tasks in the task list. If there are no tasks, it prints a message indicating that no tasks are available.

#### Implementation Details

The `ListTaskCommand` class implements the `Command<TaskList>` interface and interacts with the `TaskList` to retrieve and display tasks.

#### Operations

`ListTaskCommand#execute(String parts, TaskList taskList)`

- Retrieves all tasks from the `TaskList`.
- If tasks exist, prints the list of tasks to the user.
- If no tasks exist, displays the message: `noting to do yet`.
- The `parts` parameter is not used for this command.

#### 8. MarkTaskCommand

The `MarkTaskCommand` handles marking a task as completed. It requires a valid task number as input.

#### Implementation Details

The `MarkTaskCommand` class implements the `Command<TaskList>` interface and interacts with the `TaskList` to mark a specified task as done.

#### Operations

`MarkTaskCommand#execute(String parts, TaskList taskList)`

- Validates whether the provided input is a valid integer using `IntegerChecker.isInteger(parts)`.
- Converts the input to an integer and marks the corresponding task as done using `taskList.markTaskAsDone(taskNumber)`.
- Displays an error message if:
  - The input is not a valid task number.
  - The task number does not exist in the list.

#### 9. UnmarkTaskCommand

The `UnmarkTaskCommand` handles marking a task as undone. It requires a valid task number as input.

#### Implementation Details

The `UnmarkTaskCommand` class implements the `Command<TaskList>` interface and interacts with the `TaskList` to mark a specified task as undone.

#### Operations

`UnmarkTaskCommand#execute(String parts, TaskList taskList)`

- Validates whether the provided input is a valid integer using `IntegerChecker.isInteger(parts)`.
- Converts the input to an integer and marks the corresponding task as undone using `taskList.markTaskAsUndone(taskNumber)`.
- Displays an error message if:
  - The input is not a valid task number.
  - The task number does not exist in the list.

#### 10. RenameTaskCommand

The `RenameTaskCommand` handles renaming a task. It requires a task number and the new task name.

#### Implementation Details

The `RenameTaskCommand` class implements the `Command<TaskList>` interface and interacts with the `TaskList` to rename a specified task.

#### Operations

`RenameTaskCommand#execute(String parts, TaskList taskList)`

- Parses the task number and the new task name from the user input.
- Renames the specified task in the `TaskList` using `taskList.renameTask(taskNumber, newTaskName)`.
- Displays an error message if:
  - The input is missing the task number or new task name.
  - The task number format is invalid.
  - The task number does not exist in the list.

  

## Appendix A: Product scope
### Target user profile

Our target users are teaching assistants (TAs) and tutors who manage both student-related administrative tasks and personal workload. They often handle responsibilities like tracking attendance, recording remarks, and planning tutorials, requiring an efficient way to stay organized. These users are comfortable with command-line interfaces (CLI) and prefer automation to manual data entry, seeking a tool that integrates smoothly into their workflow without the overhead of graphical applications.

### Value proposition

Teaching assistants and tutors often struggle with time-consuming administrative tasks which can be tedious and inefficient with traditional tools. Our CLI-based solution streamlines these processes, offering a fast, automated, and distraction-free way to manage academic responsibilities. By eliminating the need for GUI-based software, it enhances productivity and reduces manual effort.

## User Stories

| Version | As a ...                       | I want to ...                                                                          | So that I can ...                                                                            |
|---------|--------------------------------|----------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------|
| v1.0    | inexperienced or new TA        | know how to use TASync easily by referring to a readily accessible built-in user guide | quickly learn how to use the application and avoid mistakes when interacting with the system |
| v1.0    | TA                             | edit (add/delete) the student list                                                     | track who is in my class and ensure accurate attendance records                              |
| v1.0    | TA                             | mark attendance for tutorial sessions                                                  | keep track of student attendance to ensure participation                                     |
| v1.0    | TA                             | create weekly tutorial attendance list                                                 | organize attendance per session and quickly reference past attendance records                |
| v1.0    | TA after each session          | add remarks for the student after each tutorial                                        | refer back to feedback for future sessions and provide constructive comments                 |
| v1.0    | TA with lots of classes        | find students in my tutorials easily                                                   | quickly locate students and manage tutorial-related activities                               |
| v1.0    | TA after each session          | give remarks on my students for future reference                                       | provide personalized feedback to students for future reference and improvement               |
| v1.0    | TA managing multiple tutorials | list out my tutorial classes for a specified amount of time                            | view my scheduled tutorials over a period of time for better planning                        |
| v1.0    | busy TA                        | add consultation slots into the list of todo                                           | show when my consultation slots are and manage my time effectively                           |
| v1.0    | busy TA                        | find a task by name                                                                    | locate a task without having to go through the entire list                                   |
| v2.0    | busy TA                        | schedule and modify my teaching schedules                                              | accommodate student consultations/meetings with professors and stay organized                |
| v2.0    | busy TA                        | print out tutorial + tasks on the particular day once the app is opened                | quickly view my teaching schedule and tasks for the day upon opening the app                 |
| v2.0    | responsible TA                 | track a student’s individual assignment grades                                         | monitor grades for students to provide timely feedback                                       |


## Non-Functional Requirements

1. Should work on any _mainstream OS_ with Java 17 installed.
2. All commands should be executable in English text, using only keyboard and no other input devices.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should
be able to accomplish most tasks using commands faster than using the mouse.
4. Should be able to hold up to 1000 students without any noticeable sluggishness in performance for typical usage.
5. Data must be stored locally in human-editable file formats.

## Glossary

* *CLI* - Command Line Interface
* *GUI* - Graphical User Interface
* *Mainstream OS* - Windows, Linux, Unix, macOS
* *TA* - Teaching Assistants

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
