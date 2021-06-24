package com.itigraduationteam.madartask.ui.fragments.save

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.itigraduationteam.madartask.R
import com.itigraduationteam.madartask.utils.Constant.REG_AGE
import com.itigraduationteam.madartask.utils.Constant.REG_GENDER
import com.itigraduationteam.madartask.utils.Constant.REG_STRING
import com.itigraduationteam.madartask.databinding.FragmentSaveBinding
import com.itigraduationteam.madartask.model.User
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern


@AndroidEntryPoint
class SaveFragment : Fragment() {
    private lateinit var binding: FragmentSaveBinding
    private lateinit var user: User
    private lateinit var viewModel: SaveViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSaveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(SaveViewModel::class.java)

        binding.addFloatingActionButton.setOnClickListener {
            val name = binding.nameEditText.text.toString().trim()
            val jobTitle = binding.jobTitleEditText.text.toString().trim()
            val age = binding.ageEditText.text.toString().trim()
            val gender = binding.genderEditText.text.toString().trim()
            if (
                checkValidity(name) &&
                checkTitleValidity(jobTitle) &&
                checkAgeValidity(age) &&
                checkGenderValidity(gender)
            ) {
                user = User(name, age.toInt(), jobTitle, gender)
                viewModel.insertUser(user)
                binding.nameEditText.setText("")
                binding.ageEditText.setText("")
                binding.genderEditText.setText("")
                binding.jobTitleEditText.setText("")
                Toast.makeText(context, resources.getString(R.string.userAdded), Toast.LENGTH_LONG).show()
            } else {
                Snackbar.make(binding.root, resources.getString(R.string.CorrectInfo), Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    // check validity of name must be text
    private fun checkValidity(str: String): Boolean {
        return if (str.isEmpty()) {
            binding.nameEditText.error = getString(R.string.error)
            false
        } else if (!Pattern.matches(REG_STRING, str)) {
            binding.nameEditText.error = getString(R.string.wrong)
            false
        } else {
            true
        }
    }

    // check validity of job title must be text
    private fun checkTitleValidity(str: String): Boolean {
        return if (str.isEmpty()) {
            binding.jobTitleEditText.error = getString(R.string.error)
            false
        } else if (!Pattern.matches(REG_STRING, str)) {
            binding.jobTitleEditText.error = getString(R.string.wrong)
            false
        } else {
            true
        }
    }

    // check validity of gender must be char ( m / f)
    private fun checkGenderValidity(str: String): Boolean {
        return if (str.isEmpty()) {
            binding.genderEditText.error = getString(R.string.errorGender)
            false
        } else if (!Pattern.matches(REG_GENDER, str)) {
            binding.genderEditText.error = getString(R.string.wrong)
            binding.genderEditText.error = getString(R.string.wrong)
            false
        } else {
            true
        }
    }

    // check validity of age  must be number and greater than 15
    private fun checkAgeValidity(age: String): Boolean {
        return if (age.isEmpty()) {
            binding.ageEditText.error = getString(R.string.error)
            false
        } else if (!Pattern.matches(REG_AGE, age)) {
            binding.ageEditText.error = getString(R.string.wrong)
            false
        } else if ((age.toInt() <= 15)) {
            binding.ageEditText.error = getString(R.string.errorAge)
            false
        } else if ((age.toInt() > 100)) {
            binding.ageEditText.error = getString(R.string.errorAge)
            false
        } else {
            true
        }

    }
}

