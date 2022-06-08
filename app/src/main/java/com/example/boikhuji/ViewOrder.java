package com.example.boikhuji;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.boikhuji.Adapter.MyCartAdapter;
import com.example.boikhuji.Adapter.ViewAdapter;
import com.example.boikhuji.Model.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class ViewOrder extends AppCompatActivity {


    FirebaseFirestore db;
    FirebaseAuth auth;
    RecyclerView recyclerView;
    ViewAdapter viewAdapter;
    List<MyCartModel> cartModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);
        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        recyclerView=findViewById(R.id.viewrec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        cartModelList = new ArrayList<>();
        viewAdapter = new ViewAdapter(getApplicationContext(),cartModelList);
        recyclerView.setAdapter(viewAdapter);

        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("MyOrder").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                                String documentid = documentSnapshot.getId();
                                MyCartModel cartModel = documentSnapshot.toObject(MyCartModel.class);
                                cartModel.setDocumentId(documentid);
                                cartModelList.add(cartModel);
                                viewAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),Menu.class));
        finish();
    }
}