// Java Program to Implement Generic Linked List

// Importing all input output classes
import java.util.Scanner;

// Node Class: (Generic Node class for LinkedList)
class Node<T> {
  private T element; // Data element to sore an item
  private Node<T> next; // a reference to the next Node

  // Parameterized constructor to assign value
  Node(T item) {
    this.element = item; // "this" refers to current object itself
    this.next = null;
  }

  void setNext(Node<T> n){ // set the private memeber
    this.next = n;
  }

  Node<T> getNext(){ //get the private member
    return this.next;
  }

  void setElement(T item){ // set the private memeber
    this.element = item;
  }

  T getElement(){ //get the private member
    return this.element;
  }

  public void displayRecursively(){ // display Node recursively
    System.out.print(this.getElement());
    if (this.next != null){
      System.out.print(" , ");
      this.next.displayRecursively();
    } else {
      System.out.println("] ");
    }
  }
}

// List Class: ( Generic LinkedList class)
class List<T extends Comparable<T>> {

  // Generic Node instance
  private Node<T> head;
  private int size; // store the size of the list

  List(){ // Default constructor
    this.head = null; // a reference ot the head node
    this.size = 0;    // maintain the size of the list
  } 

  // ********************************** isEmpty() **********************************
  boolean isEmpty(){ // a method to check if the List is empty
    return (this.head == null);
  }

  // ********************************** addLast() **********************************
  void addLast(T item) { // add a Node containing item at the end of List
    Node<T> newNode = new Node<>(item); // create a new Node with the given item

    if(this.head == null){ // Check if the head 
      head = newNode;
    } else {
      Node<T> temp = this.head; // create a temporary reference to the first node in the list

      while (temp.getNext() != null) { // move the reference to the last node
        temp = temp.getNext();
      }
      temp.setNext(newNode); // link the last node to the new node referenced by newNode 
    }
    this.size++; // update the size of the list
  }

  // ********************************** addFront() **********************************
  void addFront(T item){ //a method to add an item at the front of the list
    Node<T> newNode = new Node<>(item); // create a new Node with the given item
    newNode.setNext(this.head);
    this.head = newNode;
    this.size++;
    System.out.println("new node added at front !");
  }

  // ********************************** addAt() **********************************
  void addAt (int index, T item) { // add item in a new Node at index position

    // Checking if position is valid
    if (index < 0 || index >= this.size ) {
      System.out.println("index is out of bound !");
    } else {
      Node<T> newNode = new Node<>(item); // create a new Node with the given item

      int count = 0;  // start a counter
      Node<T> temp = head; // create a temporary Node reference
      while(temp != null && count < index-1){ // move to index position
        count++;
        temp = temp.getNext();
      }
      if(index >0){
        newNode.setNext(temp.getNext());
        temp.setNext(newNode);
      }else if( index == 0) {
        newNode.setNext(this.head);
        this.head = newNode;
      }
      System.out.println("new node added at index "+ index);
    }
  }

  // ********************************** size() **********************************
  public int size(){ // a method to return the number of elements in the lsit
    return this.size;
  }

  // ********************************** displayAll() **********************************
  public void displayAll(){ // Method to display the LinkedList

    System.out.println("displayAll():");
    System.out.println("____________List of "+this.size+" items________________");

    if (this.isEmpty()){
      System.out.println("The list is empty ...! ");
    } else {
      Node<T> temp = this.head;
      System.out.print("[ ");
      while(temp != null){
        System.out.print(temp.getElement());
        if(temp.getNext() == null)
          System.out.println("] ");
        else
          System.out.print(" , ");
        temp = temp.getNext();
      }
    }  
    System.out.println();
  }

  // ********************************** displayRecursively() **********************************
  public void displayRecursively(){
    System.out.println("displayRecursively():");
    System.out.println("____________List of "+this.size+" items________________");
    if (this.isEmpty()){
      System.out.println("The list is empty ...! ");
    } else {
      System.out.print("[ ");
      this.head.displayRecursively();
    }
    System.out.println();
  }

  // ********************************** removeLast() **********************************
  public void removeLast(){
    if(this.isEmpty()){
      System.out.println("The list is empty !");
    } else if(this.head.getNext() == null){
      this.head = null;
      this.size--;    // update the size
      System.out.println("last item removed");
    } else {
      Node<T> temp = this.head;
      
      while((temp.getNext() != null) && (temp.getNext().getNext() != null)){
        temp = temp.getNext();
      }
      temp.setNext(null);
      temp = null;
      this.size--;        // update teh size
      System.out.println("last item removed");
    }
  }

