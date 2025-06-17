package com.shahinidrisi.crudrealtimeadmin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.shahinidrisi.crudrealtimeadmin.databinding.ActivityUploadBinding

class UploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadBinding
    private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener{
            val ownerName = binding.uploadOwnerName.text.toString()
            val vehicleBrand = binding.uploadVehicleBrand.text.toString()
            val vehicleRTO = binding.uploadVehicleRTO.text.toString()
            val vehicleNumber = binding.uploadVehiclNumber.text.toString()

            databaseReference = FirebaseDatabase.getInstance().getReference("vehicle Information")
            val vehicleData = VehicleData(ownerName,vehicleBrand,vehicleRTO,vehicleNumber)
            databaseReference.child(vehicleNumber).setValue(vehicleData).addOnSuccessListener {
                binding.uploadOwnerName.text.clear()
                binding.uploadVehicleBrand.text.clear()
                binding.uploadVehicleRTO.text.clear()
                binding.uploadVehiclNumber.text.clear()

                //Toast is a small message that pops up on the screen for a short period of time.
                Toast.makeText(this,"Saved", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@UploadActivity,MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener{
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }
        }
    }
}