package com.shahinidrisi.crudrealtimeadmin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.shahinidrisi.crudrealtimeadmin.databinding.ActivityUpdateBinding
import com.shahinidrisi.crudrealtimeadmin.databinding.ActivityUploadBinding

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseReference = FirebaseDatabase.getInstance().getReference("vehicle Information")

        binding.updateButton.setOnClickListener{
            val referenceVehicleNumber = binding.referenceVehicleNumber.text.toString()
            val updateOwnerName = binding.updateOwnerName.text.toString()
            val updateVehicleBrand = binding.updateVehicleBrand.text.toString()
            val upadateVehicleRTO = binding.updateVehicleRTO.text.toString()

            updateData(referenceVehicleNumber, updateOwnerName, updateVehicleBrand,upadateVehicleRTO)
        }

    }

    private fun updateData(vehicleNumber: String, ownerName: String, vehicleBrand: String, vehicleRTO: String) {
        val vehicleData = mapOf(
            "ownerName" to ownerName,
            "vehicleBrand" to vehicleBrand,
            "vehicleRTO" to vehicleRTO
        )

        databaseReference.child(vehicleNumber).updateChildren(vehicleData).addOnSuccessListener {
            binding.referenceVehicleNumber.text.clear()
            binding.updateOwnerName.text.clear()
            binding.updateVehicleBrand.text.clear()
            binding.updateVehicleRTO.text.clear()
            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show()
        }
    }
}