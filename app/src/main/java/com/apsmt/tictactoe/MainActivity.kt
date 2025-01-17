package com.apsmt.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.apsmt.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private var boardList = mutableListOf<Button>()
    private var currentTurn = Turn.CROSS

    companion object {
        private const val CROSS = "X"
        private const val NOUGHT = "O"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBoard()

    }

    private fun initBoard(){
        boardList.add(binding.btn1)
        boardList.add(binding.btn2)
        boardList.add(binding.btn3)
        boardList.add(binding.btn4)
        boardList.add(binding.btn5)
        boardList.add(binding.btn6)
        boardList.add(binding.btn7)
        boardList.add(binding.btn8)
        boardList.add(binding.btn9)

        for(button in boardList){
            button.setOnClickListener { tapBoard(it) }
        }
    }

    private fun tapBoard(view: View){
        if(view is Button){
            addToBoard(view)
        }
    }

    private fun addToBoard(button: Button) {
        if(currentTurn == Turn.CROSS) {
            button.text = CROSS
            currentTurn = Turn.NOUGHT
        }

        else if(currentTurn == Turn.NOUGHT) {
            button.text = NOUGHT
            currentTurn = Turn.CROSS
        }

        setTurnLabel()
    }

    private fun setTurnLabel() {
        var turnText = ""
        if(currentTurn == Turn.CROSS){
            turnText = "Turn $CROSS"
        }

        else if(currentTurn == Turn.NOUGHT){
            turnText = "Turn $NOUGHT"
        }

        binding.txvGameStatus.text = turnText
    }
}