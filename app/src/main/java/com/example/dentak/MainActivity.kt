package com.example.dentak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    /*メンバ変数*/
    var f = flag()

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
        /*テキストビュー*/
        //表示画面
        val disp = findViewById<TextView>(R.id.disp)
        disp.text = f.NumberFormat(flag.dispry)
        /*ボタンの処理*/
        //数字
        btnlist.forEachIndexed { i, button ->
            button.setOnClickListener {
                f.Input(((i).toString()))
                disp.text = f.NumberFormat(flag.dispry)
            }
        }
        //演算子
        btn_add.setOnClickListener {
            f.Input("+")
            disp.text = f.NumberFormat(flag.dispry)
        }
        btn_sub.setOnClickListener {
            f.Input("-")
            disp.text = f.NumberFormat(flag.dispry)
        }
        btn_div.setOnClickListener {
            f.Input("/")
            disp.text = f.NumberFormat(flag.dispry)
        }
        btn_mul.setOnClickListener {
            f.Input("*")
            disp.text = f.NumberFormat(flag.dispry)
        }

        btn_equal.setOnClickListener {
            f.Input("=")
            disp.text = f.NumberFormat(flag.dispry)
        }
        btn_C.setOnClickListener {
            f.Input("C")
            disp.text = f.NumberFormat(flag.dispry)
        }
        btn_PlusMinus.setOnClickListener {
            f.Input("+/-")
            disp.text = f.NumberFormat(flag.dispry)
        }
        btn_point.setOnClickListener {
            f.Input(".")
            disp.text = flag.dispry
        }
    }

}
