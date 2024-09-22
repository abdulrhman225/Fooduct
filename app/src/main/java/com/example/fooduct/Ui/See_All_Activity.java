package com.example.fooduct.Ui;

import static android.content.ContentValues.TAG;

import static com.example.fooduct.Ui.MainActivity.orders;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fooduct.Model.getProductData;
import com.example.fooduct.Orders.All_Pro_same_section;
import com.example.fooduct.Orders.Custom_See_All_Adapter;
import com.example.fooduct.Orders.OnClickListener;
import com.example.fooduct.Orders.order;
import com.example.fooduct.R;
import com.example.fooduct.databinding.ActivitySeeAllBinding;
import com.squareup.picasso.Picasso;

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
                binding.SectionName.setText("خضراوات");
                setupNutsProduct();
                break;
            case 2:
                binding.SectionName.setText("فواكه");
                setupDrinksProduct();
                break;
            case 3:
                binding.SectionName.setText("توابل");
                setUpSpiceData();
                break;
            case 4:
                binding.SectionName.setText("لحوم");
                setUpMeatData();
                break;
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
                        custom_dialog(allProSameSections.get(position).getImage() , allProSameSections.get(position).getProName(), allProSameSections.get(position).getProPrice());

                    }
                });

                binding.SeeAllRv.setAdapter(see_all_adapter);
                binding.SeeAllRv.setLayoutManager(new GridLayoutManager(See_All_Activity.this ,2));
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
                        custom_dialog(allProSameSections.get(position).getImage() , allProSameSections.get(position).getProName(), allProSameSections.get(position).getProPrice());

                    }
                });

                binding.SeeAllRv.setAdapter(see_all_adapter);
                binding.SeeAllRv.setLayoutManager(new GridLayoutManager(See_All_Activity.this ,2));
                binding.SeeAllRv.setHasFixedSize(true);
            }
        });
    }

    public void setUpSpiceData(){
        productData = new ViewModelProvider(this).get(getProductData.class);
        productData.getSpiceData();
        productData.mutableLiveData.observe(this, new Observer<ArrayList<All_Pro_same_section>>() {
            @Override
            public void onChanged(ArrayList<All_Pro_same_section> allProSameSections) {
                see_all_adapter = new Custom_See_All_Adapter(allProSameSections, new OnClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        custom_dialog(allProSameSections.get(position).getImage() , allProSameSections.get(position).getProName(), allProSameSections.get(position).getProPrice());

                    }
                });

                binding.SeeAllRv.setAdapter(see_all_adapter);
                binding.SeeAllRv.setLayoutManager(new GridLayoutManager(See_All_Activity.this ,2));
                binding.SeeAllRv.setHasFixedSize(true);
            }
        });
    }

    public void setUpMeatData(){
        productData = new ViewModelProvider(this).get(getProductData.class);
        productData.getMeatData();
        productData.mutableLiveData.observe(this, new Observer<ArrayList<All_Pro_same_section>>() {
            @Override
            public void onChanged(ArrayList<All_Pro_same_section> allProSameSections) {
                see_all_adapter = new Custom_See_All_Adapter(allProSameSections, new OnClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        custom_dialog(allProSameSections.get(position).getImage() , allProSameSections.get(position).getProName(), allProSameSections.get(position).getProPrice());
                    }
                });

                binding.SeeAllRv.setAdapter(see_all_adapter);
                binding.SeeAllRv.setLayoutManager(new GridLayoutManager(See_All_Activity.this ,2));
                binding.SeeAllRv.setHasFixedSize(true);
            }
        });
    }
    public void custom_dialog(String productImage , String productName, String productPrice) {
        View view = LayoutInflater.from(See_All_Activity.this).inflate(R.layout.custom_dialog, null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(See_All_Activity.this)
                .setView(view);
        AlertDialog alertdialog = builder.create();
        alertdialog.show();

        ImageButton btn_add = view.findViewById(R.id.custom_dialog_add);
        ImageButton btn_remove = view.findViewById(R.id.custom_dialog_minus);
        TextView tv_number = view.findViewById(R.id.custom_dialog_NumberOfItem);
        Button btn_addToBasket = view.findViewById(R.id.custom_dialog_addToBasket);

        View view1 = view.findViewById(R.id.custom_dialog_pro);
        TextView tv_pro_Name = (TextView) view1.findViewById(R.id.Product_Name);
        ImageView iv_pro_Image = (ImageView) view1.findViewById(R.id.Product_Image);
        TextView tv_pro_price = (TextView) view1.findViewById(R.id.Product_Price);

        tv_pro_Name.setText(productName);
        Picasso.get().load(productImage).into(iv_pro_Image);
        tv_pro_price.setText(productPrice);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_number.setText(String.valueOf(Integer.parseInt(tv_number.getText().toString()) + 1));
            }
        });

        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(tv_number.getText().toString()) > 0)
                    tv_number.setText(String.valueOf(Integer.parseInt(tv_number.getText().toString()) - 1));
            }
        });

        btn_addToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Integer.parseInt(tv_number.getText().toString()) > 0) {
                    orders.add(new order(tv_pro_Name.getText().toString(), Integer.parseInt(tv_number.getText().toString())));
                    HomePage.btn_finishOrdering.setVisibility(View.VISIBLE);
                    alertdialog.cancel();
                }
            }

        });

    }

}