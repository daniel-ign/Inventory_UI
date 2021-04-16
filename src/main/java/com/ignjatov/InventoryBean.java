package com.ignjatov;

import com.ignjatov.entity.Inventory;
import com.ignjatov.inventory.InventoryInterface;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@SessionScoped
@Named
public class InventoryBean implements Serializable {
    @EJB
    private InventoryInterface inventoryInterface;

    //@NotEmpty
    private String name;
    //@NotEmpty
    private String sport;
    //@NotEmpty
    private int quantity;
    //@NotEmpty
    private double pricePerUnit;

    public List<Inventory> getInventoryList(){
        return inventoryInterface.getInventoryList();
    }

    public void addInventory(){
        Inventory inventory = new Inventory(name,sport,quantity,pricePerUnit);
        Optional<Inventory> inventoryExists = inventoryInterface.getInventoryList().stream()
                .filter(i -> i.getName().equals(name) && i.getSport().equals(sport)).findFirst();
        if (inventoryExists.isPresent()){
            inventoryInterface.addQuantity(inventory,quantity);
        }
        else{
            inventoryInterface.addToList(inventory);
        }
        clearFields();

    }

    private void clearFields(){
        setName("");
        setQuantity(0);
        setSport("");
        setPricePerUnit(0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

}
