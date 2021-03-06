package com.example.govsupport.listFrags;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.govsupport.AppHelper;
import com.example.govsupport.R;
import com.example.govsupport.data.Condition;
import com.example.govsupport.data.NowUserCondition;
import com.example.govsupport.data.SearchConditionResults;
import com.example.govsupport.data.SupportConditions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListFrag_5_Etc2 extends Fragment {
    private static final String TAG = "Result";

    String conditionUrl = "https://api.odcloud.kr/api/gov24/v1/supportConditions?page=1&perPage=500&serviceKey=yZJhcwbc4u6qNYRnb9G809Y%2B3GgjLV36H5bRO35YO6qD%2F3ZEZKCHna9WqLqQiVh2IAXRx8HXuBlzZrZs%2BLDcpg%3D%3D";
    SupportConditions supportConditions;
    ArrayList<Condition> items;

    ListFrag_6_Result listFrag_6_result;
    Button btn_next_list_frag5;

    RadioGroup radioGroup_listFrag_5_partOne;
    RadioGroup radioGroup_listFrag_5_partTwo;
    RadioGroup radioGroup_listFrag_5_partThree;
    RadioGroup radioGroup_listFrag_5_partFour;
    CheckBox btn_list_etc_2_12;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if(AppHelper.requestQueue == null){
            AppHelper.requestQueue = Volley.newRequestQueue(getContext());
        }

        StringRequest request = new StringRequest(Request.Method.GET, conditionUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Result", "frag5??? request??? onResponse() ??????");
                Gson gson = new Gson();
                supportConditions = gson.fromJson(response, SupportConditions.class);
                items = supportConditions.data;
//                getSearchResult(items);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "error");
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };

        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);

        super.onCreate(savedInstanceState);
    }

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
                    case R.id.btn_list_etc_2_1: // ??????????????????
                        NowUserCondition.getInstance().setJA0320("Y");
                        NowUserCondition.getInstance().addItem("JA0320");
                        break;
                    case R.id.btn_list_etc_2_2: // ??????/????????????
                        NowUserCondition.getInstance().setJA0321("Y");
                        NowUserCondition.getInstance().addItem("JA0321");
                        break;
                    case R.id.btn_list_etc2_partOne_null :
                        NowUserCondition.getInstance().setJA0320("Y");
                        NowUserCondition.getInstance().addItem("JA0321");
                        NowUserCondition.getInstance().setJA0321("Y");
                        NowUserCondition.getInstance().addItem("JA0321");
                }
                int partTwoChecked = radioGroup_listFrag_5_partTwo.getCheckedRadioButtonId();
                switch (partTwoChecked){
                    case R.id.btn_list_etc_2_3: //???????????????
                        NowUserCondition.getInstance().setJA0401("Y");
                        NowUserCondition.getInstance().addItem("JA0401");
                        break;
                    case R.id.btn_list_etc_2_4: //??????????????????
                        NowUserCondition.getInstance().setJA0402("Y");
                        NowUserCondition.getInstance().addItem("JA0402");
                        break;
                    case R.id.btn_list_etc_2_5: //???????????????/????????????
                        NowUserCondition.getInstance().setJA0403("Y");
                        NowUserCondition.getInstance().addItem("JA0403");
                        break;
                    case R.id.btn_list_etc_2_6: //1?????????
                        NowUserCondition.getInstance().setJA0404("Y");
                        NowUserCondition.getInstance().addItem("JA0404");
                        break;
                    case R.id.btn_list_etc2_partTwo_null:
                        NowUserCondition.getInstance().setJA0401("Y");
                        NowUserCondition.getInstance().addItem("JA0401");
                        NowUserCondition.getInstance().setJA0402("Y");
                        NowUserCondition.getInstance().addItem("JA0402");
                        NowUserCondition.getInstance().setJA0403("Y");
                        NowUserCondition.getInstance().addItem("JA0403");
                        NowUserCondition.getInstance().setJA0404("Y");
                        NowUserCondition.getInstance().addItem("JA0404");
                        break;
                }
                int partThreeChecked = radioGroup_listFrag_5_partThree.getCheckedRadioButtonId();
                switch (partThreeChecked){
                    case R.id.btn_list_etc_2_7: //??????/??????/?????????
                        NowUserCondition.getInstance().setJA0405("Y");
                        NowUserCondition.getInstance().addItem("JA0405");
                        break;
                    case R.id.btn_list_etc_2_8: //??????/??????/???????????????
                        NowUserCondition.getInstance().setJA0406("Y");
                        NowUserCondition.getInstance().addItem("JA0406");
                        break;
                    case R.id.btn_list_etc_2_9: //??????/????????????
                        NowUserCondition.getInstance().setJA0407("Y");
                        NowUserCondition.getInstance().addItem("JA0407");
                        break;
                    case R.id.btn_list_etc2_partThree_null :
                        NowUserCondition.getInstance().setJA0405("Y");
                        NowUserCondition.getInstance().addItem("JA0405");
                        NowUserCondition.getInstance().setJA0406("Y");
                        NowUserCondition.getInstance().addItem("JA0406");
                        NowUserCondition.getInstance().setJA0407("Y");
                        NowUserCondition.getInstance().addItem("JA0407");
                        break;
                }
                int partFourChecked = radioGroup_listFrag_5_partFour.getCheckedRadioButtonId();
                switch(partFourChecked){
                    case R.id.btn_list_etc_2_10: //?????????/?????????
                        NowUserCondition.getInstance().setJA0408("Y");
                        NowUserCondition.getInstance().addItem("JA0408");
                        break;
                    case R.id.btn_list_etc_2_11: //?????????/?????????
                        NowUserCondition.getInstance().setJA0409("Y");
                        NowUserCondition.getInstance().addItem("JA0409");
                        break;
                    case R.id.btn_list_etc2_partFour_null:
                        NowUserCondition.getInstance().setJA0408("Y");
                        NowUserCondition.getInstance().addItem("JA0408");
                        NowUserCondition.getInstance().setJA0409("Y");
                        NowUserCondition.getInstance().addItem("JA0409");
                        break;
                }

                if(btn_list_etc_2_12.isChecked()){
                    NowUserCondition.getInstance().setJA0411("Y");
                }


                getSearchResult(items);
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.addToBackStack(null);
                ft.add(R.id.fragment_list, listFrag_6_result).commit();
            }
        });

        return rootView;
    }

    public void getSearchResult(ArrayList<Condition> items){
        Log.d("Result", "getSearchResult() ??????");
        NowUserCondition instance = NowUserCondition.getInstance();
        ArrayList<String> checkedItems = instance.getCheckedItems();

        for(Condition x : items){
            boolean flag = true;
            for(String checkedItem : checkedItems){
                String str1 = x.getIdx(checkedItem) == null ? "null" : x.getIdx(checkedItem);
                String str2 = instance.getIdx(checkedItem);
                if(!str1.equals(str2)){
                    flag = false;
                }
            }
            if(flag){
                SearchConditionResults.getInstance().addItem(x.getSVC_ID());
            }
        }
    }
}