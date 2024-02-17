package com.phone.data.Entity;

public class ReversalTable {
    private String hour;
    private Integer count;

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ReversalTable{" +
                "hour='" + hour + '\'' +
                ", count=" + count +
                '}';
    }
}