  // ********************************** removeAt() **********************************
  public void removeAt(int index){
    if(this.isEmpty()){ // check the empty list
      System.out.println("The list is empty !");
    } else if(index >= this.size || index < 0){ // check index is valid
      System.out.println("index is out of bound !");
    } else if (index == 0){ // remove the first Node
      this.head = this.head.getNext();
      this.size--;
      System.out.println("item is removed at index "+index);
    } else{ // remove other than the first node
      int count = 0;
      Node<T> temp = this.head;

      while(count < index-1){ // search for the node at position index
        count++;
        temp = temp.getNext();
      }

      Node<T> pNode = temp.getNext(); // hold on to that node temporarily 
      temp.setNext(pNode.getNext());  // hold on to that node temporarily 
      pNode.setNext(null);            // disconnect that node form the list
      this.size--;                    // update the size
      System.out.println("item is removed at index "+index);
    }
  }

  // ********************************** removeFront() **********************************
  public void removeFront(){
    if(this.isEmpty()){
      System.out.println("The list is empty !");
    }else{
      Node<T> temp = this.head;
      this.head = this.head.getNext();
      temp.setNext(null);
      System.out.println("front item removed");
    }
  }

  // ********************************** getAt() **********************************
  T getAt(int index){
    T res = null;
    if (this.isEmpty()){
      System.out.println("The list is empty !");
    } else if (index >= this.size || index < 0){
      System.out.println("index is out of bound !");
    } else if (index == 0) {
      res = this.head.getElement();
    } else {
      int count = 0;
      Node<T> temp = this.head;

      while(count++ != index){ // search for the node at position index
        temp = temp.getNext();
      }
      res = temp.getElement();
    }
    return res;
  }

  // ********************************** splitAt() - Question 1 **********************************
  // Splits the current list at the given index:
  //   - this list keeps elements at positions 0 .. index (inclusive)
  //   - a new list containing elements at positions index .. size-1 is returned
  // Both lists share the element at 'index' (as shown in the assignment example).
  public List<T> splitAt(int index) {

    List<T> newList = new List<>(); // the right-hand list to return

    // Validate the index
    if (this.isEmpty()) {
      System.out.println("The list is empty — cannot split!");
      return newList;
    }
    if (index < 0 || index >= this.size) {
      System.out.println("index is out of bound !");
      return newList;
    }

    // Walk to the node AT 'index' so we can cut the chain right after it
    int count = 0;
    Node<T> temp = this.head;
    while (count < index) {        // stop when temp points to node[index]
      temp = temp.getNext();
      count++;
    }

    // 'temp' is now the node at position 'index'.
    // The new list starts from this same node (element is shared per the spec).
    newList.head = temp;           // right part begins at index

    // Count how many nodes go into the new list so we can keep sizes accurate
    int rightSize = 0;
    Node<T> walker = newList.head;
    while (walker != null) {
      rightSize++;
      walker = walker.getNext();
    }
    newList.size = rightSize;

    // Sever the chain: the left part ends right before index
    // (if index == 0 the left list becomes empty)
    if (index == 0) {
      this.head = null;
      this.size = 0;
    } else {
      // Re-walk to node[index-1] and cut its next pointer
      Node<T> prev = this.head;
      for (int i = 0; i < index - 1; i++) {
        prev = prev.getNext();
      }
      prev.setNext(null);          // left part now ends here
      this.size = index;           // left list has 'index' elements (0..index-1)
    }

    return newList;
  }

  // ********************************** insertSorted() - Question 2 **********************************
  // Inserts 'item' into the list in ascending sorted order.
  // For the list to stay sorted, only this method should be used for insertion.
  public void insertSorted(T item) {

    Node<T> newNode = new Node<>(item); // new node to insert

    // Case 1: empty list — just set the head
    if (this.isEmpty()) {
      this.head = newNode;
      this.size++;
      return;
    }

    // Case 2: new item belongs before the current head
    if (item.compareTo(this.head.getElement()) <= 0) {
      newNode.setNext(this.head);
      this.head = newNode;
      this.size++;
      return;
    }

    // Case 3: walk the list to find the correct insertion point
    Node<T> temp = this.head;
    while (temp.getNext() != null &&
           item.compareTo(temp.getNext().getElement()) > 0) {
      temp = temp.getNext();
    }
    // Insert newNode between temp and temp.getNext()
    newNode.setNext(temp.getNext());
    temp.setNext(newNode);
    this.size++;
  }

