package com.example.mysqlinsert;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mysqlinsert.DBQuer.DatabaseMethods;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * A simple {@link Fragment} subclass.
 */
public class ComplainFragment extends Fragment {
    private Spinner spinner2;
    MultiAutoCompleteTextView userComplain;
    EditText userName,userTp,useremail;
    Spinner spinner;
    Button complainAddd;
    String user;
    String u_tp;
    String email;
    String userEmail;
    String UserTPL;
    Connection conn;
    String uComplain,spinerValue,u_TP,u_email;


    UserDetails udetails = new UserDetails();
    DatabaseMethods method = new DatabaseMethods();
    public ComplainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_complain2, container, false);


        user = udetails.getuName();
        u_tp =udetails.getuTp();
        email = udetails.getuEmail();


        userName = root.findViewById(R.id.user);
        userName.setText(user);

        userTp = root.findViewById(R.id.tp);
        userTp.setText(u_tp);
        useremail = root.findViewById(R.id.email);
        useremail.setText(email);

        userComplain = root.findViewById(R.id.complain);
        complainAddd = root.findViewById(R.id.addCom);





        String [] values =
                {"Select complaint type","Supply failed at premises","Supply failed in area","One phase out","Broken service wire","Abrormal/low voltage","Conductor burning","Voltage flickering","High voltage","Meter burning","Flashing insulators","Tree/Other structure fallen on electricity line","Flood","Other",};
         spinner = (Spinner) root.findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 1:
                        spinerValue = "Supply failed at premises";
                        break;
                    case 2:
                        spinerValue = "Supply failed in area";
                        break;
                    case 3:
                        spinerValue = "One phase out";
                        break;
                    case 4:
                        spinerValue = "Broken service wire";
                        break;
                    case 5:
                        spinerValue = "Abrormal/low voltage";
                        break;
                    case 6:
                        spinerValue = "Conductor burning";
                        break;
                    case 7:
                        spinerValue = "Voltage flickering";
                        break;
                    case 8:
                        spinerValue = "High voltage";
                        break;
                    case 9:
                        spinerValue = "Meter burning";
                        break;
                    case 10:
                        spinerValue = "Flashing insulators";
                        break;
                    case 11:
                        spinerValue = "Tree/Other structure fallen on electricity line";
                        break;
                    case 12:
                        spinerValue  = "Flood";
                        break;
                    case  13:
                        spinerValue = "other";
                        break;
                    default:
                        spinerValue = "other";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        complainAddd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uComplain = userComplain.getText().toString();

                u_TP = userTp.getText().toString();
                u_email = useremail.getText().toString();

                ComplainAdd addCom = new ComplainAdd();
                addCom.execute();

            }
        });


        return root;
    }

    public class ComplainAdd extends AsyncTask<String ,String ,String>{
        String msg;


        @Override
        protected String doInBackground(String... strings) {


            if (user.equals("") || uComplain.equals("") || u_email.equals("") || u_TP.equals("")){
                msg = "Please fill all the fields!!!";
            }
            else{

                msg = "Complain successfully";
                method.complainAdd(user,spinerValue,uComplain,u_email,u_TP);
            }

            return msg;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            new SweetAlertDialog(getContext())
                    .setTitleText(msg)
                    .show();

            userTp.setText("");
            useremail.setText("");
            userComplain.setText("");
        }
    }


}
