package com.itigraduationteam.madartask.ui.fragments.show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.itigraduationteam.madartask.R
import com.itigraduationteam.madartask.databinding.FragmentShowBinding
import com.itigraduationteam.madartask.ui.fragments.show.adapter.UserAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowFragment : Fragment() {

    private lateinit var binding: FragmentShowBinding
    private lateinit var userAdapter: UserAdapter
    private lateinit var viewModel: ShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ShowViewModel::class.java)

        initRecyclerView()
        observeData()
        viewModel.getAllUsers()
    }


    private fun observeData() {
        viewModel.userList.observe(viewLifecycleOwner, Observer { users ->
            if (users.isNotEmpty()) {
                binding.userDataRecyclerView.visibility = View.VISIBLE
                binding.noUsertext.visibility = View.INVISIBLE
                userAdapter.swapData(users)
            }else {
                binding.userDataRecyclerView.visibility = View.INVISIBLE
                binding.noUsertext.visibility = View.VISIBLE
            }
        })
    }

    private fun initRecyclerView() {
        userAdapter = UserAdapter() { _, user, _ ->
            Toast.makeText(requireContext(),resources.getString(R.string.username)+user.mName,Toast.LENGTH_SHORT).show()
        }

        binding.userDataRecyclerView.apply {
            adapter = userAdapter
        }
    }

}