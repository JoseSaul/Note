package view

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.note.R
import model.NotesModel
import notes.Note
import notes.NoteList

class NoteActivity : AppCompatActivity() {

    private lateinit var model: NotesModel
    private lateinit var noteswriter: EditText
    private lateinit var layoutnotes: LinearLayout
    private lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        model = intent.extras!!.getSerializable("model") as NotesModel
        name = intent.extras!!.getString("listname").toString()

        layoutnotes = findViewById(R.id.layout_notes)
        noteswriter = findViewById(R.id.notes_writer)

        updateView()
    }


    private fun updateView() {
        var list = model.getNoteList(name)
        if (list!!.getNotes().isNotEmpty()){
            if (this.layoutnotes.childCount > 0){
                layoutnotes.removeAllViews()
            }
            for (note in list.getNotes()){
                addView(note)
            }
        }
    }

    private fun addView(note: Note){
        val notesView: View = layoutInflater.inflate(R.layout.add_notes,null,false)
        val notetext: TextView = notesView.findViewById(R.id.note_text)

        notetext.text = note.getMessage()

        //buttonEnter.setOnClickListener {  }

        layoutnotes.addView(notesView)
    }

    fun addNewNote(view: View){
        if (this.noteswriter.text.toString() == "") return
        model.addNote(name, Note(noteswriter.text.toString()))
        updateView()
        noteswriter.setText("")
    }

}