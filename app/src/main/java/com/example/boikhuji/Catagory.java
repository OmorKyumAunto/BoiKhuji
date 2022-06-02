package com.example.boikhuji;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class Catagory extends AppCompatActivity {
    MeowBottomNavigation bottomNav;
    CardView offer,food,medical,adventure,story;


    private final int ID_Home = 1;
    private final int ID_Events = 2;
    private final int ID_Map= 3;
    private final int ID_Account=4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagory);
        bottomNav = findViewById(R.id.bottomNav);
        offer=findViewById(R.id.offerbook);
        food=findViewById(R.id.foodbook);
        medical=findViewById(R.id.medical);
        adventure=findViewById(R.id.adventurebook);
        story=findViewById(R.id.storybook);
        offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        bottomNav.add(new MeowBottomNavigation.Model(1,R.drawable.homeic));
        bottomNav.add(new MeowBottomNavigation.Model(2,R.drawable.cateic));
        bottomNav.add(new MeowBottomNavigation.Model(3,R.drawable.ebookicon));
        bottomNav.add(new MeowBottomNavigation.Model(4,R.drawable.menuic));



        bottomNav.setCount(2,"5");
        bottomNav.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                if(item.getId()==1){
                    startActivity(new Intent(getApplicationContext(),Dashboard.class));
                    return;
                }
                else if(item.getId()==2){

                    return;
                }
                else if(item.getId()==3){
                    startActivity(new Intent(getApplicationContext(), Ebook.class));
                    return;
                }
                else if(item.getId()==4){
                    startActivity(new Intent(getApplicationContext(),Menu.class));
                    return;
                }
                return;
            }
        });
        bottomNav.show(2,true);
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