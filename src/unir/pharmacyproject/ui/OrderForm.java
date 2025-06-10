package unir.pharmacyproject.ui;

import unir.pharmacyproject.exception.OrderException;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import unir.pharmacyproject.model.Order;
import unir.pharmacyproject.service.OrderService;

public class OrderForm extends JFrame {
	private final String PRINCIPAL_ADDRESS = "Calle de la Rosa n. 28";
	private final String SECUNDARY_ADDRESS = "Calle Alcazabilla n. 3";
	private final String TYPE_EMPTY_OPTION = "Seleccionar...";

    private OrderService orderService;
    private JTextField txtMedicineName;
    private JComboBox<String> cmbMedicineType;
    private JTextField txtQuantity;
    private JRadioButton rbCofarma, rbEmpsephar, rbCemefar;
    private JCheckBox cbPrincipal, cbSecondary;
    private JButton btnClear, btnConfirm;
    private ButtonGroup distributorGroup;

    public OrderForm() {
        orderService = new OrderService();

        initWindow(); 
        initFormElements();
        addFormElements();
        addActionListenerToButtons();

        setVisible(true);
    }

    public void clearForm() {
        txtMedicineName.setText("");
        cmbMedicineType.setSelectedIndex(0);
        txtQuantity.setText("");
        distributorGroup.clearSelection();
        cbPrincipal.setSelected(false);
        cbSecondary.setSelected(false);
    }

    private void initWindow() {
        setTitle("Crear Nueva Orden"); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void initFormElements() {
        txtMedicineName = new JTextField();
        cmbMedicineType = new JComboBox<>(new String[] {TYPE_EMPTY_OPTION, "Analgésico", "Analéptico", "Anestésico", "Antiácido", "Antidepresivo", "Antibiótico"});
        txtQuantity = new JTextField();

        rbCofarma = new JRadioButton("Cofarma");
        rbEmpsephar = new JRadioButton("Empsephar");
        rbCemefar = new JRadioButton("Cemefar");
        distributorGroup = new ButtonGroup();
        distributorGroup.add(rbCofarma);
        distributorGroup.add(rbEmpsephar);
        distributorGroup.add(rbCemefar);

        cbPrincipal = new JCheckBox("Principal");
        cbSecondary = new JCheckBox("Secundaria");

        btnClear = new JButton("Limpiar");
        btnConfirm = new JButton("Confirmar");
    }

    private void addFormElements() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(15, 25, 15, 25));
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;

        // Título del formulario
        JLabel titleLabel = new JLabel("Crear Nueva Orden de Medicamento", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);

        gbc.gridy = ++y;
        mainPanel.add(Box.createVerticalStrut(15), gbc);

        gbc.gridwidth = 1; y++;

        gbc.gridx = 0; gbc.gridy = y;
        mainPanel.add(new JLabel("Nombre del Medicamento:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(txtMedicineName, gbc);

        gbc.gridx = 0; gbc.gridy = ++y;
        mainPanel.add(new JLabel("Tipo del Medicamento:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(cmbMedicineType, gbc);

        gbc.gridx = 0; gbc.gridy = ++y;
        mainPanel.add(new JLabel("Cantidad:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(txtQuantity, gbc);

        gbc.gridx = 0; gbc.gridy = ++y;
        mainPanel.add(new JLabel("Distribuidor:"), gbc);
        gbc.gridx = 1;
        JPanel distributorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        distributorPanel.add(rbCofarma);
        distributorPanel.add(rbEmpsephar);
        distributorPanel.add(rbCemefar);
        mainPanel.add(distributorPanel, gbc);

        gbc.gridx = 0; gbc.gridy = ++y;
        mainPanel.add(new JLabel("Sucursal:"), gbc);
        gbc.gridx = 1;
        JPanel branchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        branchPanel.add(cbPrincipal);
        branchPanel.add(cbSecondary);
        mainPanel.add(branchPanel, gbc);

        gbc.gridx = 0; gbc.gridy = ++y; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnClear);
        buttonPanel.add(btnConfirm);
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel);
    }

    private void addActionListenerToButtons() {
        btnClear.addActionListener(e -> clearForm());
        btnConfirm.addActionListener(e -> confirmOrder());
    }

    private void confirmOrder() {
        try {
            Order order = getOrder();
            orderService.validateOrder(order);
            new ConfirmationWindow(this, order, orderService);
        } catch (OrderException ex) {
            showError(ex.getMessage());
        } catch (Exception ex) {
            showError("Error confirmando orden");
        }
    }

    private Order getOrder() throws OrderException {
        String name = txtMedicineName.getText().trim();
        String type = (String) cmbMedicineType.getSelectedItem();
        String quantityText = txtQuantity.getText().trim();
        String distributor = rbCofarma.isSelected() ? "Cofarma" : rbEmpsephar.isSelected() ? "Empsephar" : rbCemefar.isSelected() ? "Cemefar" : null;
        ArrayList<String> branches = new ArrayList<>();

        type = type.equals(TYPE_EMPTY_OPTION) ? "" : type;
        quantityText = quantityText.isEmpty() ? "0" : quantityText;

        if (cbPrincipal.isSelected()) branches.add(PRINCIPAL_ADDRESS);
        if (cbSecondary.isSelected()) branches.add(SECUNDARY_ADDRESS);

        Order order = new Order(name, type, 0, distributor, branches);

        try {
            order.setQuantity(quantityText);
        } catch (NumberFormatException ex) {
            throw new OrderException("La cantidad debe ser un número entero.");
        }

        return order;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Validation Error", JOptionPane.ERROR_MESSAGE);
    }
}