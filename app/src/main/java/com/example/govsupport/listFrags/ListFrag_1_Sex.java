package com.example.govsupport.listFrags;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.govsupport.R;
import com.example.govsupport.data.NowUserCondition;


public class ListFrag_1_Sex extends Fragment {

    Context context;

    Button btn_next_list_frag1;
    ViewPager2 viewPager2_list;
    RadioGroup radioGroup_list_frag_1;

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list_frag_1_sex, container, false);
        viewPager2_list = getActivity().findViewById(R.id.viewPager2_list);
        radioGroup_list_frag_1 = rootView.findViewById(R.id.radioGroup_list_frag_1);
        btn_next_list_frag1 = rootView.findViewById(R.id.btn_next_list_frag1);
        btn_next_list_frag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId = radioGroup_list_frag_1.getCheckedRadioButtonId();
                if(checkedId == R.id.btn_male_list_frag1){
                    NowUserCondition.getInstance().setJA0101("Y");
                } else {
                    NowUserCondition.getInstance().setJA0102("Y");
                }

                int current = viewPager2_list.getCurrentItem();
                viewPager2_list.setCurrentItem(current+1);
            }
        });
        return rootView;
    }
}