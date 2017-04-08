import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Window extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lFile, lSize, lTime;
	private JTextField tFilePath, tHashCode;
	private JButton bUpload, bGetHash;
	private String path;
	

	public Window() {
	
		setSize(410,220);
		setTitle("MD5");
		setLayout(null);
		
		lFile = new JLabel("MD5 HASH");
		lFile.setBounds(20, 0, 150, 20);
		add(lFile);
		
		bUpload = new JButton("Upload File");
		bUpload.setBounds(20, 30, 120, 20);
		add(bUpload);
		bUpload.addActionListener(this);
	
		tFilePath = new JTextField("");
		tFilePath.setBounds(150, 30, 220, 20);
		add(tFilePath);
		
		bGetHash = new JButton("Get hash code");
		bGetHash.setBounds(20, 70, 120, 20);
		add(bGetHash);
		bGetHash.addActionListener(this);
	
		tHashCode = new JTextField("");
		tHashCode.setBounds(20, 100, 350, 20);
		add(tHashCode);
		
		lSize = new JLabel("");
		lSize.setBounds(20, 130, 350, 20);
		add(lSize);
		
		lTime = new JLabel("");
		lTime.setBounds(20, 150, 350, 20);
		add(lTime);

		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		
		if(source==bUpload) {
			
			JFileChooser chooseFilePath = new JFileChooser();
			chooseFilePath.setCurrentDirectory(new java.io.File("."));
			chooseFilePath.setDialogTitle("Select Your File.");
			chooseFilePath.setFileSelectionMode(JFileChooser.FILES_ONLY);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT", "txt");
			chooseFilePath.setFileFilter(filter);
			chooseFilePath.setAcceptAllFileFilterUsed(false);
			if (chooseFilePath.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				path = chooseFilePath.getSelectedFile().toString();
				tFilePath.setText(path);
				
			}
			
			
		}
		else if(source==bGetHash) {
			
				long startTime = System.nanoTime();
				File file = new File(path);
			    byte[] fileBytes = MD5.fileIntoBytes(file);
			    byte[] hash = MD5.getMD5(fileBytes);
			    String hashCode = MD5.bytesToString(hash);
				tHashCode.setText(hashCode);
				long endTime = System.nanoTime();
				long duration = (endTime - startTime);
				long size = file.length();
				lSize.setText("Size: " + size + " bytes");
				lTime.setText("Time: " + duration/1000000 + " miliseconds");
	
		}
	}
}
