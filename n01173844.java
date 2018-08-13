/************************************************************
Assignment 6 - "HashTable"
Randy Jackson - N01173844
201710.COP3530.10014
4/6/17
************************************************************/
import java.io.*;
import java.util.*;
import java.lang.*;

public class n01173844 
{

   public static void main(String args[]) throws IOException
   {
      //obtaining files 1, 2, and 3
      File main = new File(args[0]);
      File search = new File(args[1]);
      File delete = new File(args[2]);
      
      //____Counting number of lines (n) / getting first prime after 2n
      int n = getNumberOfStrings(main);
      int arraySize = getArraySize(n);
      
      //fetchin Strings from args 1-3
      String[] wordArray = getWordArray(main);
      String[] findArray = getWordArray(search);
      String[] deleteArray = getWordArray(delete);
      
      HashTable A = createLPHashTable(arraySize, n, wordArray);   //Hashtable using LP
      HashTable B = createQPHashTable(arraySize, n, wordArray);   //Hashtable using QP
      
      //Printing first table from HashTable class
      System.out.println("LP Insertion Table:");
      A.displayFirstTable();
      System.out.println("QP Insertion Table:");
      B.displayFirstTable();
      
      //Printing Find Table from method
      displayFindTable(A, findArray, true);
      displayFindTable(B, findArray, false);
      
      //printing Delete Table from method
      displayDeleteTable(A, deleteArray, true);
      displayDeleteTable(B, deleteArray, false);
      
   }
   
   //-------------------displayDeleteTable prints the delete table for LP and QP
   
   public static void displayDeleteTable(HashTable hashTable, String[] findArray, boolean type)
   {
      //results array used to store info for printing
      results[] resultObj = new results[findArray.length];
      
   
      //_________filling the results array with third text file strings
      //_______if-else used to distinguish between LP and QP
      if(type)
      {
         for(int i = 0; i < findArray.length; i++)
            resultObj[i] = hashTable.delete(findArray[i]);
            
         System.out.println("LP Deletion Table:");  
      }
      else
      {
         for(int i = 0; i < findArray.length; i++)
            resultObj[i] = hashTable.findQP(findArray[i]);
         
         System.out.println("QP Deletion Table:");   
      }
   
      double sumSuccess = 0; // summation of success probe lengths
      double sumFailure = 0; // summation of failure probe lengths
   
      int successCount = 0;  //iterators used for success/failure totals
      int failureCount = 0;
      
      
         System.out.println("String\t\t\tSuccess\t\t\tFailure\t\t\tProbe Length For Success\t\t\tProbe Length For Failure");
         
         for(int j=0; j< findArray.length; j++)
         {
            if(resultObj[j].getStatus())
            {
               sumSuccess += resultObj[j].getProbeLength();
               successCount++;
               System.out.println(findArray[j] + "\t\t\t\t" + "yes" + "\t\t\t\t" + "  " + "\t\t\t\t\t\t\t" + resultObj[j].getProbeLength());
            }
            else
            {
               sumFailure += resultObj[j].getProbeLength();
               failureCount++;
               System.out.println(findArray[j] + "\t\t\t\t" + "   " + "\t\t\t\t" + "yes" + "\t\t\t\t" + "    " + "\t\t\t\t\t\t\t\t\t\t\t\t" + resultObj[j].getProbeLength());
            }
         }
       System.out.printf("Average Probe Length For Success: %.2f \n",(sumSuccess/(int)successCount) );
       System.out.printf("Average Probe Length For Failure: %.2f \n",(sumFailure/(int)failureCount) );
       System.out.print("\n");
    }

   
   //---------------------------displayFindTable prints the table for the find feature
   
