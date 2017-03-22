import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.lang.Comparable;

/**
* This program reads the file input in the commandline argument
* It will display the array prior to initializing elements then
* read the file and display the contents of the file in the stated
* elements dictated in the file tnen print the Houses in alphabetical
* order. This program is written for compass.csv which is themed for 
* the Hawaiian Star Compass used for navigation.
* 
* 
* @author Kai, Bradley
* 10/7/2016
*/ 
public class CompareToExmple {

   /**
   * This is the main method that starts up the program.
   * 
   * @param args holds the input made in the commandline argument
   */ 
   public static void main(String[] args) {
   
      //initializing variables
      //variables to read file/lines and hold lines
      String line = "";
      String headerline = "";
      Scanner inputFromFile = null;
      
      //variables to store line inputs
      String house1 = "";
      String degrees1 = "";
      String star1 = "";
      Integer degrees2 = 0;
      
      //variables for set/get; designated c for change
      String cHouse = "";
      Integer cDegrees = 0;
      String cStar = "";

      
      //error checking for commandline input
      if (args.length != 1) {
         System.out.println("Please enter ONE file name in the commandline argument.");
         //terminate program
         System.exit(1);
      }//end args.length if
    
      //making an array of Nature object
      //made size 18 to replicate the example output for assignment #8
      final Integer size = 10;
      HawaiianStarCompass array[] = new HawaiianStarCompass [size];
      Integer[] degrees = new Integer[size];
      String[] houses = new String[size];
      String[] stars = new String[size]; 
    
      //output array of Nature objects to the screen (should be "null" for all elements)
      //Will print even if there are no files to be read
      System.out.println("Display HawaiianStarCompass array[] without initializing elements:");
      System.out.println("index  element");
         for(int i = 0; i < size; i++){
            System.out.println("  " + i + "     " + array[i]);
         }//end w/o initializing elements for loop 
      System.out.println();
      
      //read from file and store data from file in your Nature array of objects

      //connecting to the file
      try{
         File file = new File(args[0]);
         inputFromFile = new Scanner(file);

            //if file is found, read the file   
            if (inputFromFile != null) {
      
            //storing first line of the file into string
            headerline = inputFromFile.nextLine();
           
               //to be used for incrementing arrays
               int i = 0;
            
               //continue loop to read the remaining lines
               while (inputFromFile.hasNextLine()) {
            
                  //take and hold the next line
                  line = inputFromFile.nextLine();

                  //separate the line with commas
                  Scanner lineInput = new Scanner(line).useDelimiter(",");
                  
                  //assign strings to each input now separated by commas               

                     house1 = lineInput.next();
                     degrees1 = lineInput.next();
                     star1 = lineInput.next();

                  try {
                     //pass the string to an integer
                     degrees2 = Integer.parseInt(degrees1);
                  }//end s -> i try
                  
                  catch (NumberFormatException exception) {
                     System.out.println(degrees1 + " is not a number.");
                  }//end NumberFormatException catch
                  
                  //just incase someone modified the file and put negative integers (why???!!!!)
                  if (degrees2 < 0) {
                     degrees2 = -degrees2;
                  }//end negative integer if
                     
                  //assign to string/integer to arrays
                  try {
                  houses[i] = house1;
                  degrees[i] = degrees2;
                  stars[i] = star1;
                  }//end assign arrays try
                  
                  catch (InputMismatchException e) {
                     inputFromFile.next( );
                  }//end InputMismatchException catch
                  
                  //increment each array by one
                  ++i;
                  
               }//end inputFromFile while
            
            }//end inputfile null if
            
         }//end connecting to file try 
         catch (FileNotFoundException exception) {
            //error message
            System.out.print("Did not find file named \"");
            System.out.println(args[0] + "\"");       
         }//end FileNotFoundException catch
         
         catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Array is over the size.");
         }//end ArrayIndexOutOfBoundsException catch
         
    //by initializing each array element using the constructor 
    for (int i = 0; i < size; i++) {
      array[i] = new HawaiianStarCompass(houses[i], degrees[i], stars[i]);
    }//end for
    
    //use toString() to display the array again with data from input file
    //Stop printing the array if file was not found
    if (inputFromFile != null) {
      System.out.println("Read from input file: " + args[0]);
      System.out.println("Display HawaiianStarCompass array[] after initializing elements:");
      System.out.println("Index    House Name      Degrees      Star Name");
      
      //personalized for the array file 
      for(int i = 0; i < 10; i++){
         System.out.printf("%3d      %-10s    \n", i, array[i].toString());
      }//end for
      
    }//end inputFromFile if
    
