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
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StockService extends Remote {
    int getStock(String product) throws RemoteException;
    void updateStock(String product, int quantity) throws RemoteException;
}


