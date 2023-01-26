import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ControleDeProdutos extends JFrame implements ActionListener {

    // Declaracao das variaveis
    private ArrayList<Product> products;
    private JLabel lblName;
    private JTextField txtName;
    private JLabel lblPrice;
    private JTextField txtPrice;
    private JLabel lblBarcode;
    private JTextField txtBarcode;
    private JLabel lblExpirationDate;
    private JTextField txtExpirationDate;
    private JButton btnAdd;
    private JButton btnRemove;
    private JButton btnView;

    public ControleDeProdutos() {

        // Titulo da janela
        super("Controle de produtos");

        // Instanciando a lista de produtos
        products = new ArrayList<Product>();

        // Configurando a janela
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        this.setLayout(new BorderLayout());

        // Criando as labels
        this.lblName = new JLabel("Nome*: ");
        this.lblPrice = new JLabel("Preço: ");
        this.lblBarcode = new JLabel("Codigo de barras*: ");
        this.lblExpirationDate = new JLabel("Data de validade: ");

        // Criando os text fields
        this.txtName = new JTextField(20);
        this.txtPrice = new JTextField(20);
        this.txtBarcode = new JTextField(20);
        this.txtExpirationDate = new JTextField(20);

        // Criando os buttons
        this.btnAdd = new JButton("Adicionar");
        this.btnAdd.setActionCommand("Adicionar");
        this.btnAdd.addActionListener(this);
        this.btnRemove = new JButton("Remover");
        this.btnRemove.setActionCommand("Remover");
        this.btnRemove.addActionListener(this);
        this.btnView = new JButton("Ver produtos");
        this.btnView.setActionCommand("Ver produtos");
        this.btnView.addActionListener(this);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(4, 2));
        topPanel.add(this.lblName);
        topPanel.add(this.txtName);
        topPanel.add(this.lblPrice);
        topPanel.add(this.txtPrice);
        topPanel.add(this.lblBarcode);
        topPanel.add(this.txtBarcode);
        topPanel.add(this.lblExpirationDate);
        topPanel.add(this.txtExpirationDate);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(this.btnAdd);
        bottomPanel.add(this.btnRemove);
        bottomPanel.add(this.btnView);

        this.add(topPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Adicionar")) {

            // Obtendo informacoes 
            String name = this.txtName.getText();
            double price = 0;
            String barcode = this.txtBarcode.getText();
            String expirationDate = this.txtExpirationDate.getText();

            // Verificando se o nome nao esta vazio
            if (name.equals("")) {
                JOptionPane.showMessageDialog(this, "O Nome não pode estar vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                // Verificando se o codigo de barras esta vazio
                if (barcode.equals("")) {
                    JOptionPane.showMessageDialog(this, "O Código de barras não pode estar vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Verificando se a daa de validade nao esta vazia
                    if (expirationDate.equals("")) {
                                if(String.valueOf(price).equals("0.0")){
                                    // Criando um objeto produto simples
                                        SimpleProduct product = new SimpleProduct(name,barcode);

                                        // Adiciona o produto a lista
                                        this.products.add(product.store());

                                        // Limpando os Text Fields
                                        this.txtName.setText("");
                                        this.txtPrice.setText("");
                                        this.txtBarcode.setText("");
                                        this.txtExpirationDate.setText("");
                                }else{
                                    JOptionPane.showMessageDialog(this, "A data de validade não pode estar vazia", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                            }
                    } else {
                        // Confirma que o preço é válido
                        try {
                            price = Double.parseDouble(this.txtPrice.getText());
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(this, "O Preço não é válido!", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                        // Cira um objeto produto
                        Product product = new Product(name, price, barcode, expirationDate);

                        // Adiciona o produto na lista
                        this.products.add(product);

                        // Limpa os Text Fields
                        this.txtName.setText("");
                        this.txtPrice.setText("");
                        this.txtBarcode.setText("");
                        this.txtExpirationDate.setText("");
                    }
                }
            }
        } else if (e.getActionCommand().equals("Remover")) {

            // Obtendo iformacoes
            String name = this.txtName.getText();
            String barcode = this.txtBarcode.getText();
            
            // Verifica se o nome esta vazio
            if (name.equals("")) {
                JOptionPane.showMessageDialog(this, "O Nome não pode estar vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {

                // Verifica se o codigo de barras esta vazio
                if (barcode.equals("")) {
                    JOptionPane.showMessageDialog(this, "O Código de barras não pode estar vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {

                    // Remove o produto da lista
                    for (int i = 0; i < this.products.size(); i++) {
                        Product product = this.products.get(i);
                        if (product.getName().equals(name) && product.getBarcode().equals(barcode)) {
                            this.products.remove(i);
                        }
                    }

                    // Limpa os Text Fields
                    this.txtName.setText("");
                    this.txtPrice.setText("");
                    this.txtBarcode.setText("");
                    this.txtExpirationDate.setText("");
                }
            }
        } else if (e.getActionCommand().equals("Ver produtos")) {

            // Verifica se a lista esta vazia
            if (this.products.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhum produto cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            
            } else {

                // Mostra a lista de produtos
                String message = "";
                for (Product product : this.products) {
                    message += product.getName() + " - " + product.getPrice() + " - " + product.getBarcode() + " - "
                            + product.getExpirationDate() + "\n";
                }
                JOptionPane.showMessageDialog(this, message, "Produtos", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new ControleDeProdutos();
    }

}

class Product extends SimpleProduct{

    // Declaração de variaveis
    private double price;
    
    private String expirationDate;

    public Product(String name, double price, String barcode,String expirationDate) {
        super(name,barcode);
        this.price = price;
        this.expirationDate = expirationDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

}

class SimpleProduct{

    // Declaração de variaveis
    private String name;
    private String barcode;

    public SimpleProduct(String name, String barcode) {
        this.name = name;
        this.barcode = barcode;
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Product store(){
        return new Product(this.name, 0.0, this.barcode,"");
    }
} 
