package main.java.util;
/**
 * The SwingTest class is used to provide UI capabilities
 *
 * @author Shilpashree_V
 * @version 1.0
 * @since February 2015
 * 
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author Shilpashree_V
 * UI for config.properties file
 */
@SuppressWarnings("serial")
public class SwingTest extends JPanel {
	MainTestNG test = new MainTestNG();

	String configFile = "config.properties";

	String browse = "Browse";
	public static final int MODE_OPEN = 1;
	public static final int MODE_SAVE = 2;
	JButton ltestcasebrowse, ltestsuitebrowse, belementLoc, bbrowserDriver,
			bClear, bClose, bscreenshot, bGenerate;
	JTextField ftestcase, ftestsuite, felementLoc, fbrowserDriver, fscreenshot,
			fURL;
	JComboBox<String> fbrowserName;
	static JFrame frame = new JFrame();
	JFileChooser fileChooser = new JFileChooser();
	GridBagConstraints grid = new GridBagConstraints();
	JPanel controlPanel = new JPanel();

	public SwingTest() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		} catch (ClassNotFoundException e) {
			MainTestNG.LOGGER.info("Exception" + e.getCause());
		} catch (Exception e) {
			MainTestNG.LOGGER.info("Exception" + e.getMessage());
		}
		SwingUtilities.updateComponentTreeUI(this);

		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setTitle("ISAF");

		controlPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		controlPanel.setBackground(Color.white);

		controlPanel.setLayout(new GridBagLayout());

		grid.gridx = 0;
		grid.gridy = 0;
		grid.insets = new Insets(5, 20, 5, 5);

		JLabel ltestcase = new JLabel("Testcase: ");

		controlPanel.add(ltestcase, grid);

		ftestcase = new JTextField();
		ftestcase.setPreferredSize(new Dimension(250, 20));
		ftestcase.setMinimumSize(new Dimension(250, 20));
		ftestcase.setMaximumSize(new Dimension(250, 20));
		grid.gridx = 1;
		grid.gridy = 0;
		grid.insets = new Insets(5, 20, 5, 5);
		ftestcase
				.setToolTipText("Path of excel file in which testcases are written and file type should be .xlsx");
		controlPanel.add(ftestcase, grid);
		ltestcase.setLabelFor(ftestcase);

		ltestcasebrowse = new JButton(browse);
		addbutton(ltestcasebrowse, ftestcase);
		grid.gridx = 2;
		grid.gridy = 0;
		grid.insets = new Insets(5, 20, 5, 5);
		controlPanel.add(ltestcasebrowse, grid);

		JLabel ltestsuite = new JLabel("Testsuite: ");
		grid.gridx = 0;
		grid.gridy = 1;
		grid.insets = new Insets(5, 20, 5, 5);
		controlPanel.add(ltestsuite, grid);

		ftestsuite = new JTextField();
		ftestsuite.setPreferredSize(new Dimension(250, 20));
		ftestsuite.setMinimumSize(new Dimension(250, 20));
		ftestsuite.setMaximumSize(new Dimension(250, 20));
		ftestsuite
				.setToolTipText("Path of excel file- TestSuite and file type should be .xlsx");
		grid.gridx = 1;
		grid.gridy = 1;
		grid.insets = new Insets(5, 20, 5, 5);

		controlPanel.add(ftestsuite, grid);
		ltestsuite.setLabelFor(ftestsuite);

		ltestsuitebrowse = new JButton(browse);
		addbutton(ltestsuitebrowse, ftestsuite);
		grid.gridx = 2;
		grid.gridy = 1;
		controlPanel.add(ltestsuitebrowse, grid);

		JLabel lelementLoc = new JLabel("Element Locators: ");
		grid.gridx = 0;
		grid.gridy = 2;
		controlPanel.add(lelementLoc, grid);

		felementLoc = new JTextField();
		felementLoc.setPreferredSize(new Dimension(250, 20));
		felementLoc.setMinimumSize(new Dimension(250, 20));
		felementLoc.setMaximumSize(new Dimension(250, 20));
		grid.gridx = 1;
		grid.gridy = 2;
		felementLoc
				.setToolTipText("Path of elemet locators properties file and file type should be .properties");
		controlPanel.add(felementLoc, grid);
		lelementLoc.setLabelFor(felementLoc);

		belementLoc = new JButton(browse);
		addbutton(belementLoc, felementLoc);
		grid.gridx = 2;
		grid.gridy = 2;
		controlPanel.add(belementLoc, grid);

		JLabel lbrowserName = new JLabel("Browser Name");
		grid.gridx = 0;
		grid.gridy = 3;
		controlPanel.add(lbrowserName, grid);

		String[] brNamesList = { "firefox", "IE", "chrome" };
		fbrowserName = new JComboBox<String>(brNamesList);
		fbrowserName.setBackground(Color.WHITE);


		fbrowserName.setPreferredSize(new Dimension(250, 20));
		grid.gridx = 1;
		grid.gridy = 3;

		fbrowserName.setToolTipText("Enter browser Eg: firefox,IE,chrome");
		controlPanel.add(fbrowserName, grid);

		JLabel lbrowserDriver = new JLabel("Browser Driver: ");
		grid.gridx = 0;
		grid.gridy = 4;
		controlPanel.add(lbrowserDriver, grid);

		fbrowserDriver = new JTextField();
		fbrowserDriver.setPreferredSize(new Dimension(250, 20));
		grid.gridx = 1;
		grid.gridy = 4;
		fbrowserDriver
				.setToolTipText("exe of specific browser server (IEDriverServer.exe, ChormeDriverServer.exe) and file type should be .exe");
		controlPanel.add(fbrowserDriver, grid);
		lbrowserDriver.setLabelFor(fbrowserDriver);

		bbrowserDriver = new JButton(browse);
		addbutton(bbrowserDriver, fbrowserDriver);
		grid.gridx = 2;
		grid.gridy = 4;
		controlPanel.add(bbrowserDriver, grid);

		JLabel lscreenshot = new JLabel("Screenshot: ");
		grid.gridx = 0;
		grid.gridy = 5;
		controlPanel.add(lscreenshot, grid);

		fscreenshot = new JTextField();
		fscreenshot.setPreferredSize(new Dimension(250, 20));
		fscreenshot
				.setToolTipText("Screenshots on failure of testcase to be saved");
		grid.gridx = 1;
		grid.gridy = 5;

		controlPanel.add(fscreenshot, grid);
		lscreenshot.setLabelFor(fscreenshot);

		bscreenshot = new JButton(browse);
		addbutton(bscreenshot, fscreenshot);
		grid.gridx = 2;
		grid.gridy = 5;
		controlPanel.add(bscreenshot, grid);

		JLabel lURL = new JLabel("URL: ");
		grid.gridx = 0;
		grid.gridy = 6;
		controlPanel.add(lURL, grid);

		fURL = new JTextField();
		fURL.setPreferredSize(new Dimension(250, 20));
		fURL.setToolTipText("URL of the application to be tested");
		grid.gridx = 1;
		grid.gridy = 6;

		controlPanel.add(fURL, grid);

		lURL.setLabelFor(fURL);

		execute();

		bClear = new JButton("Clear");
		clearButton(bClear);
		grid.gridx = 1;
		grid.gridy = 15;
		controlPanel.add(bClear, grid);

		frame.getContentPane().add(controlPanel);

		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);

		frame.dispose();
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		frame.setVisible(true);
	}

	private void execute() {

		bGenerate = new JButton("Execute");
		generatebutton(bGenerate);
		grid.gridx = 3;
		grid.gridy = 15;
		controlPanel.add(bGenerate, grid);

	}

	private void clearButton(JButton bClear) {
		bClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ftestcase.setText("");
				ftestsuite.setText("");
				fbrowserDriver.setText("");
				fscreenshot.setText("");
				felementLoc.setText("");
				fURL.setText("");

			}
		});

	}

	private void addbutton(JButton ltestcasebrowse2, final JTextField textField) {
		ltestcasebrowse2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				buttonActionPerformed(e, textField);

			}

		});
	}

	private void generatebutton(final JButton b) {
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				generatePropFile(e, b);

			}

		});
	}

	private void buttonActionPerformed(ActionEvent evt, JTextField textField) {
		if (textField.equals(fscreenshot)) {
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			textField.setText(fileChooser.getCurrentDirectory()
					.getAbsolutePath());
		}
		if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
		}

	}

	private void generatePropFile(ActionEvent evt, JButton b) {

		boolean status = true;

		if (!ftestcase.getText().endsWith(".xlsx")
				|| ftestcase.getText().length() == 0) {
			status = false;

			JOptionPane.showMessageDialog(ftestcase,
					"Field is blank or invalid file type");

		} else if (!ftestsuite.getText().endsWith(".xlsx")
				|| ftestcase.getText().length() == 0) {
			status = false;

			JOptionPane
					.showMessageDialog(ftestsuite,
							"testsuite: invalid file type.Please add file with  extension .xlsx");
		} else if (!felementLoc.getText().endsWith(".properties")) {
			status = false;

			JOptionPane
					.showMessageDialog(
							felementLoc,
							"Element Locators: invalid file type.Please add file with  extension .properties");
		} else if (!fbrowserDriver.getText().endsWith(".exe")) {
			status = false;

			JOptionPane
					.showMessageDialog(fbrowserDriver,
							"BrowserDriver: invalid file type.Please add file with  extension .exe");
		} else if (fURL.getText().length() == 0) {
			status = false;

			JOptionPane.showMessageDialog(fURL, "Please enter the url");
		}

		String testcase = "TestCasePath";
		String testsuite = "TestSuiteName";
		String locators = "ELEMENT_LOCATORS";
		String browserdriver = "IEDRIVER";
		String url = "URL";
		String screenshot = "SCREENSHOTS";
		String browsername = "BROWSER_NAME";
		if (ftestcase.getText().length() == 0) {

			FileInputStream in;
			try {
				in = new FileInputStream(configFile);
				Properties props = new Properties();
				props.load(in);

				if (props.getProperty(testcase) != null) {
					ftestcase.setText(props.getProperty(testcase));
				}
				if (props.getProperty(testsuite) != null
						&& ftestsuite.getText().length() == 0) {
					ftestsuite.setText(props.getProperty(testsuite));
				}
				if (props.getProperty(locators) != null
						&& felementLoc.getText().length() == 0) {
					felementLoc.setText(props.getProperty(locators));
				}

				if (props.getProperty(browserdriver) != null
						&& fbrowserDriver.getText().length() == 0) {
					fbrowserDriver.setText(props.getProperty(browserdriver));
				}

				fURL.setText(props.getProperty(url));
				fscreenshot.setText(props.getProperty(screenshot));
				frame.validate();
				frame.repaint();
				in.close();

			} catch (IOException e1) {
				MainTestNG.LOGGER.info("IOException"+ e1.getMessage());

			}

		} else {
			try {

				FileInputStream in = new FileInputStream(configFile);
				Properties props = new Properties();
				props.load(in);
				in.close();

				FileOutputStream out = new FileOutputStream(configFile);

				props.setProperty(testcase, ftestcase.getText());

				if (ftestsuite.getText().length() != 0) {
					props.setProperty(testsuite, ftestsuite.getText());
				}

				if (felementLoc.getText().length() != 0) {
					props.setProperty(locators, felementLoc.getText());
				}
				if (fbrowserName.getSelectedItem().toString() != null) {
					props.setProperty(browsername, fbrowserName
							.getSelectedItem().toString());
				}

				if (fbrowserDriver.getText().length() != 0) {

					props.setProperty(browserdriver, fbrowserDriver.getText());

				}
				if (fURL.getText().length() != 0) {
					props.setProperty(url, fURL.getText());
				}
				if (fscreenshot.getText().length() != 0) {
					props.setProperty(screenshot, fscreenshot.getText());
				}

				props.store(out, null);
				out.close();

			} catch (Exception z) {
				MainTestNG.LOGGER.info("e"+z);
				
			}

		}

		if (status) {
			test.testng();
		}

	}

	public String getSelectedFilePath() {
		return ftestcase.getText();
	}

	public JFileChooser getFileChooser() {
		return this.fileChooser;
	}
}
