public class Principal {
    
    public static void main(String [] args){
        
        UDPServidor servidor = new UDPServidor();
        UDPCliente [] cliente = new UDPCliente[10];
        
        Thread ts = new Thread(servidor);
        ts.start();
        
        for(int i = 0; i < cliente.length ; i++){
            cliente[i] = new UDPCliente();
            Thread tc = new Thread(cliente[i]);
            tc.start();
        }
        
        
        
    }
    
}
