import java.util.*;

/**
 * Models a max heap to store flashcards. It has
 * methods to add, remove and sort elements.
 */
public class FlashcardPQ implements PriorityQueue<Flashcard> {
  private List<Flashcard> heap;
  public FlashcardPQ(){
    heap = new ArrayList<Flashcard>();
  }
  /**
   * Rerurns the size of the heap.
   * @return the size of the heap
   */
  public int getHeapSize(){
    return heap.size();
  }
  /**
   * returns true if the element is a leaf on the heap; false otherwise
   * @param position the index of a vertex in the heap
   * @return true if the element is a leaf on the heap; false otherwise.
   */
  public boolean isLeaf(int position){
    return  (position >= getHeapSize()/2) && (position < getHeapSize());
  }
  /**
   * Returns the index of the left child of position.
   * @param position the position of the node
   * @return the index where the left child of the vertex requested is, or -1 if it does not exist
   */
  public int getLeftChild(int position){
    if(position >= getHeapSize()/2){
      return -1;
    }
    return (2*position + 1);
  }
   /**
   * Returns the index of the right child of position.
   * @param position the position of the node
   * @return the index where the right child of the vertex requested is, or -1 if it does not exist
   */
  public Flashcard getRightChild(int position){
    if(position >= (getHeapSize()-1)/2){
      return null;
    }
    return heap.get(2*position+2);
  }
  /**
   * Returns the parent of the node. 
   * @param position the index of the node requested by the user
   * @return the index of the parent of the node requested, or -1 if it is the root.
   */
  public int getParent(int position){
    if(position <= 0){
      return -1;
    }
    return ((position - 1)/2);
  }
  /**
   * Swaps positions of elements a and b within the array.
   * @param a an integer representing the position of a vertex a of a heap, which will go to position b
   * @param b an integer representing the position of a vertex b of a heap, which will be swapped to position b
   */
  public void swap(int a, int b){
    Flashcard aux = heap.get(a);
    heap.set(a, heap.get(b));
    heap.set(b, aux);
  }
  /**
   * Sorts currentIndex the heap by putting it closer to the root
   * if it is larger than its parent value.
   * @param currentIndex an integer representing the index of an element, priority of this element is going to be compared with the priority of the parent of this vertex
   */
  private void heapifyUp(int currentIndex){
    if(currentIndex != 0){
      Flashcard priority2 = heap.get(getParent(currentIndex));
      if((heap.get(currentIndex).compareTo(priority2) > 0)){
        swap(currentIndex, getParent(currentIndex));
        heapifyUp(getParent(currentIndex));
      }
    }
  }
  /**
   * Sorts currentIndex in the heap, putting it down if it is smaller
   * than its children elements.
   * @param currentIndex an integer representing the index of an element, the priority of this element is going to be used to sort it
   */
  private void heapifyDown(int currentIndex){
    if ((currentIndex < 0) || (currentIndex >= getHeapSize())) {
       return; 
    }
    while (!isLeaf(currentIndex)) {
      int j = getLeftChild(currentIndex);
      if ((j<(heap.size()-1)) && (heap.get(j).compareTo(heap.get(j+1)) < 0)) {
        j++; 
      }
      if (heap.get(currentIndex).compareTo(heap.get(j)) > 0) { return; }
      swap(currentIndex, j);
      currentIndex = j;
    }
  }
   /**
    * Adds the given item to the heap, seting it in its correct position
    * @param item the element to be added to the heap
    */
    public void add(Flashcard item){
      heap.add(item);
      heapifyUp(getHeapSize()- 1);
      
    }
    /** 
     * Removes and returns the first item of the heap.
     * Returns null if the heap is empty.
     * @return the Flashcard which is in the first position of the heap or null if the it is empty
     */
    public Flashcard poll(){
      Flashcard toReturn = heap.get(0);
      if(getHeapSize() == 0){
        return null;
      }
      swap(0, getHeapSize() -1);
      heap.remove(getHeapSize()-1);
      heapifyDown(0);
      return toReturn;
    }
    
    /** 
     * Returns the item on top of the heap, or null if it is empty.
     * @return the item on top of the heap or null if it is empty.
     */
    public Flashcard peek(){
      if(getHeapSize() == 0){
        return null;
      }
      return heap.get(0);
    }
    
