/**
  * Laboratorio 4  
  * Autor: Felipe Galvão Gregório e João Pedro Moreto Lourenção
  */

import java.io.Serializable;

public class Mensagem implements Serializable {
    
	String mensagem;
	
	//Cliente -> Servidor
    public Mensagem(String mensagem, String opcao){    	
                
        setMensagem(mensagem,opcao);
        
    }
    //Servidor -> Cliente
    public Mensagem(String mensagem){
    	this.mensagem = new String(mensagem);
    }
    public String getMensagem(){
    	return this.mensagem;
    }
    public void setMensagem(String fortune, String opcao){
    	String mensagem="";
    	
    	switch(opcao){
			case "1": {
        		mensagem += "{\"method\":\"read\",\"args\":[\"\"]}";
			break;
			}
			case "2": {
				mensagem +="{\"method\":\"write\",\"args\":[\""+ fortune +"\"]}";    
			break;
			}
		}//fim switch
    	this.mensagem = mensagem;
    }
    
}
