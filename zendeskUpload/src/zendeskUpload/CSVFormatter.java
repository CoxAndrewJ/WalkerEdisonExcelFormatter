package zendeskUpload;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVFormatter {
	
	private Scanner scan;
	private File file;
	
	//private	int numberOfTokens = 0; //Count how many commas we have
	private final int NORMAL_TOKEN_AMOUNT = 2; //We will subtract this amount from number of tokens to provide us with how many parts are requested
	private int tokenDifference = 0;
	
	private StringBuilder sb = new StringBuilder();
	private String line;
	private String[] rows;
	private String[] splitLine;
	
	public CSVFormatter(File file) throws FileNotFoundException, IOException {
		this.file = file;
		this.scan = new Scanner(this.file);
		this.rows = new String[countRows()];
	}
	
	/**
	 * Return the total number of rows in the CSV
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public int countRows() throws FileNotFoundException, IOException {
		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		try {
			byte[] c = new byte[1024];
		    int count = 0;
		    int readChars = 0;
		    boolean empty = true;
		    while ((readChars = inputStream.read(c)) != -1) {
		        empty = false;
		        for (int i = 0; i < readChars; ++i) {
		            if (c[i] == '\n') {
		                ++count;
		            }
		        }
		    }
			return (count == 0 && !empty) ? 1 : count-1;
		}finally {
			inputStream.close();
		}
	}
	
	/**
	 * Place all the rows into an array of strings
	 * @return
	 */
	public String[] getRows() {
		scan.nextLine(); //remove the header
		int counter = 0;
		while(scan.hasNextLine()) {
			rows[counter] = scan.nextLine();
			counter++;
		}
		return rows;
	}
	
	/**
	 * Take the array of strings, and split them into separate lines if the number of parts >1.
	 * 
	 * @param rows
	 * @return a formatted array of strings
	 */
	public String[] formatRows(String[] rows) {
		String[] newRows; //if our tokenDifference >1, we know that multiple parts are requested. Create a new row for each part, grabbing only the part number
		ArrayList<String> formattedRows = new ArrayList<>(); //if our tokenDifference >1, we know that multiple parts are requested. Create a new row for each part, grabbing only the part number
		
		for(int i = 0 ; i < rows.length ; i++) { //For loop for each row
			//System.out.println("iteration : " + i);
			splitLine = rows[i].split(",");
			int numberOfTokens = splitLine.length;//count how many commas are in our new split line
			tokenDifference = numberOfTokens - NORMAL_TOKEN_AMOUNT;
			
			if(tokenDifference == 0) formattedRows.add(rows[i].replaceAll("\"", ""));
			
			if(tokenDifference>0) {
				for(int j = 0 ; j<tokenDifference ; j++) { //for loop for each column of row 'i'
					formattedRows.add(splitLine[0] + ',' +splitLine[j+1].replaceAll("\"", " ") + ',' + splitLine[splitLine.length-1]);
				}
			}
		}
		
		newRows = new String[formattedRows.size()];
		for(int i = 0 ; i < newRows.length ; i++) {
			newRows[i] = formattedRows.get(i);
			//System.out.println(formattedRows.get(i));
		}
		return newRows;
	}
	

}
