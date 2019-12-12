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
    EditText etName, etAge, etAddress;
    RadioGroup radioGroup;
    Button btnSave;
    String name, age, address, gender;
    public static List<Student> studentArrayList = new ArrayList<>();

    public boolean validate() {
        if (TextUtils.isEmpty(name)) {
            etName.setError("Enter your FullName");
            etName.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(age)) {
            etAge.setError("Enter your Age");
            etAge.requestFocus();
            return false;
        }
        if (!TextUtils.isDigitsOnly(age)) {
            etAge.setError("Invalid Number");
            etAge.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(address)) {
            etAddress.setError("Enter your Address");
            etAddress.requestFocus();
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

        etName = root.findViewById(R.id.eName);
        etAge = root.findViewById(R.id.eAge);
        etAddress = root.findViewById(R.id.eAddress);
        radioGroup = root.findViewById(R.id.radioGroup);
        btnSave = root.findViewById(R.id.button);


        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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

                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        name = etName.getText().toString();
                        age = etAge.getText().toString();
                        address = etAddress.getText().toString();
                        if (validate()) {
                            studentArrayList.add(new Student(name, age, gender, address));

                            Toast.makeText(getContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();

                            etName.setText(null);
                            etAge.setText(null);
                            etAddress.setText(null);

                        }
                    }

                });

            }
        });
        return root;
    }


}