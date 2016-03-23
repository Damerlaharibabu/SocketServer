package Socket;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;

public class Client1 {
	// static DataOutputStream doc;
	Socket s;
	static JTextField input = new JTextField(20);
	static JTextArea textArea = new JTextArea(15, 50);
	public static ArrayList<String> client = new ArrayList<String>();

	void hai() throws UnknownHostException, IOException {
		s = new Socket("localhost", 6666);
		JFrame frame = new JFrame("Test");
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(true);
		// textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setFont(Font.getFont(Font.SANS_SERIF));
		JScrollPane scroller = new JScrollPane(textArea);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel inputpanel = new JPanel();
		inputpanel.setLayout(new FlowLayout());
		JButton button = new JButton("Enter");
		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		panel.add(scroller);
		inputpanel.add(input);
		inputpanel.add(button);
		panel.add(inputpanel);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		frame.setResizable(false);
		input.requestFocus();

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					DataOutputStream dos = new DataOutputStream(s
							.getOutputStream());
					String data = input.getText();
					input.setText("");
					dos.writeUTF(data);
					DataInputStream dis = new DataInputStream(s
							.getInputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		while (true) {
			DataInputStream dis = new DataInputStream(s.getInputStream());
			String infor = dis.readUTF();
			textArea.setText(infor);
			ServerRespo ssss = new ServerRespo();
			String kk = ssss.data;
			textArea.setText("\n" + infor);
		}
	}
	public static void main(String[] args) throws UnknownHostException,
			IOException {
		Client1 n = new Client1();
		n.hai();
	}
}
