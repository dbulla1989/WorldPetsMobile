package com.example.worldpetsmobile;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.worldpetsmobile.entities.Pet;
import com.example.worldpetsmobile.globalresource.GlobalResource;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddPet extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_PICK_IMAGE = 2;
    private static final int PERMISSION_REQUEST_CAMERA = 100;
    private static final int PERMISSION_REQUEST_STORAGE = 101;

    private ImageView imageViewPetPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_pet);

        EditText petName = findViewById(R.id.editText_pet_name);
        EditText petAge = findViewById(R.id.editText_pet_age);
        EditText petBreed = findViewById(R.id.editText_pet_breed);
        imageViewPetPhoto = findViewById(R.id.imageView_pet_photo);

        Button buttonTakePhoto = findViewById(R.id.button_take_photo);
        Button buttonChoosePhoto = findViewById(R.id.button_choose_photo);
        Button buttonSave = findViewById(R.id.button_save);
        Button buttonCancel = findViewById(R.id.button_cancel);

        buttonTakePhoto.setOnClickListener(v -> checkCameraPermission());
        buttonChoosePhoto.setOnClickListener(v -> checkStoragePermission());

        buttonSave.setOnClickListener(v -> {

            SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
            Integer userId = sharedPreferences.getInt("userId",0);

            Bitmap bitmap = ((BitmapDrawable) imageViewPetPhoto.getDrawable()).getBitmap();
            String base64String = convertBitmapToBase64(bitmap);
            Pet newPet = new Pet();
            newPet.setPersonId(userId);
            newPet.setName(petName.getText().toString());
            newPet.setBreed(petBreed.getText().toString());
            newPet.setAge(Integer.parseInt(petAge.getText().toString()));
            newPet.setEncodedImage(base64String);
            Gson gson = new Gson();
            String jsonBody = gson.toJson(newPet);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String response = GlobalResource.getInstance().SendRequest("api/pet", "POST", jsonBody);

                        if (!response.isEmpty()){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(AddPet.this, "Datos de la mascota guardados", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(AddPet.this, MainApplicaton.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        });

        buttonCancel.setOnClickListener(v -> {
            // Lógica para cancelar el proceso
            finish();
        });

    }

    private String convertBitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }



    private void checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    PERMISSION_REQUEST_CAMERA);
        } else {
            takePhoto();
        }
    }

    private void checkStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Android 13+
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_MEDIA_IMAGES},
                        PERMISSION_REQUEST_STORAGE);
            } else {
                choosePhotoFromGallery();
            }
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSION_REQUEST_STORAGE);
            } else {
                choosePhotoFromGallery();
            }
        }
    }

    private void takePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void choosePhotoFromGallery() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, REQUEST_PICK_IMAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CAMERA && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            takePhoto();
        } else if (requestCode == PERMISSION_REQUEST_STORAGE && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            choosePhotoFromGallery();
        } else {
            // Si el permiso fue denegado
            if (requestCode == PERMISSION_REQUEST_CAMERA) {
                Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show();
            } else if (requestCode == PERMISSION_REQUEST_STORAGE) {
                Toast.makeText(this, "Permiso de almacenamiento denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE && data != null) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imageViewPetPhoto.setImageBitmap(photo);
            } else if (requestCode == REQUEST_PICK_IMAGE && data != null) {
                Uri selectedImage = data.getData();
                imageViewPetPhoto.setImageURI(selectedImage);
            }
        }
    }

    private String encodeImageViewToBase64() {
        // Obtén el Bitmap del ImageView
        imageViewPetPhoto.setDrawingCacheEnabled(true);
        imageViewPetPhoto.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) imageViewPetPhoto.getDrawable()).getBitmap();

        // Comprime el Bitmap a formato JPEG y guarda en un ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        byte[] byteArray = outputStream.toByteArray();

        // Codifica el ByteArray en Base64
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }
}