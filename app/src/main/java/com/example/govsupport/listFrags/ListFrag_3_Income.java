package com.example.govsupport.listFrags;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.govsupport.ListFrag;
import com.example.govsupport.R;
import com.example.govsupport.data.NowUserCondition;
import com.example.govsupport.data.SupportConditions;


public class ListFrag_3_Income extends Fragment {

    ViewPager2 viewPager2_list;
    Button btn_next_list_frag3;
    RadioGroup radioGroup_list_frag_3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list_frag_3__income, container, false);

        viewPager2_list = getActivity().findViewById(R.id.viewPager2_list);
        btn_next_list_frag3 = rootView.findViewById(R.id.btn_next_list_frag3);
        radioGroup_list_frag_3 = rootView.findViewById(R.id.radioGroup_list_frag_3);
        btn_next_list_frag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = viewPager2_list.getCurrentItem();
                viewPager2_list.setCurrentItem(current+1);
                NowUserCondition instance = NowUserCondition.getInstance();
                Log.d("HOHO", instance.getJA0101() + ", " + instance.getJA0103());

                // ListFrag의 SupportConditions를 가져와야한다.
            }
        });

        return rootView;
    }
}