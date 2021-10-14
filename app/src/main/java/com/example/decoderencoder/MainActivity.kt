package com.example.decoderencoder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var rvAdapter: RVadapter

    lateinit var encodeFiled : EditText
    lateinit var decodeFiled: EditText

    lateinit var encodeBut: Button
    lateinit var decodeBut: Button

    lateinit var entries: ArrayList<Phrase>

    val alphabet = "abcdefghijklmnopqrstuvwxyz"

    var output = ""
    var pos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        encodeFiled = findViewById(R.id.encodeFiled)
        decodeFiled = findViewById(R.id.decodeFiled)

        encodeBut = findViewById(R.id.encodeBtu)
        decodeBut = findViewById(R.id.decodeBut)

        entries = arrayListOf()

        rvAdapter = RVadapter(entries)

        //recycler view
        recyclerView.adapter = RVadapter(entries)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //button
        encodeBut.setOnClickListener {
            encoder()
            entries.add(Phrase(output, false))
            rvAdapter.update()
            recyclerView.adapter?.notifyDataSetChanged()
        }

        decodeBut.setOnClickListener {
            decoder()
            entries.add(Phrase(output, false))
            rvAdapter.update()
            recyclerView.adapter?.notifyDataSetChanged()
        }
        entries.clear()
    }
    fun decoder(){
        if(!decodeFiled.text.isNullOrEmpty()){
            for(letter in decodeFiled.text.toString()){
                if(alphabet.indexOf(letter) < 0){
                    if(alphabet.uppercase().indexOf(letter) < 0){
                        output += letter
                    }else{
                        pos = alphabet.uppercase().indexOf(letter) - 13
                        if(pos < 0){
                            pos += 26
                        }
                        output += alphabet.uppercase()[pos]
                    }
                }else{
                    pos = alphabet.indexOf(letter) - 13
                    if(pos < 0){
                        pos += 26
                    }
                    output += alphabet[pos]
                }
            }
            entries.add(Phrase(decodeFiled.text.toString(), true))
            decodeFiled.text.clear()
        }else{
            Toast.makeText(this, "Phrase cannot be empty", Toast.LENGTH_LONG).show()
        }
    }

    fun encoder(){
        if(!encodeFiled.text.isNullOrEmpty()) {
            for (letter in encodeFiled.text.toString()) {
                if (alphabet.indexOf(letter) < 0) {
                    if (alphabet.uppercase().indexOf(letter) < 0) {
                        output += letter
                    } else {
                        pos = alphabet.uppercase().indexOf(letter) + 13
                        if (pos > 25) {
                            pos -= 26
                        }
                        output += alphabet.uppercase()[pos]
                    }
                } else {
                    pos = alphabet.indexOf(letter) + 13
                    if (pos > 25) {
                        pos -= 26
                    }
                    output += alphabet[pos]
                }
            }
            entries.add(Phrase(encodeFiled.text.toString(), true))
            encodeFiled.text.clear()

        }
    }
}