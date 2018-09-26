package com.hc.bean.host;

import java.io.Serializable;

public class ListData  implements Serializable {
    private int Ids;

    public int getsIds() {
        return Ids;
    }

    public void setsIds(int ids) {
        Ids = ids;
    }

    private String Id;
    private String PhoneNum;
    private String PhoneServerPass;
    private String FullName;
    private String RegPackage;
    private String HeartBag;
    private String Address;
    private String Remark;
    private int PhoneOperator;
    private int PhoneResidualFlow;
    private boolean IsEnergySwitch;
    private boolean IsLocked;
    private String DataItemDetailName;
    private int DataItemDetailIndex;
    private String CreatedTime;
    private String OrganizeName;
    private String OrganId;

    public String getsOrganId() {
        return OrganId;
    }

    public void setsOrganId(String organId) {
        OrganId = organId;
    }

    public void setsId(String Id) {
        this.Id = Id;
    }
    public String getsId() {
        return Id;
    }

    public void setsPhoneNum(String PhoneNum) {
        this.PhoneNum = PhoneNum;
    }
    public String getsPhoneNum() {
        return PhoneNum;
    }

    public void setsPhoneServerPass(String PhoneServerPass) {
        this.PhoneServerPass = PhoneServerPass;
    }
    public String getsPhoneServerPass() {
        return PhoneServerPass;
    }

    public void setsFullName(String FullName) {
        this.FullName = FullName;
    }
    public String getsFullName() {
        return FullName;
    }

    public void setsRegPackage(String RegPackage) {
        this.RegPackage = RegPackage;
    }
    public String getsRegPackage() {
        return RegPackage;
    }

    public void setsHeartBag(String HeartBag) {
        this.HeartBag = HeartBag;
    }
    public String getsHeartBag() {
        return HeartBag;
    }

    public void setsAddress(String Address) {
        this.Address = Address;
    }
    public String getsAddress() {
        return Address;
    }

    public void setsRemark(String Remark) {
        this.Remark = Remark;
    }
    public String getsRemark() {
        return Remark;
    }

    public void setsPhoneOperator(int PhoneOperator) {
        this.PhoneOperator = PhoneOperator;
    }
    public int getsPhoneOperator() {
        return PhoneOperator;
    }

    public void setsPhoneResidualFlow(int PhoneResidualFlow) {
        this.PhoneResidualFlow = PhoneResidualFlow;
    }
    public int getsPhoneResidualFlow() {
        return PhoneResidualFlow;
    }

    public void setsIsEnergySwitch(boolean IsEnergySwitch) {
        this.IsEnergySwitch = IsEnergySwitch;
    }
    public boolean getsIsEnergySwitch() {
        return IsEnergySwitch;
    }

    public void setsIsLocked(boolean IsLocked) {
        this.IsLocked = IsLocked;
    }
    public boolean getsIsLocked() {
        return IsLocked;
    }

    public void setsDataItemDetailName(String DataItemDetailName) {
        this.DataItemDetailName = DataItemDetailName;
    }
    public String getsDataItemDetailName() {
        return DataItemDetailName;
    }

    public void setsDataItemDetailIndex(int DataItemDetailIndex) {
        this.DataItemDetailIndex = DataItemDetailIndex;
    }
    public int getsDataItemDetailIndex() {
        return DataItemDetailIndex;
    }

    public void setsCreatedTime(String CreatedTime) {
        this.CreatedTime = CreatedTime;
    }
    public String getsCreatedTime() {
        return CreatedTime;
    }

    public void setsOrganizeName(String OrganizeName) {
        this.OrganizeName = OrganizeName;
    }
    public String getsOrganizeName() {
        return OrganizeName;
    }
}
