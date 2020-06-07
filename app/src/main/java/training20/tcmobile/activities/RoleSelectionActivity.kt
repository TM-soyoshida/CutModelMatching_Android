package training20.tcmobile.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_role_selection.*
import training20.tcmobile.RoleManager
import training20.tcmobile.R
import training20.tcmobile.fragments.RoleSelectionHairdresserCardFragment
import training20.tcmobile.fragments.RoleSelectionModelCardFragment

class RoleSelectionActivity : AppCompatActivity() {

    private inner class TabAdapter(fragmentManager: FragmentManager):
        FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
    {
        override fun getCount(): Int {
            return 2
        }

        override fun getItem(position: Int): Fragment {
            return when(position) {
                0 -> RoleSelectionModelCardFragment()
                else -> RoleSelectionHairdresserCardFragment()
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when(position) {
                0 -> getString(R.string.activity_role_selection_tab_item_title_model)
                else -> getString(R.string.activity_role_selection_tab_item_title_hairdresser)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_role_selection)
        setupViews()
        RoleManager.setRole(null)
    }

    private fun setupViews() {
        viewPager.adapter = TabAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }
}
