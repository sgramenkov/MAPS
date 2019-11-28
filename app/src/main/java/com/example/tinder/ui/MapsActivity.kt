package com.example.tinder.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.tinder.R
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


class MapsActivity : AppCompatActivity(), OnMapReadyCallback,
    GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
    LocationListener {
        var url = "https://maps.googleapis.com/maps/api/directions/json?origin=59.945442,30.268842&destination=58.946766,30.273462&key=AIzaSyC29iWFWp4YPV7nxQ3ysLxZiNPlsCjG4HM"

    private lateinit var mMap: GoogleMap
    private var lat: Double? = null
    private var lng: Double? = null
    var mLocationPermissionGranted: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        if (ContextCompat.checkSelfPermission(
                this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            mLocationPermissionGranted = true
            Log.e("PERMISSION", mLocationPermissionGranted.toString())
            var locationManager:LocationManager =  getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1,0f,object :android.location.LocationListener{
                override fun onLocationChanged(location: Location?) {
                    lat=location?.latitude
                    lng=location?.longitude
                    Log.e("COORDS","$lat $lng")
                }

                override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onProviderEnabled(provider: String?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onProviderDisabled(provider: String?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            })
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1
            )
        }

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        // Add a marker in Sydney and move the camera
        mMap.uiSettings.isZoomControlsEnabled=true
        mMap.isMyLocationEnabled = true



        /*val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/
    }

    override fun onConnected(p0: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onConnectionSuspended(p0: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLocationChanged(p0: Location?) {
        val lat: Double? = p0?.latitude
        val lng: Double? = p0?.longitude
        Toast.makeText(this, "$lat $lng", Toast.LENGTH_SHORT).show()
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(lat!!, lng!!), 8F))
    }
    fun getRequestURL(origin:LatLng,destination:LatLng): String {
        var origin:String="origin="+origin.latitude+","+origin.longitude
        var dest:String="destination="+destination.latitude+","+destination.longitude
        var sensor:String="sensor=false"
        var param:String=origin+"&"+dest+"&"+sensor
        var output="json"
        var url="https://maps.googleapis.com/maps/directions/"+output+"?"+param
        return url
    }


}
