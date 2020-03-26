package com.albuquerque.moviecatalog.app.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.albuquerque.moviecatalog.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {
        button.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_movieDetailFragment)
        }

        activity?.let { slider.setup(arrayListOf("https://i.pinimg.com/736x/5e/62/b6/5e62b67f13ee0d57d7789945493de45b.jpg", "https://i.pinimg.com/736x/5e/62/b6/5e62b67f13ee0d57d7789945493de45b.jpg", "https://i.pinimg.com/736x/5e/62/b6/5e62b67f13ee0d57d7789945493de45b.jpg")) }

    }

}
