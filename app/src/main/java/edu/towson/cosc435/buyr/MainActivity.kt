package edu.towson.cosc435.buyr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import edu.towson.cosc435.buyr.lists.ListsFragment
import edu.towson.cosc435.buyr.options.LoginRegister
import edu.towson.cosc435.buyr.options.OptionsFragment
import edu.towson.cosc435.buyr.options.SessionManager
import edu.towson.cosc435.buyr.watchlist.WatchlistFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpTabs()

        val logout = findViewById<TextView>(R.id.sign_out)
        logout.setOnClickListener { logout() }
    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(ListsFragment(), getString(R.string.lists_label))
        adapter.addFragment(WatchlistFragment(), getString(R.string.watchlist_label))
        adapter.addFragment(OptionsFragment(), getString(R.string.options_label))
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_list_24)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_remove_red_eye_24)
        tabs.getTabAt(2)!!.setIcon(R.drawable.ic_baseline_settings_24)
    }

    private lateinit var sessionManagement: SessionManager

    private fun logout() {
        //remove session and return to login View
        //reset session to '-1'
        //go back to login screen>?
        sessionManagement.removeSession()

        //back to loginRegister? create if else to confirm with the user?
        toLoginScreen()

    }

    private fun toLoginScreen() {
        //from 'this' to whichever activity you want
        val intent = Intent(this, LoginRegister::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}