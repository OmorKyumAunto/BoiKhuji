package com.example.boikhuji;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideOption;
import com.example.boikhuji.Model.model;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class BookDetails extends AppCompatActivity {

    ImageView bpic,additem,removeitem;
    Button addtocart;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    int totalQuantity=1;
    int totalPrice=0;
    TextView bookqnty;
    TextView bname,wname,bprice,bstock,bsummary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        bpic=findViewById(R.id.bpic);
        bname=findViewById(R.id.bname);
        wname=findViewById(R.id.wname);
        bprice=findViewById(R.id.bprice);
        bstock=findViewById(R.id.bstock);
        bsummary=findViewById(R.id.bsummary);
        additem=findViewById(R.id.additem);
        removeitem=findViewById(R.id.removeitem);
        addtocart=findViewById(R.id.addcartbtn);
        bookqnty=findViewById(R.id.bookqnty);

        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();


//        view
        String imgurl= getIntent().getStringExtra("bpic");
        Glide.with(this).load(imgurl).into(bpic);
        bname.setText(getIntent().getStringExtra("bname"));
        wname.setText(getIntent().getStringExtra("wname"));
        bprice.setText(getIntent().getStringExtra("bprice"));
        bstock.setText(getIntent().getStringExtra("bstock"));
        bsummary.setText(getIntent().getStringExtra("bsummary"));
//




//        int total_price = Integer.parseInt(totprice.getText().toString()) - theRemovedItemPrice ;
//        totprice.setText(String.valueOf(total_price) ;
//        cartwork


        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(totalQuantity<10){

                    totalQuantity++;
                    bookqnty.setText(String.valueOf(totalQuantity));
                }
            }
        });
        removeitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(totalQuantity>0){
                    totalQuantity--;
                    bookqnty.setText(String.valueOf(totalQuantity));
                }
            }
        });

        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addedToCart();
            }
        });



    }

    private void addedToCart() {

        String saveCurrentDate,saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MM,dd,yyyy");
        saveCurrentDate=currentDate.format(calForDate.getTime());
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        totalPrice =  Integer.parseInt(bprice.getText().toString())*totalQuantity;

         final HashMap<String ,Object> cartMap = new HashMap<>();
         cartMap.put("productName",bname.getText().toString());
         cartMap.put("productPrice",bprice.getText().toString());
         cartMap.put("currentDate",saveCurrentDate);
         cartMap.put("currentTime",saveCurrentTime);
         cartMap.put("totalQuantity",bookqnty.getText().toString());
         cartMap.put("totalPrice",totalPrice);


         firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                 .collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                     @Override
                     public void onComplete(@NonNull Task<DocumentReference> task) {
                         Toast.makeText(BookDetails.this,"Added To a Cart",Toast.LENGTH_SHORT).show();
                         finish();
                     }
                 });





    }
}