/**
  * Laboratorio 4  
  * Autor: Felipe Galvão Gregório e João Pedro Moreto Lourenção
  */

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;
import java.rmi.RemoteException;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
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

        // Obter a Lista de pares disponíveis do arquivo Peer.java

        List<Peer> listaPeers = new ArrayList<>(EnumSet.allOf(Peer.class));
        System.out.println(listaPeers.get(0));

        try {

            Registry registro = LocateRegistry.getRegistry("127.0.0.1", 1099);

            // Escolhe um peer aleatório da lista de peers para conectar
            SecureRandom sr = new SecureRandom();

            IMensagem stub = null;
            Peer peer = null;

            boolean conectou = false;
            while (!conectou) {
                peer = listaPeers.get(sr.nextInt(listaPeers.size()));
                try {
                    stub = (IMensagem) registro.lookup(peer.getNome());
                    conectou = true;
                } catch (java.rmi.ConnectException e) {
                    System.out.println(peer.getNome() + " indisponível. ConnectException. Tentando o próximo...");
                } catch (java.rmi.NotBoundException e) {
                    System.out.println(peer.getNome() + " indisponível. NotBoundException. Tentando o próximo...");
                }
            }
            System.out.println("Conectado no peer: " + peer.getNome());

            String opcao = "";
            Scanner leitura = new Scanner(System.in);
            do {
                System.out.println("1) Read");
                System.out.println("2) Write");
                System.out.println("x) Exit");
                System.out.print(">> ");
                opcao = leitura.next();
                switch (opcao) {
                    case "1": {
                        Mensagem mensagem = new Mensagem("", opcao);
                        Mensagem resposta = stub.enviar(mensagem); // dentro da mensagem tem o campo 'read'
                        System.out.println(resposta.getMensagem());
                        break;
                    }
                    case "2": {
                        // Monta a mensagem
                        System.out.print("Add fortune: ");
                        String fortune = leitura.next();

                        Mensagem mensagem = new Mensagem(fortune, opcao);
                        Mensagem resposta = stub.enviar(mensagem); // dentro da mensagem tem o campo 'write'
                        System.out.println(resposta.getMensagem());
                        break;
                    }
                }
            } while (!opcao.equals("x"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

