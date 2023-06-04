/**
 * Lab05: Sistema P2P
 * 
 * Autor: Felipe Galvão Gregório e João Pedro Moreto
 * 
 */

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IMensagem extends Remote {
    
    public Mensagem enviar(Mensagem mensagem) throws RemoteException;
    
}
