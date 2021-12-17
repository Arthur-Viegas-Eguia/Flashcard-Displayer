# Flashcard-Displayer
Homework developed for Data Structures taught by Anya Vostinar.
## Overview
The program models a studying session using flashcards. It contniuously asks the user questions, waits for the answers and automatically checks them.
If the answer is correct the program informs the user and deprioritizes the question, if the answer is incorrect it gets prioritized as the user has difficulties with it.
After the studying session the program updates the priority level of the questions. With this program you can:
- Upload your own question dataset with via external files
- Have answers automatically corrected by the program
- Answer as many questions you like
- Having the priority of questions automatically uploaded based on whether you got them correct or inorrect
- Tracking your progress, by automatically updating the priority of your questions on the original question bank file
## Usage
To use the program, you have to compile and run the code after downloading. To do this, simply
type in the terminal:
```
javac *.java
java FlashcardDisplayer value
```
Where value represents the path of the external file which contains the questions dataset. This file is a comma separated list and must follow the following format:
```
priority,Front of the flashcard,back of the flashcard.
```
Each line represents a flashcard and there must be a single flashcard per line.

