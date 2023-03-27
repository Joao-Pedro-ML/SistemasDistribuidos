public class Principal {
    
    public static void main(String [] args){
        
        TCPServidor servidor = new TCPServidor();
        TCPCliente [] cliente = new TCPCliente[10];
        
        Thread ts = new Thread(servidor);
        ts.start();
        
        for(int i = 0; i < cliente.length; i++){
            cliente[i] = new TCPCliente();
            Thread tc = new Thread(cliente[i]);
            tc.start();
        }
        
        
        
    }
    
}
