package com.thuannluit.listkaraoke;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

import com.thuannluit.adapter.MusicAdapter;
import com.thuannluit.models.Music;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvBaiHatGoc;
    ArrayList<Music> dsBaiHatGoc;
    MusicAdapter adapterBaiHatGoc;

    ListView lvBaiHatYeuThich;
    ArrayList<Music> dsBaiHatYeuThich;
    MusicAdapter adapterBaiHatYeuThich;

    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabId.equalsIgnoreCase("t1")){
                    xuLyHienThiBaiHatGoc();
                }else if (tabId.equalsIgnoreCase("t2")){
                    xuLyHienThiBaiHatYeuThich();
                }
            }
        });
    }

    private void xuLyHienThiBaiHatGoc() {

    }

    private void xuLyHienThiBaiHatYeuThich() {
        dsBaiHatYeuThich.clear();
        for (Music music: dsBaiHatGoc){
            if (music.isThich()) dsBaiHatYeuThich.add(music);
        }
        adapterBaiHatYeuThich.notifyDataSetChanged();
    }

    private void addControls() {

        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tab1 = tabHost.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("",getResources().getDrawable(R.drawable.music));
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("",getResources().getDrawable(R.drawable.music_favorite));
        tabHost.addTab(tab2);

        lvBaiHatGoc = (ListView) findViewById(R.id.lvBaiHatGoc);
        dsBaiHatGoc = new ArrayList<>();
        adapterBaiHatGoc = new MusicAdapter(MainActivity.this,R.layout.item,dsBaiHatGoc);
        lvBaiHatGoc.setAdapter(adapterBaiHatGoc);

        lvBaiHatYeuThich = (ListView) findViewById(R.id.lvBaiHatYeuThich);
        dsBaiHatYeuThich = new ArrayList<>();
        adapterBaiHatYeuThich = new MusicAdapter(MainActivity.this,R.layout.item,dsBaiHatYeuThich);
        lvBaiHatYeuThich.setAdapter(adapterBaiHatYeuThich);

        giaLapBaiHat();
    }

    private void giaLapBaiHat() {
        dsBaiHatGoc.add(new Music("000001","Hồn Quê","Hiền Thục",true));
        dsBaiHatGoc.add(new Music("000002","Khó","Nam Cường",false));
        dsBaiHatGoc.add(new Music("000003","Ngày Xuân Long Phụng Sum Vầy","VMusic",true));
        dsBaiHatGoc.add(new Music("000004","Luật Cho Người Ra Đi","Yuki Huy Nam",false));
        dsBaiHatGoc.add(new Music("000005","Tận cùng của nỗi nhớ","Will",true));
        dsBaiHatGoc.add(new Music("000006","Những Kẻ Mộng Mơ","Noo Phước Thịnh",false));
        dsBaiHatGoc.add(new Music("000007","Người Tình Mùa Đông","Hà Anh Tuấn",true));
        dsBaiHatGoc.add(new Music("000008","Bán Duyên","Đình Dũng",false));
        dsBaiHatGoc.add(new Music("000009","Tướng Quân","Nhật Phong",true));
        dsBaiHatGoc.add(new Music("000010","Cao ốc 20","B RAY x DatG",false));
        dsBaiHatGoc.add(new Music("000011","Một Bước Yêu Vạn Dặm Đau","Mr Siro",true));
        dsBaiHatGoc.add(new Music("000012","Cô Thắm Không Về","Phát Hồ x JokeS Bii x Sinike ft. DinhLong",false));
        dsBaiHatGoc.add(new Music("000013","Ex's Hate Me","B Ray x Masew",true));
        dsBaiHatGoc.add(new Music("000014","Đã Lỡ Yêu Em Nhiều","JustaTee",false));
        dsBaiHatGoc.add(new Music("000015","Một Khúc Tương Tư","Anh Duy",true));
        dsBaiHatGoc.add(new Music("000016","So Close","Binz x Phương Ly",false));
        dsBaiHatGoc.add(new Music("000017","Sóng Gio","K-ICM x JACK",true));
        dsBaiHatGoc.add(new Music("000018","Chuyện Tình Tôi","Kay Trần x Nguyễn Khoa x Kass",false));
        dsBaiHatGoc.add(new Music("000019","Cô gái mét 52","HuyR ft. Tùng Viu",true));
        dsBaiHatGoc.add(new Music("000020","Độ Ta Không Độ Nàng","Thiên An",false));
        dsBaiHatGoc.add(new Music("000021","Du Hành Khắp Thiên Hạ","Thiên An",true));
        dsBaiHatGoc.add(new Music("000022","Cao ốc 20","B RAY x DatG",false));
        dsBaiHatGoc.add(new Music("000023","Tình Đơn Phương","Edward Duong Nguyen Ft Tùng Acoustic",true));
        dsBaiHatGoc.add(new Music("000024","Mưa Trên Cuộc Tình","Edward Duong Nguyen Ft Tùng Acoustic",false));

        adapterBaiHatGoc.notifyDataSetChanged();
    }
}
