package com.example.myintensivetask60.ui

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myintensivetask60.ListGenerator
import com.example.myintensivetask60.MainActivity
import com.example.myintensivetask60.R
import com.example.myintensivetask60.data.Contact
import com.example.myintensivetask60.databinding.FragmentContactsBinding
import com.example.myintensivetask60.features.contacts.ContactAdapter
import com.example.myintensivetask60.features.contacts.ContactItemClickListener
import com.example.myintensivetask60.utils.BaseFragment
import com.example.myintensivetask60.utils.openKeyboard
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ContactsFragment : BaseFragment<FragmentContactsBinding>(FragmentContactsBinding::inflate),
    ContactItemClickListener {

    private val adapter: ContactAdapter by lazy { ContactAdapter(this) }

    private var contactList: ArrayList<Contact> = arrayListOf()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        contactList = ListGenerator.gener()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onItemClick(contact: Contact) {
        openDetails(DetailsContactFragment.newInstance(contact))
    }

    override fun onLongPress(contact: Contact) {
        displayDeleteDialog(contact)
    }

    private fun displayDeleteDialog(contact: Contact) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.delete_item_long_click_title))
            .setMessage(getString(R.string.delete_item_long_click_description))
            .setPositiveButton(getString(R.string.delete)
            ) { _, _ ->
                val newList = contactList.filter { it != contact }
                contactList.remove(contact)
                adapter.submitList(newList)
            }
            .setNegativeButton(getString(R.string.cancel)) { _, _, -> }
            .show()
    }

    private fun initViews() {
        with(binding) {
            adapter.submitList(contactList)
            contactFragmentRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            contactFragmentRecyclerView.adapter = adapter
            contactFragmentSearch.setOnClickListener {
                searchEditText.requestFocus()
                searchEditText.openKeyboard(requireContext())
            }
            searchEditText.addTextChangedListener { searchItem(it.toString()) }
        }
    }

    private fun openDetails(detailsContact: DetailsContactFragment) {
        (requireActivity() as MainActivity).openFragment(detailsContact)
    }

    private fun searchItem(query: String) {
        val filteredList = contactList.filter {
            it.lastName.contains(query) || it.name.contains(query)
        }
        adapter.submitList(filteredList)
    }

    companion object {

        private const val ARG_DATA = "ARG_DATA"

        fun newInstance(contact: Contact) =
            ContactsFragment().apply {
                arguments = bundleOf(
                    ARG_DATA to contact
                )
            }
    }
}