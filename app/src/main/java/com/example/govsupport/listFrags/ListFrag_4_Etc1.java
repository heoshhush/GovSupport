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


public class ListFrag_4_Etc1 extends Fragment {

    ViewPager2 viewPager2_list;
    Button btn_next_list_frag4;
    RadioGroup radioGroup_listFrag_4_partOne;
    RadioGroup radioGroup_listFrag_4_partTwo;
    RadioGroup radioGroup_listFrag_4_partThree;
    RadioGroup radioGroup_listFrag_4_partFour;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list_frag_4_etc1, container, false);

        radioGroup_listFrag_4_partOne = rootView.findViewById(R.id.radioGroup_listFrag_4_partOne);
        radioGroup_listFrag_4_partTwo = rootView.findViewById(R.id.radioGroup_listFrag_4_partTwo);
        radioGroup_listFrag_4_partThree = rootView.findViewById(R.id.radioGroup_listFrag_4_partThree);
        radioGroup_listFrag_4_partFour = rootView.findViewById(R.id.radioGroup_listFrag_4_partFour);

        viewPager2_list = getActivity().findViewById(R.id.viewPager2_list);
        btn_next_list_frag4 = rootView.findViewById(R.id.btn_next_list_frag4);

        btn_next_list_frag4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int partOneChecked = radioGroup_listFrag_4_partOne.getCheckedRadioButtonId();
                switch(partOneChecked){
                    case R.id.btn_list_etc_1_1 : //난임
                        NowUserCondition.getInstance().setJA0301("Y");
                        NowUserCondition.getInstance().addItem("JA0301");
                        break;
                    case R.id.btn_list_etc_1_2 : //임신부
                        NowUserCondition.getInstance().setJA0302("Y");
                        NowUserCondition.getInstance().addItem("JA0302");
                        break;
                    case R.id.btn_list_etc_1_3 : //출산후
                        NowUserCondition.getInstance().setJA0303("Y");
                        NowUserCondition.getInstance().addItem("JA0303");
                        break;
                    case R.id.btn_list_etc1_partOne_null :
                        NowUserCondition.getInstance().setJA0301("Y");
                        NowUserCondition.getInstance().addItem("JA0301");
                        NowUserCondition.getInstance().setJA0302("Y");
                        NowUserCondition.getInstance().addItem("JA0302");
                        NowUserCondition.getInstance().setJA0303("Y");
                        NowUserCondition.getInstance().addItem("JA0303");
                        break;
                }

                int partTwoChecked = radioGroup_listFrag_4_partTwo.getCheckedRadioButtonId();
                switch(partTwoChecked){
                    case R.id.btn_list_etc_1_4 : //심한 장애
                        NowUserCondition.getInstance().setJA0304("Y");
                        NowUserCondition.getInstance().addItem("JA0304");
                        break;
                    case R.id.btn_list_etc_1_5 : //심하지 않은 장애
                        NowUserCondition.getInstance().setJA0305("Y");
                        NowUserCondition.getInstance().addItem("JA0305");
                        break;
                    case R.id.btn_list_etc1_partTwo_null:
                        NowUserCondition.getInstance().setJA0304("Y");
                        NowUserCondition.getInstance().addItem("JA0304");
                        NowUserCondition.getInstance().setJA0305("Y");
                        NowUserCondition.getInstance().addItem("JA0305");
                        break;
                }

                int partThreeChecked = radioGroup_listFrag_4_partThree.getCheckedRadioButtonId();
                switch(partThreeChecked){
                    case R.id.btn_list_etc_1_6 : // 국가유공자
                        NowUserCondition.getInstance().setJA0306("Y");
                        NowUserCondition.getInstance().addItem("JA0306");
                        break;
                    case R.id.btn_list_etc_1_7 : // 제대군인
                        NowUserCondition.getInstance().setJA0307("Y");
                        NowUserCondition.getInstance().addItem("JA0307");
                        break;
                    case R.id.btn_list_etc1_partThree_null :
                        NowUserCondition.getInstance().setJA0306("Y");
                        NowUserCondition.getInstance().addItem("JA0306");
                        NowUserCondition.getInstance().setJA0307("Y");
                        NowUserCondition.getInstance().addItem("JA0307");
                        break;
                }

                int partFourChecked = radioGroup_listFrag_4_partFour.getCheckedRadioButtonId();
                switch(partFourChecked){
                    case R.id.btn_list_etc_1_8 : //경관농업인
                        NowUserCondition.getInstance().setJA0308("Y");
                        NowUserCondition.getInstance().addItem("JA0308");
                        break;
                    case R.id.btn_list_etc_1_9 : //경종농업인
                        NowUserCondition.getInstance().setJA0309("Y");
                        NowUserCondition.getInstance().addItem("JA0309");
                        break;
                    case R.id.btn_list_etc_1_10 : //과수농업인
                        NowUserCondition.getInstance().setJA0310("Y");
                        NowUserCondition.getInstance().addItem("JA0310");
                        break;
                    case R.id.btn_list_etc_1_11 : //원예/특용 농업인
                        NowUserCondition.getInstance().setJA0311("Y");
                        NowUserCondition.getInstance().addItem("JA0311");
                        break;
                    case R.id.btn_list_etc_1_12 : //농지소유자
                        NowUserCondition.getInstance().setJA0312("Y");
                        NowUserCondition.getInstance().addItem("JA0312");
                        break;
                    case R.id.btn_list_etc_1_13 : //기타농업인
                        NowUserCondition.getInstance().setJA0313("Y");
                        NowUserCondition.getInstance().addItem("JA0313");
                        break;
                    case R.id.btn_list_etc_1_14 : //연근해/구획어업인
                        NowUserCondition.getInstance().setJA0314("Y");
                        NowUserCondition.getInstance().addItem("JA0314");
                        break;
                    case R.id.btn_list_etc_1_15 : //양식업인
                        NowUserCondition.getInstance().setJA0315("Y");
                        NowUserCondition.getInstance().addItem("JA0315");
                        break;
                    case R.id.btn_list_etc_1_16 : //어선소유자
                        NowUserCondition.getInstance().setJA0316("Y");
                        NowUserCondition.getInstance().addItem("JA0316");
                        break;
                    case R.id.btn_list_etc_1_17 : //기타어업인
                        NowUserCondition.getInstance().setJA0317("Y");
                        NowUserCondition.getInstance().addItem("JA0317");
                        break;
                    case R.id.btn_list_etc_1_18 : //축산업인
                        NowUserCondition.getInstance().setJA0318("Y");
                        NowUserCondition.getInstance().addItem("JA0318");
                        break;
                    case R.id.btn_list_etc_1_19 : //임업인
                        NowUserCondition.getInstance().setJA0319("Y");
                        NowUserCondition.getInstance().addItem("JA0319");
                        break;
                    case R.id.btn_list_etc1_partFour_null :
                        NowUserCondition.getInstance().setJA0308("Y");
                        NowUserCondition.getInstance().addItem("JA0308");
                        NowUserCondition.getInstance().setJA0309("Y");
                        NowUserCondition.getInstance().addItem("JA0309");
                        NowUserCondition.getInstance().setJA0310("Y");
                        NowUserCondition.getInstance().addItem("JA0310");
                        NowUserCondition.getInstance().setJA0311("Y");
                        NowUserCondition.getInstance().addItem("JA0311");
                        NowUserCondition.getInstance().setJA0312("Y");
                        NowUserCondition.getInstance().addItem("JA0312");
                        NowUserCondition.getInstance().setJA0313("Y");
                        NowUserCondition.getInstance().addItem("JA0313");
                        NowUserCondition.getInstance().setJA0314("Y");
                        NowUserCondition.getInstance().addItem("JA0314");
                        NowUserCondition.getInstance().setJA0315("Y");
                        NowUserCondition.getInstance().addItem("JA0315");
                        NowUserCondition.getInstance().setJA0316("Y");
                        NowUserCondition.getInstance().addItem("JA0316");
                        NowUserCondition.getInstance().setJA0317("Y");
                        NowUserCondition.getInstance().addItem("JA0317");
                        NowUserCondition.getInstance().setJA0318("Y");
                        NowUserCondition.getInstance().addItem("JA0318");
                        NowUserCondition.getInstance().setJA0319("Y");
                        NowUserCondition.getInstance().addItem("JA0319");
                        break;
                }

                int current = viewPager2_list.getCurrentItem();
                viewPager2_list.setCurrentItem(current+1);
            }
        });
        return rootView;
    }
}