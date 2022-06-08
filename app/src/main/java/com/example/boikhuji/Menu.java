package com.example.boikhuji;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.firebase.auth.FirebaseAuth;

public class Menu extends AppCompatActivity {
    MeowBottomNavigation bottomNav;
    private FirebaseAuth firebaseAuth;
    CardView vieworder,agentregister,logoutbtn;

    private final int ID_Home = 1;
    private final int ID_Events = 2;
    private final int ID_Map= 3;
    private final int ID_Account=4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        bottomNav = findViewById(R.id.bottomNav);
        agentregister=findViewById(R.id.agentreg);
        logoutbtn=findViewById(R.id.logout);
        vieworder=findViewById(R.id.viewcart);
        firebaseAuth = FirebaseAuth.getInstance();
        vieworder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ViewOrder.class));
                finish();
            }
        });
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });
        agentregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AgentRegister.class));
                finish();
            }
        });

        bottomNav.add(new MeowBottomNavigation.Model(1,R.drawable.homeic));
        bottomNav.add(new MeowBottomNavigation.Model(2,R.drawable.cateic));
        bottomNav.add(new MeowBottomNavigation.Model(3,R.drawable.cartic));
        bottomNav.add(new MeowBottomNavigation.Model(4,R.drawable.menuic));
        bottomNav.setCount(2,"4");
        bottomNav.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                if(item.getId()==1){
                    startActivity(new Intent(getApplicationContext(),Dashboard.class));
                    finish();
                    return;
                }
                else if(item.getId()==2){
                    startActivity(new Intent(getApplicationContext(),Catagory.class));
                    finish();
                    return;
                }
                else if(item.getId()==3){
                    startActivity(new Intent(getApplicationContext(), MyCart.class));
                    finish();
                    return;
                }
                else if(item.getId()==4){

                    return;
                }
                return;
            }
        });
        bottomNav.show(4,true);
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