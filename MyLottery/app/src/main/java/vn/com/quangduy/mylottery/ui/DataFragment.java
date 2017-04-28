package vn.com.quangduy.mylottery.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaControllerCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.BufferUnderflowException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.quangduy.mylottery.R;
import vn.com.quangduy.mylottery.dto.LotteryDTO;
import vn.com.quangduy.mylottery.service.RestClient;

/**
 * Created by quangduy on 28/04/2017.
 */

public class DataFragment extends Fragment {
    TextView txtNumberDB;
    TextView txtNumber1;
    TextView txtNumber2;
    TextView txtNumber3;
    TextView txtNumber4;
    TextView txtNumber5;
    TextView txtNumber6;
    TextView txtNumber7;
    TextView txtNumber8;
    TextView txtTime;
    private LotteryDTO lotteryDTO;
    private List<String>listsLottery = new ArrayList<>();
    public static DataFragment newInstance(LotteryDTO lotteryDTO) {
        DataFragment dataFragment = new DataFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("result", lotteryDTO);
        dataFragment.setArguments(bundle);
        return dataFragment;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy");
        String year = ft.format(date);
        View view = inflater.inflate(R.layout.fragment_data,container,false);

        txtNumberDB = (TextView)view.findViewById(R.id.txtNumberDB);
        txtNumber1 = (TextView)view.findViewById(R.id.txtNumber1);
        txtNumber2 = (TextView)view.findViewById(R.id.txtNumber2);
        txtNumber3 = (TextView)view.findViewById(R.id.txtNumber3);
        txtNumber4 = (TextView)view.findViewById(R.id.txtNumber4);
        txtNumber5 = (TextView)view.findViewById(R.id.txtNumber5);
        txtNumber6 = (TextView)view.findViewById(R.id.txtNumber6);
        txtNumber7 = (TextView)view.findViewById(R.id.txtNumber7);
        txtNumber8 = (TextView)view.findViewById(R.id.txtNumber8);
        txtTime = (TextView)view.findViewById(R.id.txtTime);


        txtNumberDB.setText(showPrice().get(8));
        txtNumber1.setText(showPrice().get(0));
        txtNumber2.setText(showPrice().get(1));
        txtNumber3.setText(showPrice().get(2));
        txtNumber4.setText(showPrice().get(3));
        txtNumber5.setText(showPrice().get(4));
        txtNumber6.setText(showPrice().get(5));
        txtNumber7.setText(showPrice().get(6));
        txtNumber8.setText(showPrice().get(7));
        txtTime.setText(showPrice().get(9) +"-"+year);
        return view;
    }

    private List<String> showPrice(){


        lotteryDTO = (LotteryDTO)getArguments().getSerializable("result");
        List<String>price;
        for(int i =0; i<lotteryDTO.getPrices().size();i++){

            if(i==8){
                price = lotteryDTO.getPrices().get("DB");
            }
            else{
                price = lotteryDTO.getPrices().get(Integer.toString(i+1));
            }

            String priceLottery = price.get(0);
            if(price.size()>1){
                for(int j = 1 ;j < price.size(); j++){
                    priceLottery = priceLottery +" - "+ price.get(j);
                }
            }
            listsLottery.add(priceLottery);
        }
        String date = lotteryDTO.getDate();
        listsLottery.add(date);
    return listsLottery;

    }

}