   public static void displayFindTable(HashTable hashTable, String[] findArray, boolean type)
   {
      //DataItem array used to store info for printing
      results[] resultObj = new results[findArray.length];
      
   
      //_________filling the DataItem array with second text file strings
      //_______if-else used to distinguish between LP and QP
      if(type)
      {
         for(int i = 0; i < findArray.length; i++)
            resultObj[i] = hashTable.find(findArray[i]);
            
         System.out.println("LP Find Table:");  
      }
      else
      {
         for(int i = 0; i < findArray.length; i++)
            resultObj[i] = hashTable.findQP(findArray[i]);
            
         System.out.println("QP Find Table:");     
      }
   
      double sumSuccess = 0; // summation of success probe lengths
      double sumFailure = 0; // summation of failure probe lengths
   
      int successCount = 0;   //iterators used for success/failure totals
      int failureCount = 0;
   
         System.out.println("String\t\t\tSuccess\t\t\tFailure\t\t\tProbe Length For Success\t\t\tProbe Length For Failure");
         
         for(int j=0; j< findArray.length; j++)
         {
            if(resultObj[j].getStatus())
            {
               sumSuccess += resultObj[j].getProbeLength();
               successCount++;
               System.out.println(findArray[j] + "\t\t\t\t" + "yes" + "\t\t\t\t" + "  " + "\t\t\t\t\t\t\t" + resultObj[j].getProbeLength());
            }
            else
            {
               sumFailure += resultObj[j].getProbeLength();
               failureCount++;
               System.out.println(findArray[j] + "\t\t\t\t" + "   " + "\t\t\t\t" + "yes" + "\t\t\t\t" + "    " + "\t\t\t\t\t\t\t\t\t\t\t\t" + resultObj[j].getProbeLength());
            }
         }
       System.out.printf("Average Probe Length For Success: %.2f \n",(sumSuccess/(int)successCount) );
       System.out.printf("Average Probe Length For Failure: %.2f \n",(sumFailure/(int)failureCount) );
       System.out.print("\n");
    }
   
   //------------------------------------------createLPHashTable populates the LP hash table
   public static HashTable createLPHashTable(int arraySize, int n, String[] wordArray)
   {
      DataItem aDataItem; // creating a data item to store string
      HashTable temp = new HashTable(arraySize); //creating the hashTable to be returned

      for(int i=0; i < n; i++) // insert data for each line
      {
   
         aDataItem = new DataItem(wordArray[i]);
      
         temp.insert(aDataItem);
         
      }
      
      return temp;
      
   }
   
   //------------------------------------------createQPHashTable populates the QP hash table
   public static HashTable createQPHashTable(int arraySize, int n, String[] wordArray)
   {
      DataItem aDataItem; // creating a data item to store string
      HashTable temp = new HashTable(arraySize); //creating the hashTable to be returned

      for(int i=0; i < n; i++) // insert data for each line
      {
   
         aDataItem = new DataItem(wordArray[i]);
      
         temp.insertQP(aDataItem);
         
      }
      
      return temp;
      
   }
   
   //-------------------------------------------getWordArray returns the array of strings from args files
   public static String[] getWordArray(File main)throws IOException
   {
      FileReader fileReader = new FileReader(main);
      
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      
      List<String> lines = new ArrayList<String>();
      
      String line = null;
      
      while ((line = bufferedReader.readLine()) != null) 
      {
         lines.add(line);
      }
      
      bufferedReader.close();
      
      return lines.toArray(new String[lines.size()]);
   }
   
   //-----------------------------------------getNumberOfStrings returns the number of strings in args[0]
   public static int getNumberOfStrings(File main)throws IOException
   {
      FileReader fr = new FileReader(main);
 
      LineNumberReader lnr = new LineNumberReader(fr);

      int lineNumber = 0;

    	while (lnr.readLine() != null){ lineNumber++; }

    	lnr.close();
      
      return lineNumber;
   }
   
   //----------------------------------------getArraySize counts number of strings
   //----------------------------------------then returns first prime number after 2*n
   public static int getArraySize(int numberOfStrings)
   {  
      //____Finding first prime number after 2n
      
      int arraySize = getPrime(2 * numberOfStrings);
      
      return arraySize;

   }
   
   //---------------------------------------------------------------------
   //-------------------Methods used to obtain first prime number after 2n
   //---------------------------------------------------------------------
   public static int getPrime(int min) // returns 1st prime > min
   {
      for(int j = min+1; true; j++) // for all j > min
         if( isPrime(j) ) // is j prime?
            return j; // yes, return it
   }
   // -------------------------------------------------------------
   public static boolean isPrime(int n) // is n prime?
   {
      for(int j=2; (j*j <= n); j++) // for all j

         if( n % j == 0) // divides evenly by j?
            return false; // yes, so not prime

               return true; // no, so prime
   }
   
