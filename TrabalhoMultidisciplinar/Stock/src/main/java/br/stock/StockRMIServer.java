/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.stock;

/**
 * @Disciplina Sistemas Distribuidos
 * @Titulo Trabalho Multidisciplinar
 * @author Joao Pedro Moreto Lourencao
 * @RA 2150980
 */
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StockRMIServer {
    public static void main(String[] args) {
        try {
            Stock stock = new Stock();

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("StockRMI", stock);

            System.out.println("StockRMI Server ready");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


