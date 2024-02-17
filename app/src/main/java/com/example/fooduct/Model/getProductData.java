package com.example.fooduct.Model;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooduct.AdminPage.custom_order;
import com.example.fooduct.Orders.All_Pro_same_section;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class getProductData extends ViewModel {
    public MutableLiveData<ArrayList<All_Pro_same_section>> mutableLiveData = new MutableLiveData<>();
    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();

    ArrayList<All_Pro_same_section> data = new ArrayList<>();


    public void getNutsData(){
        mRef.child("products").child("nuts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for(DataSnapshot snap : snapshot.getChildren()){
                    String nutsName = snap.child("name").getValue().toString();
                    String nutsPrice = snap.child("price").getValue().toString();
                    String nutsImage = snap.child("image").getValue().toString();

                    All_Pro_same_section pro = new All_Pro_same_section(nutsImage , nutsName , nutsPrice);
                    data.add(pro);
                }
                mutableLiveData.setValue(data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void getDrinksData(){
        mRef.child("products").child("drinks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for(DataSnapshot snap : snapshot.getChildren()){
                    String nutsName = snap.child("name").getValue().toString();
                    String nutsPrice = snap.child("price").getValue().toString();
                    String nutsImage = snap.child("image").getValue().toString();

                    All_Pro_same_section pro = new All_Pro_same_section(nutsImage , nutsName , nutsPrice);
                    data.add(pro);
                }
                mutableLiveData.setValue(data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
