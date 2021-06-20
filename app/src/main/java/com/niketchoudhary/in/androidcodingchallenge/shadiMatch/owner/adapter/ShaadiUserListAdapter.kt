package com.niketchoudhary.`in`.androidcodingchallenge.shadiMatch.owner.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.niketchoudhary.`in`.androidcodingchallenge.R
import com.niketchoudhary.`in`.androidcodingchallenge.databinding.AdapterLayoutShaadiUserListBinding
import com.niketchoudhary.`in`.androidcodingchallenge.shadiMatch.data.ShadiMatchDataTable
import com.niketchoudhary.`in`.androidcodingchallenge.utility.AppExecutors
import com.niketchoudhary.`in`.androidcodingchallenge.utility.DataBoundPagedListAdapter
import com.niketchoudhary.`in`.androidcodingchallenge.utility.Status


class ShaadiUserListAdapter(
    private val dataBindingComponent: DataBindingComponent,
    private val appExecutors: AppExecutors,
    private val callback: ((ShadiMatchDataTable?, action: String, position: Int) -> Unit)?
) : DataBoundPagedListAdapter<ShadiMatchDataTable, AdapterLayoutShaadiUserListBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<ShadiMatchDataTable>() {

        override fun areItemsTheSame(
            oldItem: ShadiMatchDataTable,
            newItem: ShadiMatchDataTable
        ): Boolean {
            return oldItem.selectionStatus == newItem.selectionStatus
        }

        override fun areContentsTheSame(
            oldItem: ShadiMatchDataTable,
            newItem: ShadiMatchDataTable
        ): Boolean {
            return oldItem.selectionStatus == newItem.selectionStatus
        }
    }

) {

    override fun createBinding(parent: ViewGroup): AdapterLayoutShaadiUserListBinding {
        val binding = DataBindingUtil
            .inflate<AdapterLayoutShaadiUserListBinding>(
                LayoutInflater.from(parent.context),
                R.layout.adapter_layout_shaadi_user_list,
                parent,
                false,
                dataBindingComponent
            )


        return binding
    }

    override fun bind(
        binding: AdapterLayoutShaadiUserListBinding,
        item: ShadiMatchDataTable,
        position: Int
    ) {
        binding.data = item

        binding.accept.setOnClickListener {
            binding.data?.let {
                callback?.invoke(it, ACCEPT, position)
            }
        }

        binding.decline.setOnClickListener {
            binding.data?.let {
                callback?.invoke(it, DECLINE, position)
            }
        }
    }

    override fun retryAction() {
        callback?.invoke(null, "retry", 0)
    }

    var netState: Status? = null
    override fun updateNetworkState(newNetworkState: Status) {
        super.updateNetworkState(newNetworkState)
        this.netState = newNetworkState
    }

    companion object {
        const val ACCEPT = "accept"
        const val DECLINE = "decline"
    }
}