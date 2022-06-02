package com.example.boikhuji;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class Ebook extends AppCompatActivity {
    MeowBottomNavigation bottomNav;

    private final int ID_Home = 1;
    private final int ID_Events = 2;
    private final int ID_Map= 3;
    private final int ID_Account=4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook);
        bottomNav = findViewById(R.id.bottomNav);

        bottomNav.add(new MeowBottomNavigation.Model(1,R.drawable.homeic));
        bottomNav.add(new MeowBottomNavigation.Model(2,R.drawable.cateic));
        bottomNav.add(new MeowBottomNavigation.Model(3,R.drawable.ebookicon));
        bottomNav.add(new MeowBottomNavigation.Model(4,R.drawable.menuic));



        bottomNav.setCount(2,"4");
        bottomNav.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                if(item.getId()==1){
                    startActivity(new Intent(getApplicationContext(),Dashboard.class));
                    return;
                }
                else if(item.getId()==2){
                    startActivity(new Intent(getApplicationContext(),Catagory.class));
                    return;
                }
                else if(item.getId()==3){
                    return;
                }
                else if(item.getId()==4){
                    startActivity(new Intent(getApplicationContext(),Menu.class));
                    return;
                }
                return;
            }
        });
        bottomNav.show(3,true);
        bottomNav.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
            }
        });

        bottomNav.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

            }
        });
    }
}