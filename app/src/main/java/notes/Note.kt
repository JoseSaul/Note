package notes

class Note constructor(message: String){

    private var check: Comparable<Boolean> = false
    private var message: String = message

    fun getMessage():String{
        return message
    }
}