/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;
import java.sql.Date;
import java.sql.Time;
/**
 *
 * @author haikalriez
 */

public class facilityBookingBean {


private int bookingID;
private String studentID;
private int facilityID;
private String facilityName;
private Date bookingDate;
private Time startTime;
private Time endTime;
private String purpose;
private int numberOfParticipants;
private String bookingStatus;

public facilityBookingBean() {
}

public int getBookingID() {
    return bookingID;
}

public void setBookingID(int bookingID) {
    this.bookingID = bookingID;
}

public String getStudentID() {
    return studentID;
}

public void setStudentID(String studentID) {
    this.studentID = studentID;
}

public int getFacilityID() {
    return facilityID;
}

public void setFacilityID(int facilityID) {
    this.facilityID = facilityID;
}

public String getFacilityName() {
    return facilityName;
}

public void setFacilityName(String facilityName) {
    this.facilityName = facilityName;
}

public Date getBookingDate() {
    return bookingDate;
}

public void setBookingDate(Date bookingDate) {
    this.bookingDate = bookingDate;
}

public Time getStartTime() {
    return startTime;
}

public void setStartTime(Time startTime) {
    this.startTime = startTime;
}

public Time getEndTime() {
    return endTime;
}

public void setEndTime(Time endTime) {
    this.endTime = endTime;
}

public String getPurpose() {
    return purpose;
}

public void setPurpose(String purpose) {
    this.purpose = purpose;
}

public int getNumberOfParticipants() {
    return numberOfParticipants;
}

public void setNumberOfParticipants(int numberOfParticipants) {
    this.numberOfParticipants = numberOfParticipants;
}

public String getBookingStatus() {
    return bookingStatus;
}

public void setBookingStatus(String bookingStatus) {
    this.bookingStatus = bookingStatus;
}


}

