package unir.pharmacyproject.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import unir.pharmacyproject.model.Order;
import unir.pharmacyproject.service.OrderService;

public class ConfirmationWindow extends JFrame {
    private Order order;
    private OrderService orderService;
    private OrderForm orderForm;

    public ConfirmationWindow(OrderForm orderForm, Order order, OrderService orderService) {
        this.orderForm = orderForm;
        this.order = order;
        this.orderService = orderService;

        initWindow();
        initElements();
    }

    private void initWindow() {
        setTitle("Pedido para el distribuidor " + order.getDistributor());
        setSize(450, 160);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initElements() {
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(new EmptyBorder(20, 30, 20, 30));

        String info = order.getQuantity() + " unidades del " + order.getMedicineType().toLowerCase() + " " + order.getMedicineName();
        JLabel lblInfo = new JLabel("<html><div style='text-align: center;'><b>" + info + "</b></div></html>", SwingConstants.CENTER);
        lblInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        String branchText = "Para la farmacia situada en " + String.join(" y ", order.getBranches());
        JLabel lblBranches = new JLabel("<html><div style='text-align: center;'>" + branchText + "</div></html>", SwingConstants.CENTER);
        lblBranches.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton btnCancel = new JButton("Cancelar");
        JButton btnSend = new JButton("Enviar Pedido");

        btnCancel.addActionListener(e -> dispose());
        btnSend.addActionListener(e -> sendOrder());

        btnPanel.add(btnCancel);
        btnPanel.add(btnSend);

        content.add(lblInfo);
        content.add(Box.createVerticalStrut(10));
        content.add(lblBranches);
        content.add(Box.createVerticalStrut(20));
        content.add(btnPanel);

        add(content);
        setVisible(true);
    }

    private void sendOrder() {
        orderService.sendOrder(order);
        showConfirmation();
        orderForm.clearForm();
        dispose();
    }

    private void showConfirmation() {
        JOptionPane.showMessageDialog(this, "Su pedido ha sido enviado exitosamente.", "Pedido enviado", JOptionPane.INFORMATION_MESSAGE);
    }
}
