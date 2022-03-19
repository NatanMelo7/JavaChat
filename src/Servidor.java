
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {

	static ServerSocket serversocket;
	static Socket client_socket;
	static Conexao c;
	static String msg;

	public Servidor() {

		try {
			serversocket = new ServerSocket(9600);
			System.out.println("Criando o Server Socket");
		} catch (Exception e) {
			System.out.println("Nao criei o server socket...");
		}
	}

	public static void main(String args[]) {
		new Servidor();
		Scanner teclado = new Scanner(System.in);
		boolean system = true;
		String texto;
		while (system) {
			if (connect()) {

				System.out.println("Esperando mensagem do cliente....");
				texto = c.receive(client_socket); //
				System.out.println(texto); // fase de dados

				System.out.print("Servidor >>> ");
				String msg = teclado.nextLine();
				String resposta = "Servidor Envia: " + msg;

				c.send(client_socket, resposta);
			}
		}
		try {
			client_socket.close();
			serversocket.close();
		} // desconexao
		catch (Exception e) {
			System.out.println("N„o encerrou a conex„o corretamente" + e.getMessage());
		}
	}

	static boolean connect() {
		boolean ret;
		try {
			client_socket = serversocket.accept(); // fase de conex√£o
			ret = true;
		} catch (Exception e) {
			System.out.println("N„o fez conex„o" + e.getMessage());
			ret = false;
		}
		return ret;
	}
}
