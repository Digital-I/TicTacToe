package com.squirrel.lesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children

class Game : AppCompatActivity() {
    var stat = true
    private val buttonsSet: MutableSet<Button> = mutableSetOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)//activity_main
        val cell = findViewById<TableLayout>(R.id.Cell)
        for(row in cell.children){
            for(button in (row as TableRow).children){
                buttonsSet.add((button as Button))
                button.setOnClickListener{
                    if(button.text == "") {
                        button.text = if (stat) "X" else "O"
                        stat = !stat
                        checkWin()
                    }
                }
            }
        }
    }

    fun checkWin(){
        val combinations = setOf(setOf(0, 1, 2), setOf(3, 4, 5), setOf(6, 7, 8),
            setOf(0, 3, 6), setOf(1, 4, 7), setOf(2, 5, 8),
            setOf(0, 4, 8), setOf(2, 4, 6))
        var XorO: String = ""
        for (combination in combinations){
            XorO = buttonsSet.elementAt(combination.elementAt(0)).text as String

            if (buttonsSet.elementAt(combination.elementAt(0)).text == XorO &&
                buttonsSet.elementAt(combination.elementAt(1)).text == XorO &&
                buttonsSet.elementAt(combination.elementAt(2)).text == XorO
                && XorO != ""){
                findViewById<TextView>(R.id.winMessage).text = "Win $XorO"
            }
        }
    }
}