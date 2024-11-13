package com.example.worldpetsmobile.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.example.worldpetsmobile.Inicio;
import com.example.worldpetsmobile.R;


public class GalleryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        // Configurar OnClickListeners para cada CardView
        CardView cardView1 = view.findViewById(R.id.consulta);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Inicio.class);
                startActivity(intent);
            }
        });

        CardView cardView2 = view.findViewById(R.id.vacuna);
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Inicio.class);
                startActivity(intent);
            }
        });

        CardView cardView3 = view.findViewById(R.id.despa);
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Inicio.class);
                startActivity(intent);
            }
        });

        CardView cardView4 = view.findViewById(R.id.cirugia);
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Inicio.class);
                startActivity(intent);
            }
        });

        CardView cardView5 = view.findViewById(R.id.urgencia);
        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Inicio.class);
                startActivity(intent);
            }
        });

        CardView cardView6 = view.findViewById(R.id.odontologia);
        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Inicio.class);
                startActivity(intent);
            }
        });

        CardView cardView7 = view.findViewById(R.id.examenes);
        cardView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Inicio.class);
                startActivity(intent);
            }
        });

        CardView cardView8 = view.findViewById(R.id.nutricion);
        cardView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Inicio.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
