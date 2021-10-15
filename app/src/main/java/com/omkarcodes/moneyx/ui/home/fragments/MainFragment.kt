package com.omkarcodes.moneyx.ui.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.omkarcodes.moneyx.R
import com.omkarcodes.moneyx.databinding.FragmentMainBinding
import com.omkarcodes.moneyx.ui.home.adapters.MainViewpagerAdapter

class MainFragment : Fragment(R.layout.fragment_main){

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)

        binding.apply {

            vpMain.adapter = MainViewpagerAdapter(requireActivity())

            vpMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    if (position == 0)
                        bottomNavigationView.selectedItemId = R.id.navHome
                    else
                        bottomNavigationView.selectedItemId = R.id.navTransactions
                }
            })

            bottomNavigationView.setOnNavigationItemSelectedListener {
                if (it.itemId == R.id.navHome){
                    vpMain.currentItem = 0
                }else{
                    vpMain.currentItem = 1
                }
                true
            }

            fabAdd.setOnClickListener {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToNewTransactionFragment())
            }
        }

    }

     override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}