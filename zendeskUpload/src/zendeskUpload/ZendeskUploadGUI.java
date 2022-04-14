package zendeskUpload;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class ZendeskUploadGUI extends JPanel implements ActionListener{

	//Gui components
	private final int GUI_WIDTH = 400;
	private final int GUI_HEIGHT = 300;
	private JButton selectFileButton;
		
	//the csvFixer we will use
	private CSVFixer csvFixer;

	//File component
	private File selectedCSVFile;
	
	
	ZendeskUploadGUI(){
		initGUI();
	}
	
	private void initGUI() {
		setPreferredSize(new Dimension(GUI_WIDTH, GUI_HEIGHT));
		setFocusable(true);
		initSelectFileButton();
	}
	
	
	private void initSelectFileButton() {
		selectFileButton = new JButton("Select CSV File");
		selectFileButton.addActionListener(this);
		selectFileButton.setPreferredSize(new Dimension(GUI_WIDTH-50,GUI_HEIGHT-50));
		add(selectFileButton);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==selectFileButton) {
			JFileChooser fileChooser = new JFileChooser();
			int response = fileChooser.showOpenDialog(null);//select file to open
			if(response == JFileChooser.APPROVE_OPTION) {
				selectedCSVFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
				//selectedFilePath = selectedCSVFile.getAbsolutePath(); //used to determine where we store the downloaded CSV
				
				//Now, instantiate a CSVFixer object to do the logic of splitting.
				try {
					loadCSVFixer();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	
		
		//System.out.println(selectedCSVFile.exists());

	}

	/*************
	 * Methods performed after action occurs
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 *************/
	
	private void loadCSVFixer() throws FileNotFoundException, IOException {
		csvFixer = new CSVFixer(selectedCSVFile);
	}
	
}
