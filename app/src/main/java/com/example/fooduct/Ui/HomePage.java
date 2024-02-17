package com.example.fooduct.Ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fooduct.databinding.FragmentHomePageBinding;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePage extends Fragment {

    FragmentHomePageBinding binding;
    public OnClickLisi lisi;
    public SeeAllOnClick click;
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

        if (!MainActivity.orders.isEmpty())
            binding.MainFinishOrdering.setVisibility(View.VISIBLE);

        binding.MainFirstPro1.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick();
            }
        });
        binding.MainFirstPro2.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick();
            }
        });
        binding.MainFirstPro3.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick();
            }
        });
        binding.MainFirstPro4.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick();
            }
        });
        binding.MainSecondPro1.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick();
            }
        });
        binding.MainSecondPro2.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick();
            }
        });
        binding.MainSecondPro3.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick();
            }
        });
        binding.MainSecondPro4.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick();
            }
        });
        binding.MainThirdPro1.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick();
            }
        });
        binding.MainThirdPro2.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick();
            }
        });
        binding.MainThirdPro3.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick();
            }
        });
        binding.MainThirdPro4.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick();
            }
        });
        binding.MainForthPro1.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick();
            }
        });
        binding.MainForthPro2.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick();
            }
        });
        binding.MainForthPro3.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick();
            }
        });
        binding.MainForthPro4.ProductCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisi.onClick();
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
                OrderListener.ClickFinish(binding.MainUserName.getText().toString() ,
                        binding.MainPhoneNumber.getText().toString());
                binding.MainFinishOrdering.setVisibility(View.GONE);
            }
        });


        binding.MainUserName.setText(mAuth.getCurrentUser().getDisplayName());
        binding.MainPhoneNumber.setText(mAuth.getCurrentUser().getPhoneNumber()
                .toString().replace("+962" , "0"));


        binding.MainAdminPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adminPage.adminPage();
            }
        });



        return view;
    }

    public interface OnClickLisi {
        void onClick();
    }

    public interface SeeAllOnClick{
        void seeAllClick(int i);
    }

    public interface finishOrderListener{
        void ClickFinish(String UserName , String PhoneNumber);
    }

    public interface goToAdminPage{
        void adminPage();
    }
}