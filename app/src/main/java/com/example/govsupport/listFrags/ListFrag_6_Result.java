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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.govsupport.AppHelper;
import com.example.govsupport.R;
import com.example.govsupport.data.Condition;
import com.example.govsupport.data.Detail;
import com.example.govsupport.data.DetailList;
import com.example.govsupport.data.NowUserCondition;
import com.example.govsupport.data.ServiceItem;
import com.example.govsupport.data.ServiceList;
import com.example.govsupport.data.SupportConditions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListFrag_6_Result extends Fragment {

    Context context;
    ArrayList<String> searchResults;
    ArrayList<ServiceItem> searchResults_serviceItem;

    RecyclerView recyclerView_list_frag6;

    String conditionUrl = "https://api.odcloud.kr/api/gov24/v1/supportConditions?page=1&perPage=500&serviceKey=yZJhcwbc4u6qNYRnb9G809Y%2B3GgjLV36H5bRO35YO6qD%2F3ZEZKCHna9WqLqQiVh2IAXRx8HXuBlzZrZs%2BLDcpg%3D%3D";
    String detailUrl = "https://api.odcloud.kr/api/gov24/v1/serviceDetail?page=1&perPage=50&serviceKey=yZJhcwbc4u6qNYRnb9G809Y%2B3GgjLV36H5bRO35YO6qD%2F3ZEZKCHna9WqLqQiVh2IAXRx8HXuBlzZrZs%2BLDcpg%3D%3D";
    String serviceUrl = "https://api.odcloud.kr/api/gov24/v1/serviceDetail?page=1&perPage=1&cond%5BSVC_ID%3A%3AEQ%5D=WLU000000020&serviceKey=yZJhcwbc4u6qNYRnb9G809Y%2B3GgjLV36H5bRO35YO6qD%2F3ZEZKCHna9WqLqQiVh2IAXRx8HXuBlzZrZs%2BLDcpg%3D%3D";

    SupportConditions supportConditions;
    ArrayList<Condition> items;

    DetailList detailList;
    ArrayList<Detail> detailItems;
    ListFrag_6_detailsAdapter listFrag_6_detailsAdapter;

    ServiceList serviceList;
    ArrayList<ServiceItem> serviceItems;
    ListFrag_6_serviceListAdapter listFrag_6_serviceListAdapter;

    private static final String TAG = "List";

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = context;
        super.onAttach(context);
    }


    public void setSupportConditions(SupportConditions supportConditions) {
        this.supportConditions = supportConditions;
    }

    public SupportConditions getSupportConditions() {
        return supportConditions;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        if(AppHelper.requestQueue == null){
            AppHelper.requestQueue = Volley.newRequestQueue(context);
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

        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);

        StringRequest serviceRequest = new StringRequest(Request.Method.GET, serviceUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                serviceList = gson.fromJson(response, ServiceList.class);
                serviceItems = serviceList.data;

                // 0928 - searchResults가 nullPointer Error 발생! volley의 동기화문제인듯.
                for(ServiceItem item: serviceItems){
                    if(searchResults.contains(item.SVC_ID)){
                        searchResults_serviceItem.add(item);
                    }
                }

                // 데이터 작업은 여기서 미리 다 해주자.
                listFrag_6_serviceListAdapter = new ListFrag_6_serviceListAdapter();
                listFrag_6_serviceListAdapter.setItems(searchResults_serviceItem);
                listFrag_6_serviceListAdapter.notifyDataSetChanged();
                recyclerView_list_frag6.setAdapter(listFrag_6_serviceListAdapter);
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

        serviceRequest.setShouldCache(false);
        AppHelper.requestQueue.add(serviceRequest);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list_frag_6__result, container, false);

        //volley는 비동기이기 때문에, ui 작업 등을 데이터 호출 완료 후에 하려면
        // volley가 제공하는 callback인 onResponse에서 비동기로 예상되는 작업을 미리 다 해주어야한다.(volley가 콜백을 제공하는 이유)

        recyclerView_list_frag6 = rootView.findViewById(R.id.recyclerView_list_frag6);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView_list_frag6.setLayoutManager(layoutManager);

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