package com.example.worldpetsmobile.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldpetsmobile.AddPet;
import com.example.worldpetsmobile.CardAdapter;
import com.example.worldpetsmobile.entities.Pet;
import com.example.worldpetsmobile.R;
import com.example.worldpetsmobile.databinding.FragmentHomeBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private List<Pet> data;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null)
//                        .setAnchorView(R.id.fab).show();
                Intent intent = new Intent(getActivity(), AddPet.class);
                startActivity(intent);

            }
        });

        // Configurar el RecyclerView
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1)); // Dos columnas

        // Datos de ejemplo
        data = new ArrayList<>();
        data.add(new Pet(R.drawable.pet_image, "Max", "Labrador", 3));
        data.add(new Pet(R.drawable.pet_image, "Bella", "Golden Retriever", 2));
        data.add(new Pet(R.drawable.pet_image, "Firulais", "Chandis", 20));
        // Agrega más mascotas según sea necesario

        adapter = new CardAdapter(data);
        recyclerView.setAdapter(adapter);

        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}