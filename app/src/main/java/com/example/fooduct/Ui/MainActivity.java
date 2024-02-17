package com.example.fooduct.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fooduct.AdminPage.custom_order;
import com.example.fooduct.AdminPage.SetUpFireBaseDataBase;
import com.example.fooduct.Orders.All_Pro_same_section;
import com.example.fooduct.Orders.Custom_See_All_Adapter;
import com.example.fooduct.Orders.OnClickListener;
import com.example.fooduct.R;
import com.example.fooduct.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.example.fooduct.Orders.order;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity implements HomePage.OnClickLisi, HomePage.SeeAllOnClick
        , HomePage.finishOrderListener, HomePage.goToAdminPage, Search.onChangeListener, Search.adapterListener
        , Setting.logOutListener, Setting.ChangeUserNameListener{

    ActivityMainBinding binding;
    FirebaseAuth mAuth;
    public static String Section;
    public static ArrayList<order> orders = new ArrayList<>();
    String lastOrder = "";


    SetUpFireBaseDataBase model;
    private static final String TAG = "MainActivity";

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.MainNavigationDrawer.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Home_Page:
                        replaceFragment(new HomePage());
                        break;
                    case R.id.Search_Page:
                        replaceFragment(new Search());
                        break;
                    case R.id.Settings_Page:
                        replaceFragment(new Setting());
                        break;
                }

                return true;
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null || !user.isEmailVerified() && !user.getPhoneNumber().isEmpty()) {
            Intent intent = new Intent(MainActivity.this, LogInActivity.class);
            startActivity(intent);
        } else {
            replaceFragment(new HomePage());
        }
    }


    public void seeAll(int section) {
        Intent intent = new Intent(MainActivity.this, See_All_Activity.class);
        intent.putExtra(Section, section);
        startActivity(intent);
    }


    public void custom_dialog() {
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_dialog, null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                .setView(view);
        AlertDialog alertdialog = builder.create();
        alertdialog.show();

        ImageButton btn_add = view.findViewById(R.id.custom_dialog_add);
        ImageButton btn_remove = view.findViewById(R.id.custom_dialog_minus);
        TextView tv_number = view.findViewById(R.id.custom_dialog_NumberOfItem);
        Button btn_addToBasket = view.findViewById(R.id.custom_dialog_addToBasket);

        View view1 = view.findViewById(R.id.custom_dialog_pro);
        TextView tv_pro_Name = (TextView) view1.findViewById(R.id.Product_Name);


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


    public void finish_ordering(String UserName, String PhoneNumber) {
        for (int i = 0; i < orders.size(); i++) {
            lastOrder += (i + 1) + "- Product Name :" + orders.get(i).getPro_Name() + " Number : " + orders.get(i).getNumber() + "\n";
        }
        Log.d(TAG, "finish_ordering: abd  " + lastOrder);


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                .setTitle("رسالة تأكيد")
                .setMessage(lastOrder)
                .setPositiveButton("تاكيد الطلب", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        model = new SetUpFireBaseDataBase();
                        model.sendOrder(new custom_order(lastOrder, UserName
                                , PhoneNumber));

                        orders.clear();
                        lastOrder = "";

                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.MainContainer, fragment);
        transaction.commit();
    }


    @Override
    public void onClick() {
        custom_dialog();
    }


    @Override
    public void seeAllClick(int i) {
        seeAll(i);
    }


    @Override
    public void ClickFinish(String UserName, String PhoneNumber) {
        finish_ordering(UserName, PhoneNumber);
    }

    @Override
    public void adminPage() {
        Intent intent = new Intent(MainActivity.this, AdminActivity.class);
        startActivity(intent);
    }

    @Override
    public void onChange(String newText, ArrayList<All_Pro_same_section> proSameSections) {

        FilterList(newText, proSameSections);
    }

    public void FilterList(String newText, ArrayList<All_Pro_same_section> proSameSections) {
        ArrayList<All_Pro_same_section> items = new ArrayList<>();
        for (All_Pro_same_section i : proSameSections) {
            if (i.getProName().toLowerCase().contains(newText.toLowerCase()))
                items.add(i);
        }

        if (items.isEmpty()) {
            Toast.makeText(this, "The Product Not Found", Toast.LENGTH_SHORT).show();
            return;
        }
        setUpRecyclerView(items);
    }

    public void setUpRecyclerView(ArrayList<All_Pro_same_section> products) {
        products = new ArrayList<>();

        Custom_See_All_Adapter seeAll = new Custom_See_All_Adapter(products, new OnClickListener() {
            @Override
            public void onItemClick(int position) {
                custom_dialog();
            }
        });
        Search.rv.setAdapter(seeAll);
        Search.rv.setLayoutManager(new LinearLayoutManager(this));
        Search.rv.setHasFixedSize(true);
    }

    @Override
    public void listener(int position) {
        custom_dialog();
    }

    @Override
    public void changeUserName() {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_change_dialog, null, false);
        EditText et_NewUserName = v.findViewById(R.id.custom_change_changeUserPhone);
        et_NewUserName.setText(user.getDisplayName());
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this).setTitle("تفيير أسم المستخدم").setView(v)
                .setPositiveButton("تفيير", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        UserProfileChangeRequest changeRequest = new UserProfileChangeRequest.Builder().setDisplayName(et_NewUserName.getText().toString()).build();

                        user.updateProfile(changeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });


                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();

    }



    @Override
    public void logOut() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this).setTitle("رسالة تأكيد")
                .setMessage("هل أنت متأكد من عملية تسجيل الخروج")
                .setPositiveButton("تأكيد تسجيل الخروج", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        mAuth = FirebaseAuth.getInstance();
                        mAuth.signOut();
                        Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
