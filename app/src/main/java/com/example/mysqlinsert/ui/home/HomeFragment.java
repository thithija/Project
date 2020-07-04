package com.example.mysqlinsert.ui.home;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mysqlinsert.DBQuer.DatabaseMethods;
import com.example.mysqlinsert.R;
import com.example.mysqlinsert.pay;
import com.example.mysqlinsert.ui.complain.GalleryFragment;
import com.example.mysqlinsert.ui.phistory.SendFragment;
import com.example.mysqlinsert.ui.popUp.PopUpMessage;
import com.example.mysqlinsert.ui.uhistory.uhistoryFragment;
import com.example.mysqlinsert.ui.viewbills.ToolsFragment;
import com.google.android.gms.ads.AdView;
import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;


public class HomeFragment extends Fragment {
    private AdView adView;
    private CardView cardView;
    private FlipperLayout flipper;
    TextView bill;
    LinearLayout usageH,pHistory,cmpln,about;
    private Button payr;

//   MenuItem menu;

    DatabaseMethods methods = new DatabaseMethods();

    StartBill st = new StartBill();
    @SuppressLint("FragmentLiveDataObserve")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        final RatingBar ratingBar = root.findViewById(R.id.ratingBar1);
         flipper = (FlipperLayout) root.findViewById( R.id.flipper );
        setLayout();
        st.execute();
        bill = root.findViewById(R.id.viewBill);
        usageH = root.findViewById(R.id.uh_card1);
        pHistory = root.findViewById(R.id.uh_card2);
        cmpln = root.findViewById(R.id.card_complain);
        payr =root.findViewById(R.id.hpay);
        about=root.findViewById(R.id.aboutto);


//       menu= root.findViewById(R.id.action_notification);
//
//        menu.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
//                Intent intent = new Intent(getContext(), PopUpMessage.class);
//                startActivity(intent);
//
//                return false;
//            }
//        });

        //navigate to view my bill
        bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, new ToolsFragment());
                transaction.commit();
            }
        });

        //navigate to usage history
        usageH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, new uhistoryFragment());
                transaction.commit();
            }
        });

        //navigate to payment history
        pHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, new SendFragment());
                transaction.commit();
            }
        });

        //navigate to complain page
        cmpln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, new GalleryFragment());
                transaction.commit();
            }
        });

        about.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent( getActivity(), com.example.mysqlinsert.about.class );
                startActivity( intent );

            }

        } );

        payr.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent( getActivity(), pay.class );
                startActivity( intent );

            }

        } );


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(fromUser){
                    Toast.makeText(ratingBar.getContext(),"Your Selected Ratings  : " + String.valueOf(rating),Toast.LENGTH_LONG).show();
                }
            }
        });




        return root;
    }

    private void setLayout() {
        int[] images = new int[]{R.drawable.sli,R.drawable.elec,R.drawable.dpp,R.drawable.er,R.drawable.home,R.drawable.bulb};
        for (int i=0; i<6; i++){
            FlipperView view = new FlipperView( getContext() );
            view.setImageDrawable( images[i] );
            flipper.addFlipperView( view );

        }

    }

    public class StartBill extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            methods.setBillDetails();
            return null;
        }
    }

}