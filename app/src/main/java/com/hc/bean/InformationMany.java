package com.hc.bean;

import java.io.Serializable;

public class InformationMany implements Serializable {

    private String Id;
    private String CreatedTime;
    private String TypeName;
    private int TypeIndex;
    private boolean IsReaded;
    private String ObjectId;
    private ObjectResult ObjectResult;
    private int ids;
    public int getsIds() {
        return ids;
    }

    public void setsIds(int ids) {
        this.ids = ids;
    }

    public void setsId(String Id) {
        this.Id = Id;
    }
    public String getsId() {
        return Id;
    }

    public void setsCreatedTime(String CreatedTime) {
        this.CreatedTime = CreatedTime;
    }
    public String getsCreatedTime() {
        return CreatedTime;
    }

    public void setsTypeName(String TypeName) {
        this.TypeName = TypeName;
    }
    public String getsTypeName() {
        return TypeName;
    }

    public void setsTypeIndex(int TypeIndex) {
        this.TypeIndex = TypeIndex;
    }
    public int getsTypeIndex() {
        return TypeIndex;
    }

    public void setsIsReaded(boolean IsReaded) {
        this.IsReaded = IsReaded;
    }
    public boolean getsIsReaded() {
        return IsReaded;
    }

    public void setsObjectId(String ObjectId) {
        this.ObjectId = ObjectId;
    }
    public String getsObjectId() {
        return ObjectId;
    }

    public void setsObjectResult(ObjectResult ObjectResult) {
        this.ObjectResult = ObjectResult;
    }
    public ObjectResult getsObjectResult() {
        return ObjectResult;
    }

}