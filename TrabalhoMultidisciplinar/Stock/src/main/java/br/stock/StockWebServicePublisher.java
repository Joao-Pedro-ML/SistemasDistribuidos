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
import javax.xml.ws.Endpoint;

public class StockWebServicePublisher {
    public static void main(String[] args) throws RemoteException {
        StockWebService stockWebService = new StockWebService();
        String address = "http://localhost:8080/stock-service";
        Endpoint.publish(address, stockWebService);
        System.out.println("Stock Web Service published at: " + address);
    }
}

