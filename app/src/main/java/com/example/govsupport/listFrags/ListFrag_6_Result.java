package com.example.govsupport.listFrags;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ListFrag_6_Result extends Fragment {

    Context context;

    ArrayList<ServiceItem> searchResults_serviceItem;

    RecyclerView recyclerView_list_frag6;

    boolean isLoading = false;
    int LOAD_IDX;

    String serviceUrl1 = "https://api.odcloud.kr/api/gov24/v1/serviceList?page=1&perPage=300&serviceKey=yZJhcwbc4u6qNYRnb9G809Y%2B3GgjLV36H5bRO35YO6qD%2F3ZEZKCHna9WqLqQiVh2IAXRx8HXuBlzZrZs%2BLDcpg%3D%3D";
    String serviceUrl2 = "https://api.odcloud.kr/api/gov24/v1/serviceList?page=2&perPage=300&serviceKey=yZJhcwbc4u6qNYRnb9G809Y%2B3GgjLV36H5bRO35YO6qD%2F3ZEZKCHna9WqLqQiVh2IAXRx8HXuBlzZrZs%2BLDcpg%3D%3D";
    String serviceUrl3 =   "https://api.odcloud.kr/api/gov24/v1/serviceList?page=3&perPage=300&serviceKey=yZJhcwbc4u6qNYRnb9G809Y%2B3GgjLV36H5bRO35YO6qD%2F3ZEZKCHna9WqLqQiVh2IAXRx8HXuBlzZrZs%2BLDcpg%3D%3D";
    String serviceUrl4 = "https://api.odcloud.kr/api/gov24/v1/serviceList?page=4&perPage=300&serviceKey=yZJhcwbc4u6qNYRnb9G809Y%2B3GgjLV36H5bRO35YO6qD%2F3ZEZKCHna9WqLqQiVh2IAXRx8HXuBlzZrZs%2BLDcpg%3D%3D";

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

        Log.d("Result", "frag6 호출");

        StringRequest serviceRequest = new StringRequest(Request.Method.GET, serviceUrl4, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Result", "frag6의 serviceRequest의 onResponse() 호출");
                searchResults_serviceItem = new ArrayList<>();
                Gson gson = new Gson();
                serviceList = gson.fromJson(response, ServiceList.class);
                serviceItems = serviceList.data;


                ArrayList<String> searchResults_Condition = SearchConditionResults.getInstance().getSearchResult();
                Log.d("Result", "조건에 맞는 검색 결과 : " + searchResults_Condition.size() + ", " + searchResults_Condition.toString());
                for(ServiceItem item: serviceItems){
                    if(searchResults_Condition.contains(item.서비스ID)){
                        searchResults_serviceItem.add(item);
                    }
                }
                Collections.sort(searchResults_serviceItem, new ServiceItemComparator());
                listFrag_6_serviceListAdapter = new ListFrag_6_serviceListAdapter();
                listFrag_6_serviceListAdapter.setItems(searchResults_serviceItem);
                listFrag_6_serviceListAdapter.notifyDataSetChanged();

                LOAD_IDX = 1;

                listFrag_6_serviceListAdapter.setOnItemClickListener(new ListFrag_6_serviceListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int pos) {
                        Bundle bundle = new Bundle();
                        bundle.putString("SVC_ID", searchResults_serviceItem.get(pos).서비스ID);
                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.addToBackStack(null);
                        ListFrag_7_DetailResult listFrag_7_detailResult = new ListFrag_7_DetailResult();
                        listFrag_7_detailResult.setArguments(bundle);
                        ft.add(R.id.fragment_list, listFrag_7_detailResult);
                        ft.commit();
                    }
                });


                recyclerView_list_frag6.setAdapter(listFrag_6_serviceListAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Result", "error");
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
        AppHelper.requestQueue = Volley.newRequestQueue(getContext());
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
        Log.d("Result", "Position :" + layoutManager.findLastCompletelyVisibleItemPosition());
        recyclerView_list_frag6.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                //data = searchResults_serviceItem
                if(!isLoading){
                    if(layoutManager != null && layoutManager.findLastCompletelyVisibleItemPosition() <= searchResults_serviceItem.size() - 1){
                        loadMoreItem();
                        isLoading = true;
                        //해야할일 1. adapter에서 loading 화면 만들기.
                        //해야할일 2. 첫 결과가 아무것도 없을때는 어떻게하나?
                        //해야할일 3. adapter에 뜨는 카드 수랑, searchResults_Condition.size()랑 다름...
                    }
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        if(layoutManager.findLastCompletelyVisibleItemPosition() == -1){
            for(int i = LOAD_IDX; i < 5; i++){
                loadMoreItem();
            }
        }

        return rootView;
    }

    public void loadMoreItem(){
//        searchResults_serviceItem.add(null);
//        listFrag_6_serviceListAdapter.notifyDataSetChanged(); // 혹은, 불러올 사이즈를 알면 notifyItemInserted(items.size()-1); 할 수 있다.

        String url = "";
        switch(LOAD_IDX){
            case 1:
                url = "https://api.odcloud.kr/api/gov24/v1/serviceList?page=1&perPage=300&serviceKey=yZJhcwbc4u6qNYRnb9G809Y%2B3GgjLV36H5bRO35YO6qD%2F3ZEZKCHna9WqLqQiVh2IAXRx8HXuBlzZrZs%2BLDcpg%3D%3D";
                break;
            case 2:
                url = "https://api.odcloud.kr/api/gov24/v1/serviceList?page=2&perPage=300&serviceKey=yZJhcwbc4u6qNYRnb9G809Y%2B3GgjLV36H5bRO35YO6qD%2F3ZEZKCHna9WqLqQiVh2IAXRx8HXuBlzZrZs%2BLDcpg%3D%3D";
                break;
            case 3:
                url =   "https://api.odcloud.kr/api/gov24/v1/serviceList?page=3&perPage=300&serviceKey=yZJhcwbc4u6qNYRnb9G809Y%2B3GgjLV36H5bRO35YO6qD%2F3ZEZKCHna9WqLqQiVh2IAXRx8HXuBlzZrZs%2BLDcpg%3D%3D";
                break;
        }

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                serviceList = gson.fromJson(response, ServiceList.class);
                serviceItems = serviceList.data;
                ArrayList<String> searchResults_Condition = SearchConditionResults.getInstance().getSearchResult();
                for(ServiceItem item : serviceItems){
                    if(searchResults_Condition.contains(item.서비스ID)){
                        searchResults_serviceItem.add(item);
                        Log.d("Result", LOAD_IDX + " loadMoreItem : " + item.서비스명);
                    }
                }
                listFrag_6_serviceListAdapter.notifyDataSetChanged();
                LOAD_IDX++;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Result", "error from scroll load");
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };

        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
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