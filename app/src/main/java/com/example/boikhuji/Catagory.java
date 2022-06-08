package com.example.boikhuji;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class Catagory extends AppCompatActivity {
    MeowBottomNavigation bottomNav;
    ImageView search;
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
        search=findViewById(R.id.catsearch);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SearchPage.class));
                finish();
            }
        });
        offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),OfferBooks.class));
                finish();
            }
        });
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FoodBook.class));
                finish();
            }
        });
        medical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Medicalbook.class));
                finish();
            }
        });
        adventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Adventurebook.class));
                finish();
            }
        });
        story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Storybook.class));
                finish();
            }
        });

        bottomNav.add(new MeowBottomNavigation.Model(1,R.drawable.homeic));
        bottomNav.add(new MeowBottomNavigation.Model(2,R.drawable.cateic));
        bottomNav.add(new MeowBottomNavigation.Model(3,R.drawable.cateic));
        bottomNav.add(new MeowBottomNavigation.Model(4,R.drawable.menuic));



        bottomNav.setCount(2,"5");
        bottomNav.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                if(item.getId()==1){
                    startActivity(new Intent(getApplicationContext(),Dashboard.class));
                    finish();
                    return;
                }
                else if(item.getId()==2){

                    return;
                }
                else if(item.getId()==3){
                    startActivity(new Intent(getApplicationContext(), MyCart.class));
                    finish();
                    return;
                }
                else if(item.getId()==4){
                    startActivity(new Intent(getApplicationContext(),Menu.class));
                    finish();
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

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(), Dashboard.class));

    }
}