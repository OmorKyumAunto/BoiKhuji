package com.example.boikhuji;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SearchView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.boikhuji.Adapter.SliderAdapter;
import com.example.boikhuji.Adapter.dadapter;
import com.example.boikhuji.Adapter.dashadapter;
import com.example.boikhuji.Model.dmodel;
import com.example.boikhuji.Model.model;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {
    RecyclerView recview,downrec;
    ArrayList<model> datalist;
    ArrayList<dmodel> dashdatalist;
    FirebaseFirestore db;
    dadapter adapter;
    ImageView search;
    dashadapter dasadapter;


    SliderView sliderView;
    int[] images = {R.drawable.one,
            R.drawable.two,
            R.drawable.three
    };
    MeowBottomNavigation bottomNav;

    private final int ID_Home = 1;
    private final int ID_Events = 2;
    private final int ID_Map= 3;
    private final int ID_Account=4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        changeStatusBarColor();

        recview=findViewById(R.id.my_recycler);
        recview.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        datalist=new ArrayList<>();
        adapter=new dadapter(datalist,getApplicationContext());
        recview.setAdapter(adapter);

        downrec=findViewById(R.id.downrecycler);
        downrec.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        dashdatalist=new ArrayList<>();
        dasadapter=new dashadapter(dashdatalist,getApplicationContext());
        downrec.setAdapter(dasadapter);

        search=findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SearchPage.class));
                finish();
            }
        });




        db=FirebaseFirestore.getInstance();
        db.collection("OfferBooks").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list= queryDocumentSnapshots.getDocuments();

                        for(DocumentSnapshot d:list){
                            model obj = d.toObject(model.class);
                            datalist.add(obj);

                        }

                        adapter.notifyDataSetChanged();

                    }
                });

        db.collection("TopBook").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot>list=queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list){
                            dmodel obj= d.toObject(dmodel.class);
                            dashdatalist.add(obj);
                        }
                        dasadapter.notifyDataSetChanged();
                    }
                });





        sliderView =findViewById(R.id.homeimageslider);
        SliderAdapter sliderAdapter = new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);

        sliderView.startAutoCycle();



        bottomNav = findViewById(R.id.bottomNav);

        bottomNav.add(new MeowBottomNavigation.Model(1,R.drawable.homeic));
        bottomNav.add(new MeowBottomNavigation.Model(2,R.drawable.cateic));
        bottomNav.add(new MeowBottomNavigation.Model(3,R.drawable.cartic));
        bottomNav.add(new MeowBottomNavigation.Model(4,R.drawable.menuic));



        bottomNav.setCount(2,"4");

        bottomNav.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                if(item.getId()==1){
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
                    startActivity(new Intent(getApplicationContext(),Menu.class));
                    finish();
                    return;
                }
                return;
            }
        });

        bottomNav.show(1,true);
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

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Want To Exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Dashboard.super.onBackPressed();

                        System.exit(0);
                        finish();
                    }

                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void changeStatusBarColor() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
           //window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu,menu);
        MenuItem item=menu.findItem(R.id.search);
        SearchView searchView=(SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
               processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void processsearch(String s) {

        db.collection("OfferBooks").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot>list=queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list){
                            dmodel obj= d.toObject(dmodel.class);
                            dashdatalist.add(obj);
                        }
                        dasadapter.notifyDataSetChanged();
                    }
                });


    }
}