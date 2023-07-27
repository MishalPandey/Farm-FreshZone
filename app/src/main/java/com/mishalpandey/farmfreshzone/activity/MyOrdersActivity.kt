package com.mishalpandey.farmfreshzone.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mishalpandey.farmfreshzone.Model.AllOrderModel
import com.mishalpandey.farmfreshzone.R
import com.mishalpandey.farmfreshzone.adapter.AllOrderAdapter
import com.mishalpandey.farmfreshzone.databinding.ActivityAddressBinding
import com.mishalpandey.farmfreshzone.databinding.ActivityMyOrdersBinding

class MyOrdersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyOrdersBinding
    private lateinit var list: ArrayList<AllOrderModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMyOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = ArrayList()

        val preferences =
            this.getSharedPreferences("user", AppCompatActivity.MODE_PRIVATE)

        Firebase.firestore.collection("allOrders")
            .whereEqualTo("userId", preferences.getString("number", "")!!)
            .get().addOnSuccessListener {
                list.clear()

                for (doc in it) {
                    val data = doc.toObject(AllOrderModel::class.java)
                    list.add(data)
                }
                binding.recyclerView.adapter = AllOrderAdapter(list, this)
            }




    }
}