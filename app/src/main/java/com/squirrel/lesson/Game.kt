package com.squirrel.lesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.children

class Game : AppCompatActivity() {
    var stat = true
    private val buttonsSet: MutableSet<Button> = mutableSetOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        findViewById<TextView>(R.id.winMessage).text = "${if(stat) "X" else "O"} move"
        val cell = findViewById<TableLayout>(R.id.Cell)
        for(row in cell.children) {
            for(button in (row as TableRow).children) {
                buttonsSet.add((button as Button))
                button.setOnClickListener {
                    if(button.text == "") {
                        button.text = if (stat) "X" else "O"
                        findViewById<TextView>(R.id.winMessage).text = "${if(stat) "X" else "O"} move"
                        stat = !stat
                        checkWin()
                    }
                }
            }
        }
        restart()
    }

    private fun checkWin() {
        val combinations = setOf(setOf(0, 1, 2), setOf(3, 4, 5), setOf(6, 7, 8),
            setOf(0, 3, 6), setOf(1, 4, 7), setOf(2, 5, 8),
            setOf(0, 4, 8), setOf(2, 4, 6))
        var XorO = ""
        for (combination in combinations) {
            XorO = buttonsSet.elementAt(combination.elementAt(0)).text as String

            if (buttonsSet.elementAt(combination.elementAt(0)).text == XorO &&
                buttonsSet.elementAt(combination.elementAt(1)).text == XorO &&
                buttonsSet.elementAt(combination.elementAt(2)).text == XorO
                && XorO != ""){
                findViewById<TextView>(R.id.winMessage).text = "Win $XorO"
            }
        }
    }

    private fun restart() {
        val restartButton = findViewById<ImageButton>(R.id.reButton)
        restartButton.setOnClickListener {
            for (button in buttonsSet){
                button.text = ""
                findViewById<TextView>(R.id.winMessage).text = "${if(stat) "X" else "O"} move"
            }
        }
    }
}