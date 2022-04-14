package zendeskUploadTest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import zendeskUpload.CSVFixer;

class CSVFixerTest {

	@Test
	void testWriteToNewCSV() {
		//CSVFixer csvFixer = new CSVFixer();
		//csvFixer.writeToNewCSV();
		File formattedFile = new File("resources/netsuiteExcel.csv");
		assertEquals(true, formattedFile.exists());
	}

}
