# vihaan27- Project Portfolio Page

## Overview

## Summary of Contributions
### Code contributed
Please view my code contribution at:
- [Link to my code dashboard](#https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=vihaan27&breakdown=true)

### Enhancements implemented

#### 1. Implemented marks and marksList classes
- **Marks Class**
  - Created the marks class to represent the marks achieved by a student for a given assignment. The marks
  class stores attributes representing assignment name, marks achieved and maximum marks.

- **MarksList Class**
  - Created the marksList class to represent a list of marks, and handle operations related to the marks list.
  - Designed to be stored as an attribute for each student, the MarksList class contains a list of marks objects,
  representing the marks for all assignments by the student. It contains methods to add and delete marks from the list,
  print the list, and find a marks object by its assignment name.

#### 2. Marks Commands
The Marks commands allow the target user, TAs and tutors to quickly and flexibly track the marks achieved for
different assignments by students. The `NEWMARKS -m` and `DELETEMARKS -m` commands allow the user to input and make
changes to a student's marks, while the `LIST -m` command allows users to view a snapshot of a student's performance.

- **AddMarksCommand**
  - Implemented the AddMarksCommand class, handling the execution of the `NEWMARKS -m` command. This command is
  responsible for adding new marks for an assignment by a given student. The execute() function parses the user input
  to find the tutorial name, matric number, assignment name, marks achieved and max marks, and accordingly creates and adds
  the marks to the given student's marksList.

- **DeleteMarksCommand**
  - Implemented the DeleteMarksCommand class, handling the execution of the `DELETEMARKS -m` command. This command is
  responsible for deleting marks with a given assignment name from a student's marksList. The execute() function parses
  the user input to find the tutorial name, matric number and assignment name supplied by the user, searches for the marks
  for the given assignment name in the student's list, and deletes it.

- **ListMarksCommand**
  - Implemented the ListMarksCommand class, handling the execution of the `LIST -m` command. This command is responsible
  for listing the marks achieved for all assignments by a given student. The execute() function parses the user input to 
  find the tutorial name and matric number supplied by the user, and prints all marks for the matching student, followed 
  by the calculated average marks expressed as a percentage.

#### 3. Persistent storage of marks
Implemented the marksListLoader and marksListSaver classes, collectively responsible for saving all marks objects
for all students to a text file in a standard format, and loading it from the text file when TASync is launched. The classes
also implement checks to maintain data integrity, deleting corrupted or invalid stored data and handling cases when the save
file or directory is deleted.

#### 4. Testing
Implemented JUnit tests for marks and marksList classes.

### Contributions to the UG

- Complete documentation for all marks commands, namely the `NEWMARKS -m`, `DELETEMARKS -m` and `LIST -m` commands,
explaining the functionality and providing the command formats and example usage.

### Contributions to the DG

1. Complete documentation of the implementation details for the AddMarksCommand, DeleteMarksCommand and ListMarksCommand
classes, with sequence diagrams illustrating interactions with other classes throughout execution.
2. Completed _Appendix C_ documenting non-functional requirements for TASync.
3. Completed _Appendix E: Instructions for manual testing_ providing a comprehensive view of testing TASync
from initial launch and covering all user-testable features.

### Contributions to team-based tasks
- Set up the team organisation and team project repository.

### Review/mentoring contributions
- Reviewed PRs from other team members, with feedback on enhancements and bug fixes.

### Contributions beyond the project team
- Conducted extensive testing for another team, providing bug reports for functionality,
features and documentation.