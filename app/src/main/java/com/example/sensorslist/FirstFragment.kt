package com.example.sensorslist

import android.content.Context.SENSOR_SERVICE
import android.content.res.Configuration
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sensorslist.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sensorManager = requireActivity().getSystemService(SENSOR_SERVICE) as SensorManager
        val allSensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)

        Log.d("APPLE", allSensors.toString())

        val adapter = SensorAdapter(allSensors) { index ->
            binding.textviewMessage.text = allSensors[index].toString()
        }

        binding.recyclerview.adapter = adapter

        var columns = 2
        val currentOrientation = this.resources.configuration.orientation
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            columns = 4
        } else if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            columns = 2
        }
        binding.recyclerview.layoutManager = GridLayoutManager(this.context, columns)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}