package com.example.noteapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.databinding.FragmentNoteListBinding

class NoteListFragment : Fragment() {
    private var binding: FragmentNoteListBinding? = null


    private val notes = mutableListOf<Note>()
    private lateinit var adapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NoteAdapter(notes)
        binding?.recyclerView?.layoutManager = LinearLayoutManager(context)
        binding?.recyclerView?.adapter = adapter

        binding?.fab?.setOnClickListener {
            findNavController().navigate(R.id.action_noteListFragment_to_addNoteFragment)
//            parentFragmentManager.commit {
//                setReorderingAllowed(true)
//                replace(R.id.nav_host_fragment, AddNoteFragment())
//                addToBackStack(null)
//            }
        }

        setFragmentResultListener("noteRequestKey") { _, bundle ->
            val title = bundle.getString("noteTitleKey")
            val content = bundle.getString("noteContentKey")
            if (title != null && content != null) {
                addNote(Note(title, content))
            }
        }
    }

    private fun addNote(note: Note) {
        notes.add(note)
        adapter.notifyItemInserted(notes.size - 1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
