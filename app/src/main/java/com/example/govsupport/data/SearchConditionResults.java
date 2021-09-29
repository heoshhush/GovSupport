package com.example.govsupport.data;

import java.util.ArrayList;

public class SearchConditionResults {
    private static SearchConditionResults instance = null;
    private static ArrayList<String> searchResult;


    public static SearchConditionResults getInstance(){
        if(instance == null){
            instance = new SearchConditionResults();
        }
        return instance;
    }

    public void addItem(String str){
        if(searchResult == null){
            searchResult = new ArrayList<String>();
        }
        searchResult.add(str);
    }

    public ArrayList<String> getSearchResult(){
        return searchResult;
    }
}
