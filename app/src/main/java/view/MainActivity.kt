package view

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.note.R
import model.NotesModel
import notes.NoteList

class MainActivity : AppCompatActivity(){

    private var settings = arrayOf(false)

    private lateinit var layoutlist: LinearLayout
    private lateinit var listname: EditText
    private lateinit var model: NotesModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = NotesModel(getSharedPreferences("save", MODE_PRIVATE))
        layoutlist = findViewById(R.id.layout_list)
        listname = findViewById(R.id.list_name)

        loadPreferences()
        updateView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val alphabetical: MenuItem = menu!!.findItem(R.id.alphabetical_orders)
        alphabetical.isChecked = settings[0]
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.alphabetical_orders -> {
                item.isChecked = !item.isChecked
                settings[0] = item.isChecked
                updateView()
            }
        }
        savePreferences()
        return super.onOptionsItemSelected(item)
    }

    private fun savePreferences(){
        val sharedPreferences: SharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean("Alphabetical", settings[0])
        editor.apply()
    }

    private fun loadPreferences() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        settings[0] = sharedPreferences.getBoolean("Alphabetical", false)
    }

    fun addNewList(view: View){
        if (this.listname.text.toString() == "") return
        model.addList(NoteList(listname.text.toString()))
        updateView()

        listname.setText("")
    }

    private fun updateView() {
        if (model.getCollection().isNotEmpty()){
            if (layoutlist.childCount > 0){
                layoutlist.removeAllViews()
            }
            for (list in model.getCollection( settings[0] )){
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
        intent.putExtra("list name", list.getName())
        startActivity(intent)
    }

    private fun deleteView(v: View, list: NoteList){
        Toast.makeText(applicationContext, getString(R.string.removed_list), Toast.LENGTH_SHORT).show()
        model.removeList(list)
        layoutlist.removeView(v)
    }

}
