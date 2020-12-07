package edu.towson.cosc435.buyr.splashscreen.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import edu.towson.cosc435.buyr.R
import kotlinx.android.synthetic.main.fragment_first_screen.view.*

class FirstScreen : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first_screen, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        view.next1.setOnClickListener {
            viewPager?.currentItem = 1
        }
        return view
    }
}