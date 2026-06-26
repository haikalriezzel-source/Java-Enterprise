/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

/**
 *
 * @author haikalriez
 */
public class equipmentBean {
   private String equipmentName;
    private String brandModel;
    private int quantity;
    private String equipmentImage;
    private int equipmentID;
    private String equipmentStatus;

    public equipmentBean() {
    }

    public String getEquipmentName() {
        return equipmentName;
    }
    
    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getBrandModel() {
        return brandModel;
    }

    public void setBrandModel(String brandModel) {
        this.brandModel = brandModel;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getEquipmentImage() {
        return equipmentImage;
    }

    public void setEquipmentImage(String equipmentImage) {
        this.equipmentImage = equipmentImage;
    }
    
    public int getEquipmentID() {
    return equipmentID;
    }

    public void setEquipmentID(int equipmentID) {
    this.equipmentID = equipmentID;
    }
    
    public String getEquipmentStatus() {
    return equipmentStatus;
    }
    
    public void setEquipmentStatus(String equipmentStatus) {
    this.equipmentStatus =equipmentStatus;
    }
} 

