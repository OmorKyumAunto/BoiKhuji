package com.example.boikhuji;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.boikhuji.Adapter.myadapter;
import com.example.boikhuji.Model.model;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FoodBook extends AppCompatActivity {

    RecyclerView recview;
    ArrayList<model> datalist;
    FirebaseFirestore db;
    myadapter adapter;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_food_book);
        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();



        recview=findViewById(R.id.recview);
        recview.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        datalist=new ArrayList<>();
        adapter=new myadapter(datalist,getApplicationContext());
        recview.setAdapter(adapter);

        db=FirebaseFirestore.getInstance();
        db.collection("ReceipeBooks").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list= queryDocumentSnapshots.getDocuments();

                        if(progressDialog.isShowing())
                            progressDialog.dismiss();
                        for(DocumentSnapshot d:list){
                            model obj = d.toObject(model.class);
                            datalist.add(obj);

                        }

                        adapter.notifyDataSetChanged();
                        if(progressDialog.isShowing())
                            progressDialog.dismiss();
                    }
                });







    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), Catagory.class));
        finish();
    }
}