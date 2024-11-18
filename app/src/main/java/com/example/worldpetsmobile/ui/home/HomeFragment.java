package com.example.worldpetsmobile.ui.home;

import static android.content.Context.MODE_PRIVATE;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.worldpetsmobile.R;
import com.example.worldpetsmobile.databinding.FragmentHomeBinding;
import com.example.worldpetsmobile.entities.Pet;
import com.example.worldpetsmobile.globalresource.GlobalResource;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class HomeFragment extends Fragment {

    private Integer userId;
    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private List<Pet> data;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user_prefs", MODE_PRIVATE);
        userId = sharedPreferences.getInt("userId", 0);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddPet.class);
                startActivity(intent);

            }
        });

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1)); // Dos columnas

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String response = GlobalResource.getInstance().SendRequest("api/pet/all/" + userId.toString(), "GET", null);

                    if (!response.isEmpty()) {

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Gson gson = new Gson();
                                Type listType = new TypeToken<List<Pet>>() {
                                }.getType();
                                data = gson.fromJson(response, listType);

                                if (data.size() > 0) {
                                    adapter = new CardAdapter(data);
                                    recyclerView.setAdapter(adapter);
                                }

                            }
                        });
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}