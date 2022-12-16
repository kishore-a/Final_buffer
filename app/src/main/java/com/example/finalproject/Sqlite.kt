package com.example.finalproject

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
class Sqlite : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)

        // below code is to add on click
        // listener to our add name button
        val addName=findViewById<Button>(R.id.addName)
        val printName=findViewById<Button>(R.id.printName)
        val enterName=findViewById<EditText>(R.id.enterName)
        val enterAge=findViewById<EditText>(R.id.enterAge)
        val Name=findViewById<TextView>(R.id.Name)
        val Age=findViewById<TextView>(R.id.Age)
        val Delete=findViewById<Button>(R.id.Delete)

        Delete.setOnClickListener{
            val db=DBHelper(this,null)

                db.deletedb()



        }
        addName.setOnClickListener{

            // below we have created
            // a new DBHelper class,
            // and passed context to it
            val db = DBHelper(this, null)

            // creating variables for values
            // in name and age edit texts
            val name = enterName.text.toString()
            val age = enterAge.text.toString()

            // calling method to add
            // name to our database
            db.addName(name, age)

            Toast.makeText(this, name + " added to database", Toast.LENGTH_LONG).show()

            enterName.text.clear()
            enterAge.text.clear()
        }

       
        printName.setOnClickListener{

     
            val db = DBHelper(this, null)

         
            val cursor = db.getName()

            

            cursor!!.moveToFirst()

                Name.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
                Age.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")

                while(cursor.moveToNext()){
                    Name.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
                    Age.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")
                }


            cursor.close()
        }
    }
}