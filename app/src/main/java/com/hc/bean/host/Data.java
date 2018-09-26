package com.hc.bean.host;

import java.io.Serializable;
import java.util.List;

public class Data  implements Serializable {
    private List<ListData> ListData;
    private int Total;
    public void setsListData(List<ListData> ListData) {
        this.ListData = ListData;
    }
    public List<ListData> getsListData() {
        return ListData;
    }

    public void setsTotal(int Total) {
        this.Total = Total;
    }
    public int getsTotal() {
        return Total;
    }
}
