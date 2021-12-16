import java.util.*;
import java.io.*;
/**
 * Models a studying session with flashcards. The program takes a customized
 * question bank and asks the questions from it until told it is to stop. The program
 * autoatically corrects the answers, and updates the priority of the flashcards
 * based on correct/incorrect answers. The more difficulty an user has in
 * a question, the more it will be asked. It also saves the flashcards with
 * their updated priority in the end of the program.
 */
public class FlashcardDisplayer {
    FlashcardPQ cards = new FlashcardPQ();
    String fileName;

    /**
     * Constructor of the class.
     * @param inputFile The file with the flashcards
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
     * Writes out all flashcards to the original file
     * with their updated priority level after the session.
     * 
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
    * Continuously asks users questions and corrects
    * their answers. Updates priority of flashcards 
    * based on the correctness of the answers/
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
     * Receives the question dataset from an external file, asks user questions
     * until told to stop, and saves progress of studying session.
     * @param args the path to an external text file containing the question dataset
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
