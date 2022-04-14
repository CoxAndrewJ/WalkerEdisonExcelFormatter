package zendeskUpload;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class ZendeskUpload extends JFrame{

	public ZendeskUpload() {
		initUI();
	}
	
	private void initUI() {
		add(new ZendeskUploadGUI());
		pack();
		setTitle("Zendesk Excel Formatter");
		setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
			JFrame exe = new ZendeskUpload();
			exe.setVisible(true);
		});	
	}
}
