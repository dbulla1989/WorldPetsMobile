package com.example.worldpetsmobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worldpetsmobile.entities.Service;
import java.util.List;

public class ServiceCardAdapter extends RecyclerView.Adapter<ServiceCardAdapter.ViewHolder> {

    private List<Service> data;

    public ServiceCardAdapter (List<Service> data){
        this.data = data;
    }

    @NonNull
    @Override
    public ServiceCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_card, parent, false);
        return new ServiceCardAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceCardAdapter.ViewHolder holder, int position) {
        Service service = data.get(position);
        holder.textViewServiceName.setText("Nombre: " + service.getName());
        holder.textViewServiceDate.setText("Fecha: " + service.getDate());
        holder.textViewServiceLocation.setText("Sede: " + service.getLocation());
        holder.textViewServiceSpecialist.setText("Especialista: " + service.getSpecialist());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewServiceName, textViewServiceDate, textViewServiceLocation, textViewServiceSpecialist;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewServiceName = itemView.findViewById(R.id.text_service_name);
            textViewServiceDate = itemView.findViewById(R.id.text_date);
            textViewServiceLocation = itemView.findViewById(R.id.text_location);
            textViewServiceSpecialist = itemView.findViewById(R.id.text_specialist);
        }
    }
}
