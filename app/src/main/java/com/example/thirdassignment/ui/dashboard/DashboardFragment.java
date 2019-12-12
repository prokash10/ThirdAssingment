package com.example.thirdassignment.ui.dashboard;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.thirdassignment.R;
import com.example.thirdassignment.model.Student;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    EditText etname, etage, etaddress;
    RadioGroup radiogroup;
    Button btnsave;
    String name, age, address, gender;
    public static List<Student> studentArrayList = new ArrayList<>();

    public boolean validate() {
        if (TextUtils.isEmpty(name)) {
            etname.setError("Enter your FullName");
            etname.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(age)) {
            etage.setError("Enter your Age");
            etage.requestFocus();
            return false;
        }
        if (!TextUtils.isDigitsOnly(age)) {
            etage.setError("Invalid Number");
            etage.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(address)) {
            etaddress.setError("Enter your Address");
            etaddress.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(gender)) {
            Toast.makeText(getContext(), " Select your Gender ", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        etname = root.findViewById(R.id.etname);
        etage = root.findViewById(R.id.etage);
        etaddress = root.findViewById(R.id.etaddress);
        radiogroup = root.findViewById(R.id.radiogroup);
        btnsave = root.findViewById(R.id.btnsave);


        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (i == R.id.rbMale) {
                            gender = "Male";
                        }
                        if (i == R.id.rbFemale) {
                            gender = "Female";
                        }
                        if (i == R.id.rbOther) {
                            gender = "Other";
                        }

                    }


                });

                btnsave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        name = etname.getText().toString();
                        age = etage.getText().toString();
                        address = etaddress.getText().toString();
                        if (validate()) {
                            studentArrayList.add(new Student(name, age, gender, address));

                            Toast.makeText(getContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();

                            etname.setText(null);
                            etage.setText(null);
                            etaddress.setText(null);

                        }
                    }

                });

            }
        });
        return root;
    }


}