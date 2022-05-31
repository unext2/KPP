package com.example.demo;

public class DataClass {
    private final String year;
    private final String day;

    public DataClass(String year, String day){
        this.year = year;
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    public String getYear() {
        return year;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null) return false;
        if(getClass()!=o.getClass()) return false;
        DataClass dataClass = (DataClass) o;
        return ((this.year.equals(((DataClass) o).year) && this.day == ((DataClass) o).day));
    }
    @Override
    public int hashCode(){
        int res = 1;
        res = 31*res + year.hashCode();
        res = 31*res + day.hashCode();
        return res;
    }
}
