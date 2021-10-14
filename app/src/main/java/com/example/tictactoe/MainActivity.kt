package com.example.tictactoe

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    private lateinit var btTopL: Button
    private lateinit var btTop: Button
    private lateinit var btTopR: Button
    private lateinit var btCenterL: Button
    private lateinit var btCenter: Button
    private lateinit var btCenterR: Button
    private lateinit var btBottomL: Button
    private lateinit var btBottom: Button
    private lateinit var btBottomR: Button

    private lateinit var tvPlayAgain: TextView
    private lateinit var tvWinner: TextView
    private lateinit var rotateAnimation: Animation

    private lateinit var cvPlayer1: CardView
    private lateinit var cvPlayer2: CardView

    private lateinit var tvP1Text: TextView
    private lateinit var tvP2Text: TextView

    private lateinit var buttons: List<Button>
    private var playerOneTurn = true
    private var draw = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvPlayAgain = findViewById(R.id.tvPlayAgain)
        tvWinner = findViewById(R.id.tvWinner)
        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_animation)

        cvPlayer1 = findViewById(R.id.cvPlayer1)
        cvPlayer2 = findViewById(R.id.cvPlayer2)

        tvP1Text = findViewById(R.id.tvP1Text)
        tvP2Text = findViewById(R.id.tvP2Text)

        cvPlayer1.setBackgroundColor(Color.MAGENTA)

        btTopL = findViewById(R.id.btTopL)
        btTop = findViewById(R.id.btTop)
        btTopR = findViewById(R.id.btTopR)
        btCenterL = findViewById(R.id.btCenterL)
        btCenter = findViewById(R.id.btCenter)
        btCenterR = findViewById(R.id.btCenterR)
        btBottomL = findViewById(R.id.btBottomL)
        btBottom = findViewById(R.id.btBottom)
        btBottomR = findViewById(R.id.btBottomR)

        buttons = listOf(btTopL, btTop, btTopR, btCenterL, btCenter, btCenterR, btBottomL, btBottom, btBottomR)

        for(btn in buttons){
            addOnClickListener(btn)
        }
    }

    private fun addOnClickListener(button: Button){
        button.setOnClickListener {
            if(!tvWinner.isVisible){
                if(button.text.isBlank()){
                    if(playerOneTurn){
                        button.text = "X"
                        if(checkWin()){
                            gameOver(1)
                        }else{
                            playerOneTurn = false
                            cvPlayer2.setBackgroundColor(Color.MAGENTA)
                            cvPlayer1.setBackgroundColor(Color.WHITE)
                        }
                    }else{
                        button.text = "O"
                        if(checkWin()){
                            gameOver(2)
                        }else{
                            playerOneTurn = true
                            cvPlayer1.setBackgroundColor(Color.MAGENTA)
                            cvPlayer2.setBackgroundColor(Color.WHITE)
                        }
                    }
                }else{
                    Toast.makeText(this, "Please choose another tile", Toast.LENGTH_LONG).show()
                }
            }
            draw = true
            for(button in buttons){
                if(button.text.isBlank()){draw = false}
            }
            if(draw){gameOver(0)}
        }
    }

    private fun gameOver(player: Int){
        if(player>0){
            tvWinner.text = "Player $player Wins!"
        }else{
            tvWinner.text = "Draw"
        }
        tvWinner.isVisible = true
        tvWinner.startAnimation(rotateAnimation)
        tvPlayAgain.isVisible = true
        tvP1Text.text = "YES"
        cvPlayer1.setOnClickListener { this.recreate() }
        tvP2Text.text = "NO"
        cvPlayer1.setBackgroundColor(Color.BLUE)
        cvPlayer2.setBackgroundColor(Color.BLUE)
    }

    private fun checkWin(): Boolean{
        if(btTopL.text == btTop.text && btTopL.text == btTopR.text && btTopL.text.isNotBlank()){
            btTopL.startAnimation(rotateAnimation)
            btTop.startAnimation(rotateAnimation)
            btTopR.startAnimation(rotateAnimation)
            return true
        }
        if(btCenterL.text == btCenter.text && btCenterL.text == btCenterR.text && btCenterL.text.isNotBlank()){
            btCenterL.startAnimation(rotateAnimation)
            btCenter.startAnimation(rotateAnimation)
            btCenterR.startAnimation(rotateAnimation)
            return true
        }
        if(btBottomL.text == btBottom.text && btBottomL.text == btBottomR.text && btBottomL.text.isNotBlank()){
            btBottomL.startAnimation(rotateAnimation)
            btBottom.startAnimation(rotateAnimation)
            btBottomR.startAnimation(rotateAnimation)
            return true
        }
        if(btTopL.text == btCenterL.text && btTopL.text == btBottomL.text && btTopL.text.isNotBlank()){
            btTopL.startAnimation(rotateAnimation)
            btCenterL.startAnimation(rotateAnimation)
            btBottomL.startAnimation(rotateAnimation)
            return true
        }
        if(btTop.text == btCenter.text && btTop.text == btBottom.text && btTop.text.isNotBlank()){
            btTop.startAnimation(rotateAnimation)
            btCenter.startAnimation(rotateAnimation)
            btBottom.startAnimation(rotateAnimation)
            return true
        }
        if(btTopR.text == btCenterR.text && btTopR.text == btBottomR.text && btTopR.text.isNotBlank()){
            btTopR.startAnimation(rotateAnimation)
            btCenterR.startAnimation(rotateAnimation)
            btBottomR.startAnimation(rotateAnimation)
            return true
        }
        if(btTopL.text == btCenter.text && btTopL.text == btBottomR.text && btTopL.text.isNotBlank()){
            btTopL.startAnimation(rotateAnimation)
            btCenter.startAnimation(rotateAnimation)
            btBottomR.startAnimation(rotateAnimation)
            return true
        }
        if(btBottomL.text == btCenter.text && btBottomL.text == btTopR.text && btBottomL.text.isNotBlank()){
            btBottomL.startAnimation(rotateAnimation)
            btCenter.startAnimation(rotateAnimation)
            btTopR.startAnimation(rotateAnimation)
            return true
        }
        return false
    }
}