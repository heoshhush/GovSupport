package com.example.govsupport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView botNavView;
    SearchFrag searchFrag;
    ListFrag listFrag;
    BookMarkFrag bookMarkFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchFrag = new SearchFrag();

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.frameLayout_main, searchFrag).commit();
        botNavView = findViewById(R.id.botNavView);
        botNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                switch(itemId){
                    case R.id.search_menu :
                        if(searchFrag != null)fm.beginTransaction().show(searchFrag).commit();
                        if(listFrag != null) fm.beginTransaction().hide(listFrag).commit();
                        if(bookMarkFrag != null) fm.beginTransaction().hide(bookMarkFrag).commit();
                        break;
                    case R.id.list_menu :
                        if(listFrag == null){
                            listFrag = new ListFrag();
                            fm.beginTransaction().add(R.id.frameLayout_main, listFrag).commit();
                        }

                        if(searchFrag != null)fm.beginTransaction().hide(searchFrag).commit();
                        if(listFrag != null) fm.beginTransaction().show(listFrag).commit();
                        if(bookMarkFrag != null) fm.beginTransaction().hide(bookMarkFrag).commit();
                        break;
                    case R.id.bookMark_menu :
                        if(bookMarkFrag == null){
                            bookMarkFrag = new BookMarkFrag();
                            fm.beginTransaction().add(R.id.frameLayout_main, bookMarkFrag).commit();
                        }

                        if(searchFrag != null)fm.beginTransaction().hide(searchFrag).commit();
                        if(listFrag != null) fm.beginTransaction().hide(listFrag).commit();
                        if(bookMarkFrag != null) fm.beginTransaction().show(bookMarkFrag).commit();
                        break;
                }
                return true;
            }
        });
    }
}