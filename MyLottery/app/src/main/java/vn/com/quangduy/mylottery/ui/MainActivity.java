package vn.com.quangduy.mylottery.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import vn.com.quangduy.mylottery.R;


public class MainActivity extends AppCompatActivity {
    private String NAME[]={"AG","BD","BL","BP","BTH","CM","CT","HCM"};
    ListView lvLocal;
    private List<String>placeDTOList = new ArrayList<>();
    ListLocalAdapter listLocalAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();
    }
    private void getData(){
        lvLocal = (ListView)findViewById(R.id.lvLocal);

        placeDTOList.add("AN GIANG");
        placeDTOList.add("BÌNH DƯƠNG");
        placeDTOList.add("BẠC LIÊU");
        placeDTOList.add("BÌNH PHƯỚC");
        placeDTOList.add("BÌNH THUẬN");
        placeDTOList.add("CÀ MAU");
        placeDTOList.add("CẦN THƠ");
        placeDTOList.add("HỒ CHÍ MINH");

        listLocalAdapter = new ListLocalAdapter(getApplication(),placeDTOList);
        lvLocal.setAdapter(listLocalAdapter);
        lvLocal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                //transmit code of local to ShowActivity
                Bundle bundle = new Bundle();
                bundle.putString("Local1",NAME[position]);
                bundle.putString("Local2",placeDTOList.get(position));
                intent.putExtra("data",bundle);

                startActivity(intent);
            }
        });
    }
    public class ListLocalAdapter extends ArrayAdapter{
        private LayoutInflater inflater;

        private Context context;

        private List<String>lists;


        public ListLocalAdapter(Context context,List<String>lists){
            super(context,0,lists);
            this.context = context;
            this.lists = lists;
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            ItemHolder itemHolder;

            if(convertView == null){
                convertView = inflater.inflate(R.layout.list_item,parent,false);
                itemHolder = new ItemHolder(convertView);
                convertView.setTag(itemHolder);
            }
            else{
                itemHolder = (ItemHolder)convertView.getTag();
            }
            itemHolder.txtName = (TextView)convertView.findViewById(R.id.txtName);
            itemHolder.txtIcon =(TextView)convertView.findViewById(R.id.txtIcon);
            for (int i =0; i<lists.size();i++){
                    if(position == i){
                        itemHolder.txtName.setText(lists.get(i));
                        itemHolder.txtIcon.setText(NAME[i]);
                    }
            }

            return convertView;
        }
    }
    public class ItemHolder{
        TextView txtName;
        TextView txtIcon;
        public ItemHolder(View v){
            ButterKnife.bind(this,v);
        }

    }

}
