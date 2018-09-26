package com.hc.bean;

public class UserSelect {
    private String FilterGroup;
    private int PageIndex;
    private int PageSize;
    private String SortField;
    private String SortOrder;
    public void setFilterGroup(String FilterGroup) {
        this.FilterGroup = FilterGroup;
    }
    public String getFilterGroup() {
        return FilterGroup;
    }

    public void setPageIndex(int PageIndex) {
        this.PageIndex = PageIndex;
    }
    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageSize(int PageSize) {
        this.PageSize = PageSize;
    }
    public int getPageSize() {
        return PageSize;
    }

    public void setSortField(String SortField) {
        this.SortField = SortField;
    }
    public String getSortField() {
        return SortField;
    }

    public void setSortOrder(String SortOrder) {
        this.SortOrder = SortOrder;
    }
    public String getSortOrder() {
        return SortOrder;
    }
}