    /** 
     * Returns true if the queue is empty; false otherwise
     * @return true if the heap is empty; false otherwise
     */
    public boolean isEmpty(){
      return (getHeapSize() == 0);
    }
    
    /** 
    * Removes all items from the queue.
    */
    public void clear(){
      heap = new ArrayList<Flashcard>();
    }

    /**
     * Creates a String of the current setup of the priority queue
     * @return a string to save to a file
     */
    public String toString() {
      String toReturn = "";
      for(int i = 0; i < getHeapSize(); i++){
        toReturn += heap.get(i);
      }
      return toReturn;
    }

  /**
   * Tests the program and all its methods
   */
    public static void main(String[] args) {
      FlashcardPQ test = new FlashcardPQ();
      System.out.println(test.isEmpty());
      System.out.println(test.peek());
      test.add(new Flashcard(100, "1", "1"));
      System.out.println(test.isLeaf(0));
      System.out.println(test.isEmpty());
      System.out.println(test.peek());
      test.clear();
      System.out.println(test.isEmpty());
      System.out.println(test.peek());
      test.add(new Flashcard(101, "2", "2"));
      System.out.println(test.getHeapSize());
      System.out.println(test.peek());
      test.add(new Flashcard(99, "2", "2"));
      System.out.println(test.getHeapSize());
      System.out.println(test.peek());
      test.add(new Flashcard(85, "2", "2"));
      System.out.println(test.isLeaf(0));
      System.out.println(test.isLeaf(1));
      System.out.println(test.isLeaf(2));
      System.out.println(test.getHeapSize());
      System.out.println(test.peek());
      test.add(new Flashcard(90, "2", "2"));
      System.out.println(test.peek());
      test.add(new Flashcard(105, "2", "2"));
      System.out.println(test.peek());
      test.add(new Flashcard(102, "2", "2"));
      test.add(new Flashcard(80, "2", "2"));
      test.add(new Flashcard(92, "2", "2"));
      test.add(new Flashcard(100, "2", "2"));
      test.add(new Flashcard(103, "2", "2"));
      test.add(new Flashcard(99, "2", "2"));
      test.add(new Flashcard(1000, "2", "2"));
      test.add(new Flashcard(60, "2", "2"));
      System.out.println(test.heap.get(0));
      System.out.println(test.heap.get(1));
      System.out.println(test.heap.get(2));
      System.out.println(test.heap.get(3));
      System.out.println(test.heap.get(4));
      System.out.println(test.heap.get(5));
      System.out.println(test.heap.get(6));
      System.out.println(test.heap.get(7));
      System.out.println(test.heap.get(8));
      System.out.println(test.heap.get(9));
      System.out.println(test.heap.get(10));
      System.out.println(test.heap.get(11));
      FlashcardPQ test2 = new FlashcardPQ();
      test2.add(new Flashcard(100, "1", "1"));
      test2.add(new Flashcard(99, "2", "2"));
      test2.add(new Flashcard(101, "2", "2"));
      test2.add(new Flashcard(20, "2", "2"));
      System.out.println(test2.getHeapSize());
      System.out.println(test2.heap.get(0));
      System.out.println(test2.heap.get(1));
      System.out.println(test2.heap.get(2));
      System.out.println(test2.heap.get(3));
      System.out.println();
      System.out.println("Poll");
      System.out.println();
      test2.poll();
      System.out.println(test2.getHeapSize());
      System.out.println(test2.heap.get(0));
      System.out.println(test2.heap.get(1));
      System.out.println(test2.heap.get(2));
      test2.swap(0, 1);
      System.out.println(test2.heap.get(0));
      System.out.println(test2.heap.get(1));
      test2.swap(0, 1);
      System.out.println(test2.heap.get(0));
      System.out.println(test2.heap.get(1));
      System.out.println(test2.heap.get(2));
      System.out.println(test2.getParent(1));
      System.out.println(test2.getParent(2));
      test2.add(new Flashcard(20, "2", "3"));
      System.out.println(test2.getRightChild(0));
      System.out.println(test2.getLeftChild(1));
      System.out.println(test2.toString());
      System.out.println(test);
      test.poll();
      System.out.println(test);
      test.poll();
      System.out.println(test);
      test.poll();
      System.out.println(test);
      test.poll();
      System.out.println(test);
    }
}
