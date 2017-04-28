package vn.com.quangduy.mylottery.service;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import vn.com.quangduy.mylottery.dto.LotteryDTO;

/**
 * Created by quangduy on 28/04/2017.
 */

public interface LotteryService {

    @GET("/kqxsmn")
    Call<HashMap>getListLottery();

}
