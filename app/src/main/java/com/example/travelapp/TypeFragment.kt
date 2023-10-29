package com.example.travelapp

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.travelapp.databinding.FragmentOrderBinding
import com.example.travelapp.databinding.FragmentTicketBinding
import com.example.travelapp.databinding.FragmentTypeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TypeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TypeFragment : Fragment() {

    private lateinit var binding: FragmentTypeBinding

    private lateinit var tickettyped : Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTypeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tickettyped = getResources().getStringArray(com.example.travelapp.R.array.tickettype);

        with(binding) {

            //membuat adapter untuk menghubungkan array dengan tampilan yang akan ditampilkan
            val adapterTicketType = ArrayAdapter(
                this@TypeFragment.requireContext(),
                R.layout.simple_spinner_item,
                tickettyped
            )
            adapterTicketType.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
            spinnerTicketType.adapter = adapterTicketType

            spinnerTicketType.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        val tickettype = tickettyped[position]
                        Toast.makeText(
                            this@TypeFragment.requireContext(),
                            tickettype,
                            Toast.LENGTH_SHORT
                        ).show()

                        val selectedItem = spinnerTicketType.selectedItem.toString()
                        val bundle = Bundle()
                        bundle.putString("typeTicket", selectedItem)
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        // Metode ini akan dipanggil jika tidak ada item yang dipilih
                    }
                }

            btnBuyorder.setOnClickListener {
                // Mengirim data ke orderfragment
                findNavController().apply {
                    previousBackStackEntry?.savedStateHandle?.
                    set("ticketType", spinnerTicketType.selectedItem.toString())
                }.navigateUp()
            }
        }
    }
}