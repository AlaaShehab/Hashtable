package hashing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author Personal
 *
 */
public class ReadFile implements IReadFile {

	/* (non-Javadoc)
	 * @see fileOperations.IReadFile#readfile(java.lang.String)
	 */
	public Integer[] readfile(String fileName) {
		ArrayList<Integer> keys = new ArrayList<Integer>();
		FileReader file;
		String key = null;
		try {
			file = new FileReader(fileName);
			BufferedReader readFile = new BufferedReader(file);
			key = readFile.readLine();
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String[] stringSet = key.split(",");
		
		
		HashSet<Integer> set = new HashSet<Integer>();
		
		for (int i = 0; i < stringSet.length; i++) {
			set.add(Integer.parseInt(stringSet[i]));
		}
		
		Integer[] intSet = new Integer[set.size()];
		Iterator<Integer> itr = set.iterator();
		
		int i = 0;
		while (itr.hasNext()) {
			intSet[i++] = itr.next();
		}
		
		return intSet;
	}


}