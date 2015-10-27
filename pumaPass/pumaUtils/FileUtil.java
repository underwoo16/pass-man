/*
 * A collection of methods that deal with file IO.
 * @author Cody Machine
*/
package pumaPass.pumaUtils;

import java.io.File;
import java.io.*;
//import java.nio.file.*;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class FileUtil
{
   
   /**
     * Reads in all the records from the data file.
     * @param filename name of the input file
     * @return returns a string array containing the records
    */
   public static String[] readRecords(final String filename) throws Exception
   {
      File file = new File("Saves/" + filename + ".dat");
      Scanner fin = new Scanner(file);
      
      int recordCount = fin.nextInt();
      fin.nextLine();
      String[] data = new String[recordCount * 3];
      
      for (int x = 0; x < (recordCount * 3); x++)
      {
         data[x] = fin.nextLine();
      }
      
      fin.close();
      return data;
   }
   
   /**
     * Writes all the records to a data file.
     * @param filename name of the output file
    */
   public static void writeRecords(final String filename, final String[] data) throws Exception
   {
      File file = new File("Saves/" + filename + ".dat");
      PrintStream fin = new PrintStream(file);
      
      int recordCount = (data.length / 3);
      fin.println(recordCount);
      
      for (int x = 0; x < (recordCount * 3); x++)
      {
         fin.println(data[x]);
      }
      
      fin.close();
   }
   
   /**
     * Reads in all the filenames of save files in the /Saves folder.
     * @return a string array containing the filenames
    */
   public static String[] readSaveFilenames()
   {
	   String[] saves;
       ArrayList<String> saveList = new ArrayList<String>();
      
       File dir = new File("Saves/");
       if (dir.exists())
       {
    	  	File[] list = dir.listFiles();
      		if (list.length == 0)
      			return null;
      
      		for (int x = 0; x < list.length; x++)
      		{
    	  		if (list[x].isFile() && list[x].getName().endsWith(".dat"))
         		{
        	 		saveList.add(list[x].getName());
         		}         
      		}
      		
      		saves = new String[saveList.size()];
      		saves = saveList.toArray(saves);
       }
       else
       {
    	    dir.mkdir();
    	  	saves = null;
       }
       return saves;
   }
   
   /**
     * Delete a save file. Can also delete empty directories.
     * @param filename name of the file to be deleted
     * @return returns true if the deletion was successful
    */
   public static boolean deleteFile(final String filename)
   {
      File file = new File("Saves/" + filename + ".dat");
      return file.delete();
   }
   
   /**
    * Check if a profile name is valid. For the time being, only alphanumeric ASCII chars allowed.
    * @param filename name to be chacked
    * @return returns true if name is valid
   */
   public static boolean checkValidFN(final String newProf)
   {
	   Pattern pat = Pattern.compile("[^a-zA-Z0-9]");
	   boolean temp = !(pat.matcher(newProf).find());
	   
	   for (int x = 0; x < newProf.length(); x++)
	   {
		   if (newProf.charAt(x) == ' ')
			   temp = false;
	   }
	   
	   return temp;
   }
   
} // end class FileUtil