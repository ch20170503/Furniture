package com.hc.bean;

import java.io.Serializable;

public class ResultInfo implements Serializable {
        private String Message;
        private boolean Successed;
        private int ResultType;
        private Data Data;
        public void setsMessage(String Message) {
            this.Message = Message;
        }
        public String getsMessage() {
            return Message;
        }

        public void setsSuccessed(boolean Successed) {
            this.Successed = Successed;
        }
        public boolean getsSuccessed() {
            return Successed;
        }

        public void setsResultType(int ResultType) {
            this.ResultType = ResultType;
        }
        public int getsResultType() {
            return ResultType;
        }

        public void setsDataInfo(Data Data) {
            this.Data = Data;
        }
        public Data getsDataInfo() {
            return Data;
        }
}
