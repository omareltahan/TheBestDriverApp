package com.thebest.resturant.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.thebest.resturant.Networking.SingleShotLocationProvider;
import com.thebest.resturant.R;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ChooseMapLocation extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap mMap;
    LatLng lastVal;
    TextView txt;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_choose);
        img = findViewById(R.id.img);
        txt = findViewById(R.id.txt);
        img.setOnClickListener(view -> finish());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        isLocationOpend();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.setOnMapClickListener(latLng -> {
            {
                mMap.clear();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18.0f));
                mMap.clear();
                int height = 64;
                int width = 64;
                BitmapDrawable bitmapdraw = (BitmapDrawable) ContextCompat.getDrawable(getApplicationContext(), R.drawable.marker_icon);
                Bitmap b = bitmapdraw.getBitmap();

                Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng).icon(BitmapDescriptorFactory.fromBitmap(smallMarker));

                mMap.addMarker(markerOptions);
                try {
                    Geocoder geocoder;
                    List<Address> addresses;
                    geocoder = new Geocoder(getApplicationContext(), new Locale(Locale.getDefault().getLanguage()));
                    addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    String global_address = addresses.get(0).getAddressLine(0);
                    txt.setText(global_address);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void isLocationOpend() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    101);
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        101);
            }
        } else {
            MakeSingleShotRequest();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            MakeSingleShotRequest();
        }
    }

    void MakeSingleShotRequest() {
        SingleShotLocationProvider.requestSingleUpdate(this,
                location -> {
                    {
                        try {
                            lastVal = new LatLng(location.latitude, location.longitude);
                            Geocoder geocoder;
                            List<Address> addresses;
                            geocoder = new Geocoder(getApplicationContext(), new Locale(Locale.getDefault().getLanguage()));
                            addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1);
                            String global_address = addresses.get(0).getAddressLine(0);
                            txt.setText(global_address);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }
}