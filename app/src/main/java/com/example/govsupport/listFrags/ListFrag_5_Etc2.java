package com.example.govsupport.listFrags;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import com.example.govsupport.R;
import com.example.govsupport.data.NowUserCondition;

public class ListFrag_5_Etc2 extends Fragment {

    ListFrag_6_Result listFrag_6_result;
    Button btn_next_list_frag5;

    RadioGroup radioGroup_listFrag_5_partOne;
    RadioGroup radioGroup_listFrag_5_partTwo;
    RadioGroup radioGroup_listFrag_5_partThree;
    RadioGroup radioGroup_listFrag_5_partFour;
    CheckBox btn_list_etc_2_12;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list_frag_5_etc2, container, false);
        listFrag_6_result = new ListFrag_6_Result();

        radioGroup_listFrag_5_partOne = rootView.findViewById(R.id.radioGroup_listFrag_5_partOne);
        radioGroup_listFrag_5_partTwo = rootView.findViewById(R.id.radioGroup_listFrag_5_partTwo);
        radioGroup_listFrag_5_partThree = rootView.findViewById(R.id.radioGroup_listFrag_5_partThree);
        radioGroup_listFrag_5_partFour = rootView.findViewById(R.id.radioGroup_listFrag_5_partFour);
        btn_list_etc_2_12 = rootView.findViewById(R.id.btn_list_etc_2_12);

        btn_next_list_frag5 = rootView.findViewById(R.id.btn_next_list_frag5);
        btn_next_list_frag5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int partOneChecked = radioGroup_listFrag_5_partOne.getCheckedRadioButtonId();
                switch(partOneChecked){
                    case R.id.btn_list_etc_2_1: // 초중고등학생
                        NowUserCondition.getInstance().setJA0320("Y");
                        NowUserCondition.getInstance().addItem("JA0320");
                        break;
                    case R.id.btn_list_etc_2_2: // 대학/대학원생
                        NowUserCondition.getInstance().setJA0321("Y");
                        NowUserCondition.getInstance().addItem("JA0321");
                        break;
                }
                int partTwoChecked = radioGroup_listFrag_5_partTwo.getCheckedRadioButtonId();
                switch (partTwoChecked){
                    case R.id.btn_list_etc_2_3: //다문화가족
                        NowUserCondition.getInstance().setJA0401("Y");
                        NowUserCondition.getInstance().addItem("JA0401");
                        break;
                    case R.id.btn_list_etc_2_4: //북한이탈주민
                        NowUserCondition.getInstance().setJA0402("Y");
                        NowUserCondition.getInstance().addItem("JA0402");
                        break;
                    case R.id.btn_list_etc_2_5: //한부모가정/조손가정
                        NowUserCondition.getInstance().setJA0403("Y");
                        NowUserCondition.getInstance().addItem("JA0403");
                        break;
                    case R.id.btn_list_etc_2_6: //1인가구
                        NowUserCondition.getInstance().setJA0404("Y");
                        NowUserCondition.getInstance().addItem("JA0404");
                        break;
                }
                int partThreeChecked = radioGroup_listFrag_5_partThree.getCheckedRadioButtonId();
                switch (partThreeChecked){
                    case R.id.btn_list_etc_2_7: //질병/부상/질환자
                        NowUserCondition.getInstance().setJA0405("Y");
                        NowUserCondition.getInstance().addItem("JA0405");
                        break;
                    case R.id.btn_list_etc_2_8: //중증/난치/희귀질환자
                        NowUserCondition.getInstance().setJA0406("Y");
                        NowUserCondition.getInstance().addItem("JA0406");
                        break;
                    case R.id.btn_list_etc_2_9: //요양/치매환자
                        NowUserCondition.getInstance().setJA0407("Y");
                        NowUserCondition.getInstance().addItem("JA0407");
                        break;
                }
                int partFourChecked = radioGroup_listFrag_5_partFour.getCheckedRadioButtonId();
                switch(partFourChecked){
                    case R.id.btn_list_etc_2_10: //근로자/직장인
                        NowUserCondition.getInstance().setJA0408("Y");
                        NowUserCondition.getInstance().addItem("JA0408");
                        break;
                    case R.id.btn_list_etc_2_11: //구직자/실업자
                        NowUserCondition.getInstance().setJA0409("Y");
                        NowUserCondition.getInstance().addItem("JA0409");
                        break;
                }

                if(btn_list_etc_2_12.isChecked()){
                    NowUserCondition.getInstance().setJA0411("Y");
                }

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_list, listFrag_6_result).commit();
            }
        });

        return rootView;
    }
}