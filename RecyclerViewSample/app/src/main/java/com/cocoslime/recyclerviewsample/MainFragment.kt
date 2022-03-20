package com.cocoslime.recyclerviewsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.cocoslime.recyclerviewsample.databinding.FragmentCommitListBinding
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentCommitListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_commit_list, container, false)

        val application = requireNotNull(this.activity).application
        val viewModel = ViewModelProvider(this, MainViewModel.Factory(application))
            .get(MainViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = MainAdapter()
        binding.commitList.adapter = adapter
        viewModel.commitList.observe(viewLifecycleOwner, Observer { commits ->
            commits?.let { adapter.commitList = commits}
        })

        return binding.root
    }

}