package A3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Raj (40085012)
 *
 */
public class FileInput {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String path = "src/Input Files/CSTA_test_file3.txt";
		IntelligentSIDC it = new IntelligentSIDC();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			
			String str = br.readLine(); 
			while(str != null) {
				int id = Integer.parseInt(str);
				it.add(id, "comp 6481");
				str = br.readLine();
			}
			br.close();
		}
		catch(IOException e) {
			System.out.println("File not found");
		}
		it.allKeys(true);
	}

}
