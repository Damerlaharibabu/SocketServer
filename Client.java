package Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws UnknownHostException,
			IOException {
		Socket s = new Socket("localhost", 6666);
		Scanner sca = new Scanner(System.in);
		String temp = sca.nextLine();
		DataOutputStream sendDate= new DataOutputStream( s.getOutputStream());
		DataInputStream receiv = new DataInputStream(s.getInputStream());
		
		sendDate.writeUTF(temp);
		receiv.readUTF();

	}
}
