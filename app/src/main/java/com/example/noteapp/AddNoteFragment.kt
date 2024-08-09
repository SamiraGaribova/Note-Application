package com.example.noteapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.Fragment
import com.example.noteapp.databinding.FragmentAddNoteBinding

class AddNoteFragment : Fragment() {
    private var binding: FragmentAddNoteBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.buttonSave?.setOnClickListener {
            val title = binding?.editTextTitle?.text.toString()
            val content = binding?.editTextContent?.text.toString()
            if (title.isNotEmpty() && content.isNotEmpty()) {
                setFragmentResult("noteRequestKey", bundleOf("noteTitleKey" to title, "noteContentKey" to content))
                parentFragmentManager.popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
