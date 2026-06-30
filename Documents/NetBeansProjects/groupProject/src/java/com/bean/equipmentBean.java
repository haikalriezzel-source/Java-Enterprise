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
    private String equipmentImage;
    private int equipmentID;
    private int totalQuantity;
    private int availableQuantity;
    private int maintenanceQuantity;
    private int damagedQuantity;

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
    
    public int getTotalQuantity() {
    return totalQuantity;
    }
    
    public void setTotalQuantity(int totalQuantity) {
    this.totalQuantity =totalQuantity;
    }
    
    public int getAvailableQuantity() {
    return availableQuantity;
    }
    
    public void setAvailableQuantity(int availableQuantity) {
    this.availableQuantity =availableQuantity;
    }
    
    public int getDamagedQuantity() {
    return damagedQuantity;
    }
    
    public void setDamagedQuantity(int damagedQuantity) {
    this.damagedQuantity =damagedQuantity;
    }
    
    public int getMaintenanceQuantity() {
    return maintenanceQuantity;
    }
    
    public void setMaintenanceQuantity(int maintenanceQuantity) {
    this.maintenanceQuantity =maintenanceQuantity;
    }
} 

