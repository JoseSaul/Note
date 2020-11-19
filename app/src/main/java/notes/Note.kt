package notes

class Note{

    private var check = false
    private var message: String

    constructor(message: String){
        this.message = message
    }

    constructor(check: Boolean, message: String){
        this.check = check
        this.message = message
    }

    fun setCheck(check: Boolean){
        this.check = check
    }

    fun getCheck(): Boolean{
        return check
    }

    fun getMessage(): String{
        return message
    }
}