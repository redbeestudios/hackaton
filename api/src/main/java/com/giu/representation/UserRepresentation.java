package com.giu.representation;

import java.util.List;

/**
 * Created by martin on 22/07/16.
 */
public class UserRepresentation {

    private String cn;
    private String employeeID;
    private String mail;
    private String alternativeMail;
    private List<String> otherMailbox;
    private String employeeNumber;
    private String telephoneNumber;
    private String Name;
    private String lastName;
    private String workRelationship;
    private String newPassword;
    private String newPasswordCheck;
    private String oldPassword;
    private String streetAddress;
    private String dgtal;
    private boolean isTransfer;
    private String dn;
    private String state;
    private String employeeType;
    private List <String> membersOf;

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public String getDgtal() {
        return dgtal;
    }

    public void setDgtal(String dgtal) {
        this.dgtal = dgtal;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public void setMail(String mail){
        this.mail = mail;
    }

    public void setEmployeeNumber(String employeeNumber){
        this.employeeNumber = employeeNumber;
    }

    public void setTelephoneNumber(String telephoneNumber ){
        this.telephoneNumber = telephoneNumber;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<String> getOtherMailbox() {
        return otherMailbox;
    }

    public void setOtherMailbox(List<String> otherMailbox) {
        this.otherMailbox = otherMailbox;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordCheck() {
        return newPasswordCheck;
    }

    public void setNewPasswordCheck(String newPasswordCheck) {
        this.newPasswordCheck = newPasswordCheck;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getWorkRelationship() {
        return workRelationship;
    }

    public void setWorkRelationship(String workRelationship) {
        this.workRelationship = workRelationship;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public boolean getIsTransfer() {
        return isTransfer;
    }

    public void setIsTransfer(boolean transfer) {
        isTransfer = transfer;
    }

    public void setMembersOf(List<String> membersOf) {
        this.membersOf = membersOf;
    }

    public List<String> getMembersOf() {
        return membersOf;
    }

    public String getAlternativeMail() {
        return alternativeMail;
    }

    public void setAlternativeMail(String alternativeMail) {
        this.alternativeMail = alternativeMail;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }
}
