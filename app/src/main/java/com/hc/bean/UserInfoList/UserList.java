package com.hc.bean.UserInfoList;

import java.io.Serializable;

public class UserList implements Serializable {

        private String Message;
        private boolean Successed;
        private int ResultType;
        private Data Data;
        public void setMessage(String Message) {
            this.Message = Message;
        }
        public String getMessage() {
            return Message;
        }

        public void setSuccessed(boolean Successed) {
            this.Successed = Successed;
        }
        public boolean getSuccessed() {
            return Successed;
        }

        public void setResultType(int ResultType) {
            this.ResultType = ResultType;
        }
        public int getResultType() {
            return ResultType;
        }

        public void setsData(Data Data) {
            this.Data = Data;
        }
        public Data getsData() {
            return Data;
        }
}
