package zendeskUpload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CSVFixer {

	private File file;
	private String selectedFilePath;
	private CSVFormatter csvFormatter;
	
	CSVFixer(File file){
		this.file = file;
		selectedFilePath = this.file.getParent();
		
		try {
			runFormatter();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void runFormatter() throws FileNotFoundException, IOException {
		csvFormatter = new CSVFormatter(file);
		
		String[] rows;
		rows = csvFormatter.getRows();
		
		for(int i = 0 ; i < rows.length ; i++) {
			System.out.println(rows[i]);
		}
		
		rows = csvFormatter.formatRows(rows);
		System.out.println("Formatted:");
		for(int i = 0 ; i < rows.length ; i++) {
			System.out.println(rows[i]);
		}
		
		writeToNewCSV(rows);
	}
	
	public void writeToNewCSV(String[] rows) {
		
		try {
			File formattedFile = new File(selectedFilePath + "\\netsuiteExcel.csv");

			//System.out.println(formattedFile.getAbsolutePath());
			
			if(!formattedFile.exists()) {
				formattedFile.createNewFile();
			}
			
			//Write to the netsuiteExcel.csv
			PrintWriter printWriter = new PrintWriter(formattedFile);
			for(String s : rows) printWriter.write(s + "\n");
			printWriter.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	public static void main(String[] args) throws IOException {
		
		File file = new File("resources/zendeskExcel.csv");
		
		CSVFormatter csvFormatter = new CSVFormatter(file);
	
		String[] rows;
		rows = csvFormatter.getRows();
		
		for(int i = 0 ; i < rows.length ; i++) {
			//System.out.println(rows[i]);
		}
		
		rows = csvFormatter.formatRows(rows);
		for(int i = 0 ; i < rows.length ; i++) {
			//System.out.println(rows[i]);
		}
		
		writeToNewCSV(rows);
	}
	*/
	
	

}
