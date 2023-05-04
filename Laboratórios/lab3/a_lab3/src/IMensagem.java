/**
 * Laboratorio 3  
 * Felipe Galvão Gregório e João Pedro Moreto Lourenção
 */
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IMensagem extends Remote {
    
    public Mensagem enviar(Mensagem mensagem) throws RemoteException;
    
}
