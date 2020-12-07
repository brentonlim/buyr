package edu.towson.cosc435.buyr.splashscreen.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.towson.cosc435.buyr.R
import edu.towson.cosc435.buyr.splashscreen.onboarding.screens.FirstScreen
import edu.towson.cosc435.buyr.splashscreen.onboarding.screens.SecondScreen
import edu.towson.cosc435.buyr.splashscreen.onboarding.screens.ThirdScreen
import kotlinx.android.synthetic.main.fragment_view_pager.view.*

class ViewPagerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        view.viewPager.adapter = adapter

        return view
    }
}