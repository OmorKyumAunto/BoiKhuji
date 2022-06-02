package com.example.boikhuji;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AgentRegister extends AppCompatActivity {
    EditText shopname,userid,shopemail,shopadd,shopmob,shoppass;
    Button apply;
    private FirebaseAuth shopauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeStatusBarColor();
        setContentView(R.layout.activity_agent_register);
        shopname=findViewById(R.id.username);
        userid=findViewById(R.id.userid);
        shopemail=findViewById(R.id.useremail);
        shopadd=findViewById(R.id.useraddress);
        shopmob=findViewById(R.id.userphone);
        shoppass=findViewById(R.id.password);
        shopauth= FirebaseAuth.getInstance();
        apply=findViewById(R.id.regbtn);

        DatabaseReference table_user= FirebaseDatabase.getInstance().getReference("SellerDetails");

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = shopname.getText().toString().trim();
                String email = shopemail.getText().toString().trim();
                String id = userid.getText().toString().trim();
                String phone = shopmob.getText().toString().trim();
                String address=shopadd.getText().toString().trim();
                String password= shoppass.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    shopemail.setError("Email Is Required");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    shoppass.setError("Password Is Required");
                    return;
                }
                if(password.length()<= 6){
                    shoppass.setError("Password must be large then 6 Character");
                    return;
                }


                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.child(userid.getText().toString()).exists()){
                            Toast.makeText(AgentRegister.this, "Failed To Register Already Exists", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Agent agent = new Agent(userid.getText().toString(),shopemail.getText().toString(),shopname.getText().toString(),shopadd.getText().toString(),shopmob.getText().toString(),shoppass.getText().toString());
                            table_user.child(userid.getText().toString()).setValue(agent);
                            Toast.makeText(AgentRegister.this, "Congratulation For Joining with Us..", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(),Menu.class));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    private void changeStatusBarColor() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }
}