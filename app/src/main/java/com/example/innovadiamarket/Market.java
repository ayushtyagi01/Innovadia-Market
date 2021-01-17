package com.example.innovadiamarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.leo.simplearcloader.SimpleArcLoader;

import java.util.ArrayList;
import java.util.List;

public class Market extends AppCompatActivity {
    RecyclerView recyclerview;
    List<String> imageCost=new ArrayList<>();
    List<String> imageName=new ArrayList<>();
    List<String> imagePhone=new ArrayList<>();
    List<String> imageUrl=new ArrayList<>();
    SimpleArcLoader simpleArcLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        recyclerview=findViewById(R.id.recyclerview);
        simpleArcLoader=findViewById(R.id.loader);

        simpleArcLoader.postDelayed(new Runnable() {
            @Override
            public void run() {
                simpleArcLoader.setVisibility(View.INVISIBLE);
                recyclerview.setVisibility(View.VISIBLE);
            }
        },4000);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(Market.this,1);
        recyclerview.setLayoutManager(gridLayoutManager);

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Images");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                showdata(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void showdata(DataSnapshot snapshot) {
        for(DataSnapshot ds : snapshot.getChildren()){
           String imagecost= ds.child("imageCost").getValue().toString();
            imageCost.add(imagecost);
            String imagename = ds.child("imageName").getValue().toString().toUpperCase();
            imageName.add(imagename);
            String imagephone=ds.child("imagePhone").getValue().toString();
            imagePhone.add(imagephone);
            String imageURL=ds.child("imageURL").getValue().toString();
            imageUrl.add(imageURL);

        }
        MarketAdapter marketAdapter=new MarketAdapter(this,imageCost,imageName,imagePhone,imageUrl);
        recyclerview.setAdapter(marketAdapter);
    }
}
