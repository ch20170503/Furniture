package com.hc.bean;

import java.io.Serializable;

public class UserMany implements Serializable {

    private int sId;

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    private String RealName;
        private String NickName;
        private String HeadIconPath;
        private String Email;
        private boolean EmailConfirmed;
        private String WeChat;
        private boolean WeChatConfirmed;
        private String PhoneNumber;
        private boolean PhoneNumberConfirmed;
        private String Remark;
        private int Sex;
        private boolean IsSysReceive;
        private String Language;
        private String Theme;
        private boolean IsAlarm;
        private String CreatedTime;
        private String CreatorUserId;
        private String LastUpdatedTime;
        private String LastUpdatorUserId;
        private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setsRealName(String RealName) {
            this.RealName = RealName;
        }
        public String getsRealName() {
            return RealName;
        }

        public void setsNickName(String NickName) {
            this.NickName = NickName;
        }
        public String getsNickName() {
            return NickName;
        }

        public void setsHeadIcon(String HeadIcon) {
            this.HeadIconPath = HeadIcon;
        }
        public String getsHeadIcon() {
            return HeadIconPath;
        }

        public void setsEmail(String Email) {
            this.Email = Email;
        }
        public String getsEmail() {
            return Email;
        }

        public void setsEmailConfirmed(boolean EmailConfirmed) {
            this.EmailConfirmed = EmailConfirmed;
        }
        public boolean getsEmailConfirmed() {
            return EmailConfirmed;
        }

        public void setsWeChat(String WeChat) {
            this.WeChat = WeChat;
        }
        public String getsWeChat() {
            return WeChat;
        }

        public void setsWeChatConfirmed(boolean WeChatConfirmed) {
            this.WeChatConfirmed = WeChatConfirmed;
        }
        public boolean getsWeChatConfirmed() {
            return WeChatConfirmed;
        }

        public void setsPhoneNumber(String PhoneNumber) {
            this.PhoneNumber = PhoneNumber;
        }
        public String getsPhoneNumber() {
            return PhoneNumber;
        }

        public void setsPhoneNumberConfirmed(boolean PhoneNumberConfirmed) {
            this.PhoneNumberConfirmed = PhoneNumberConfirmed;
        }
        public boolean getsPhoneNumberConfirmed() {
            return PhoneNumberConfirmed;
        }

        public void setsRemark(String Remark) {
            this.Remark = Remark;
        }
        public String getsRemark() {
            return Remark;
        }

        public void setsSex(int Sex) {
            this.Sex = Sex;
        }
        public int getsSex() {
            return Sex;
        }

        public void setsIsSysReceive(boolean IsSysReceive) {
            this.IsSysReceive = IsSysReceive;
        }
        public boolean getsIsSysReceive() {
            return IsSysReceive;
        }

        public void setsLanguage(String Language) {
            this.Language = Language;
        }
        public String getsLanguage() {
            return Language;
        }

        public void setsTheme(String Theme) {
            this.Theme = Theme;
        }
        public String getsTheme() {
            return Theme;
        }

        public void setsIsAlarm(boolean IsAlarm) {
            this.IsAlarm = IsAlarm;
        }
        public boolean getsIsAlarm() {
            return IsAlarm;
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
