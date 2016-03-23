package Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerRespo extends Thread {
	public Socket k;
	String data;
	String old;

	void takingInformationFromClient(Socket s) {
		this.k = s;
	}
	public void run() {
		while(true){
		try {
			DataInputStream dis = new DataInputStream(k.getInputStream());
			data = dis.readUTF();
			old = old+"\n"+data;
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int z = 0; z < Server.sockets.size(); z++) {
			Socket tempSocket = Server.sockets.get(z);
			try {
				DataOutputStream dot = new DataOutputStream(
						tempSocket.getOutputStream());
				dot.writeUTF(old);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
		
	}
	}
}
