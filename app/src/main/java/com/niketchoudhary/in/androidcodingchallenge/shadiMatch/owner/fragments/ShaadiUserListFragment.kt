package com.niketchoudhary.`in`.androidcodingchallenge.shadiMatch.owner.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.niketchoudhary.`in`.androidcodingchallenge.R
import com.niketchoudhary.`in`.androidcodingchallenge.databinding.FragmentShaadiUserListBinding
import com.niketchoudhary.`in`.androidcodingchallenge.shadiMatch.observer.ShaadiUserDataViewModel
import com.niketchoudhary.`in`.androidcodingchallenge.shadiMatch.owner.adapter.ShaadiUserListAdapter
import com.niketchoudhary.`in`.androidcodingchallenge.shadiMatch.owner.adapter.ShaadiUserListAdapter.Companion.ACCEPT
import com.niketchoudhary.`in`.androidcodingchallenge.shadiMatch.owner.adapter.ShaadiUserListAdapter.Companion.DECLINE
import com.niketchoudhary.`in`.androidcodingchallenge.utility.*

import javax.inject.Inject

class ShaadiUserListFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var executors: AppExecutors

    private val viewModel: ShaadiUserDataViewModel by viewModels { viewModelFactory }

    var dataBinding by autoCleared<FragmentShaadiUserListBinding>()
    var adapter by autoCleared<ShaadiUserListAdapter>()

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    var flag = false
    var empId: String? = null
    private var itemCount: Int = 0
    private var _status = Status.LOADING

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return DataBindingUtil.inflate<FragmentShaadiUserListBinding>(
            inflater,
            R.layout.fragment_shaadi_user_list,
            container,
            false,
            dataBindingComponent
        )
            .also { dataBinding = it }
            .run { this.root }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (isNetworkAvailable()) viewModel.deleteList()

        viewModel.init()

        adapter = ShaadiUserListAdapter(
            dataBindingComponent = dataBindingComponent,
            appExecutors = executors
        ) { data, action, position ->

            when (action) {
                "retry" -> viewModel.retry()

                ACCEPT -> {
                    if (data != null) {
                        viewModel.updateMatchStatus(
                            id = data.cell,
                            selectionStatus = "Member Accepted",
                            position
                        )
                    }
                }

                DECLINE -> {
                    if (data != null) {
                        viewModel.updateMatchStatus(
                            id = data.cell,
                            selectionStatus = "Member Declined",
                            position
                        )
                    }
                }
            }
        }

        dataBinding.let {
            it.recycler.adapter = adapter
            it.lifecycleOwner = this
        }

        viewModel.posts.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            dataBinding.count = it?.size

            viewModel.shouldFetch = false

            try {
                if (itemCount == 0) {
                    it?.let { itemCount = it.size }
                    Handler(Looper.getMainLooper()).postDelayed({
                        if (isVisible)
                            dataBinding.recycler.scrollToPosition(viewModel.position)
                    }, 200)
                }
            } catch (e: Throwable) {

            }
        })
        viewModel.networkState.observe(viewLifecycleOwner, Observer { status ->
            status?.let {
                dataBinding.status = status
                adapter.updateNetworkState(status)
                if (_status == Status.ERROR) {
                    showSnackBar(getString(R.string.generalError))
                }
            }
        })
    }

    override fun onResume() {
        itemCount = 0
        super.onResume()
    }

    fun navController() = findNavController()

    private fun showSnackBar(message: String) {
        if (!isVisible) return
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }
}