import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	static Conexao c;
	static Socket socket;

	public Cliente() {
		try {
			socket = new Socket("localhost", 9600);
		} // fase de conexão
		catch (Exception e) {
			System.out.println("Nao consegui resolver o host...");
		}
	}

	public static void main(String args[]) {
		Scanner teclado = new Scanner(System.in);
		boolean system = true;
		while (system) {
			System.out.print("Cliente >>>");
			String msg = "";
			msg = teclado.nextLine();

			String requisicao = "Cliente envia : " + msg;
			String texto;
			new Cliente();
			c.send(socket, requisicao);
			texto = c.receive(socket); // fase de dados
			System.out.println(texto);
		}
		try {
			socket.close(); // fase de desconexão
		} catch (IOException e) {
			System.out.println("Não encerrou a conexão corretamente" + e.getMessage());
		}
	}
}
