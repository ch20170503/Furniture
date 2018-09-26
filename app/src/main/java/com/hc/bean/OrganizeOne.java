package com.hc.bean;

import java.io.Serializable;

public class OrganizeOne implements Serializable {

        private String Id;
        private String ParentId;
        private String TelePhone;
        private String Fax;
        private String FullName;
        private String Email;
        private String Country;
        private String Province;
        private String City;
        private String County;
        private String Address;
        private String Remark;
        private int SortCode;
        private String CreatedTime;
        private String CreatorUserId;
        private String LastUpdatedTime;
        private String LastUpdatorUserId;
        private String OrganizeLogoPath;

        public String getsOrganizeLogo() {
            return OrganizeLogoPath;
        }

        public void setsOrganizeLogo(String organizeLogo) {
            OrganizeLogoPath = organizeLogo;
        }

    public void setsId(String Id) {
            this.Id = Id;
        }
        public String getsId() {
            return Id;
        }

        public void setsParentId(String ParentId) {
            this.ParentId = ParentId;
        }
        public String getsParentId() {
            return ParentId;
        }

        public void setsTelePhone(String TelePhone) {
            this.TelePhone = TelePhone;
        }
        public String getsTelePhone() {
            return TelePhone;
        }

        public void setsFax(String Fax) {
            this.Fax = Fax;
        }
        public String getsFax() {
            return Fax;
        }

        public void setsFullName(String FullName) {
            this.FullName = FullName;
        }
        public String getsFullName() {
            return FullName;
        }

        public void setsEmail(String Email) {
            this.Email = Email;
        }
        public String getsEmail() {
            return Email;
        }

        public void setsCountry(String Country) {
            this.Country = Country;
        }
        public String getsCountry() {
            return Country;
        }

        public void setsProvince(String Province) {
            this.Province = Province;
        }
        public String getsProvince() {
            return Province;
        }

        public void setsCity(String City) {
            this.City = City;
        }
        public String getsCity() {
            return City;
        }

        public void setsCounty(String County) {
            this.County = County;
        }
        public String getsCounty() {
            return County;
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

        public void setsSortCode(int SortCode) {
            this.SortCode = SortCode;
        }
        public int getsSortCode() {
            return SortCode;
        }

        public void setsCreatedTime(String CreatedTime) {
            this.CreatedTime = CreatedTime;
        }
        public String getsCreatedTime() {
            return CreatedTime;
        }

        public void setsCreatorUserId(String CreatorUserId) {
            this.CreatorUserId = CreatorUserId;
        }
        public String getsCreatorUserId() {
            return CreatorUserId;
        }

        public void setsLastUpdatedTime(String LastUpdatedTime) {
            this.LastUpdatedTime = LastUpdatedTime;
        }
        public String getsLastUpdatedTime() {
            return LastUpdatedTime;
        }

        public void setsLastUpdatorUserId(String LastUpdatorUserId) {
            this.LastUpdatorUserId = LastUpdatorUserId;
        }
        public String getsLastUpdatorUserId() {
            return LastUpdatorUserId;
        }
}
