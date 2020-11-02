package View

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.note.R

class MainActivity : AppCompatActivity() {

    private lateinit var layout_list:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layout_list = findViewById(R.id.layout_list)
        val boton : Button = findViewById(R.id.EnterListButton)
    }

    fun addNewList(view: View){
        addView()
    }

    fun addView(){
        val listView: View = layoutInflater.inflate(R.layout.add_list,null,false)
        val buttonEnter: Button = listView.findViewById(R.id.EnterListButton)
        val buttonDelete: Button = listView.findViewById(R.id.DeleteListButton)

        buttonEnter.setText("Hola")

        buttonEnter.setOnClickListener(View.OnClickListener { PruebaEntrar() })
        buttonDelete.setOnClickListener(View.OnClickListener { deleteView(listView) })


        layout_list.addView(listView)
    }


    private fun PruebaEntrar(){
        val toast = Toast.makeText(applicationContext, "Hello", Toast.LENGTH_LONG)
        toast.show()

        val intent = Intent(this,NoteActivity::class.java)
        startActivity(intent)
    }

    private fun deleteView(v: View){
        val toast = Toast.makeText(applicationContext, "Elimino", Toast.LENGTH_LONG)
        toast.show()

        layout_list.removeView(v)
    }


}
