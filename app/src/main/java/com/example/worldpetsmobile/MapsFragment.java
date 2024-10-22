package com.example.worldpetsmobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.Random;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar el layout para este fragmento
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        // Obtener el SupportMapFragment y recibir una notificación cuando el mapa esté listo para usarse
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Habilitar todas las herramientas de gestos
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setScrollGesturesEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setTiltGesturesEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);

        // Mover la cámara a una posición inicial
        LatLng location= new LatLng(4.576875149581924, -74.22405457572414); // Sídney, por ejemplo
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10));

        // Agregar marcadores aleatorios
        addRandomMarkers(10);
    }

    private void addRandomMarkers(int count) {
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            double lat = -34 + random.nextDouble(); // Generar latitudes aleatorias
            double lng = 151 + random.nextDouble(); // Generar longitudes aleatorias
            LatLng randomLatLng = new LatLng(lat, lng);
            mMap.addMarker(new MarkerOptions().position(randomLatLng).title("Punto Aleatorio " + (i + 1)));
        }
    }
}