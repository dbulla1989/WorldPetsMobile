package com.example.worldpetsmobile;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PersonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonFragment newInstance(String param1, String param2) {
        PersonFragment fragment = new PersonFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_person, container, false);
        Spinner spinner = view.findViewById(R.id.spinner);
        List<String> options = new ArrayList<>();
        options.add("Cedula de Ciudadania");
        options.add("Cedula de Extranjeria");
        options.add("Pasaporte");
        options.add("Permiso de Permanencia");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        EditText txtUser = view.findViewById(R.id.txt_UsernamePerson);
        EditText txtPass = view.findViewById(R.id.txt_PasswordPerson);
        TextView tvSignUp = view.findViewById(R.id.tv_SingUp);
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegisterUser.class);
                startActivity(intent);
            }
        });

        Button btnLogin = view.findViewById(R.id.btn_LoginPersons);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = txtUser.getText().toString();
                String password = txtPass.getText().toString();

                if (username.isEmpty() || password.isEmpty()){
                    Toast.makeText(getActivity(), "El usuario o la contraseña no pueden ir vacios.", Toast.LENGTH_SHORT).show();
                } else if (!username.equals("Admin") || !password.equals("Admin")) {
                    Toast.makeText(getActivity(), "Datos incorrectos", Toast.LENGTH_SHORT).show();
                } else if (username.equals("Admin") && password.equals("Admin")) {
                    Toast.makeText(getActivity(), "Bienvenido", Toast.LENGTH_SHORT).show();
                    // Crear un Intent para iniciar SecondActivity
                    Intent intent = new Intent(getActivity(), MainApplicaton.class);
                    // Iniciar SecondActivity
                    startActivity(intent);
                }

            }
        });

        return  view;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_person, container, false);
    }
}