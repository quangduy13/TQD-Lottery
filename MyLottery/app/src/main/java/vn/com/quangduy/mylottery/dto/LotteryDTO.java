package vn.com.quangduy.mylottery.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by quangduy on 28/04/2017.
 */

public class LotteryDTO implements Serializable {
    private String province;
    private String date;
    private HashMap<String,List<String>>prices;

    public LotteryDTO(String province, String date, HashMap<String, List<String>>prices){

        this.province = province;
        this.date = date;
        this.prices = prices;

    }
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public HashMap<String, List<String>> getPrices() {
        return prices;
    }

    public void setPrices(HashMap<String, List<String>> prices) {
        this.prices = prices;
    }
}
