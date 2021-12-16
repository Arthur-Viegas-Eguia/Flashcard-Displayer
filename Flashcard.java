public class Flashcard implements Comparable<Flashcard>{
  private Integer priority;
  private String front;
  private String back;
  /**
   * Creates a new flashcard with the given
   * priority level, text for the front of the card (front),
   * and text for the back of the card (back).
   */
  public Flashcard(Integer priority, String front, String back){
    this.priority = priority;
    this.front = front;
    this.back = back;
  }

  /**
   * Gets the text for the front of this flashcard.
   * @return the front text of the flashcard
   */
  public String getFrontText(){
    return front;
  }
  
  /**
   * Gets the text for the Back of this flashcard.
   * @return the back text of the flashcard
   */
  public String getBackText(){
    return back;
  }
  
  /**
   * Gets the priority level of this flashcard.
   * @return the priority level of the flashcard
   */
  public Integer getPriority(){
    return priority;
  }

  /**
   * Compares the priority levels of the current flashcard and another flashcard
   * @param b, a second flashcad, which will be compared to the current flashcard
   * @return a negative integer if b comes before a, a positive integer if a comes before b, otherwise, it returns 0.
   */
  public int compareTo(Flashcard b){
    return this.priority.compareTo(b.priority);
  }

  /**
   * Returns a string in the format "priority,front,back\n"
   * @return a string in the format "priority,front,back\n"
   */
  public String toString() {
    return priority+","+front+","+back+"\n";
  }

  /**
   * Updates the priority of the card but doesn't allow priority to become negative.
   * @param newPriority an integer representing the new priority level of a flashcard
   */
  public void setPriority(Integer newPriority){
    if(newPriority >= 0){
      priority = newPriority;
    }
  }

    
}