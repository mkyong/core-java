package com.mkyong.io.csv.opencsv;

import com.opencsv.bean.CsvBindByPosition;

public class Country {

    @CsvBindByPosition(position = 0)
    private String startIp;

    @CsvBindByPosition(position = 1)
    private String endIp;

    @CsvBindByPosition(position = 2)
    private String countryCode;

    @CsvBindByPosition(position = 3)
    private String country;

    @Override
    public String toString() {
        return "Country{" +
                "startIp='" + startIp + '\'' +
                ", endIp='" + endIp + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public String getStartIp() {
        return startIp;
    }

    public void setStartIp(String startIp) {
        this.startIp = startIp;
    }

    public String getEndIp() {
        return endIp;
    }

    public void setEndIp(String endIp) {
        this.endIp = endIp;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
