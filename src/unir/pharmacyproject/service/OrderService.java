package unir.pharmacyproject.service;

import unir.pharmacyproject.exception.OrderException;
import unir.pharmacyproject.model.Order;

public class OrderService {
    
    public void sendOrder(Order order) {
        // A modo de simulación solo mostramos mensaje en consola
        System.out.println("Pedido enviado.");
    }
    
    public void validateOrder(Order order) throws OrderException {
        if (order.getMedicineName().isEmpty() || !order.getMedicineName().matches("[a-zA-Z0-9 ]+")) {
            throwError("Nombre de medicamento inválido.");
        }
        
        if (order.getMedicineType().isEmpty()) {
            throwError("Seleccione un tipo de medicamento.");
        }
        
        if (order.getQuantity() <= 0) { 
            throwError("La cantidad debe ser un número positivo.");
        }
        
        if (order.getDistributor() == null) {
            throwError("Seleccione un distribuidor.");
        }
        
        if (order.getBranches().isEmpty()) {
            throwError("Seleccione al menos una sucursal.");
        }
    }
    
    private void throwError(String message) throws OrderException {
        throw new OrderException(message);
    }
}

