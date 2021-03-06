package model

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import notes.Note
import notes.NoteList

class NotesModel(private var sharedPreferences: SharedPreferences) {

    private var collectionLists : MutableList<NoteList>
    private var gson: Gson = Gson()

    init {
        collectionLists = mutableListOf()
        loadData()
    }

    fun getCollection() : MutableList<NoteList> {
        return collectionLists
    }

    fun getCollection(setting: Boolean) : MutableList<NoteList> {
        return if (setting){
            val reorganized: MutableList<NoteList> = mutableListOf()
            for(list in collectionLists){
                reorganized.add(list)
            }
            reorganized.sortBy { it.getName() }
            reorganized
        }else collectionLists
    }

    fun addList(noteList: NoteList){
        collectionLists.add(noteList)
        saveData()
    }
    
    fun removeList(noteList: NoteList){
        collectionLists.remove(noteList)
        saveData()
    }

    fun getNoteList(name: String): NoteList? {
        for (list in collectionLists){
            if (list.getName() == name){
                return list
            }
        }
        return null
    }

    fun addNote(name: String, note: Note){
        getNoteList(name)!!.addNote(note)
        saveData()
    }

    fun checkNote(namelist: String, text: String, check: Boolean){
        getNoteList(namelist)!!.checkNote(text,check)
        saveData()
    }

    fun deleteNote(name: String, text: String){
        if (getNoteList(name)?.removeNote(text) == true){
            saveData()
        }
    }

    private fun saveData(){
        val sp = sharedPreferences
        val editor: SharedPreferences.Editor = sp.edit()
        val json = gson.toJson(collectionLists)
        editor.putString("list",json)
        editor.apply()
    }

    private fun loadData(){
        val json = sharedPreferences.getString("list",null)
        if (!json.isNullOrEmpty()){
            val type = object : TypeToken<MutableList<NoteList>>() {}.type
            this.collectionLists = gson.fromJson(json, type)
        }
    }


}