package com.example.fooduct.Ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.fooduct.Model.getProductData;
import com.example.fooduct.Orders.All_Pro_same_section;
import com.example.fooduct.Orders.Custom_See_All_Adapter;
import com.example.fooduct.Orders.OnClickListener;
import com.example.fooduct.databinding.FragmentHomePageBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePage extends Fragment {

    FragmentHomePageBinding binding;
    public OnClickLisi lisi;
    public SeeAllOnClick click;
    getProductData productData = new getProductData();

    public finishOrderListener OrderListener;
    public goToAdminPage adminPage;

    public static Button btn_finishOrdering;


    FirebaseAuth mAuth;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomePage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomePage.
     */
    // TODO: Rename and change types and number of parameters
    public static HomePage newInstance(String param1, String param2) {
        HomePage fragment = new HomePage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        lisi = (OnClickLisi) context;
        click = (SeeAllOnClick) context;
        OrderListener = (finishOrderListener) context;
        adminPage = (goToAdminPage) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();

        binding = FragmentHomePageBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        btn_finishOrdering = binding.MainFinishOrdering;

        setUpNutsData();
        setUpDrinksData();
        setUpSpiceData();
        setUpMeatData();

        if (!MainActivity.orders.isEmpty())
            binding.MainFinishOrdering.setVisibility(View.VISIBLE);

        binding.MainFirstPro1.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D8%AE%D8%B6%D8%B1%D9%88%D8%A7%D8%AA%2F%D8%AE%D9%8A%D8%A7%D8%B1.png?alt=media&token=36afe729-4f58-4322-b454-433489ac2fa1" , binding.MainFirstPro1.ProductName.getText().toString() , binding.MainFirstPro1.ProductPrice.getText().toString());
            }
        });
        binding.MainFirstPro2.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D9%81%D9%88%D8%A7%D9%83%D9%87%2F%D8%A8%D8%B1%D8%AA%D9%82%D8%A7%D9%84.png?alt=media&token=772b84e4-238b-401a-b284-dd7f672be6cb" , binding.MainFirstPro2.ProductName.getText().toString() , binding.MainFirstPro2.ProductPrice.getText().toString());
            }
        });
        binding.MainFirstPro3.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D8%AA%D9%88%D8%A7%D8%A8%D9%84%2F%D9%82%D8%B1%D9%81%D8%A9.png?alt=media&token=38c80409-ebf2-49a0-88ff-93a53829f41d" , binding.MainFirstPro3.ProductName.getText().toString() , binding.MainFirstPro3.ProductPrice.getText().toString());
            }
        });
        binding.MainFirstPro4.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D9%84%D8%AD%D9%88%D9%85%2F%D8%AF%D8%AC%D8%A7%D8%AC.png?alt=media&token=f1a764c9-3595-4b43-84a5-623a39aa726c" ,binding.MainFirstPro4.ProductName.getText().toString() , binding.MainFirstPro4.ProductPrice.getText().toString());
            }
        });
        binding.MainSecondPro1.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D8%AE%D8%B6%D8%B1%D9%88%D8%A7%D8%AA%2F%D8%A8%D9%86%D8%AF%D9%88%D8%B1%D8%A9.png?alt=media&token=61a2d977-504d-44c6-8143-f5e474c49eae" , binding.MainSecondPro1.ProductName.getText().toString() , binding.MainSecondPro1.ProductPrice.getText().toString());
            }
        });
        binding.MainSecondPro2.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D9%81%D9%88%D8%A7%D9%83%D9%87%2F%D9%85%D9%88%D8%B2.png?alt=media&token=92b0c065-8550-4c35-923d-756a381b2257" ,binding.MainSecondPro2.ProductName.getText().toString() , binding.MainSecondPro2.ProductPrice.getText().toString());
            }
        });
        binding.MainSecondPro3.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D8%AA%D9%88%D8%A7%D8%A8%D9%84%2F%D9%87%D9%8A%D9%84.png?alt=media&token=1ef6d73a-0c81-471f-9212-fd136a30eec7" , binding.MainSecondPro3.ProductName.getText().toString() , binding.MainSecondPro3.ProductPrice.getText().toString());
            }
        });
        binding.MainSecondPro4.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D9%84%D8%AD%D9%88%D9%85%2F%D9%84%D8%AD%D9%85%20%D8%AE%D8%B1%D9%88%D9%81.png?alt=media&token=e6ed8c97-d2c9-40f9-8959-9c00e4fadd7e" , binding.MainSecondPro4.ProductName.getText().toString() , binding.MainSecondPro4.ProductPrice.getText().toString() );
            }
        });
        binding.MainThirdPro1.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D8%AE%D8%B6%D8%B1%D9%88%D8%A7%D8%AA%2F%D8%A8%D8%B5%D9%84.png?alt=media&token=412a08ab-30d9-40bf-9bf0-528c4806e8a0", binding.MainThirdPro1.ProductName.getText().toString() , binding.MainThirdPro1.ProductPrice.getText().toString());
            }
        });
        binding.MainThirdPro2.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D9%81%D9%88%D8%A7%D9%83%D9%87%2F%D8%AA%D9%81%D8%A7%D8%AD.png?alt=media&token=2d58c49e-28bd-4475-9a96-94fd1de36666" ,binding.MainThirdPro2.ProductName.getText().toString() , binding.MainThirdPro2.ProductPrice.getText().toString() );
            }
        });
        binding.MainThirdPro3.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D8%AA%D9%88%D8%A7%D8%A8%D9%84%2F%D8%B2%D9%86%D8%AC%D8%A8%D9%8A%D9%84.png?alt=media&token=fd1a17b1-67a6-4666-b1cb-323e9c280579",binding.MainThirdPro3.ProductName.getText().toString() , binding.MainThirdPro3.ProductPrice.getText().toString());
            }
        });
        binding.MainThirdPro4.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D9%84%D8%AD%D9%88%D9%85%2F%D9%84%D8%AD%D9%85%20%D8%B9%D8%AC%D9%84.png?alt=media&token=2a31d2d3-2558-46c0-b7c5-cc96939e2f8c" , binding.MainThirdPro4.ProductName.getText().toString() , binding.MainThirdPro4.ProductPrice.getText().toString());
            }
        });
        binding.MainForthPro1.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D8%AE%D8%B6%D8%B1%D9%88%D8%A7%D8%AA%2F%D8%A8%D8%A7%D8%B0%D9%86%D8%AC%D8%A7%D9%86.png?alt=media&token=a98581ab-04b8-4972-af4d-5a09796421a5" , binding.MainForthPro1.ProductName.getText().toString() , binding.MainForthPro1.ProductPrice.getText().toString());
            }
        });
        binding.MainForthPro2.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D9%81%D9%88%D8%A7%D9%83%D9%87%2F%D8%AE%D9%88%D8%AE.png?alt=media&token=b2ba1412-1332-412a-9265-6586cbe61519" , binding.MainForthPro2.ProductName.getText().toString() , binding.MainForthPro2.ProductPrice.getText().toString());
            }
        });
        binding.MainForthPro3.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D8%AA%D9%88%D8%A7%D8%A8%D9%84%2F%D8%A8%D8%A7%D8%A8%D8%B1%D9%8A%D9%83%D8%A7.png?alt=media&token=bcace2d8-7387-4dac-9b3d-82af64149a3e", binding.MainForthPro3.ProductName.getText().toString() , binding.MainForthPro3.ProductPrice.getText().toString());
            }
        });
        binding.MainForthPro4.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D9%84%D8%AD%D9%88%D9%85%2F%D8%B3%D9%85%D9%83.png?alt=media&token=727e3d2c-1f11-4a6a-9ea9-d9f81a064a8f" , binding.MainForthPro4.ProductName.getText().toString() , binding.MainForthPro4.ProductPrice.getText().toString());
            }
        });


        binding.seeAll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.seeAllClick(1);
            }
        });
        binding.seeAll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.seeAllClick(2);
            }
        });
        binding.seeAll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.seeAllClick(3);
            }
        });
        binding.seeAll4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.seeAllClick(4);
            }
        });


        binding.MainFinishOrdering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderListener.ClickFinish(binding.MainUserName.getText().toString(),
                        binding.MainPhoneNumber.getText().toString());
                binding.MainFinishOrdering.setVisibility(View.GONE);
            }
        });


        binding.MainUserName.setText(mAuth.getCurrentUser().getDisplayName());



        if(!mAuth.getCurrentUser().getEmail().equals("admin@gmail.com")){
            binding.MainAdminPage.setVisibility(View.INVISIBLE);
            binding.MainPhoneNumber.setText(mAuth.getCurrentUser().getPhoneNumber()
                    .toString().replace("+962", "0"));
        }
        binding.MainAdminPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adminPage.adminPage();
            }
        });


        return view;
    }

    public void setUpNutsData() {

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D8%AE%D8%B6%D8%B1%D9%88%D8%A7%D8%AA%2F%D8%AE%D9%8A%D8%A7%D8%B1.png?alt=media&token=36afe729-4f58-4322-b454-433489ac2fa1").into(binding.MainFirstPro1.ProductImage);
        binding.MainFirstPro1.ProductName.setText("خيار");
        binding.MainFirstPro1.ProductPrice.setText("10$");

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D8%AE%D8%B6%D8%B1%D9%88%D8%A7%D8%AA%2F%D8%A8%D9%86%D8%AF%D9%88%D8%B1%D8%A9.png?alt=media&token=61a2d977-504d-44c6-8143-f5e474c49eae").into(binding.MainSecondPro1.ProductImage);

        binding.MainSecondPro1.ProductName.setText("بندورة");
        binding.MainSecondPro1.ProductPrice.setText("10$");

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D8%AE%D8%B6%D8%B1%D9%88%D8%A7%D8%AA%2F%D8%A8%D8%B5%D9%84.png?alt=media&token=412a08ab-30d9-40bf-9bf0-528c4806e8a0").into(binding.MainThirdPro1.ProductImage);
        binding.MainThirdPro1.ProductName.setText("بصل");
        binding.MainThirdPro1.ProductPrice.setText("10$");

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D8%AE%D8%B6%D8%B1%D9%88%D8%A7%D8%AA%2F%D8%A8%D8%A7%D8%B0%D9%86%D8%AC%D8%A7%D9%86.png?alt=media&token=a98581ab-04b8-4972-af4d-5a09796421a5").into(binding.MainForthPro1.ProductImage);
        binding.MainForthPro1.ProductName.setText("باذنجان");
        binding.MainForthPro1.ProductPrice.setText("10$");


    }

    public void setUpDrinksData() {

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D9%81%D9%88%D8%A7%D9%83%D9%87%2F%D8%A8%D8%B1%D8%AA%D9%82%D8%A7%D9%84.png?alt=media&token=772b84e4-238b-401a-b284-dd7f672be6cb").into(binding.MainFirstPro2.ProductImage);
        binding.MainFirstPro2.ProductName.setText("برتقال");
        binding.MainFirstPro2.ProductPrice.setText("20$");

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D9%81%D9%88%D8%A7%D9%83%D9%87%2F%D9%85%D9%88%D8%B2.png?alt=media&token=92b0c065-8550-4c35-923d-756a381b2257").into(binding.MainSecondPro2.ProductImage);

        binding.MainSecondPro2.ProductName.setText("موز");
        binding.MainSecondPro2.ProductPrice.setText("20$");

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D9%81%D9%88%D8%A7%D9%83%D9%87%2F%D8%AA%D9%81%D8%A7%D8%AD.png?alt=media&token=2d58c49e-28bd-4475-9a96-94fd1de36666").into(binding.MainThirdPro2.ProductImage);
        binding.MainThirdPro2.ProductName.setText("تفاح");
        binding.MainThirdPro2.ProductPrice.setText("20$");

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D9%81%D9%88%D8%A7%D9%83%D9%87%2F%D8%AE%D9%88%D8%AE.png?alt=media&token=b2ba1412-1332-412a-9265-6586cbe61519").into(binding.MainForthPro2.ProductImage);
        binding.MainForthPro2.ProductName.setText("خوخ");
        binding.MainForthPro2.ProductPrice.setText("20$");

    }

    public void setUpSpiceData() {

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D8%AA%D9%88%D8%A7%D8%A8%D9%84%2F%D9%82%D8%B1%D9%81%D8%A9.png?alt=media&token=38c80409-ebf2-49a0-88ff-93a53829f41d").into(binding.MainFirstPro3.ProductImage);
        binding.MainFirstPro3.ProductName.setText("قرفة");
        binding.MainFirstPro3.ProductPrice.setText("30$");

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D8%AA%D9%88%D8%A7%D8%A8%D9%84%2F%D9%87%D9%8A%D9%84.png?alt=media&token=1ef6d73a-0c81-471f-9212-fd136a30eec7").into(binding.MainSecondPro3.ProductImage);
        binding.MainSecondPro3.ProductName.setText("هيل");
        binding.MainSecondPro3.ProductPrice.setText("30$");

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D8%AA%D9%88%D8%A7%D8%A8%D9%84%2F%D8%B2%D9%86%D8%AC%D8%A8%D9%8A%D9%84.png?alt=media&token=fd1a17b1-67a6-4666-b1cb-323e9c280579").into(binding.MainThirdPro3.ProductImage);
        binding.MainThirdPro3.ProductName.setText("زنجبيل");
        binding.MainThirdPro3.ProductPrice.setText("30$");

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D8%AA%D9%88%D8%A7%D8%A8%D9%84%2F%D8%A8%D8%A7%D8%A8%D8%B1%D9%8A%D9%83%D8%A7.png?alt=media&token=bcace2d8-7387-4dac-9b3d-82af64149a3e").into(binding.MainForthPro3.ProductImage);
        binding.MainForthPro3.ProductName.setText("البابريكا");
        binding.MainForthPro3.ProductPrice.setText("30$");

    }

    public void setUpMeatData() {

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D9%84%D8%AD%D9%88%D9%85%2F%D8%AF%D8%AC%D8%A7%D8%AC.png?alt=media&token=f1a764c9-3595-4b43-84a5-623a39aa726c").into(binding.MainFirstPro4.ProductImage);
        binding.MainFirstPro4.ProductName.setText("دجاج");
        binding.MainFirstPro4.ProductPrice.setText("40$");

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D9%84%D8%AD%D9%88%D9%85%2F%D9%84%D8%AD%D9%85%20%D8%AE%D8%B1%D9%88%D9%81.png?alt=media&token=e6ed8c97-d2c9-40f9-8959-9c00e4fadd7e").into(binding.MainSecondPro4.ProductImage);
        binding.MainSecondPro4.ProductName.setText("لحم خروف");
        binding.MainSecondPro4.ProductPrice.setText("40$");

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D9%84%D8%AD%D9%88%D9%85%2F%D9%84%D8%AD%D9%85%20%D8%B9%D8%AC%D9%84.png?alt=media&token=2a31d2d3-2558-46c0-b7c5-cc96939e2f8c").into(binding.MainThirdPro4.ProductImage);
        binding.MainThirdPro4.ProductName.setText("لحم عجل");
        binding.MainThirdPro4.ProductPrice.setText("40$");

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/food-product-application.appspot.com/o/%D9%84%D8%AD%D9%88%D9%85%2F%D8%B3%D9%85%D9%83.png?alt=media&token=727e3d2c-1f11-4a6a-9ea9-d9f81a064a8f").into(binding.MainForthPro4.ProductImage);
        binding.MainForthPro4.ProductName.setText("سمك");
        binding.MainForthPro4.ProductPrice.setText("40$");

    }

    public interface OnClickLisi {
        void onClick(String productImage, String productName , String productPrice);
    }

    public interface SeeAllOnClick {
        void seeAllClick(int i);
    }

    public interface finishOrderListener {
        void ClickFinish(String UserName, String PhoneNumber);
    }

    public interface goToAdminPage {
        void adminPage();
    }
}