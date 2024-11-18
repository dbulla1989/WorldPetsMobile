package com.example.worldpetsmobile;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private FusedLocationProviderClient fusedLocationClient;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private static final int BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE = 2;

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

        // Inicializar el cliente de geolocalización
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext());

        // Configurar los botones flotantes
        FloatingActionButton fabGeolocate = view.findViewById(R.id.fab_geolocate);
        FloatingActionButton fabZoomIn = view.findViewById(R.id.fab_zoom_in);
        FloatingActionButton fabZoomOut = view.findViewById(R.id.fab_zoom_out);

        fabGeolocate.setOnClickListener(v -> getDeviceLocation());
        fabZoomIn.setOnClickListener(v -> googleMap.animateCamera(CameraUpdateFactory.zoomIn()));
        fabZoomOut.setOnClickListener(v -> googleMap.animateCamera(CameraUpdateFactory.zoomOut()));

        return view;
    }

    private void getDeviceLocation() {
        // Comprobar si se tiene el permiso para acceder a la ubicación
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Si no se tiene el permiso, solicitarlo
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }

        // Verificar si se tiene el permiso para acceder a la ubicación en segundo plano (Android 10 o superior)
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Si no se tiene el permiso, solicitarlo
            requestPermissions(new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }

        // Obtener la ubicación del dispositivo
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(requireActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            // Crear un LatLng con las coordenadas del dispositivo
                            LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15f)); // Mover el mapa al lugar y hacer zoom
                            googleMap.addMarker(new MarkerOptions().position(userLocation).title("Mi ubicación"));
                        } else {
                            Toast.makeText(getActivity(), "No se pudo obtener la ubicación", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        // Configuración adicional del mapa
        googleMap.getUiSettings().setZoomControlsEnabled(false);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);  // Activar la opción de ubicación del usuario en el mapa
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permiso concedido, intentar obtener la ubicación nuevamente
            getDeviceLocation();
        } else {
            // Permiso denegado
            Toast.makeText(getActivity(), "Permiso de ubicación denegado", Toast.LENGTH_LONG).show();
        }
    }
}
