/**
 * Laboratorio 3  
 * Felipe Galvão Gregório e João Pedro Moreto Lourenção
 */
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.rmi.RemoteException;

public class ClienteRMI {
    
    
	
	public static void read(IMensagem stub) {
		Mensagem mensagem = new Mensagem("", "1");
		try {
			Mensagem resposta = stub.enviar(mensagem);
			System.out.println(resposta.getMensagem());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public static void write(String fortune, IMensagem stub) {
		Mensagem mensagem = new Mensagem(fortune, "2");
		try {
			Mensagem resposta = stub.enviar(mensagem);
			System.out.println(resposta.getMensagem());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
                
        try {
                        
            Registry registro = LocateRegistry.getRegistry("127.0.0.1", 1099);            
            IMensagem stub = (IMensagem) registro.lookup("servidorFortunes");  
                        
            String opcao="";
            Scanner leitura = new Scanner(System.in);
            do {
            	System.out.println("1) Read");
            	System.out.println("2) Write");
            	System.out.println("x) Exit");
            	System.out.print(">> ");
            	opcao = leitura.next();
            	switch(opcao){
            	case "1": {
            		read(stub);
            		break;
            	}
            	case "2": {
            		//Monta a mensagem                		
            		System.out.println("Adicione uma fortuna: ");
					String fortuna = leitura.next();
					write(fortuna, stub);
            		break;
            	}
            	}
            } while(!opcao.equals("x"));
                        
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
