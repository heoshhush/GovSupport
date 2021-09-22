package com.example.govsupport;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.govsupport.data.Condition;
import com.example.govsupport.data.SupportConditions;
import com.example.govsupport.listFrags.ListFrag_1_Sex;
import com.example.govsupport.listFrags.ListFrag_2_Age;
import com.example.govsupport.listFrags.ListFrag_3_Income;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListFrag extends Fragment {
    String conditionUrl = "https://api.odcloud.kr/api/gov24/v1/supportConditions?page=1&perPage=500&serviceKey=yZJhcwbc4u6qNYRnb9G809Y%2B3GgjLV36H5bRO35YO6qD%2F3ZEZKCHna9WqLqQiVh2IAXRx8HXuBlzZrZs%2BLDcpg%3D%3D";
    SupportConditions supportConditions;

    private static final String TAG = "List";
    Context context;
    static RequestQueue requestQueue = null;

    public SupportConditions getSupportConditions() {
        return supportConditions;
    }

    public void setSupportConditions(SupportConditions supportConditions) {
        this.supportConditions = supportConditions;
    }

    String conditionStr;

    ViewPager2 viewPager2_list;
    TabLayout tabLayout_list_frag;

    ListFrag_1_Sex listFrag_1_sex;
    ListFrag_2_Age listFrag_2_age;
    ListFrag_3_Income listFrag_3_income;

    Condition userCondition = new Condition();

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context);
        }

        Log.d(TAG, "ListFrag");


        StringRequest request = new StringRequest(Request.Method.GET, conditionUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response);
                Gson gson = new Gson();
                supportConditions = gson.fromJson(response, SupportConditions.class);
                Log.d(TAG, String.valueOf(supportConditions.currentCount));
                Log.d(TAG, supportConditions.data.get(0).JA0101);
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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list_, container, false);

        viewPager2_list = rootView.findViewById(R.id.viewPager2_list);
        MyPagerAdapter adapter = new MyPagerAdapter(getActivity());
        tabLayout_list_frag = rootView.findViewById(R.id.tabLayout_list_frag);

        final List<String> tabElement = Arrays.asList("성별", "나이", "수입");

        listFrag_1_sex = new ListFrag_1_Sex();
        listFrag_2_age = new ListFrag_2_Age();
        listFrag_3_income = new ListFrag_3_Income();
        adapter.addItem(listFrag_1_sex);
        adapter.addItem(listFrag_2_age);
        adapter.addItem(listFrag_3_income);

        viewPager2_list.setAdapter(adapter);

        new TabLayoutMediator(tabLayout_list_frag, viewPager2_list, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                TextView textView = new TextView(getContext());
                textView.setText(tabElement.get(position));
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                tab.setCustomView(textView);
            }
        }).attach();


        return rootView;
    }

    class MyPagerAdapter extends FragmentStateAdapter {
        ArrayList<Fragment> items = new ArrayList<>();

        public MyPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        public void addItem(Fragment item){
            items.add(item);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return items.get(position);
        }


        @Override
        public int getItemCount() {
            return items.size();
        }
    }
}