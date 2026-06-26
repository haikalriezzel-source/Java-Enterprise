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
public class DashboardBean {

    private int totalBookings;
    private int activeLoans;
    private int pendingRequests;
    private int returnedItems;
    
    public DashboardBean(){
    }

    public int getTotalBookings() {
        return totalBookings;
    }

    public void setTotalBookings(int totalBookings) {
        this.totalBookings = totalBookings;
    }

    public int getActiveLoans() {
        return activeLoans;
    }

    public void setActiveLoans(int activeLoans) {
        this.activeLoans = activeLoans;
    }

    public int getPendingRequests() {
        return pendingRequests;
    }

    public void setPendingRequests(int pendingRequests) {
        this.pendingRequests = pendingRequests;
    }

    public int getReturnedItems() {
        return returnedItems;
    }

    public void setReturnedItems(int returnedItems) {
        this.returnedItems = returnedItems;
    }
}