   //-----------------------------------results class used to store info for tables 2 and 3
   public static class results
   {
      //Keeps track of string being found
      //and the probeLength
      //Success and Failure are added seperately in above method
      //using found as an if-else statement
      
      private boolean found = true;
   
      private int probeLength;
      
      public void setStatus(boolean result)
      {
         found = result;
      } 
      
      public void setProbeLength(int length)
      {
         probeLength = length;
      }
      
      public int getProbeLength()
      {
         return probeLength;
      }
      
      public boolean getStatus()
      {
         return found;
      }
   
   }
   
   //----------------------------------------------------DataItem Class
   
   public static class DataItem
   {                  
      private String iData; // data item (key)
      private int insertionLength; //probeLength used for insertion
      
      //--------------------------------------------------------------

      public DataItem(String ii) // constructor
      { iData = ii; }
      
      //--------------------------------------------------------------
      
      public String getKey()     // used to get String from data item
      { return iData; }
      
      
      public void setInsertion(int i) //set and get Insertion Probe Length
      { insertionLength = i; }
      
      public int getInsertion()
      { return insertionLength; }
      
      //--------------------------------------------------------------
      
      }
      
      ////////////////////////////////////////////////////////////////
      
      //----------------------------------------------Start Hashtable Class
      
      public static class HashTable
      {
      
      private DataItem[] hashArray; // array holds hash table

      private int arraySize;

      private DataItem nonItem; // for deleted items

      // -------------------------------------------------------------
      
      public HashTable(int size) // constructor
      {
         arraySize = size;
         
         hashArray = new DataItem[arraySize];
         
         nonItem = new DataItem("-1"); // deleted item key is -1
      }
      
      // -------------------------------------------------------------
      
      public void displayFirstTable()
      {
      double sum = 0; // summation of probe lengths
      int total = 0; //iterator for valid cells
      
      System.out.println("Index\t\t\tString\t\t\tProbe Length For Insertion");
         
         for(int j=0; j< arraySize; j++)
         {
            if(hashArray[j] != null)
            {
            sum+= hashArray[j].getInsertion();
            total++;
            System.out.println(j + "\t\t\t\t" + hashArray[j].getKey() + "\t\t\t\t" + hashArray[j].getInsertion());
            }
         }
       System.out.printf("Average Probe Length: %.2f \n",(sum/(int)total) );  
       System.out.print("\n");   
       
      }
      
      // -------------------------------------------------------------
      public int hashFunc(String key)
      {
         if(key.length() == 1)
            return (((key.charAt(0) + '0') * 26) % arraySize);
            
         else
         {
            int hornersTotal = key.charAt(0) + '0'; //switching from char to int
                                                    //total begins with value of first letter
         
            int charNumNext = 0; // used for obtaining int of next letter
         
            for (int i = 0; i < (key.length() - 1); i++) 
            {
               charNumNext += key.charAt(i+1) + '0'; //getting int of next character
         
               hornersTotal = ( (hornersTotal * 26) + charNumNext ) % arraySize; //summation of horners polynomial
            }
         
            return hornersTotal;
         }
      }
            
