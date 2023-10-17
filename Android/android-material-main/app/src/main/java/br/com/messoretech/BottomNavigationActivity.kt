package br.com.messoretech

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.messoretech.databinding.ActivityBottomNavigationBinding
import br.com.messoretech.fragments.HomeFragment
import br.com.messoretech.fragments.SearchFragment

class BottomNavigationActivity : AppCompatActivity() {

    private val binding by lazy { ActivityBottomNavigationBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener {
            item -> when (item.itemId) {
                R.id.home -> openFragment(HomeFragment.newInstance())
                R.id.search -> openFragment(SearchFragment.newInstance())
            }
            true
        }
    }

    private fun openFragment(fragment : Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }

    companion object {
        fun createIntent(context: Context) : Intent = Intent(context, BottomNavigationActivity::class.java)
    }
}