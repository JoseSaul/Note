package notes

class NoteList {

    //lateinit var list: List<Note>
    private var listname: String

    constructor(name: String){
        listname = name
    }

    fun addNote(note: Note){
        //list.toMutableList().add(note)
    }

    fun getName(): String {
        return this.listname
    }

}