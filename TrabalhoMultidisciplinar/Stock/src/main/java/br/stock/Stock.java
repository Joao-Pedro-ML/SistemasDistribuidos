package br.stock;

/**
 * @Disciplina Sistemas Distribuidos
 * @Titulo Trabalho Multidisciplinar
 * @author Joao Pedro Moreto Lourencao
 * @RA 2150980
 */

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class Stock extends UnicastRemoteObject implements StockRMI {

    private final Map<String, Integer> stock;

    public Stock() throws RemoteException {
        stock = new HashMap<>();
    }

    @Override
    public void addProduct(String productName, int quantity) throws RemoteException {
        stock.put(productName, quantity);
    }

    @Override
    public int getQuantity(String productName) throws RemoteException {
        return stock.getOrDefault(productName, 0);
    }

    @Override
    public Map<String, Integer> getStock() throws RemoteException {
        return stock;
    }
}
