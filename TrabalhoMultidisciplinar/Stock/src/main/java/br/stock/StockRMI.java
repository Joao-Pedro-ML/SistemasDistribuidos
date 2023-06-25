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
import java.rmi.RemoteException;
import java.util.Map;

public interface StockRMI extends java.rmi.Remote {
    void addProduct(String productName, int quantity) throws RemoteException;
    int getQuantity(String productName) throws RemoteException;
    Map<String, Integer> getStock() throws RemoteException;
}



