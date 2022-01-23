package ru.varasoft.sobes_lesson1

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import ru.varasoft.sobes_lesson1.databinding.ActivityMapsBinding
import java.util.ArrayList

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object {
        private const val MY_PERMISSIONS_REQUEST_LOCATION = 99
    }

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var markerCount = 1

    private val mMarkerArray: ArrayList<Marker> = ArrayList<Marker>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as MyMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
            ),
            MY_PERMISSIONS_REQUEST_LOCATION
        )
    }

    fun hasAnyLocationPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        if (!hasAnyLocationPermission()) {
            requestLocationPermission()
            if (!hasAnyLocationPermission())
                return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.let {
                    val latlng = LatLng(it.latitude, it.longitude)
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng))
                    mMap.setOnMapClickListener({ point ->
                        val marker = mMap.addMarker(MarkerOptions().position(point).title("Marker " + markerCount++))
                        if (marker != null) {
                            mMarkerArray.add(marker)
                        };
                    })
                }
            }
    }
}