      // -------------------------------------------------INSERT for LP
      public void insert(DataItem item) // insert a DataItem
      // (assumes table not full)
      {
      
      String key = item.getKey(); // extract key
      
      int hashVal = hashFunc(key); // hash the key
      
      int insertion = 0;
      
      // until empty cell or -1,
      
      while(hashArray[hashVal] != null &&
            !(hashArray[hashVal].getKey().equals("-1")) )
      {
         
         ++insertion; //increase insertion probe by 1
         ++hashVal; // go to next cell
      
         hashVal %= arraySize; // wraparound if necessary
      
      }
      
      hashArray[hashVal] = item; // insert item
      
      hashArray[hashVal].setInsertion(insertion); // set probe length for key
      
      } // end insert()
      
      
      // -------------------------------------------------INSERT for QP
      public void insertQP(DataItem item) // insert a DataItem
      // (assumes table not full)
      {
      
      String key = item.getKey(); // extract key
      
      int hashVal = hashFunc(key); // hash the key
      
      int insertion = 0;
      
      int powerIterator = 1;
      
      // until empty cell or -1,
      
      while(hashArray[hashVal] != null &&
            !(hashArray[hashVal].getKey().equals("-1")) )
      {
         
         insertion++; //increase insertion probe by 1
         
         hashVal = hashVal + ((int)(Math.pow(powerIterator, 2)) ); // go to next cell
         
         powerIterator++;
      
         hashVal %= arraySize; // wraparound if necessary
      
      }
      
      hashArray[hashVal] = item; // insert item
      
      hashArray[hashVal].setInsertion(insertion); // set probe length for key
      
      } 
      
      
      //------------------------------------------------DELETE for LP      
      public results delete(String key) // delete a DataItem
      {
      results count = new results(); //outside results class to track found and probe length
      
      int hashVal = hashFunc(key); // hash the key
      
      int probeLength = 0; //iterates the probe length
      
      while(hashArray[hashVal] != null) // until empty cell,
      { 
         if(hashArray[hashVal].getKey().equals(key))
         {
            count.setProbeLength(probeLength); 
            count.setStatus(true);          //key is found
            
            DataItem temp = hashArray[hashVal]; // save item
            
            hashArray[hashVal] = nonItem; // delete item
            
            return count; // return results
         }
         
         ++hashVal; // go to next cell
         
         probeLength++;    //increase probe length
         
         count.setProbeLength(probeLength);
         count.setStatus(false);
         
         hashVal %= arraySize; // wraparound if necessary
      }
      count.setProbeLength(probeLength); 
      count.setStatus(false);
            
      return count; // can’t find item
      } // end delete()
      
      //------------------------------------------------DELETE for QP
      public results deleteQP(String key) // delete a DataItem
      {
      results count = new results();
      
      int hashVal = hashFunc(key); // hash the key
      
      int probeLength = 0;
      
      int powerIterator = 1;
      
      while(hashArray[hashVal] != null) // until empty cell,
      { // found the key?
         if(hashArray[hashVal].getKey().equals(key))
         {
            count.setProbeLength(probeLength); 
            count.setStatus(true);          //key is found
            
            DataItem temp = hashArray[hashVal]; // save item
            
            hashArray[hashVal] = nonItem; // delete item
            
            return count; // return results
         }
         
         hashVal = hashVal + ((int)(Math.pow(powerIterator, 2)) ); // go to next cell
         
         probeLength++;    //increase probe length
         
         count.setProbeLength(probeLength); 
         count.setStatus(false);
         
         hashVal %= arraySize; // wraparound if necessary
      }
      count.setProbeLength(probeLength); 
      count.setStatus(false);
            
      return count; // can’t find item
      } 

      
      //------------------------------------------------FIND for LP 
      
      public results find(String key) // find item with key
      {
      results count = new results();
      
      int hashVal = hashFunc(key); // hash the key
      int probeLength = 0;
      
      while(hashArray[hashVal] != null) // until empty cell,
      { 
         if(hashArray[hashVal].getKey().equals(key))
         {
            count.setProbeLength(probeLength); 
            count.setStatus(true);          //key is found
            return count;
         }
         
         ++hashVal; // go to next cell
         
         probeLength++;    //increase probe length
         
         count.setProbeLength(probeLength); 
         count.setStatus(false); 
         
         hashVal %= arraySize; // wraparound if necessary
      }
      count.setProbeLength(probeLength); 
      count.setStatus(false);
      
      return count;
      
      }
      
      //------------------------------------------------DELETE for QP
      public results findQP(String key) // find item with key
      {
      results count = new results();
      
      int hashVal = hashFunc(key); // hash the key
      int probeLength = 0;
      int powerIterator = 1; 
      
      while(hashArray[hashVal] != null) // until empty cell,
      { // found the key?
         if(hashArray[hashVal].getKey().equals(key))
         {
            count.setProbeLength(probeLength); 
            count.setStatus(true);          //key is found
            return count;
         }
         
         hashVal = hashVal + ((int)(Math.pow(powerIterator, 2)) ); // go to next cell
         
         probeLength++;    //increase probe length
         
         count.setProbeLength(probeLength); 
         count.setStatus(false); 
         
         hashVal %= arraySize; // wraparound if necessary
      }
      count.setProbeLength(probeLength); 
      count.setStatus(false);
      
      return count;
      
      }

      
      } 
      

}
