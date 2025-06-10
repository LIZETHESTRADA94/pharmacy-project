package unir.pharmacyproject;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import unir.pharmacyproject.ui.OrderForm;

public class PharmacyProject {

    public static void main(String[] args) {
    	 try {
             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
         } catch (Exception e) {
             e.printStackTrace();
         }
    	
        SwingUtilities.invokeLater(() -> new OrderForm());
    }
}
