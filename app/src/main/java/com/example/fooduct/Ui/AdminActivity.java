package com.example.fooduct.Ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.example.fooduct.AdminPage.OnOrderClickListener;
import com.example.fooduct.AdminPage.custom_order;
import com.example.fooduct.AdminPage.custom_orderAdapter;
import com.example.fooduct.AdminPage.SetUpFireBaseDataBase;
import com.example.fooduct.databinding.ActivityAdminBinding;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {
    ActivityAdminBinding binding;

    SetUpFireBaseDataBase model = new SetUpFireBaseDataBase();
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding.adminBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        model = new ViewModelProvider(this).get(SetUpFireBaseDataBase.class);

        model.get_The_order();
        model.mutableLiveData.observe(this, new Observer<ArrayList<custom_order>>() {
            @Override
            public void onChanged(ArrayList<custom_order> custom_orders) {
                custom_orderAdapter adapter = new custom_orderAdapter(custom_orders, new OnOrderClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        String message = custom_orders.get(position).getOrder();
                        AlertDialog.Builder builder = new AlertDialog.Builder(AdminActivity.this)
                                .setTitle("ألطلب").setMessage(message)
                                .setPositiveButton("تم تسليم الطلب", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        model.Remove_The_ordered_pro(message);
                                        finish();
                                        startActivity(getIntent());
                                    }
                                }).setNegativeButton("ألغاء", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialog.cancel();
                                    }
                                });

                        dialog = builder.create();
                        dialog.show();


                    }
                });

                binding.AdminActivityRv.setAdapter(adapter);
                binding.AdminActivityRv.setLayoutManager(new LinearLayoutManager(AdminActivity.this));
                binding.AdminActivityRv.setHasFixedSize(true);

            }
        });
    }
}