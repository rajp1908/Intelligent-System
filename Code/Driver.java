package A3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This is the driver class
 * @author Raj (40085012)
 *
 */
public class Driver {
	
	private static Scanner sc;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		IntelligentSIDC it = new IntelligentSIDC();
		
		System.out.println("Welcome to IntelligentSIDC\n");
		sc = new Scanner(System.in);
		
		int choice;
		
		do {
			
			int id;
			int ans;
			String value;
			int number = 0;
			System.out.println("1. Add record to the system");
			System.out.println("2. Print all keys in Sorted Sequence");
			System.out.println("3. Remove a key");
			System.out.println("4. Get value");
			System.out.println("5. Get next Key");
			System.out.println("6. Get previous Key");
			System.out.println("7. Get keys in a range");
			System.out.println("-1. Exit\n");
			System.out.println("Enter your choice: ");
			choice = sc.nextInt();
			
			long startTime, endTime;
			
			switch(choice) {
						
				case 1:	
						File folder = new File("./");
						File[] listOfFiles = folder.listFiles();
						int fcount = 0;
						for (int i = 0; i < listOfFiles.length; i++) {
						  File file = listOfFiles[i];
						  
						  if (file.isFile() && file.getName().endsWith(".txt")) {
							  fcount++;
						  } 
						}
						System.out.print("Enter the value for a threshold: ");
						int size = sc.nextInt(); 
						startTime = System.currentTimeMillis();
						it.SetSIDCThreshold(size);
						try {
							PrintWriter pw = new PrintWriter
									(new FileOutputStream("Output"+fcount+".txt"));
							for(int i = 0;i< size;i++) {
								number = it.generate();
								System.out.println("generated ID is : "+ number);
	//							System.out.print("Enter the Name of the student: ");
	//							value = sc.next();
								value = "Hello" + number;
								pw.println(number + " " + value);
								it.add(number, value);
								System.out.println("Student record: ID-"+ number +", Name- "+value +"\nRecord inserted");
							}
							endTime = System.currentTimeMillis();
							pw.close();
							System.out.println("Time to add: " + (endTime - startTime) + " milliseconds");
						} catch (FileNotFoundException e) {
							
							System.out.println("Error occured while writing to a file.");
						}
						break;
						
				case 2:	startTime = System.currentTimeMillis();
						it.allKeys(false);
						endTime = System.currentTimeMillis();
						System.out.println("Time taken by allKeys: " + (endTime - startTime) + " milliseconds");
						break;
						
				case 3:	System.out.print("Enter the Student ID to remove: ");
						id = sc.nextInt();
						startTime = System.currentTimeMillis();
						ans = it.remove(id);
						if(ans == 0)
							System.out.println("No students are found with given ID");
						else
							System.out.println("Student ID- " + ans + " is removed from the system.");
						endTime = System.currentTimeMillis();
						System.out.println("Time to remove: " + (endTime - startTime) + " milliseconds");
						break;
				case 4:	System.out.print("Enter the key :");
						id = sc.nextInt();
						startTime = System.currentTimeMillis();
						value = it.getValues(id);
						if(value.equals("0"))
							System.out.println("No students are found with given ID");
						else
							System.out.println("Student ID- " + id + ", Name- " + value);
						endTime = System.currentTimeMillis();
						System.out.println("Time to find a value: " + (endTime - startTime) + " milliseconds");
						break;
				case 5:	System.out.print("Enter the key: ");
						id = sc.nextInt(); 
						startTime = System.currentTimeMillis();
						ans = it.nextKey(id);
						if(ans == 0)
							System.out.println("No students are found with given ID");
						else
							System.out.println("The next student is with ID- " + ans);
						endTime = System.currentTimeMillis();
						System.out.println("Time to nextKey: " + (endTime - startTime) + " milliseconds");
						break;
				case 6:	System.out.print("Enter the key: ");
						id = sc.nextInt(); 
						ans = it.prevKey(id);
						startTime = System.currentTimeMillis();
						if(ans == 0)
							System.out.println("No students are found with given ID");
						else
							System.out.println("The previous student is with ID- " + ans);
						endTime = System.currentTimeMillis();
						System.out.println("Time to prevKey: " + (endTime - startTime) + " milliseconds");
						break;
				case 7:	System.out.print("Enter the minimum value: ");
						int k1 = sc.nextInt();
						System.out.print("Enter the maximum value: ");
						int k2 = sc.nextInt();
						startTime = System.currentTimeMillis();
						Queue<Integer> q = it.rangeKey(k1, k2);
						System.out.println("The total keys in the given range are : " + q.size());
						if(q.size() > 0) {
							System.out.println("They are: ");
							while(q.size() > 0) {
								System.out.print(q.remove() + " ");
							}
							System.out.println();
						}
						endTime = System.currentTimeMillis();
						System.out.println("Time for RangeKeys: " + (endTime - startTime) + " milliseconds");
						break;
				
			}
			
			System.out.print("\n\n");
			
		} while(choice != -1);
		
		
	}

}
