package com.example.fooduct.Ui;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.fooduct.Model.getProductData;
import com.example.fooduct.Orders.All_Pro_same_section;
import com.example.fooduct.Orders.Custom_See_All_Adapter;
import com.example.fooduct.Orders.OnClickListener;
import com.example.fooduct.databinding.ActivitySeeAllBinding;

import java.util.ArrayList;

public class See_All_Activity extends AppCompatActivity {

    ActivitySeeAllBinding binding;
    Custom_See_All_Adapter see_all_adapter;
    ArrayList<All_Pro_same_section> all_pro;

    getProductData productData = new getProductData() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivitySeeAllBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        all_pro = new ArrayList<>();



        binding.SeeAllBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        Intent intent = getIntent();
        int section =(int) intent.getIntExtra(MainActivity.Section , 0);

        switch (section){
            case 1:
                binding.SectionName.setText("مكسرات");
                setupNutsProduct();
            case 2:
                binding.SectionName.setText("مشروبات غازية");
                setupDrinksProduct();
        }

    }


    public void setupNutsProduct(){
        productData = new ViewModelProvider(this).get(getProductData.class);
        productData.getNutsData();
        productData.mutableLiveData.observe(this, new Observer<ArrayList<All_Pro_same_section>>() {
            @Override
            public void onChanged(ArrayList<All_Pro_same_section> allProSameSections) {
                see_all_adapter = new Custom_See_All_Adapter(allProSameSections, new OnClickListener() {
                    @Override
                    public void onItemClick(int position) {

                    }
                });

                binding.SeeAllRv.setAdapter(see_all_adapter);
                binding.SeeAllRv.setLayoutManager(new GridLayoutManager(See_All_Activity.this ,3));
                binding.SeeAllRv.setHasFixedSize(true);
            }
        });
    }

    public void setupDrinksProduct(){
        productData = new ViewModelProvider(this).get(getProductData.class);
        productData.getDrinksData();
        productData.mutableLiveData.observe(this, new Observer<ArrayList<All_Pro_same_section>>() {
            @Override
            public void onChanged(ArrayList<All_Pro_same_section> allProSameSections) {
                see_all_adapter = new Custom_See_All_Adapter(allProSameSections, new OnClickListener() {
                    @Override
                    public void onItemClick(int position) {

                    }
                });

                binding.SeeAllRv.setAdapter(see_all_adapter);
                binding.SeeAllRv.setLayoutManager(new GridLayoutManager(See_All_Activity.this ,3));
                binding.SeeAllRv.setHasFixedSize(true);
            }
        });
    }
}