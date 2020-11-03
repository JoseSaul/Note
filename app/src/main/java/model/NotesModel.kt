package model

import notes.NoteList

class NotesModel {

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


}