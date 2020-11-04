package model

import notes.Note
import notes.NoteList
import org.w3c.dom.NameList
import java.io.Serializable

class NotesModel: Serializable {

    private var collectionlists : MutableList<NoteList>

    constructor(){
        collectionlists = mutableListOf()
        //Leer lo guardado y cargarlo sobre collectionlists
    }

    fun getCollection() : MutableList<NoteList> {
        return collectionlists
    }

    fun addList(noteList: NoteList){
        collectionlists.add(noteList)
        updateList(collectionlists)
    }
    
    fun removeList(noteList: NoteList){
        collectionlists.remove(noteList)
        updateList(collectionlists)
    }

    private fun updateList(lists: MutableList<NoteList>) {
        //Guardar Cambios------------------
        this.collectionlists = lists
    }

    fun getNoteList(name: String): NoteList? {
        for (list in collectionlists){
            if (list.getName() == name){
                return list
            }
        }
        return null
    }

    fun addNote(listname: String, note: Note){
        var list = getNoteList(listname)
        list!!.addNote(note)
    }

    fun deleteNote(name: String, text: String){
        var list = getNoteList(name)
        list!!.removeNote(text)
    }


}