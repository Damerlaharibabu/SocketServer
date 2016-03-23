package Socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {
	public static ArrayList<Socket> sockets = new ArrayList<Socket>();

	public void run() {
		 
		try {
			ServerSocket ss = new ServerSocket(6666);
			while (true) {
				Socket s = ss.accept();
				sockets.add(s);
				ServerRespo sr = new ServerRespo();
				sr.takingInformationFromClient(s);
				sr.start();
				for (int n = 0; n < sockets.size(); n++) {
					System.out.println(sockets.get(n));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}
}
