package com.example.worldpetsmobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private List<Pet> data;

    public CardAdapter(List<Pet> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pet pet = data.get(position);
        holder.imageViewPet.setImageResource(pet.getImageResourceId());
        holder.textViewName.setText("Nombre: " + pet.getName());
        holder.textViewBreed.setText("Raza: " + pet.getBreed());
        holder.textViewAge.setText("Edad: " + pet.getAge() + " años");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewPet;
        public TextView textViewName;
        public TextView textViewBreed;
        public TextView textViewAge;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewPet = itemView.findViewById(R.id.imageViewPet);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewBreed = itemView.findViewById(R.id.textViewBreed);
            textViewAge = itemView.findViewById(R.id.textViewAge);
        }
    }
}
