package com.example.mysqlinsert.ui.complain;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.mysqlinsert.ComplainAdapter;
import com.example.mysqlinsert.ComplainFragment;
import com.example.mysqlinsert.ComplainHistoryFragment;
import com.example.mysqlinsert.R;
import com.google.android.material.tabs.TabLayout;


public class GalleryFragment extends Fragment {

    View myFragment;
    ViewPager viewPager;
    TabLayout tabLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        myFragment = inflater.inflate(R.layout.fragment_complain, container, false);


        viewPager = myFragment.findViewById(R.id.viewPager);
        tabLayout = myFragment.findViewById(R.id.tabLayout);


        return myFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       setupViewPager(viewPager);
       tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ComplainAdapter adapter = new ComplainAdapter(getChildFragmentManager());

        adapter.addFragment(new ComplainFragment(),"New Complain");
        adapter.addFragment(new ComplainHistoryFragment(),"Previous Complain");

        viewPager.setAdapter(adapter);
    }

}