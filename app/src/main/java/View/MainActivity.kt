package View

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.note.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val boton : Button = findViewById(R.id.EnterListButton)
    }

    fun PruebaEntrar(view: View){
        val toast = Toast.makeText(applicationContext, "Hello", Toast.LENGTH_LONG)
        toast.show()

        val intent = Intent(this,NoteActivity::class.java)
        startActivity(intent)
    }

}