package com.example.mysqlinsert.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mysqlinsert.EditProf;
import com.example.mysqlinsert.MainActivity;
import com.example.mysqlinsert.NewAcc;
import com.example.mysqlinsert.PasswordChange;
import com.example.mysqlinsert.R;
import com.example.mysqlinsert.UserDetails;
import com.example.mysqlinsert.about;


public class SettingFragment extends Fragment {
    TextView name,mail,mobile,acc;
    Button log,about,addnew,pswd,edit;

    UserDetails details = new UserDetails();
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate( R.layout.fragment_setting, container, false );

        name = root.findViewById(R.id.userName);
        mail = root.findViewById(R.id.userMail);
        mobile = root.findViewById(R.id.userMobile);
        acc = root.findViewById(R.id.userAcc);

        log = root.findViewById( R.id.logoutb );
        about = root.findViewById( R.id.aboutb);
//        addnew = root.findViewById( R.id.addnewb);
        pswd = root.findViewById( R.id.pswdb);
        edit = root.findViewById( R.id.editb);


        //set Profile Details
        name.setText(details.getuName());
        mail.setText(details.getuEmail());
        mobile.setText(details.getuTp());
        acc.setText(details.getuAcc());


        log.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent( getActivity(), MainActivity.class );
                startActivity( intent );
               getActivity().finish();


            }

        } );

        about.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent( getActivity(), com.example.mysqlinsert.about.class );
                startActivity( intent );

            }

        } );
        pswd.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent( getActivity(), PasswordChange.class );
                startActivity( intent );


            }

        } );

        edit.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent( getActivity(), EditProf.class );
                startActivity( intent );

            }

        } );
//        addnew.setOnClickListener( new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent intent = new Intent( getActivity(), NewAcc.class );
//                startActivity( intent );
//
//            }
//
//        } );

        return root;
    }


}