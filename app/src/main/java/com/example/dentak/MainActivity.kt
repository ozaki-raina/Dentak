package com.example.dentak

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    /*メンバ変数*/
    var cal = Calculation()
    var iscolor = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*ボタン*/
        //数字ボタンIDの配列
        val btnlist = arrayOf<Button>(
                findViewById(R.id.btn0), findViewById(R.id.btn1), findViewById(R.id.btn2),
                findViewById(R.id.btn3), findViewById(R.id.btn4), findViewById(R.id.btn5),
                findViewById(R.id.btn6), findViewById(R.id.btn7), findViewById(R.id.btn8),
                findViewById(R.id.btn9))
        //演算子
        val btn_add = findViewById<Button>(R.id.btn_add)
        val btn_sub = findViewById<Button>(R.id.btn_sub)
        val btn_div = findViewById<Button>(R.id.btn_div)
        val btn_mul = findViewById<Button>(R.id.btn_mul)
        val btn_equal = findViewById<Button>(R.id.btn_equal)
        //クリア
        val btn_C = findViewById<Button>(R.id.btn_C)
        //小数点
        val btn_point = findViewById<Button>(R.id.btn_point)
        //+/-ボタン
        val btn_PlusMinus = findViewById<Button>(R.id.btn_plus_minus)
        //色
        val btn_color = findViewById<Button>(R.id.btn_color)
        val layout = findViewById<View>(R.id.layout)
        /*テキストビュー*/
        //表示画面
        val disp = findViewById<TextView>(R.id.disp)
        disp.text = cal.InputFormat(Calculation.display)
        val tv_operator = findViewById<TextView>(R.id.tv_operator)
        /*ボタンの処理*/
        //数字
        btnlist.forEachIndexed { i, button ->
            button.setOnClickListener {
                cal.Input(((i).toString()))
                disp.text = cal.InputFormat(Calculation.display)
            }
        }
        //演算子
        btn_add.setOnClickListener {
            cal.Input("+")
            tv_operator.text = "+"
            disp.text = cal.InputFormat(Calculation.display)
        }
        btn_sub.setOnClickListener {
            cal.Input("-")
            tv_operator.text = "-"
            disp.text = cal.InputFormat(Calculation.display)
        }
        btn_div.setOnClickListener {
            cal.Input("/")
            tv_operator.text = "÷"
            disp.text = cal.InputFormat(Calculation.display)
        }
        btn_mul.setOnClickListener {
            cal.Input("*")
            tv_operator.text = "×"
            disp.text = cal.InputFormat(Calculation.display)
        }
        btn_equal.setOnClickListener {
            cal.Input("=")
            tv_operator.text = ""
            disp.text = cal.InputFormat(Calculation.display)
        }
        btn_C.setOnClickListener {
            cal.Input("C")
            tv_operator.text = ""
            disp.text = cal.InputFormat(Calculation.display)
        }
        btn_PlusMinus.setOnClickListener {
            cal.Input("+/-")
            disp.text = cal.InputFormat(Calculation.display)
        }
        btn_point.setOnClickListener {
            cal.Input(".")
            disp.text = Calculation.display
        }
        btn_color.setOnClickListener{
            if(iscolor) {
                layout.background = getDrawable(R.color.black)
                disp.setTextColor(Color.WHITE)
                tv_operator.setTextColor(Color.WHITE)
                btn_color.setText(R.string.btn_white)
                iscolor = !iscolor
            }else{
                layout.background = getDrawable(R.color.white)
                disp.setTextColor(Color.BLACK)
                tv_operator.setTextColor(Color.BLACK)
                btn_color.setText(R.string.btn_black)
                iscolor = !iscolor
            }
        }
    }
}
