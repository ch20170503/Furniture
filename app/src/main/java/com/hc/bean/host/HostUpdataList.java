package com.hc.bean.host;

import java.io.Serializable;

public class HostUpdataList  implements Serializable {
    /**
     * Copyright 2018 bejson.com
     */


    /**
     * Auto-generated: 2018-01-15 11:2:27
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */

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
        private String DataItemDetail_Id;
        private String Organize_Id;

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getId() {
            return Id;
        }

        public void setPhoneNum(String PhoneNum) {
            this.PhoneNum = PhoneNum;
        }

        public String getPhoneNum() {
            return PhoneNum;
        }

        public void setPhoneServerPass(String PhoneServerPass) {
            this.PhoneServerPass = PhoneServerPass;
        }

        public String getPhoneServerPass() {
            return PhoneServerPass;
        }

        public void setFullName(String FullName) {
            this.FullName = FullName;
        }

        public String getFullName() {
            return FullName;
        }

        public void setRegPackage(String RegPackage) {
            this.RegPackage = RegPackage;
        }

        public String getRegPackage() {
            return RegPackage;
        }

        public void setHeartBag(String HeartBag) {
            this.HeartBag = HeartBag;
        }

        public String getHeartBag() {
            return HeartBag;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getAddress() {
            return Address;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public String getRemark() {
            return Remark;
        }

        public void setPhoneOperator(int PhoneOperator) {
            this.PhoneOperator = PhoneOperator;
        }

        public int getPhoneOperator() {
            return PhoneOperator;
        }

        public void setPhoneResidualFlow(int PhoneResidualFlow) {
            this.PhoneResidualFlow = PhoneResidualFlow;
        }

        public int getPhoneResidualFlow() {
            return PhoneResidualFlow;
        }

        public void setIsEnergySwitch(boolean IsEnergySwitch) {
            this.IsEnergySwitch = IsEnergySwitch;
        }

        public boolean getIsEnergySwitch() {
            return IsEnergySwitch;
        }

        public void setIsLocked(boolean IsLocked) {
            this.IsLocked = IsLocked;
        }

        public boolean getIsLocked() {
            return IsLocked;
        }

        public void setDataItemDetail_Id(String DataItemDetail_Id) {
            this.DataItemDetail_Id = DataItemDetail_Id;
        }

        public String getDataItemDetail_Id() {
            return DataItemDetail_Id;
        }

        public void setOrganize_Id(String Organize_Id) {
            this.Organize_Id = Organize_Id;
        }

        public String getOrganize_Id() {
            return Organize_Id;
        }

    }
