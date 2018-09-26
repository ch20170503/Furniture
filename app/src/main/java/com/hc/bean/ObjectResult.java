package com.hc.bean;

import java.io.Serializable;

public class ObjectResult implements Serializable {

        private String OrganizeName;
        private String RegPackage;
        private String HostFullName;
        private int SubNum;
        private String  FullName;

    public int getsSubNum() {
        return SubNum;
    }

    public void setsSubNum(int subNum) {
        SubNum = subNum;
    }

    public String getsFullName() {
        return FullName;
    }

    public void setsFullName(String fullName) {
        FullName = fullName;
    }

    public void setsOrganizeName(String OrganizeName) {
            this.OrganizeName = OrganizeName;
        }
        public String getsOrganizeName() {
            return OrganizeName;
        }

        public void setsRegPackage(String RegPackage) {
            this.RegPackage = RegPackage;
        }
        public String getsRegPackage() {
            return RegPackage;
        }

        public void setsHostFullName(String HostFullName) {
            this.HostFullName = HostFullName;
        }
        public String getsHostFullName() {
            return HostFullName;
        }
}
