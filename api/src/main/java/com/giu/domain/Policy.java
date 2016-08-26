package com.giu.domain;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

/**
 * Created by martin on 08/08/16.
 */
@Entry(objectClasses = { "container","groupPolicyContainer", "top" })
public final class Policy {


    @Id
    private Name id;

    @Attribute(name = "cn")
    @DnAttribute(value = "cn", index = 0)
    private String cn;

    @Attribute(name = "displayName")
    private  String displayName;

    @Attribute(name = "gPCMachineExtensionNames")
    private  String machineExtensionNames;

    @Attribute(name = "gPCUserExtensionNames")
    private  String userExtensionNames;

    @Attribute(name = "name")
    private  String name;

    @Attribute(name = "objectGUID")
    private  String objectGUID;



    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getMachineExtensionNames() {
        return machineExtensionNames;
    }

    public void setMachineExtensionNames(String machineExtensionNames) {
        this.machineExtensionNames = machineExtensionNames;
    }

    public String getUserExtensionNames() {
        return userExtensionNames;
    }

    public void setUserExtensionNames(String userExtensionNames) {
        this.userExtensionNames = userExtensionNames;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjectGUID() {
        return objectGUID;
    }

    public void setObjectGUID(String objectGUID) {
        this.objectGUID = objectGUID;
    }

    public Name getId() {
        return id;
    }

    public void setId(Name id) {
        this.id = id;
    }
}
