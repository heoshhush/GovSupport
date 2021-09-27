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
                int checkedId = radioGroup_list_frag_3.getCheckedRadioButtonId();
                switch(checkedId){
                    case R.id.btn_list_income1 :
                        NowUserCondition.getInstance().setJA0201("Y");
                        NowUserCondition.getInstance().addItem("JA0201");
                        break;
                    case R.id.btn_list_income2 :
                        NowUserCondition.getInstance().setJA0202("Y");
                        NowUserCondition.getInstance().addItem("JA0202");
                        break;
                    case R.id.btn_list_income3 :
                        NowUserCondition.getInstance().setJA0203("Y");
                        NowUserCondition.getInstance().addItem("JA0203");
                        break;
                    case R.id.btn_list_income4 :
                        NowUserCondition.getInstance().setJA0204("Y");
                        NowUserCondition.getInstance().addItem("JA0204");
                        break;
                    case R.id.btn_list_income5 :
                        NowUserCondition.getInstance().setJA0205("Y");
                        NowUserCondition.getInstance().addItem("JA0205");
                        break;
                }

                int current = viewPager2_list.getCurrentItem();
                viewPager2_list.setCurrentItem(current+1);

                // 이전에 한 것 : 1. getActivity().findViewById()로 부모 프래그먼트의 View 가져온 것. 2. 싱글톤 패턴 만들어서 공유 데이터 만든 것.
                // 이제 해야할 것 " ListFrag의 SupportConditions를 가져와야한다. 즉, Volley로 가져온 데이터를 여기서 다뤄야한다.

            }
        });

        return rootView;
    }
}