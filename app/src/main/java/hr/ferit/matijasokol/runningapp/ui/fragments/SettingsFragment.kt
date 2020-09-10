package hr.ferit.matijasokol.runningapp.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import hr.ferit.matijasokol.runningapp.R
import hr.ferit.matijasokol.runningapp.other.Constants.KEY_NAME
import hr.ferit.matijasokol.runningapp.other.Constants.KEY_WEIGHT
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_settings.*
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    @Inject
    lateinit var sharedPrefs: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadFieldsFromSharedPref()

        btnApplyChanges.setOnClickListener {
            val success = applyChangesToSharedPref()

            if (success) {
                Snackbar.make(view, getString(R.string.changes_saved), Snackbar.LENGTH_LONG).show()
            } else {
                Snackbar.make(view, getString(R.string.fill_all_inputs), Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun loadFieldsFromSharedPref() {
        val name = sharedPrefs.getString(KEY_NAME, "")
        val weight = sharedPrefs.getFloat(KEY_WEIGHT, 80f)

        etName.setText(name)
        etWeight.setText(weight.toString())
    }

    private fun applyChangesToSharedPref(): Boolean {
        val nameText = etName.text.toString()
        val weightText = etWeight.text.toString()

        if (nameText.isEmpty() || weightText.isEmpty()) {
            return false
        }

        sharedPrefs.edit()
            .putString(KEY_NAME, nameText)
            .putFloat(KEY_WEIGHT, weightText.toFloat())
            .apply()

        val toolbarText = "${getString(R.string.lets_go)}, $nameText"
        requireActivity().tvToolbarTitle.text = toolbarText

        return true
    }
}