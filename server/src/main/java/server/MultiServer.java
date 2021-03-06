
package server;

import java.io.IOException;

import java.net.Socket;
import java.net.ServerSocket;

/**
 * The multithread server main class.
 */
public class MultiServer {

	/**
	 * The server's connection port.
	 */
	private static final int PORT = 8080;

	/**
	 * The server socket.
	 */
	private ServerSocket socket;

	/**
	 * The server entry point.
	 * @param args The arguments of the program
	 */
	public static void main(String[] args) {
		int port;

		if (args.length == 0) {
			port = PORT;
		} else {
			try {
				port = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				System.err.println(e.toString());
				return;
			}
		}

		if (port < 0 || port >= 65536) {
			System.err.println("Invalid port: " + port);
			return;
		}

		try {
			new MultiServer(port).run();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Construct a multithread server.
	 * @param port The connection port
	 * @throws IOException Thrown when the server socket constructor fails
	 */
	public MultiServer(int port) throws IOException {
		socket = new ServerSocket(port);
	}

	/**
	 * Run the server.
	 */
	public void run() {
		try {
			while (true) {
				Socket clientSocket = socket.accept();
				System.out.println("accepting " + clientSocket);

				try {
					new ServerOneClient(clientSocket);
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} finally {
			close();
		}
	}

	/**
	 * Close the server.
	 */
	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}

