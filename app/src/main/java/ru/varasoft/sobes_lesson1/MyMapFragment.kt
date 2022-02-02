package ru.varasoft.sobes_lesson1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.SupportMapFragment

class MyMapFragment : SupportMapFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val activity: AppCompatActivity = getActivity() as AppCompatActivity
        activity.getSupportActionBar()?.setTitle(R.string.select_location)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}