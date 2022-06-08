package com.example.boikhuji;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.boikhuji.Adapter.myadapter;
import com.example.boikhuji.Model.model;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends AppCompatActivity {


    EditText search_box;
    FirebaseFirestore db;
    private List<model> modelList;

    private RecyclerView recyclerViewSearch;
    private myadapter myadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db=FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_search_page);
        recyclerViewSearch = findViewById(R.id.search_rec);
        search_box = findViewById(R.id.search_box);
        modelList = new ArrayList<>();
        myadapter = new myadapter((ArrayList<model>) modelList,getApplicationContext());
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        recyclerViewSearch.setAdapter(myadapter);
        recyclerViewSearch.setHasFixedSize(true);
        search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(s.toString().isEmpty()){
                    modelList.clear();
                    myadapter.notifyDataSetChanged();
                }
                else{
                    searchProduct(s.toString());
                }

            }
        });


    }

    private void searchProduct(String name) {


        if(!name.isEmpty()){
            db.collection("OfferBooks").whereEqualTo("name",name).get()

                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                           if(task.isSuccessful() && task.getResult()!= null){
                               modelList.clear();
                               myadapter.notifyDataSetChanged();
                               for(DocumentSnapshot doc : task.getResult().getDocuments()){
                                   model model = doc.toObject(com.example.boikhuji.Model.model.class);
                                   modelList.add(model);
                                   myadapter.notifyDataSetChanged();

                               }
                           }
                        }
                    });
//            db.collection("MedicalBooks").whereEqualTo("name",name).get()
//
//                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                            if(task.isSuccessful() && task.getResult()!= null){
//                                modelList.clear();
//                                myadapter.notifyDataSetChanged();
//                                for(DocumentSnapshot doc : task.getResult().getDocuments()){
//                                    model model = doc.toObject(com.example.boikhuji.Model.model.class);
//                                    modelList.add(model);
//                                    myadapter.notifyDataSetChanged();
//
//                                }
//                            }
//                        }
//                    });
//            db.collection("TopBook").whereEqualTo("name",name).get()
//
//                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                            if(task.isSuccessful() && task.getResult()!= null){
//                                modelList.clear();
//                                myadapter.notifyDataSetChanged();
//                                for(DocumentSnapshot doc : task.getResult().getDocuments()){
//                                    model model = doc.toObject(com.example.boikhuji.Model.model.class);
//                                    modelList.add(model);
//                                    myadapter.notifyDataSetChanged();
//
//                                }
//                            }
//                        }
//                    });
//            db.collection("ReceipeBooks").whereEqualTo("name",name).get()
//
//                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                            if(task.isSuccessful() && task.getResult()!= null){
//                                modelList.clear();
//                                myadapter.notifyDataSetChanged();
//                                for(DocumentSnapshot doc : task.getResult().getDocuments()){
//                                    model model = doc.toObject(com.example.boikhuji.Model.model.class);
//                                    modelList.add(model);
//                                    myadapter.notifyDataSetChanged();
//
//                                }
//                            }
//                        }
//                    });
//
//            db.collection("StoryBooks").whereEqualTo("name",name).get()
//
//                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                            if(task.isSuccessful() && task.getResult()!= null){
//                                modelList.clear();
//                                myadapter.notifyDataSetChanged();
//                                for(DocumentSnapshot doc : task.getResult().getDocuments()){
//                                    model model = doc.toObject(com.example.boikhuji.Model.model.class);
//                                    modelList.add(model);
//                                    myadapter.notifyDataSetChanged();
//
//                                }
//                            }
//                        }
//                    });


        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),Dashboard.class));
        finish();
    }
}