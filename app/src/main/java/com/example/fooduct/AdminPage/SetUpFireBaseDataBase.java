package com.example.fooduct.AdminPage;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooduct.AdminPage.custom_order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class SetUpFireBaseDataBase extends ViewModel {

    public MutableLiveData<ArrayList<custom_order>> mutableLiveData = new MutableLiveData<>();

    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();


    String UserName;
    String PhoneNumber;
    String Order;

    ArrayList<custom_order> orders = new ArrayList<>();



    public void sendOrder(custom_order order) {
        String orderKey = mRef.child("order").push().getKey().toString();
        mRef.child("order").child(orderKey).push();

        HashMap<String, String> orderHashMap = new HashMap<>();
        orderHashMap.put("userName", order.getUserName());
        orderHashMap.put("PhoneNumber", order.getPhoneNumber());
        orderHashMap.put("TheOrder", order.getOrder());

        mRef.child("order").child(orderKey).setValue(orderHashMap);
    }

    public void get_The_order() {
        mRef.child("order").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                orders.clear();

                for (DataSnapshot snap : snapshot.getChildren()) {

                    UserName = snap.child("userName").getValue().toString();
                    PhoneNumber = snap.child("PhoneNumber").getValue().toString();
                    Order = snap.child("TheOrder").getValue().toString();
                    orders.add(new custom_order(Order, UserName, PhoneNumber));
                }
                mutableLiveData.setValue(orders);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void Remove_The_ordered_pro(String TheOrderedValue) {
        mRef.child("order").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {

                    UserName = snap.child("userName").getValue().toString();
                    PhoneNumber = snap.child("PhoneNumber").getValue().toString();
                    Order = snap.child("TheOrder").getValue().toString();
                    orders.add(new custom_order(Order, UserName, PhoneNumber));

                    if (Order == TheOrderedValue){
                        snap.getRef().removeValue();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
