package com.example.govsupport.listFrags;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.govsupport.R;
import com.example.govsupport.data.NowUserCondition;


public class ListFrag_2_Age extends Fragment {

    ViewPager2 viewPager2_list;
    Button btn_next_list_frag2;
    RadioGroup radioGroup_list_frag_2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list_frag_2__age, container, false);

        viewPager2_list = getActivity().findViewById(R.id.viewPager2_list);
        radioGroup_list_frag_2 = rootView.findViewById(R.id.radioGroup_list_frag_2);
        btn_next_list_frag2 = rootView.findViewById(R.id.btn_next_list_frag2);
        btn_next_list_frag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NowUserCondition instance = NowUserCondition.getInstance();
                int checkedId = radioGroup_list_frag_2.getCheckedRadioButtonId();
                switch(checkedId){
                    case R.id.btn_list_age1 :
                        instance.setJA0103("Y");
                        instance.addItem("JA0103");
                        break;
                    case R.id.btn_list_age2 :
                        instance.setJA0104("Y");
                        instance.addItem("JA0104");
                        break;
                    case R.id.btn_list_age3 :
                        instance.setJA0105("Y");
                        instance.addItem("JA0105");
                        break;
                    case R.id.btn_list_age4 :
                        instance.setJA0106("Y");
                        instance.addItem("JA0106");
                        break;
                    case R.id.btn_list_age5 :
                        instance.setJA0107("Y");
                        instance.addItem("JA0107");
                        break;
                    case R.id.btn_list_age6 :
                        instance.setJA0108("Y");
                        instance.addItem("JA0108");
                        break;
                    case R.id.btn_list_age7 :
                        instance.setJA0109("Y");
                        instance.addItem("JA0109");
                        break;
                }

                int current = viewPager2_list.getCurrentItem();
                viewPager2_list.setCurrentItem(current+1);
            }
        });

        return rootView;
    }
}