package notes

class Note {

    private var check: Comparable<Boolean> = false
    private var message: String

    constructor(message: String){
        this.message = message
    }

    constructor(check: Comparable<Boolean>, message: String){
        this.check = check
        this.message = message
    }

    fun getMessage():String{
        return message
    }
}