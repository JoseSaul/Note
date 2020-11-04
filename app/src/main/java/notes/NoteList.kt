package notes

import java.io.Serializable

class NoteList(name: String) {

    private var list: MutableList<Note> = mutableListOf()
    private var listname: String = name

    fun addNote(note: Note){
        list.add(note)
    }

    fun removeNote(text: String){
        for (note in list){
            if(note.getMessage() == text){
                list.remove(note)
            }
        }
    }

    fun getName(): String {
        return this.listname
    }

    fun getNotes(): MutableList<Note> {
        return list
    }

}