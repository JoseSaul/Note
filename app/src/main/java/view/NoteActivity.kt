package view

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.note.R
import model.NotesModel
import notes.Note

class NoteActivity : AppCompatActivity() {

    private lateinit var model: NotesModel
    private lateinit var noteswriter: EditText
    private lateinit var layoutnotes: LinearLayout
    private lateinit var name: String

    private var menushow: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        model = NotesModel(getSharedPreferences("save", MODE_PRIVATE))
        name = intent.extras!!.getString("list name").toString()

        layoutnotes = findViewById(R.id.layout_notes)
        noteswriter = findViewById(R.id.notes_writer)

        updateView()
    }

    private fun updateView() {
        val list = model.getNoteList(name)

        if (list!!.getNotes().isNotEmpty()){
            if (this.layoutnotes.childCount > 0){
                layoutnotes.removeAllViews()
            }

            for (note in list.getNotes()){
                addView(note)
            }
        }
    }

    @SuppressLint("InflateParams")
    private fun addView(note: Note){
        val notesView: View = layoutInflater.inflate(R.layout.add_notes,null,false)
        val layout: LinearLayout = notesView.findViewById(R.id.notes_button)
        val notecheck: CheckBox = notesView.findViewById(R.id.note_check)
        val notetext: TextView = notesView.findViewById(R.id.note_text)
        notetext.text = note.getMessage()
        if (note.getCheck()) notecheck.isChecked = true
        notecheck.setOnClickListener { onCheck(name, notetext.text.toString(), notecheck) }
        layout.setOnClickListener {if (!menushow){
            Handler().postDelayed(Runnable { openDeleteMenu(notesView, name, notetext.text.toString()) },100)
            menushow = true
        }}
        layoutnotes.addView(notesView)
    }

    fun addNewNote(view: View){
        if (this.noteswriter.text.toString() == "") return
        model.addNote(name, Note(noteswriter.text.toString()))
        updateView()
        noteswriter.setText("")
    }

    private fun onCheck(namelist: String, text: String, checkBox: CheckBox){
        model.checkNote(namelist, text, checkBox.isChecked)
    }

    private fun openDeleteMenu(view: View, namelist: String, text: String){
        val popupMenu = PopupMenu(this, view)
        popupMenu.setOnMenuItemClickListener{deleteNote(namelist, text)}
        popupMenu.setOnDismissListener { menushow = false }
        popupMenu.inflate(R.menu.delete_menu)
        popupMenu.show()
    }

    private fun deleteNote(namelist: String, text: String): Boolean {
        model.deleteNote(namelist, text)
        updateView()
        return true
    }


}