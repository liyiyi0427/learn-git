package com.hexin.domain.hexin6;

public class Parameter {
    private String name;

    private Integer valueId;

    private String value;

    private String memo;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getValueId() {
        return this.valueId;
    }

    public void setValueId(Integer valueId) {
        this.valueId = valueId;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}