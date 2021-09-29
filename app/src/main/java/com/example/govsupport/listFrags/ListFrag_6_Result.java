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
import com.example.govsupport.data.SearchConditionResults;
import com.example.govsupport.data.ServiceItem;
import com.example.govsupport.data.ServiceList;
import com.example.govsupport.data.SupportConditions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ListFrag_6_Result extends Fragment {

    Context context;

    ArrayList<ServiceItem> searchResults_serviceItem;

    RecyclerView recyclerView_list_frag6;

    String detailUrl = "https://api.odcloud.kr/api/gov24/v1/serviceDetail?page=1&perPage=50&serviceKey=yZJhcwbc4u6qNYRnb9G809Y%2B3GgjLV36H5bRO35YO6qD%2F3ZEZKCHna9WqLqQiVh2IAXRx8HXuBlzZrZs%2BLDcpg%3D%3D";
    String serviceUrl = "https://api.odcloud.kr/api/gov24/v1/serviceList?page=1&perPage=1200&serviceKey=yZJhcwbc4u6qNYRnb9G809Y%2B3GgjLV36H5bRO35YO6qD%2F3ZEZKCHna9WqLqQiVh2IAXRx8HXuBlzZrZs%2BLDcpg%3D%3D";


//    DetailList detailList;
//    ArrayList<Detail> detailItems;
//    ListFrag_6_detailsAdapter listFrag_6_detailsAdapter;

    ServiceList serviceList;
    ArrayList<ServiceItem> serviceItems;
    ListFrag_6_serviceListAdapter listFrag_6_serviceListAdapter;

    private static final String TAG = "List";

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = context;
        super.onAttach(context);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {



        StringRequest serviceRequest = new StringRequest(Request.Method.GET, serviceUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Result", "frag6의 serviceRequest의 onResponse() 호출");
                searchResults_serviceItem = new ArrayList<>();
                Gson gson = new Gson();
                serviceList = gson.fromJson(response, ServiceList.class);
                serviceItems = serviceList.data;

                // 0928 - searchResults가 nullPointer Error 발생! volley의 동기화문제인듯.
                // 0928-2 - searchResults에 아무것도 안들어감!!! 첫 request의 getSerachResult는 잘 작동하는데 불구하고.
                // 첫 request - searchResults 변경 - 두번째 request - datalist 변경;

                ArrayList<String> searchResults_Condition = SearchConditionResults.getInstance().getSearchResult();
                for(ServiceItem item: serviceItems){
                    if(searchResults_Condition.contains(item.서비스ID)){
                        searchResults_serviceItem.add(item);
                    }
                }
                Collections.sort(searchResults_serviceItem, new ServiceItemComparator());

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

        recyclerView_list_frag6 = rootView.findViewById(R.id.recyclerView_list_frag6);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView_list_frag6.setLayoutManager(layoutManager);

        return rootView;
    }


    public class ServiceItemComparator implements Comparator<ServiceItem>{

        @Override
        public int compare(ServiceItem o1, ServiceItem o2) {
            if(o1.조회수 > o2.조회수){
                return -1;
            } else if(o1.조회수 < o2.조회수){
                return 1;
            }
            return 0;
        }
    }
}