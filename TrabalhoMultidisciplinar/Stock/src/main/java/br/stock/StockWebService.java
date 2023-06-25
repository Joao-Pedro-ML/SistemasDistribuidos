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
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class StockWebService {
    private final Stock stock;

    public StockWebService() throws RemoteException {
        stock = new Stock();
    }

    @WebMethod
    public void addProduct(String productName, int quantity) throws RemoteException {
        stock.addProduct(productName, quantity);
    }

    @WebMethod
    public int getQuantity(String productName) throws RemoteException {
        return stock.getQuantity(productName);
    }

}

