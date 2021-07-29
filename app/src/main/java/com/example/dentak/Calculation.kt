package com.example.dentak

class Calculation {
    companion object {
        /*符号の状態
        false...プラス状態
        true...マイナス状態*/
        var isMinus = false
        /*小数点があるか
        * false...小数点なし
        　true...小数点あり*/
        var isPoint = false
        var num = ""        //入力された値を一時的に保管
        var display = "0"    //表示する値
        var total = 0.0     //合計値
        var operator = Status.NONE     //演算子の状態
    }

    /*入力されたボタンの処理*/
    fun Input(input: String) {
    when (input) {
        //数値が入力されたとき
        "0","1", "2", "3", "4", "5", "6", "7", "8", "9" ->
            if (num.length < 9) {
                num += input
                if(num == "00" && input == "0"){
                    num = num.drop(1)
                }else {
                    num = NumberFormat(num)
                    display = num
                }
            }
       // 「+」が入力されたとき
        "+" -> if (operator == Status.NONE && display != "") {
            operator = Status.ADD
            total = display.toDouble()
            num = ""
        } else if (operator != Status.NONE) {
            calc(operator)
            operator = Status.ADD
            display = total.toString()
            num = ""
        }
        //「-」が入力されたとき
        "-" -> if (operator == Status.NONE && display != "") {
            operator = Status.SUB
            total = display.toDouble()
            num = ""
        } else if (operator != Status.NONE) {
            calc(operator)
            operator = Status.SUB
            display = total.toString()
            num = ""
        }
        //「÷」が入力されたとき
        "/" -> if (operator == Status.NONE && display != "") {
            operator = Status.DIV
            total = display.toDouble()
            num = ""
        } else if (operator != Status.NONE) {
            calc(operator)
            operator = Status.DIV
            display = total.toString()
            num = "";
        }
        //「×」が入力されたとき
        "*" -> if (operator == Status.NONE && display != "") {
            operator = Status.MUL
            total = display.toDouble()
            num = ""
        } else if (operator != Status.NONE) {
            calc(operator)
            operator = Status.MUL
            display = total.toString()
            num = "";
        }
        //「=」が入力されたとき
        "=" -> {
            calc(operator)
            display = total.toString()
            operator = Status.NONE
            total = 0.0
            num = ""
        }
//        //実験
//        "0","1", "2", "3", "4", "5", "6", "7", "8", "9" ->
//            if (num.length < 9 && operator == Status.NONE) {
//                num += input
//                if(num == "0" && input == "0"){
//                    num = num.drop(1)
//                }else {
//                    num = NumberFormat(num)
//                    display = num
//                }
//            }
//        "+" -> if (operator == Status.NONE && display != "") {
//            operator = Status.ADD
//            total = display.toDouble()
//            num = ""
//        } else if (operator != Status.NONE) {
//            operator = Status.ADD
//            display = total.toString()
//            num = ""
//        }
//        //「-」が入力されたとき
//        "-" -> if (operator == Status.NONE && display != "") {
//            operator = Status.SUB
//            total = display.toDouble()
//            num = ""
//        } else if (operator != Status.NONE) {
//            operator = Status.SUB
//            display = total.toString()
//            num = ""
//        }
//        //「÷」が入力されたとき
//        "/" -> if (operator == Status.NONE && display != "") {
//            operator = Status.DIV
//            total = display.toDouble()
//            num = ""
//        } else if (operator != Status.NONE) {
//            operator = Status.DIV
//            display = total.toString()
//            num = "";
//        }
//        //「×」が入力されたとき
//        "*" -> if (operator == Status.NONE && display != "") {
//            operator = Status.MUL
//            total = display.toDouble()
//            num = ""
//        } else if (operator != Status.NONE) {
//            operator = Status.MUL
//            display = total.toString()
//            num = "";
//        }
//        //「=」が入力されたとき
//        "=" -> {
//            calc(operator)
//            display = total.toString()
//            operator = Status.NONE
//            total = 0.0
//            num = ""
//        }
        //「C」が入力されたとき
        "C" -> {
            num = ""
            display = "0"
            total = 0.0
            operator = Status.NONE
            isMinus = false
            isPoint = false
        }
        //「+/-」が押されたとき
        //false...プラス状態
        //true...マイナス状態
        "+/-" -> {
            isMinus = !isMinus
            if(num == "" ){
                num = "-0"
            } else if(num=="0."){
              num = "-0."
            } else{
                num = (num.toDouble() * (-1)).toString()
            }

            display = num
        }
        //「.」が押されたとき
        //false...小数点なし
        //true...小数点あり
        "." -> if(num.indexOf(".") == -1){
            if(num == ""){
                num += "0."
            }else {
                num += "."
            }
            display = num
            isPoint = true
        }
    }

}

    /*入力された演算子の状態*/
    enum class Status {
        NONE, //初期値
        ADD,  //「+」のとき
        SUB,  //「-」のとき
        DIV,  //「÷」のとき
        MUL,  //「×」のとき
    }

    /*計算処理*/
    fun calc(status: Status) {
        when (status) {
            Status.ADD -> {
                total +=  display.toDouble()
            }
            Status.SUB -> {
                total -= display.toDouble()
            }
            Status.DIV -> {
                total /=  display.toDouble()
            }
            Status.MUL -> {
                total *= display.toDouble()
            }
        }
        isMinus = false
        isPoint = false
    }

    /*出力の値フォーマット*/
    fun InputFormat(number: String) :String{
        val doubleNum :Double = number.toDouble()   //少数の値
        val intNum :Int= doubleNum.toInt()          //整数の値
        var formatstr = ""      //フォーマットされた値
        //小数点があるかの判定
        if(isPoint) {
            formatstr = number
        }
        else{
            //「.0」になる場合、整数にする　例）12.0→12
            if (doubleNum - intNum.toDouble() == 0.0 && intNum.toString().length < 10) {
//                if(number == "-0"){
//                    formatstr = number
                if(isMinus){
                    formatstr = number
                }else {
                    //3桁ごとに「, 」をつける
                    formatstr = String.format("%,d", intNum)
                }
                }
            //少数の場合
            else {
                formatstr = doubleNum.toString()
            }
        }
        /*計算結果が13桁以上の場合*/
        if(formatstr.length > 13){
            formatstr = String.format("%E" , doubleNum)
        }
        return formatstr
    }

    /*数値のフォーマット*/
    fun NumberFormat(number: String) :String{

        val doubleNum = number.toDouble()
        val intNum :Int= doubleNum.toInt()   //整数の値
        var formatstr = ""      //フォーマットされた値

        //小数点があるかの判定
        if(isPoint) {
            formatstr = number
        }
        else{
            //「.0」になる場合、整数にする　例）12.0→12
            if (doubleNum - intNum.toDouble() == 0.0) {
                formatstr = String.format("%d", intNum)
            }
            //少数の場合
            else {
                formatstr = doubleNum.toString()
            }
        }
        return formatstr
    }
}
