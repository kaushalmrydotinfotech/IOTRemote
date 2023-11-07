package com.rydot.iotRemote.fragment

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.iotremote.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {
   private lateinit var binding: FragmentSplashBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(layoutInflater,container,false)
        return  binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
           val controller: WindowInsetsController? =
               requireActivity().window.insetsController
           if (controller != null) {
               controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
               controller.systemBarsBehavior =
                   WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
           }
        } else {
          requireActivity().window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        Handler(Looper.getMainLooper()).postDelayed({

            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToFrame1())
        }, 3000)
    }


}