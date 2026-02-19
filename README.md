# User Guide

Donk is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI). If you can type fast, Donk can get your task management done faster than traditional GUI apps.

## Quick start

1. Ensure you have Java 17 or above installed on your Computer.

2. Download the latest `.jar` file from the releases page.

3. Copy the file to the folder you want to use as the home folder for your Donk app.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar donk.jar` command to run the application.

5. Type the command in the terminal and press Enter to execute it. e.g. typing `help` and pressing Enter will show available commands.

6. Some example commands you can try:
   - `list` : Lists all tasks.
   - `todo read book` : Adds a todo task to read book.
   - `delete 1` : Deletes the 1st task shown in the current list.
   - `bye` : Exits the app.

Refer to the Features below for details of each command.

## Features

**Notes about the command format:**

- Words in `UPPER_CASE` are the parameters to be supplied by the user.  
  e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo read book`.

- Items in square brackets are optional.  
  e.g `deadline DESCRIPTION /by DATE [TIME]` can be used as `deadline assignment /by 2024-12-01` or as `deadline assignment /by 2024-12-01 2359`.

- Date format should be `YYYY-MM-DD` and time format should be `HHMM`.

### Adding a todo task: `todo`

Adds a todo task to the task list.

**Format:** `todo DESCRIPTION`

**Examples:**

- `todo read book`
- `todo buy groceries`

### Adding a deadline task: `deadline`

Adds a deadline task with a due date to the task list.

**Format:** `deadline DESCRIPTION /by DATE [TIME]`

**Examples:**

- `deadline submit assignment /by 2024-12-01`
- `deadline finish project /by 2024-11-30 2359`

### Adding an event task: `event`

Adds an event task with start and end times to the task list.

**Format:** `event DESCRIPTION /from DATETIME /to DATETIME`

**Examples:**

- `event team meeting /from 2024-11-25 1400 /to 2024-11-25 1500`
- `event conference /from 2024-12-01 0900 /to 2024-12-01 1700`

### Listing all tasks: `list`

Shows a list of all tasks in the task list.

**Format:** `list`

### Marking a task as done: `mark`

Marks the specified task as completed.

**Format:** `mark INDEX`

- Marks the task at the specified `INDEX` as done.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …

**Examples:**

- `mark 1` marks the 1st task as done.
- `mark 3` marks the 3rd task as done.

### Marking a task as not done: `unmark`

Marks the specified task as not completed.

**Format:** `unmark INDEX`

- Marks the task at the specified `INDEX` as not done.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …

**Examples:**

- `unmark 1` marks the 1st task as not done.
- `unmark 2` marks the 2nd task as not done.

### Deleting a task: `delete`

Deletes the specified task from the task list.

**Format:** `delete INDEX`

- Deletes the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …

**Examples:**

- `list` followed by `delete 2` deletes the 2nd task in the task list.

### Finding tasks: `find`

Finds tasks whose descriptions contain the given keyword.

**Format:** `find KEYWORD`

- The search is case-insensitive. e.g `book` will match `Book`
- Only the task description is searched.
- Tasks containing the keyword will be returned.

**Examples:**

- `find book` returns tasks containing "book" in their description
- `find assignment` returns tasks containing "assignment"

### Viewing help: `help`

Shows a message with available commands and their formats.

**Format:** `help`

### Exiting the program: `bye`

Exits the program.

**Format:** `bye`

## Saving the data

Task data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## Editing the data file

Task data is saved automatically as a text file `[JAR file location]/data/donk.txt`. Advanced users are welcome to update data directly by editing that data file.

:exclamation: **Caution:** If your changes to the data file make its format invalid, Donk will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.

## FAQ

**Q:** How do I transfer my data to another Computer?  
**A:** Install the app on the other computer and overwrite the empty data file it creates with the file that contains the data from your previous Donk home folder.

## Command summary

| Action            | Format, Examples                                                                                                     |
| ----------------- | -------------------------------------------------------------------------------------------------------------------- |
| **Add todo**      | `todo DESCRIPTION` <br> e.g., `todo read book`                                                                       |
| **Add deadline**  | `deadline DESCRIPTION /by DATE [TIME]` <br> e.g., `deadline assignment /by 2024-12-01`                               |
| **Add event**     | `event DESCRIPTION /from DATETIME /to DATETIME` <br> e.g., `event meeting /from 2024-11-25 1400 /to 2024-11-25 1500` |
| **List**          | `list`                                                                                                               |
| **Mark done**     | `mark INDEX` <br> e.g., `mark 1`                                                                                     |
| **Mark not done** | `unmark INDEX` <br> e.g., `unmark 1`                                                                                 |
| **Delete**        | `delete INDEX` <br> e.g., `delete 3`                                                                                 |
| **Find**          | `find KEYWORD` <br> e.g., `find book`                                                                                |
| **Help**          | `help`                                                                                                               |
| **Exit**          | `bye`                                                                                                                |
