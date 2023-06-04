/**
 * Lab05: Sistema P2P
 * 
 * Autor: Felipe Galvão Gregório e João Pedro Moreto
 * 
 */

public enum PeerLista {
    
    PEER1 {
        @Override
        public String getNome() {
            return "PEER1";
        }        
    },
    PEER2 {
        public String getNome() {
            return "PEER2";
        }        
    },
    PEER3 {
        public String getNome() {
            return "PEER3";
        }        
    },
    PEERFJ{
        public String getNome() {
            return "PEERFJ";
        }
    }
    ;
    public String getNome(){
        return "NULO";
    }    
}
