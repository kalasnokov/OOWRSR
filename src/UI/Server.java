package UI;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

import AFSFile.AFSFile;
import AFSFile.Preset;
import Datahandlers.Base;

public class Server extends JFrame {
	boolean running = true;
	JTextArea Log;
	AFSFile config;

	public Server() throws IOException {
		super("OOWRSR Server");
		config = new AFSFile("res/options/opt.cfg", new Preset().options(),
				true);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception error) {
		}
		setLocation(500, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setSize(275, 507);

		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		Log = new JTextArea();
		Log.setWrapStyleWord(true);
		Log.setLineWrap(true);
		Log.setEditable(false);
		Log.setCaretPosition(0);
		scrollPane.setViewportView(Log);

		textField = new JTextField();
		textField.setColumns(10);
		JCheckBox Debug = new JCheckBox("Debug");
		Debug.setSelected(config.ReadBooleanSetting("debug"));
		Debug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b.setDebug(Debug.isSelected());
				try {
					config.WriteSetting("Debug", Debug.isSelected());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		JCheckBox IcCheck = new JCheckBox("IC check");
		IcCheck.setSelected(config.ReadBooleanSetting("iccheck"));
		IcCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b.setICCheck(IcCheck.isSelected());
				try {
					config.WriteSetting("iccheck", IcCheck.isSelected());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						groupLayout.createSequentialGroup().addComponent(Debug)
								.addGap(2).addComponent(IcCheck))
				.addComponent(textField, GroupLayout.DEFAULT_SIZE, 259,
						Short.MAX_VALUE)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 259,
						Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE,
								411, Short.MAX_VALUE)
						.addGap(1)
						.addGroup(
								groupLayout
										.createParallelGroup(Alignment.LEADING)
										.addComponent(Debug)
										.addComponent(IcCheck))
						.addGap(5)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE,
								29, GroupLayout.PREFERRED_SIZE)));
		getContentPane().setLayout(groupLayout);

		setVisible(true);

		ap("Base is being created\n");
		b = new Base(config.ReadBooleanSetting("debug"),
				config.ReadBooleanSetting("iccheck"), this);
		
		ap(b.getserverlog());
	}

	public void ap(String s) {
		Log.append(s);
		Log.setCaretPosition(Log.getDocument().getLength());
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8914289411250742011L;
	private JTextField textField;

	Base b;

	public static void main(String[] args) throws IOException {
		new Server();
	}
}
