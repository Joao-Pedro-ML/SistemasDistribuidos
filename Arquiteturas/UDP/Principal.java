public class Principal {
    
    public static void main(String [] args){
        
        UDPServidor servidor = new UDPServidor();
        UDPCliente cliente = new UDPCliente();
        
        Thread ts = new Thread(servidor);
        Thread tc = new Thread(cliente);
        
        ts.start();
        tc.start();
    }
    
}
