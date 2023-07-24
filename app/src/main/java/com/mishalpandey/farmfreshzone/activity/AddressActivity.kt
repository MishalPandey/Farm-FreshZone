package com.mishalpandey.farmfreshzone.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mishalpandey.farmfreshzone.R
import com.mishalpandey.farmfreshzone.databinding.ActivityAddressBinding

class AddressActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddressBinding
    private lateinit var preferences : SharedPreferences
    private lateinit var totalCost : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressBinding.inflate(layoutInflater)

        setContentView(binding.root)
        preferences = this.getSharedPreferences("user", MODE_PRIVATE)

        totalCost = intent.getStringExtra("totalCost")!!
        loadUserInfo()


        binding.proceed.setOnClickListener {
            validateData(
                binding.userNumber.text.toString(),
                binding.userName.text.toString(),
                binding.pinCode.text.toString(),
                binding.userCity.text.toString(),
                binding.userState.text.toString(),
                binding.village.text.toString(),

            )
        }
    }

    private fun validateData(number: String, name: String, pinCode: String, city: String, state: String, village: String) {

        if(number.isEmpty() || state.isEmpty() || name.isEmpty()){
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
        }else{
            storeDate( pinCode, city, state, village)
        }

    }

    private fun storeDate(pinCode: String, city: String, state: String, village: String) {

        val map = hashMapOf<String, Any>()
        map["village"] = village
        map["state"] = state
        map["city"] = city
        map["pinCode"] = pinCode

        Firebase.firestore.collection("users")
            .document(preferences.getString("number", "1")!!)
            .update(map).addOnSuccessListener {

                val b = Bundle()
                b.putStringArrayList("productIds", intent.getStringArrayListExtra("productIds"))
                b.putString("totalCost", totalCost)

                val intent = Intent(this, CheckoutActivity::class.java)
                intent.putExtras(b)
                startActivity(intent)

            }.addOnFailureListener {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }




    }

    private fun loadUserInfo() {


        Firebase.firestore.collection("users")
            .document(preferences.getString("number", "1")!!)
            .get().addOnSuccessListener {
                binding.userName.setText(it.getString("userName"))
                binding.userNumber.setText(it.getString("userPhoneNumber"))
                binding.village.setText(it.getString("village"))
                binding.userCity.setText(it.getString("city"))
                binding.pinCode.setText(it.getString("pinCode"))
                binding.userState.setText(it.getString("state"))
            }
            .addOnFailureListener {

            }
    }
}