package unir.pharmacyproject.model;

import java.util.ArrayList;

public class Order {
    private String medicineName;
    private String medicineType;
    private int quantity;
    private String distributor;
    private ArrayList<String> branches; 
	
	public Order(String name, String type, int quantity, String distributor, ArrayList<String> branches) {
		this.medicineName = name;
		this.medicineType = type;
		this.quantity = quantity;
		this.distributor = distributor;
		this.branches = branches;
	}
    
    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String name) {
        this.medicineName = name;
    }

    public String getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(String medicineType) {
        this.medicineType = medicineType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void setQuantity(String quantityStr) throws NumberFormatException {
        this.quantity = Integer.parseInt(quantityStr);
    }

    public String getDistributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    public ArrayList<String> getBranches() {
        return branches;
    }

    public void setBranches(ArrayList<String> branches) {
        this.branches = branches;
    }
}

