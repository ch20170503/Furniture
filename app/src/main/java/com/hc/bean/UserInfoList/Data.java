package com.hc.bean.UserInfoList;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable {
    private List<ListData> ListData;
    private int Total;
    public void setsListData(List<ListData> ListData) {
        this.ListData = ListData;
    }
    public List<ListData> getsListData() {
        return ListData;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }
    public int getTotal() {
        return Total;
    }
}
