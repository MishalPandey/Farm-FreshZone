package com.mishalpandey.farmfreshzone.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mishalpandey.farmfreshzone.Model.AllOrderModel
import com.mishalpandey.farmfreshzone.R
import com.mishalpandey.farmfreshzone.activity.LoginActivity
import com.mishalpandey.farmfreshzone.activity.MyInfoActivity
import com.mishalpandey.farmfreshzone.activity.MyOrdersActivity
import com.mishalpandey.farmfreshzone.adapter.AllOrderAdapter
import com.mishalpandey.farmfreshzone.databinding.FragmentMoreBinding


class MoreFragment : Fragment() {

    private lateinit var binding: FragmentMoreBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoreBinding.inflate(layoutInflater)

        binding.btnMyOrders.setOnClickListener {
            val intent = Intent(context, MyOrdersActivity::class.java)
            startActivity(intent)
        }

        binding.btnMyInfo.setOnClickListener {
            val intent = Intent(context, MyInfoActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }



        return binding.root
    }
}