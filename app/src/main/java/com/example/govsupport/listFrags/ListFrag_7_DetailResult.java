package com.example.govsupport.listFrags;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.govsupport.AppHelper;
import com.example.govsupport.R;
import com.example.govsupport.data.Detail;
import com.example.govsupport.data.DetailList;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ListFrag_7_DetailResult extends Fragment {
    String SVC_ID;
    TextView tv_listFrag7_1;
    TextView tv_listFrag7_2;
    TextView tv_listFrag7_3;
    TextView tv_listFrag7_4;
    TextView tv_listFrag7_5;
    DetailList detailList;
    ArrayList<Detail> detailItems;

    Detail targetResult;

    @Override
    public void onAttach(@NonNull Context context) {
        SVC_ID = getArguments().getString("SVC_ID");
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("Result",SVC_ID);
        String detailUrl = "https://api.odcloud.kr/api/gov24/v1/serviceDetail?page=1&perPage=1&cond%5BSVC_ID%3A%3AEQ%5D=" + SVC_ID + "&serviceKey=yZJhcwbc4u6qNYRnb9G809Y%2B3GgjLV36H5bRO35YO6qD%2F3ZEZKCHna9WqLqQiVh2IAXRx8HXuBlzZrZs%2BLDcpg%3D%3D";

        StringRequest detailRequest = new StringRequest(Request.Method.GET, "detailUrl", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                detailList = gson.fromJson(response, DetailList.class);
                detailItems = new ArrayList<>();
                detailItems = detailList.data;

                targetResult = detailItems.get(0);
                Log.d("Result",targetResult.서비스명);

                tv_listFrag7_1.setText(targetResult.SVC_ID);
                tv_listFrag7_2.setText(targetResult.서비스명);
                tv_listFrag7_3.setText(targetResult.지원대상);
                tv_listFrag7_4.setText(targetResult.신청기한);
                tv_listFrag7_5.setText(targetResult.지원내용);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Result", "frag7_onResponse_Error");
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };

        detailRequest.setShouldCache(false);
        AppHelper.requestQueue.add(detailRequest);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list_frag_7__detail, container, false);
        tv_listFrag7_1 = rootView.findViewById(R.id.tv_listFrag7_1);
        tv_listFrag7_2 = rootView.findViewById(R.id.tv_listFrag7_2);
        tv_listFrag7_3 = rootView.findViewById(R.id.tv_listFrag7_3);
        tv_listFrag7_4 = rootView.findViewById(R.id.tv_listFrag7_4);
        tv_listFrag7_5 = rootView.findViewById(R.id.tv_listFrag7_5);
        return rootView;
    }
}