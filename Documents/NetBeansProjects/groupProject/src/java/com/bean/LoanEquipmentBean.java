/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;
import java.sql.Date;

/**
 *
 * @author haikalriez
 */
public class LoanEquipmentBean {

    private String studentID;
    private int equipmentID;
    private int loanID;
    private String equipmentName;
    private Date startDate;
    private Date endDate;
    private int loanQuantity;
    private String loanStatus;
    private String studentName;

    public LoanEquipmentBean() {
    }

    public String getStudentID() {
    return studentID;
    }

    public void setStudentID(String studentID) {
    this.studentID = studentID;
    }
    
    public int getEquipmentID() {
    return equipmentID;
    }

    public void setEquipmentID(int equipmentID) {
    this.equipmentID = equipmentID;
    }
    
    public int getLoanID() {
        return loanID;
    }

    public void setLoanID(int loanID) {
        this.loanID = loanID;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getLoanQuantity() {
        return loanQuantity;
    }

    public void setLoanQuantity(int loanQuantity) {
        this.loanQuantity = loanQuantity;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }
    
    public String getStudentName(){
        return studentName;
    }
    public void setStudentName(String studentName){
        this.studentName=studentName;
    }
}