    //print out a space after looping
    System.out.println();  
    
    //use one of the methods from the Sorting.java program to sort the array
    //prevent auto sorting
    Sorting.display = false;    
    Sorting.bubbleSort(array);
    
    //use toString() to display the array again with updated data 
    System.out.println("Display HawaiianStarCompass array[] after updating elements:");
    System.out.println("Index    House Name      Degrees      Star Name");
    if (inputFromFile != null) {
      for(int i = 0; i < 10; i++){
         System.out.println("   " + i + "     " + array[i].toString());
      }//end get/set toString()
      
    }//end if      
       
   }//end of main() method
   
   /**
   *Sorts the array via bubblesort
   *
   * @param array is an array of Comparable objects
   */
   //public static void Sorting(HawaiianStarCompass[] array) {
      //Sorting.bubbleSort(array, 0, array.length-1);
   //}//end bubble sort
   
   /*
   * Sorts the array via bubblesort
   *
   * @param array is an array of Comparable objects
   * @param start is the first element in the array  	
   * @param end is the last element in the array
   */
      // public static void Sorting(HawaiianStarCompass[] array, int start, int end) {
      //flag to see if an item was swapped or not
        // boolean swap = false;
        // loop size - 1 times   
        // for (int i = start + 1; i <= end; i++) {
           // swap = false;
         	//loop from beginning of array to (last element - i)
            //for (int x = 0; x <= array.length - x; x++) {
            // swap if the first evaluated item is greater than other item
              // if (array[x].compareTo(array[x + 1]) > 0) {
               // swap
                  //HawaiianStarCompass tmp = array[x];
                  //array[x] = array[x + 1];
                  //array[x + 1] = tmp;
                  //swap = true;
               //}//end if sorting comparison
               
           // }//end for loop
            
        //}//end or loop
         
      //}//end bubblesort method
      
}// end of class CompareToExample

/**
 * Class HawaiianStarCompass stores and displays the data for each HawaiianTheme object
 * 
 * @author Kai, Bradley
 */
class HawaiianStarCompass implements java.lang.Comparable {

   //Data Field #1: Stores House names
   private String hHouse; 
   
   //Data Field #2: Stores Compass Degrees
   private Integer hDegrees;
   
   //Data Field #3: Stores Stars associated near each house
   private String hStar;
   
   /**
    * Stores the house name, approximate degrees and star name.
    * 
    * @param house1 holds the house name
    * @param degrees2 holds the degrees of the house
    * @param star1 holds the name of the star near the house   
    */
   public HawaiianStarCompass(String house1, Integer degrees2, String star1) {
      hHouse = house1;
      hDegrees = degrees2;
      hStar = star1;
   }//end constructor

   /**
    * Returns the string that displays the House name, Degrees and Star name.
    * 
    * @return returns string in the format: House Degrees Star
    */
   public String toString() {
      String holder = hHouse + "          " + hDegrees + "      " + hStar;
      return holder;
   }//end toString()
   
  /**
    * Compares the names of the house objects. 
    * In case the names are the same, it will use the degrees
    * 
    * @param object2 is the other object to be compared (object #2).
    * @return If the compared objects are equal, a zero is returned.
    *         If object #1 is alphbetically before object #2 then 
    *         a negative integer is returned.
    *         If object #1 is alphbetically before object #2 then 
    *         a positive integer is returned.    
    * @exception ClassCastException if object #2 is not a house name 
    *            then exception is thrown 
    */
   //compareTo() method - used to compare two objects
   public int compareTo(Object object2) throws ClassCastException {
         String name1 = hHouse;
         HawaiianStarCompass HawaiianStarCompass2 = (HawaiianStarCompass)object2;
         String name2 = HawaiianStarCompass2.getHouse();
         int result = name1.compareTo(name2);
            if (result == 0) {
               result = hDegrees.compareTo(HawaiianStarCompass2.getDegrees());
            }//end if
         return result;
   }//end compareTo
      
  /**
    * House name Accessor
    * 
    * @return returns the house name
    */
   //Accessor Method #1: gets the data field for House
   public String getHouse() {
      return hHouse;
   }//end get for House
   
   /**
    * Degrees name Accessor
    * 
    * @return returns the degrees
    */
   //Accessor Method #2: gets the data field for Degrees
   public Integer getDegrees() {
      return hDegrees;
   }//end get for Degrees
   
   /**
    * Star name Accessor
    * 
    * @return returns the star name
    */
   //Accessor Method #3: gets the data field for Star
   public String getStar() {
      return hStar;
   }//end method
   
}//end of class HawaiianStarCompass
