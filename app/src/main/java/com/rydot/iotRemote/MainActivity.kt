package com.rydot.iotRemote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.iotremote.R
import com.example.iotremote.databinding.ActivityMainBinding
import com.rydot.iotRemote.utils.SharedPrefs

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var navHostFragment: NavHostFragment? = null
    private var navigationController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navigationController = navHostFragment!!.navController

    }

    override fun onStop() {
        super.onStop()
        SharedPrefs.remove(applicationContext,"BTisSelected")
        SharedPrefs.remove(applicationContext,"WFisSelected")
        SharedPrefs.remove(applicationContext,"WIisSelected")
    }
    override fun onDestroy() {
        super.onDestroy()
    }
}