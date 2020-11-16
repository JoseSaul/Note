package notes

class NoteList {

    private var list: MutableList<Note>
    private var name: String

    constructor(name: String){
        this.name = name
        list = mutableListOf()
    }

    constructor(name: String, list: MutableList<Note>){
        this.name = name
        this.list = list
    }

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
        return this.name
    }

    fun getNotes(): MutableList<Note> {
        return list
    }

}