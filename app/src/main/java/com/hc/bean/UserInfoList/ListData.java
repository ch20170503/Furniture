package com.hc.bean.UserInfoList;

import java.io.Serializable;

public class ListData implements Serializable {
    private int ids;

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    private String Id;
    private String UserName;
    private String NickName;
    private String RealName;
    private String Email;
    private String PhoneNumber;
    private boolean IsLocked;
    private boolean LockoutEnabled;
    private String OrganizeName;
    private String CreatedTime;

    public String getsId() {
        return Id;
    }

    public void setsId(String id) {
        Id = id;
    }

    public void setsUserName(String UserName) {
        this.UserName = UserName;
    }
    public String getsUserName() {
        return UserName;
    }

    public void setsNickName(String NickName) {
        this.NickName = NickName;
    }
    public String getsNickName() {
        return NickName;
    }

    public void setsRealName(String RealName) {
        this.RealName = RealName;
    }
    public String getsRealName() {
        return RealName;
    }

    public void setsEmail(String Email) {
        this.Email = Email;
    }
    public String getsEmail() {
        return Email;
    }

    public void setsPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }
    public String getsPhoneNumber() {
        return PhoneNumber;
    }

    public void setsIsLocked(boolean IsLocked) {
        this.IsLocked = IsLocked;
    }
    public boolean getsIsLocked() {
        return IsLocked;
    }

    public void setsLockoutEnabled(boolean LockoutEnabled) {
        this.LockoutEnabled = LockoutEnabled;
    }
    public boolean getsLockoutEnabled() {
        return LockoutEnabled;
    }

    public void setsOrganizeName(String OrganizeName) {
        this.OrganizeName = OrganizeName;
    }
    public String getsOrganizeName() {
        return OrganizeName;
    }

    public void setsCreatedTime(String CreatedTime) {
        this.CreatedTime = CreatedTime;
    }
    public String getsCreatedTime() {
        return CreatedTime;
    }
}
