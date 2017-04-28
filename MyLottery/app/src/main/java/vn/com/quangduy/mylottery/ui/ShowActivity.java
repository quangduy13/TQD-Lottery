package vn.com.quangduy.mylottery.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.quangduy.mylottery.R;
import vn.com.quangduy.mylottery.dto.LotteryDTO;
import vn.com.quangduy.mylottery.service.RestClient;

public class ShowActivity extends AppCompatActivity {

    Button btnNew;
    Button btnChoose;
    TextView txtProvince;

    HashMap<String,HashMap>lists = new HashMap<String,HashMap>();
    List<LotteryDTO>lotteryDTOList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        getData();

        //get data into intent
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        final String local1 = bundle.getString("Local1");
        String fullName = bundle.getString("Local2");



        btnNew = (Button)findViewById(R.id.btnNew);
        btnChoose = (Button)findViewById(R.id.btnChoose);
        txtProvince = (TextView)findViewById(R.id.txtProvince);
        txtProvince.setText(fullName);

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData(local1);
            }
        });


    }
    private void getData(){//call API
        Call<HashMap> call = RestClient.getInstance().getLotteryService().getListLottery();
        call.enqueue(new Callback<HashMap>() {
            @Override
            public void onResponse(Call<HashMap> call, Response<HashMap> response) {
                if (response.isSuccessful()) {
                    lists =  response.body();
                }
            }

            @Override
            public void onFailure(Call<HashMap> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showData(String province){


        for(Map.Entry<String, HashMap>callProvince: lists.entrySet()){
            String nameProvince = callProvince.getKey();
            HashMap<String, HashMap>value = callProvince.getValue();

            if(nameProvince.equals(province)){
                int i = 1;
                for(Map.Entry<String,HashMap>callDate : value.entrySet()){

                    if(i == callProvince.getValue().size()){
                        String dateKey = callDate.getKey();
                        HashMap<String, List<String>> valuePrice = callDate.getValue();
                        LotteryDTO lotteryDTO = new LotteryDTO(nameProvince,dateKey,valuePrice);



                        //call fragment to show result lottery
                        DataFragment dataFragment = DataFragment.newInstance(lotteryDTO);
                        FragmentManager manager = getSupportFragmentManager();
                        FragmentTransaction ft = manager.beginTransaction();
                        ft.replace(R.id.content_view,dataFragment);
                        ft.commit();



                    }
                    else {
                        i++;
                    }



                }


            }


        }
    }
}
