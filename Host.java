import java.net.*;
import java.io.*;
import java.util.*;

public class Host{


	public static void main(String args[]) throws IOException{

		final int port = 8080;
		ServerSocket servidor;
		Socket cliente;	
		Scanner scanner = new Scanner(System.in);
		BufferedReader reader;
		String lido = "";
		String comando = "";
		OutputStreamWriter writer;


		try{
			

			servidor = new ServerSocket(port);
			System.out.println("Porta aberta em: " + port);
			
			cliente = servidor.accept();
			System.out.println("Conexão estabelecida : " + cliente.getInetAddress().getHostAddress());

			do{
				
				writer = new OutputStreamWriter(cliente.getOutputStream());
				PrintWriter out = new PrintWriter(writer);
				out.println( scanner.nextLine() );
				out.flush();	

				reader = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
				lido = reader.readLine();				
				System.out.println("Cliente SAYS: \n"  + lido);
						
			}while( true );

								
		}catch(Exception error){
			error.getMessage();		
		}



	}


}
