package com.apsmt.tictactoe

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.apsmt.tictactoe.databinding.ActivityMainBinding
import kotlin.random.Random

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

        binding.btnPlayAgain.setOnClickListener { startAgain() }

    }

    private fun initBoard() {
        boardList.add(binding.btn1)
        boardList.add(binding.btn2)
        boardList.add(binding.btn3)
        boardList.add(binding.btn4)
        boardList.add(binding.btn5)
        boardList.add(binding.btn6)
        boardList.add(binding.btn7)
        boardList.add(binding.btn8)
        boardList.add(binding.btn9)

        for (button in boardList) {
            button.setOnClickListener { tapBoard(it) }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun tapBoard(view: View) {
        if(view is Button) {
            if(view.text.isEmpty()) {
                addToBoard(view)

                if(checkWinner()) {
                    binding.txvGameStatus.text = "${view.text} Win!"
                    disableButton()
                }

                else if(draw()) {
                    binding.txvGameStatus.text = "Draw!"
                }
            }
        }
    }

    private fun addToBoard(button: Button) {
        if (currentTurn == Turn.CROSS) {
            button.text = CROSS
            currentTurn = Turn.NOUGHT
        } else if (currentTurn == Turn.NOUGHT) {
            button.text = NOUGHT
            currentTurn = Turn.CROSS
        }

        setTurnLabel()
    }

    private fun setTurnLabel() {
        var turnText = ""
        if (currentTurn == Turn.CROSS) {
            turnText = "Turn $CROSS"
        } else if (currentTurn == Turn.NOUGHT) {
            turnText = "Turn $NOUGHT"
        }

        binding.txvGameStatus.text = turnText
    }

    private fun checkWinner(): Boolean {
        val winningPosition = listOf(
            listOf(0, 1, 2),
            listOf(3, 4, 5),
            listOf(6, 7, 8),
            listOf(0, 4, 8),
            listOf(2, 4, 6),
            listOf(0, 3, 6),
            listOf(1, 4, 7),
            listOf(2, 5, 8)
        )

        for(i in winningPosition) {
            if(
                boardList[i[0]].text.isNotEmpty() &&
                boardList[i[0]].text == boardList[i[1]].text &&
                boardList[i[1]].text == boardList[i[2]].text
            ) {
                return true
            }
        }

        return false
    }

    private fun draw(): Boolean {
        return (boardList.all { it.text.isNotEmpty() })
    }

    private fun disableButton() {
        for(i in boardList) {
            i.isEnabled = false
        }
    }

    private fun startAgain() {
        for (i in boardList) {
            i.text = ""
            i.isEnabled = true
        }

        currentTurn = Turn.CROSS
        binding.txvGameStatus.text = "Turn $CROSS"
    }
}