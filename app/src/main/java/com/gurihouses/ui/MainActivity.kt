package com.gurihouses.ui

import android.annotation.SuppressLint
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.gurihouses.databinding.ActivityMainBinding
import com.gurihouses.util.CommonUtil
import com.gurihouses.util.LocationUtilities
import com.gurihouses.util.LocationUtilities.enableLocationSettings
import com.gurihouses.util.LocationUtilities.hasLocationPermission
import com.gurihouses.util.LocationUtilities.locationEnable
import com.gurihouses.utilities.session.SessionManager
import com.gurihouses.utilities.session.SessionVar
import java.util.*

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private lateinit var sessionManager: SessionManager
    var user_type = ""
    private lateinit var mFusedLocationClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getUserLocation()

        sessionManager = SessionManager(this)

        if (user_type.isEmpty()){
            user_type = intent.getStringExtra(SessionVar.USER_TYPE).toString()
        }else{
            user_type = sessionManager.getRole()[SessionVar.USER_TYPE].toString()
        }


        Log.d("user_type",user_type)

      if (user_type == SessionVar.PROPERTY_OWNER){

          binding.rlUser.visibility = View.GONE
          binding.rlOwner.visibility = View.VISIBLE
          val navHostFragment =
              supportFragmentManager.findFragmentById(com.gurihouses.R.id.owner_nav_host_fragment) as NavHostFragment?
          val navController = navHostFragment!!.navController
          binding.ownerBottomNavView.setupWithNavController(navController)

        }else {

          binding.rlOwner.visibility = View.GONE
          binding.rlUser.visibility = View.VISIBLE
          val navHostFragment =
              supportFragmentManager.findFragmentById(com.gurihouses.R.id.nav_host_fragment) as NavHostFragment?
          val navController = navHostFragment!!.navController
          binding.bottomNavView.setupWithNavController(navController)


        }

    }

    private fun getUserLocation() {

        if (hasLocationPermission()) {
            if (!locationEnable()) {
                enableLocationSettings()
                return
            }else{
                getLastLocation()
            }

        } else {
            LocationUtilities.requestLocationPermission(this)
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    Log.e("TAG", "location is not null")
                    Log.d("lat", location.latitude.toString())
                    Log.d("lat", location.longitude.toString())
                    sessionManager.setLatitudeSession(location.latitude.toString())
                    sessionManager.setLongitudeSession(location.longitude.toString())
                    getLocationAddress(location.latitude,location.longitude)

                } else {
                    Log.e("TAG", "location is null")
                }
            }
    }

    private fun getLocationAddress(latitude: Double, longitude: Double) {

        try {
            val geocoder = Geocoder(this, Locale.getDefault())
            val addressesList: List<Address> =
                geocoder.getFromLocation(latitude, longitude, 1)
            val location1 = addressesList[0].getAddressLine(0)
            val city: String = addressesList[0].locality
            val state: String = addressesList[0].adminArea
            sessionManager.setCitySession(city.toString())
            sessionManager.setStateSession(state.toString())

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


}