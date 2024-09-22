package com.example.fooduct.Ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fooduct.Model.getProductData;
import com.example.fooduct.Orders.All_Pro_same_section;
import com.example.fooduct.Orders.Custom_See_All_Adapter;
import com.example.fooduct.Orders.OnClickListener;
import com.example.fooduct.databinding.FragmentSearchBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Search#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Search extends Fragment {
    FragmentSearchBinding binding;
    onChangeListener changeListener;
    adapterListener listener;
    public static RecyclerView rv;
    getProductData productData = new getProductData() ;

    ArrayList<All_Pro_same_section> proSameSections;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Search() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Search.
     */
    // TODO: Rename and change types and number of parameters
    public static Search newInstance(String param1, String param2) {
        Search fragment = new Search();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        changeListener = (onChangeListener)context;
        listener = (adapterListener) context;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater , container , false);
        View v = binding.getRoot();

        rv = binding.searchViewRv;

        proSameSections = new ArrayList<>();

        productData = new ViewModelProvider(this).get(getProductData.class);
        productData.getAllData();
        productData.mutableLiveData.observe(getViewLifecycleOwner(), new Observer<ArrayList<All_Pro_same_section>>() {
            @Override
            public void onChanged(ArrayList<All_Pro_same_section> allProSameSections) {
                proSameSections = allProSameSections;
                Custom_See_All_Adapter adapter = new Custom_See_All_Adapter(proSameSections, new OnClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        listener.listener(proSameSections.get(position).getImage() , proSameSections.get(position).getProName() , proSameSections.get(position).getProPrice());
                    }
                });


                binding.searchViewRv.setAdapter(adapter);
                binding.searchViewRv.setLayoutManager(new GridLayoutManager(getContext() ,2));
                binding.searchViewRv.setHasFixedSize(true);
            }
        });



        binding.SearchViewSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                changeListener.onChange(newText , proSameSections);

                return true;
            }
        });

        return v;
    }

    public interface  onChangeListener{
        void onChange(String newText , ArrayList<All_Pro_same_section> proSameSections);
    }

    public interface adapterListener{
        void listener(String productImage , String productName, String productPrice);
    }
}