package com.example.worldpetsmobile;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.example.worldpetsmobile.entities.Veterinary;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private FusedLocationProviderClient fusedLocationClient;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private static final int BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtener el SupportMapFragment y recibir una notificación cuando el mapa esté listo para usarse
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // Inicializar el cliente de geolocalización
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Configurar los botones flotantes
        FloatingActionButton fabGeolocate = findViewById(R.id.fab_geolocate);
        FloatingActionButton fabZoomIn = findViewById(R.id.fab_zoom_in);
        FloatingActionButton fabZoomOut = findViewById(R.id.fab_zoom_out);

        fabGeolocate.setOnClickListener(v -> getDeviceLocation());
        fabZoomIn.setOnClickListener(v -> googleMap.animateCamera(CameraUpdateFactory.zoomIn()));
        fabZoomOut.setOnClickListener(v -> googleMap.animateCamera(CameraUpdateFactory.zoomOut()));
    }

    private void getDeviceLocation() {
        // Verificar si el permiso de ubicación está concedido
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Si no se tiene el permiso, solicitarlo
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }

        // Verificar si se tiene el permiso de ubicación en segundo plano (Android 10 o superior)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Si no se tiene el permiso, solicitarlo
            requestPermissions(new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }

        // Obtener la ubicación del dispositivo
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            // Crear un LatLng con las coordenadas del dispositivo
                            LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15f)); // Mover el mapa al lugar y hacer zoom
                            googleMap.addMarker(new MarkerOptions().position(userLocation).title("Mi ubicación"));
                        } else {
                            Toast.makeText(MapsActivity.this, "No se pudo obtener la ubicación", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        googleMap.getUiSettings().setZoomControlsEnabled(false);
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        googleMap.setMyLocationEnabled(true);
        getDeviceLocation();
        addVeterinaryMarkers();
    }

    private void addVeterinaryMarkers() {
        Veterinary vet1 = new Veterinary("Animal Small", "Cra. 7 #13-86 #13-28 a, Sibaté, Cundinamarca", "3142271421", 4.4f, new LatLng(4.494500687569495, -74.25773766730623));
        //Veterinary vet2 = new Veterinary("Veterinaria XYZ", "Avenida Ejemplo 456", "987-654-321", 4.8f, new LatLng(19.424420, -99.149430));
        //Veterinary vet3 = new Veterinary("Veterinaria Salud Animal", "Boulevard de la Salud 789", "555-123-456", 4.2f, new LatLng(19.456890, -99.123400));

        addVeterinaryMarker(vet1);
//        addVeterinaryMarker(vet2);
//        addVeterinaryMarker(vet3);
    }

    private void addVeterinaryMarker(Veterinary vet) {
        MarkerOptions markerOptions = new MarkerOptions()
                .position(vet.getLocation())
                .title(vet.getName())
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bandera_48));

        Marker marker = googleMap.addMarker(markerOptions);
        marker.setTag(vet);

        // Configurar el listener para el clic en los marcadores
        googleMap.setOnMarkerClickListener(clickedMarker -> {
            Veterinary clickedVet = (Veterinary) clickedMarker.getTag();
            if (clickedVet != null) {
                showBottomSheetDialog(clickedVet);
            }
            return true;
        });
    }

    private void showBottomSheetDialog(Veterinary vet) {
        // Crear el BottomSheetDialog
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View bottomSheetView = getLayoutInflater().inflate(R.layout.location_card, null);

        // Configurar los datos del establecimiento
        TextView tvName = bottomSheetView.findViewById(R.id.tv_commerce_name);
        TextView tvAddress = bottomSheetView.findViewById(R.id.tv_commerce_address);
        TextView tvPhone = bottomSheetView.findViewById(R.id.tv_commerce_phone);
        TextView tvRating = bottomSheetView.findViewById(R.id.tv_commerce_rating);
        Button btnGo = bottomSheetView.findViewById(R.id.btn_go);
        Button btnSelect = bottomSheetView.findViewById(R.id.btn_select);

        // Asignar datos a las vistas
        tvName.setText(vet.getName());
        tvAddress.setText("Dirección: " + vet.getAddress());
        tvPhone.setText("Teléfono: " + vet.getPhone());
        tvRating.setText("Valoración: " + vet.getRating() + "/5");

        // Configurar acciones de los botones
        btnGo.setOnClickListener(v -> {
            // Abrir navegación en Google Maps dentro de la misma aplicación
            Uri gmmIntentUri = Uri.parse("google.navigation:q=" + vet.getLocation().latitude + "," + vet.getLocation().longitude);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            } else {
                Toast.makeText(this, "Google Maps no está instalado", Toast.LENGTH_SHORT).show();
            }
            bottomSheetDialog.dismiss();
        });

        btnSelect.setOnClickListener(v -> {
            Toast.makeText(this, "Has seleccionado: " + vet.getName(), Toast.LENGTH_SHORT).show();
            bottomSheetDialog.dismiss();
        });

        // Mostrar el BottomSheetDialog
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido, intentar obtener la ubicación nuevamente
                getDeviceLocation();
            } else {
                // Permiso denegado
                Toast.makeText(this, "Permiso de ubicación denegado", Toast.LENGTH_LONG).show();
            }
        }
    }
}
