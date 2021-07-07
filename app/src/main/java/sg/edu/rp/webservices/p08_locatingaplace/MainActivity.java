package sg.edu.rp.webservices.p08_locatingaplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

//  Button btn1, btn2, btn3;
    Spinner spinner;
    private GoogleMap map;
    ArrayList<String> al;
    ArrayAdapter<String> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                LatLng singapore = new LatLng(1.3521, 103.8198);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(singapore,
                        11));

                UiSettings settings = map.getUiSettings();
                settings.setCompassEnabled(true);


                UiSettings ui = map.getUiSettings();
                ui.setZoomControlsEnabled(true);

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }

                LatLng north = new LatLng(1.45969896,103.81531059);
                Marker cp = map.addMarker(new
                        MarkerOptions()
                        .position(north)
                        .title("North - HQ")
                        .snippet("Block 333, Admiralty Ave 3, 765654 Operating hours: 10am-5pm" +
                                "Tel:65433456")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

                LatLng central = new LatLng(1.304833, 103.831833);
                Marker cp1 = map.addMarker(new
                        MarkerOptions()
                        .position(central)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542" +
                                "Operating hours: 11am-8pm" +
                                "Tel:67788652")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                LatLng east = new LatLng(1.367149, 103.928021);
                Marker cp2 = map.addMarker(new
                        MarkerOptions()
                        .position(east)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788 " +
                                "Operating hours: 9am-5pm" +
                                "Tel:66776677")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Toast.makeText(getApplicationContext(),marker.getTitle(),Toast.LENGTH_SHORT).show();
                        //marker.showInfoWindow();
                        return false;
                    }
                });
            }
        });

//        btn1 = findViewById(R.id.btn1);
//        btn2 = findViewById(R.id.btn2);
//        btn3 = findViewById(R.id.btn3);
//
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (map != null){
//                    LatLng singapore = new LatLng(1.45969896, 103.81531059);
//                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(singapore,
//                            15));
//
//                }
//            }
//        });
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (map != null){
//                    LatLng singapore = new LatLng(1.304833, 103.831833);
//                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(singapore,
//                            15));
//
//                }
//            }
//        });
//
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (map != null){
//                    LatLng singapore = new LatLng(1.367149, 103.928021);
//                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(singapore,
//                            15));
//
//                }
//            }
//        });
        spinner = findViewById(R.id.spinner);
        al = new ArrayList<>();
        aa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, al);
        String[] str = getResources().getStringArray(R.array.options);
        al.addAll(Arrays.asList(str));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                al.clear();
                switch (position) {
                    case 0:
                        if (map != null){
                            LatLng singapore = new LatLng(1.45969896, 103.81531059);
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(singapore,15));
                        }
                        break;
                    case 1:
                        if (map != null){
                            LatLng singapore = new LatLng(1.304833, 103.831833);
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(singapore,15));
                        }
                        break;
                    case 2:
                        if (map != null){
                            LatLng singapore = new LatLng(1.367149, 103.928021);
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(singapore,15));
                        }
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}


