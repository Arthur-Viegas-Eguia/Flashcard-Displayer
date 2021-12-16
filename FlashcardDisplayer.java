import java.util.*;
import java.io.*;
/**
 * Models a studying program with flasshcard. It requires the user to send 
 * their own database with questions, which will be used to test the knowledge
 * of the user. It has methods to continuously show users the front side of a
 * flashcard and asks for answers. If the user answered correctly, the program
 * lets the user know and sets decreases the  prioririty of the question If the
 * user answers incorrectly, the question becomes more important as priority is
 * increasedcAfter the user is done studying for the day, the program saves the
 * flashcard with the updated level for importance in an external text file
 */
public class FlashcardDisplayer {
    /**
     * cards is the variable which stores the priorityqueue where all the
     * questions of the dataset are stored. As it is an object of type
     * FlashcardPQ it has methods to add flashcards, update their priorities
     * and others.
     */
    FlashcardPQ cards = new FlashcardPQ();
    /**
     * The path to the file containing the questions dataset
     */
    String fileName;

    /**
     * Creates a FlashcardDisplayer with the flashcards in file. 
     * File has one flashcard per line. Each line is formatted as:
     * priority,front,back
     * @param inputFile The file with the flashcards saved
     */
    public FlashcardDisplayer(String inputFile) {
        fileName = inputFile;
        File input = new File(inputFile);
        Scanner scanner = null;
        try {
            scanner = new Scanner(input);
        } catch (FileNotFoundException e) {
            System.err.println(e);
            System.exit(1);
        }

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitline = line.split(",");
            cards.add(new Flashcard(Integer.parseInt(splitline[0]), splitline[1], splitline[2]));
        }
        scanner.close();
    }
    
    /**
     * Writes out all flashcards to the same file
     * they were read from so that they can be loaded by
     * the FlashcardDisplayer(String file) constructor. 
     * The FlashcardDisplayer will still have 
     * all the same flashcards after this method is called 
     * as it did before the method was called.
     */
    public void saveFlashcards() {
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(cards.toString());
            myWriter.close();
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    /**
    * Continuously displays flashcards to the user
    * and checks their answers, updating the priority
    * of each flashcard based on if the user answered 
    * correctly or not. Displays the highest priority
    * flashcard first. Ends when the user enters 'save'.
    */
    public void displayFlashcards(){
        Flashcard currentCard;
        String backText;
        String answer;
        boolean cont = true;
        Scanner userInput = new Scanner(System.in);
        while(cont){
            currentCard = cards.poll();
            System.out.println("Card:\n"+currentCard.getFrontText());
            System.out.print("Your answer: ");
            answer = userInput.nextLine();
            backText = currentCard.getBackText();
            if(backText.equals(answer)){
                System.out.println("Correct!");
                currentCard.setPriority(currentCard.getPriority() - 2);
                cards.add(currentCard);
            } else{
                System.out.println("Not quite!\nCorrect answer: "+currentCard.getBackText());
                currentCard.setPriority(currentCard.getPriority() +3);
                cards.add(currentCard);
            }
            while(true){
                System.out.print("save or next? ");
                answer = userInput.nextLine();
                if(answer.equals("save")){
                    saveFlashcards();
                    cont = false;
                    userInput.close();
                    break;
                } else if(answer.equals("next")){
                    break;
                } else{
                    System.out.println("Invalid input, please type next or save, the program does not accept other values");
                }
            }
        }
    }
    /**
     * Gets the questions dataset from an external file provided by the user.
     * Initializes the class, keeps asking the user questions and correcting
     * their answers until they want to stop studying, then the program will
     * save the user's progess.
     * @param args the path to an external text file containing a dataset of
     * questions.
     */
    public static void main(String[] args) {
        if (args.length == 0) { 
            System.err.println("Error. Please provide a file containing the questions of your study session");  
            System.exit(1);
        }
        System.out.println("Welcome! This program will display your ");
        System.out.println("flashcards to you, check if you entered the ");
        System.out.println("correct answer and then ask you save or next? ");
        System.out.println("Saving will update the information in the file ");
        System.out.println("you provided. Here is your first card.");
        String file = args[0];
        FlashcardDisplayer test = new FlashcardDisplayer(file);
        test.displayFlashcards();
    }
}
