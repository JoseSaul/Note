package view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.note.R
import model.NotesModel
import notes.Note
import notes.NoteList

class MainActivity : AppCompatActivity() {

    private lateinit var layoutlist:LinearLayout
    private lateinit var listname: EditText
    private lateinit var notesModel: NotesModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notesModel = NotesModel()
        layoutlist = findViewById(R.id.layout_list)
        listname = findViewById(R.id.list_name)

        //initList(notesModel.getCollection())
    }

    private fun initList(collection: List<NoteList>) {
        //Cargar las listas guardadas al iniciar
    }

    fun addNewList(view: View){
        if (this.listname.text.toString() == "") return
        notesModel.addList(NoteList(listname.text.toString()))
        updateView()

        listname.setText("")
    }

    private fun updateView() {
        if (notesModel.getCollection().isNotEmpty()){
            if (layoutlist.childCount > 0){
                layoutlist.removeAllViews()
            }
            for (list in notesModel.getCollection()){
                addView(list)
            }
        }
    }

    @SuppressLint("InflateParams")
    private fun addView(list: NoteList){
        val listView: View = layoutInflater.inflate(R.layout.add_list,null,false)
        val buttonEnter: Button = listView.findViewById(R.id.EnterListButton)
        val buttonDelete: Button = listView.findViewById(R.id.DeleteListButton)

        buttonEnter.text = list.getName()

        buttonEnter.setOnClickListener { openList(list) }
        buttonDelete.setOnClickListener { deleteView(listView, list) }

        layoutlist.addView(listView)
    }


    private fun openList(list: NoteList){
        val intent = Intent(this, NoteActivity::class.java)
        intent.putExtra("model", notesModel)
        intent.putExtra("listname", list.getName())
        startActivity(intent)
    }

    private fun deleteView(v: View, list: NoteList){
        val toast = Toast.makeText(applicationContext, getString(R.string.removed_list), Toast.LENGTH_SHORT)
        toast.show()
        notesModel.removeList(list)
        layoutlist.removeView(v)
    }


}
