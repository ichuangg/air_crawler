package com.sc.crawler.entry;

import java.util.List;







public class HourRoot {
    public static class HourData {
        private String time;

        private String aqi;

        private String pm2_5;

        private String pm10;

        private String co;

        private String no2;

        private String o3;

        private String so2;

        private String complexindex;

        private String rank;

        private String primary_pollutant;

        private String temp;

        private String humi;

        private String windlevel;

        private String winddirection;

        private String weather;

        public void setTime(String time) {
            this.time = time;
        }

        public String getTime() {
            return this.time;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public String getAqi() {
            return this.aqi;
        }

        public void setPm2_5(String pm2_5) {
            this.pm2_5 = pm2_5;
        }

        public String getPm2_5() {
            return this.pm2_5;
        }

        public void setPm10(String pm10) {
            this.pm10 = pm10;
        }

        public String getPm10() {
            return this.pm10;
        }

        public void setCo(String co) {
            this.co = co;
        }

        public String getCo() {
            return this.co;
        }

        public void setNo2(String no2) {
            this.no2 = no2;
        }

        public String getNo2() {
            return this.no2;
        }

        public void setO3(String o3) {
            this.o3 = o3;
        }

        public String getO3() {
            return this.o3;
        }

        public void setSo2(String so2) {
            this.so2 = so2;
        }

        public String getSo2() {
            return this.so2;
        }

        public void setComplexindex(String complexindex) {
            this.complexindex = complexindex;
        }

        public String getComplexindex() {
            return this.complexindex;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getRank() {
            return this.rank;
        }

        public void setPrimary_pollutant(String primary_pollutant) {
            this.primary_pollutant = primary_pollutant;
        }

        public String getPrimary_pollutant() {
            return this.primary_pollutant;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getTemp() {
            return this.temp;
        }

        public void setHumi(String humi) {
            this.humi = humi;
        }

        public String getHumi() {
            return this.humi;
        }

        public void setWindlevel(String windlevel) {
            this.windlevel = windlevel;
        }

        public String getWindlevel() {
            return this.windlevel;
        }

        public void setWinddirection(String winddirection) {
            this.winddirection = winddirection;
        }

        public String getWinddirection() {
            return this.winddirection;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getWeather() {
            return this.weather;
        }
    }
    public static class Data {
        private int total;

        private List<HourData> rows;

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotal() {
            return this.total;
        }

        public void setRows(List<HourData> ZhenQiHour) {
            this.rows = ZhenQiHour;
        }

        public List<HourData> getRows() {
            return this.rows;
        }
    }
    public static class Result {
        private boolean success;

        private Data data;

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public boolean getSuccess() {
            return this.success;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public Data getData() {
            return this.data;
        }
    }
    private boolean success;

    private int errcode;

    private String errmsg;

    private Result result;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public int getErrcode() {
        return this.errcode;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getErrmsg() {
        return this.errmsg;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return this.result;
    }

}

