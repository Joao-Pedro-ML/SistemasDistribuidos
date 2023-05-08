/**
  * Laboratorio 4  
  * Autor: Felipe Galvão Gregório e João Pedro Moreto Lourenção
  */
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorImpl implements IMensagem{
    
	private static final Logger logger = Logger.getLogger(ServidorImpl.class.getName());
	ArrayList<Peer> alocados;
	
    public ServidorImpl() {
          alocados = new ArrayList<>();
    }
    
    //Cliente: invoca o metodo remoto 'enviar'
    //Servidor: invoca o metodo local 'enviar'
    @Override
    public Mensagem enviar(Mensagem mensagem) throws RemoteException {
        Mensagem resposta;
        try {
            logger.log(Level.INFO, "Mensagem recebida: {0}", mensagem.getMensagem());
            resposta = new Mensagem(parserJSON(mensagem.getMensagem()));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao processar mensagem: {0}", e.getMessage());
            resposta = new Mensagem("{\n" + "\"result\": false\n" + "}");
        }
        return resposta;
    }    
    
    public String parserJSON(String json) {
		
    	int indexMethod = json.indexOf("method\":\"", 0) + 9;
        int endIndexMethod = json.indexOf("\"", indexMethod);
        String method = json.substring(indexMethod, endIndexMethod);

        int argsIndex = json.indexOf("args\":[\"", 0) + 8;
        int argsEndIndex = json.indexOf("\"", argsIndex);
        String args = json.substring(argsIndex, argsEndIndex);

        Principal fileHandler = new Principal();

        if(method.equals("read")){
            //lê fortuna do servidor
		    return "{\"result\": \""+ fileHandler.read() +"\"}";
        } else if(method.equals("write")){
            fileHandler.write(args);
		    return "{\"result\": \""+ args +"\"}";
        } else {
		    return "{\"result\": \"Falha ao identificar operação\"}";
        }
	}
    


    public void iniciar(){

    try {
    		//TODO: Adquire aleatoriamente um 'nome' do arquivo Peer.java
    		List<Peer> listaPeers = new ArrayList<Peer>(EnumSet.allOf(Peer.class));
			System.out.println(listaPeers.get(0));
    		
    		Registry servidorRegistro;
    		try {
    			servidorRegistro = LocateRegistry.createRegistry(1099);
    		} catch (java.rmi.server.ExportException e){ //Registro jah iniciado 
    			System.out.print("Registro jah iniciado. Usar o ativo.\n");
    		}
    		servidorRegistro = LocateRegistry.getRegistry(); //Registro eh unico para todos os peers
    		String [] listaAlocados = servidorRegistro.list();
    		for(int i=0; i<listaAlocados.length;i++)
    			System.out.println(listaAlocados[i]+" ativo.");
    		
    		SecureRandom sr = new SecureRandom();
    		Peer peer = listaPeers.get(sr.nextInt(listaPeers.size()));
    		
    		int tentativas=0;
    		boolean repetido = true;
    		boolean cheio = false;
    		while(repetido && !cheio){
    			repetido=false;    			
    			peer = listaPeers.get(sr.nextInt(listaPeers.size()));
    			for(int i=0; i<listaAlocados.length && !repetido; i++){
    				
    				if(listaAlocados[i].equals(peer.getNome())){
    					System.out.println(peer.getNome() + " ativo. Tentando proximo...");
    					repetido=true;
    					tentativas=i+1;
    				}    			  
    				
    			}
    			//System.out.println(tentativas+" "+listaAlocados.length);
    			    			
    			//Verifica se o registro estah cheio (todos alocados)
    			if(listaAlocados.length>0 && //Para o caso inicial em que nao ha servidor alocado,
    					                     //caso contrario, o teste abaixo sempre serah true
    				tentativas==listaPeers.size()){ 
    				cheio=true;
    			}
    		}
    		
    		if(cheio){
    			System.out.println("Sistema cheio. Tente mais tarde.");
    			System.exit(1);
    		}
    		
            IMensagem skeleton  = (IMensagem) UnicastRemoteObject.exportObject(this, 0); //0: sistema operacional indica a porta (porta anonima)
            servidorRegistro.rebind(peer.getNome(), skeleton);
            System.out.print(peer.getNome() +" Servidor RMI: Aguardando conexoes...");
                        
        } catch(Exception e) {
            e.printStackTrace();
        }        

    }
    
    public static void main(String[] args) {
        ServidorImpl servidor = new ServidorImpl();
        servidor.iniciar();
    }    
}