  // ********************************** insertionSort() - Question 3 **********************************
  // Recursively sorts the current list using a split-and-merge strategy:
  //   1. Split L in the middle: left half stays in a temp list L1,
  //      right half goes into L2.
  //   2. Recursively sort L1 and L2.
  //   3. Merge L2 into L1 using insertSorted().
  //   4. Replace the current list's contents with the sorted result.
  public void insertionSort() {

    // Base case: a list of 0 or 1 element is already sorted
    if (this.size <= 1) {
      return;
    }

    // Check for empty list before attempting to sort
    if (this.isEmpty()) {
      System.out.println("The list is empty — cannot sort!");
      return;
    }

    // --- Step 1: split at the middle index ---
    // Per the spec: index = N/2  (integer division; "+1 for odd N" is implicit)
    int midIndex = this.size / 2;

    // splitAt() modifies 'this' to hold [0 .. midIndex-1]
    // and returns a new list holding [midIndex .. N-1]
    List<T> L2 = this.splitAt(midIndex);  // right half
    List<T> L1 = this;                    // left half  (this list, now truncated)

    // --- Step 2: recursively sort each half ---
    L1.insertionSort();
    L2.insertionSort();

    // --- Step 3: merge L2 into L1 using insertSorted() ---
    Node<T> walker = L2.head;
    while (walker != null) {
      L1.insertSorted(walker.getElement()); // insert each element in sorted order
      walker = walker.getNext();
    }

    // --- Step 4: 'this' IS L1, so it already holds the sorted result ---
    // (no extra copy needed — L1 == this after the assignment above)
  }

}

// The class for the Main Program
public class assignment5 {

  public static void main(String[] args) { // The main() method

    List<Integer> list = new List<>();
    Integer ch, item, index;
    boolean quit = false;

    Scanner sc = new Scanner(System.in);

    do{
  
      System.out.println( "____________Main Menu_____________________");
      System.out.println( "select option :");
      System.out.println( "1 : insert back");
      System.out.println( "2 : insert front");
      System.out.println( "3 : insert at index");
      System.out.println( "4 : display items");
      System.out.println( "5 : get item at index");
      System.out.println( "6 : delete back");
      System.out.println( "7 : delete front");
      System.out.println( "8 : delete at index");
      System.out.println( "9 : exit");
      System.out.println( "11: insert sorted  (Q2)");
      System.out.println( "88: split at index (Q1)");
      System.out.println( "99: insertion sort (Q3)");
      ch = sc.nextInt();

      switch (ch) {
        case 1:
          System.out.println( "enter item to insert:");
          item = sc.nextInt(); // read in an integer
          list.addLast(item);
          break;
        case 2:
          System.out.println( "enter item to insert:");
          item = sc.nextInt(); // read in an integer
          list.addFront(item);
          break;
        case 3:
          System.out.println( "enter item to insert:");
          item = sc.nextInt(); // read in an integer
          System.out.println( "enter index:");
          index = sc.nextInt(); // read in an integer
          list.addAt(index, item);
          break;
        case 4:
          list.displayAll();
          list.displayRecursively();
          break;
        case 5:
          System.out.println( "enter index:");
          index = sc.nextInt();
          System.out.println( "item at index:"+index+" is: "+list.getAt(index));
          break;
        case 6:
          list.removeLast();
          break;
        case 7:
          list.removeFront();
          break;
        case 8:
          System.out.println( "enter index:");
          index = sc.nextInt();
          list.removeAt(index);
          break;
        case 9:
          quit = true;
          break;

        // ---- Question 1: splitAt() ----
        case 88:
          System.out.println("enter index to split at:");
          index = sc.nextInt();

          // Print the original list before splitting
          System.out.println("Original list before split:");
          list.displayAll();

          // Perform the split; list becomes the left part, newList the right part
          List<Integer> newList = list.splitAt(index);

          // Print the modified left part (this is the updated 'list')
          System.out.println("Left part of list after split (L):");
          list.displayAll();

          // Print the newly created right part
          System.out.println("Right part of split (NL):");
          newList.displayAll();
          break;

        // ---- Question 2: insertSorted() ----
        case 11:
          System.out.println("enter item to insert in sorted order:");
          item = sc.nextInt();
          list.insertSorted(item);
          System.out.println("Item "+item+" inserted in sorted position.");
          break;

        // ---- Question 3: insertionSort() ----
        case 99:
          if (list.isEmpty()) {
            System.out.println("The list is empty");
          } else {
            list.insertionSort();
            System.out.println("List sorted. 4 to display.");
          }
          break;

        default:
          System.out.println( "invalid selection");
      }
    } while(!quit);
  }
}