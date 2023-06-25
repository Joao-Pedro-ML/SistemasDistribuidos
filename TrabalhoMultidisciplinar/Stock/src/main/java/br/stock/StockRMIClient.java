package br.stock;

/**
 * @Disciplina Sistemas Distribuidos
 * @Titulo Trabalho Multidisciplinar
 * @author Joao Pedro Moreto Lourencao
 * @RA 2150980
 */
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StockRMIClient {

    private static JTextField productNameField;
    private static JTextField quantityField;
    private static JTextArea stockTextArea;
    private static StockRMI stockRMI;

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            stockRMI = (StockRMI) registry.lookup("StockRMI");

            JFrame frame = new JFrame("Stock Management");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();

            JLabel productNameLabel = new JLabel("Product Name:");
            productNameField = new JTextField(20);

            JLabel quantityLabel = new JLabel("Quantity:");
            quantityField = new JTextField(10);

            JButton addButton = new JButton("Add Product");
            addButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        addProduct();
                    } catch (RemoteException ex) {
                        Logger.getLogger(StockRMIClient.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            JButton viewStockButton = new JButton("View Stock");
            viewStockButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        viewStock();
                    } catch (RemoteException ex) {
                        Logger.getLogger(StockRMIClient.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            stockTextArea = new JTextArea(10, 30);
            stockTextArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(stockTextArea);

            panel.add(productNameLabel);
            panel.add(productNameField);
            panel.add(quantityLabel);
            panel.add(quantityField);
            panel.add(addButton);
            panel.add(viewStockButton);

            frame.getContentPane().add(panel, BorderLayout.NORTH);
            frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addProduct() throws RemoteException {
        String productName = productNameField.getText();
        int quantity = Integer.parseInt(quantityField.getText());

        stockRMI.addProduct(productName, quantity);
        clearFields();
    }

    private static void viewStock() throws RemoteException {
        Map<String, Integer> stock = stockRMI.getStock();

        StringBuilder stockText = new StringBuilder();
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            String productName = entry.getKey();
            int quantity = entry.getValue();
            stockText.append(productName).append(": ").append(quantity).append("\n");
        }

        stockTextArea.setText(stockText.toString());
    }

    private static void clearFields() {
        productNameField.setText("");
        quantityField.setText("");
    }
}
