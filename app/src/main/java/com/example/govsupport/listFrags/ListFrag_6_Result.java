package com.example.govsupport.listFrags;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.govsupport.R;
import com.example.govsupport.data.Condition;
import com.example.govsupport.data.NowUserCondition;
import com.example.govsupport.data.SupportConditions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListFrag_6_Result extends Fragment {

    Context context;
    ArrayList<String> searchResults;

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = context;
        super.onAttach(context);
    }

    String conditionUrl = "https://api.odcloud.kr/api/gov24/v1/supportConditions?page=1&perPage=500&serviceKey=yZJhcwbc4u6qNYRnb9G809Y%2B3GgjLV36H5bRO35YO6qD%2F3ZEZKCHna9WqLqQiVh2IAXRx8HXuBlzZrZs%2BLDcpg%3D%3D";
    SupportConditions supportConditions;
    ArrayList<Condition> items;

    static RequestQueue requestQueue = null;

    private static final String TAG = "List";

    public void setSupportConditions(SupportConditions supportConditions) {
        this.supportConditions = supportConditions;
    }

    public SupportConditions getSupportConditions() {
        return supportConditions;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context);
        }

        StringRequest request = new StringRequest(Request.Method.GET, conditionUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                supportConditions = gson.fromJson(response, SupportConditions.class);
                items = supportConditions.data;
                getSearchResult(items);

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

        request.setShouldCache(true);
        requestQueue.add(request);

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list_frag_6__result, container, false);


        return rootView;
    }

    public void getSearchResult(ArrayList<Condition> items){
        NowUserCondition instance = NowUserCondition.getInstance();
        ArrayList<String> checkedItems = instance.getCheckedItems();

        searchResults = new ArrayList<>();

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
                searchResults.add(x.getSVC_ID());
                Log.d("Result", x.getSVC_ID());
            }
            }
        }
}