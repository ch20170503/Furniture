package com.hc.bean.host;

import java.io.Serializable;

public class HostList  implements Serializable {
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

    public void setsData(Data Data) {
        this.Data = Data;
    }
    public Data getsData() {
        return Data;
    }

}
