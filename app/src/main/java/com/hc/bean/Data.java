package com.hc.bean;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable {

    private String Id;
    private String UserName;
    private int LoginCount;
    private String FirstVisitTime;
    private String PreviousVisitTime;
    private String LastVisitTime;
    private boolean IsAdministrator;
    private boolean IsLocked;
    private String PermissionList;
    private int Level;
    private OrganizeOne OrganizeOne;
    private List<UserMany> UserMany;
    private List<InformationMany> InformationMany;
    public void setsId(String Id) {
        this.Id = Id;
    }
    public String getsId() {
        return Id;
    }

    public void setsUserName(String UserName) {
        this.UserName = UserName;
    }
    public String getsUserName() {
        return UserName;
    }

    public void setsLoginCount(int LoginCount) {
        this.LoginCount = LoginCount;
    }
    public int getsLoginCount() {
        return LoginCount;
    }

    public void setsFirstVisitTime(String FirstVisitTime) {
        this.FirstVisitTime = FirstVisitTime;
    }
    public String getsFirstVisitTime() {
        return FirstVisitTime;
    }

    public void setsPreviousVisitTime(String PreviousVisitTime) {
        this.PreviousVisitTime = PreviousVisitTime;
    }
    public String getsPreviousVisitTime() {
        return PreviousVisitTime;
    }

    public void setsLastVisitTime(String LastVisitTime) {
        this.LastVisitTime = LastVisitTime;
    }
    public String getsLastVisitTime() {
        return LastVisitTime;
    }

    public void setsIsAdministrator(boolean IsAdministrator) {
        this.IsAdministrator = IsAdministrator;
    }
    public boolean getsIsAdministrator() {
        return IsAdministrator;
    }

    public void setsIsLocked(boolean IsLocked) {
        this.IsLocked = IsLocked;
    }
    public boolean getsIsLocked() {
        return IsLocked;
    }

    public void setsPermissionList(String PermissionList) {
        this.PermissionList = PermissionList;
    }
    public String getsPermissionList() {
        return PermissionList;
    }

    public void setsLevel(int Level) {
        this.Level = Level;
    }
    public int getsLevel() {
        return Level;
    }

    public void setsOrganizeOne(OrganizeOne OrganizeOne) {
        this.OrganizeOne = OrganizeOne;
    }
    public OrganizeOne getsOrganizeOne() {
        return OrganizeOne;
    }

    public void setsUserMany(List<UserMany> UserMany) {
        this.UserMany = UserMany;
    }
    public List<UserMany> getsUserMany() {
        return UserMany;
    }

    public void setsInformationMany(List<InformationMany> InformationMany) {
        this.InformationMany = InformationMany;
    }
    public List<InformationMany> getsInformationMany() {
        return InformationMany;
    }
}
