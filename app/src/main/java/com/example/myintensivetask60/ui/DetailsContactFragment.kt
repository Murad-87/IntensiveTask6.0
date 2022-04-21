package com.example.myintensivetask60.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.example.myintensivetask60.R
import com.example.myintensivetask60.data.Contact
import com.example.myintensivetask60.databinding.FragmentDetailsContactBinding
import com.example.myintensivetask60.utils.BaseFragment

class DetailsContactFragment :
    BaseFragment<FragmentDetailsContactBinding>(FragmentDetailsContactBinding::inflate) {

    private lateinit var contact: Contact

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initArgs()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initArgs() {
        contact = requireArguments().getParcelable(ARG_DATA)!!
    }

    private fun initView() {
        Glide.with(requireContext()).load(contact.url).into(binding.detailsContactImage)
        with(binding) {
            detailsContactFragmentEditName.setText(contact.name)
            detailsContactFragmentEditLastName.setText(contact.lastName)
            detailsContactFragmentEditPhoneNumber.setText(contact.phoneNumber)

            detailsContactFragmentBtnSave.setOnClickListener {
                saveChanges()
            }
        }
    }

    private fun saveChanges() {
        with(binding) {
            if (validateInputFields()) {
                contact.phoneNumber = detailsContactFragmentEditPhoneNumber.text.toString()
                contact.lastName = detailsContactFragmentEditLastName.text.toString()
                contact.name = detailsContactFragmentEditName.text.toString()
                requireActivity().onBackPressed()
            } else {
                Toast.makeText(requireContext(), getString(R.string.empty_fields_error_message), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun validateInputFields() =
        when {
            binding.detailsContactFragmentEditPhoneNumber.text.toString().trim().isEmpty() -> false
            binding.detailsContactFragmentEditLastName.text.toString().trim().isEmpty() -> false
            binding.detailsContactFragmentEditName.text.toString().trim().isEmpty() -> false
            else -> true
        }

    companion object {

        private const val ARG_DATA = "ARG_DATA"

        fun newInstance(contact: Contact) = DetailsContactFragment().apply {
                arguments = bundleOf(
                    ARG_DATA to contact
                )
            }
    }
}