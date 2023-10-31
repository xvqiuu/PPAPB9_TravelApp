package com.example.travelapp

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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrderFragment : Fragment() {

    //membuat binding terlebih dahulu
    private lateinit var binding: FragmentOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            edtTicketType.setOnClickListener {
                val action = OrderFragmentDirections.actionOrderFragmentToTypeFragment()
                findNavController().navigate(action)
            }

            //menerima data dari typefragment
            findNavController().currentBackStackEntry?.savedStateHandle?.let {
                handle ->
                handle.getLiveData<String>("ticketType")
                    .observe(viewLifecycleOwner) { res ->
                    edtTicketType.setText(res)
                }
            }
            btnBuyorder.setOnClickListener {
                findNavController().navigate(R.id.homeFragment)
            }
        }
    }
}