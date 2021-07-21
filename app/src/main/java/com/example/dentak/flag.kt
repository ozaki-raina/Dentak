package com.example.dentak

class flag {
    companion object {
        var isMinus = false
        var isPoint = false
        var num = ""
        var dispry = "0"
        var total = 0.0
        var operator = Status.NONE
    }

    fun Input(input: String) {
    when (input) {
        //数値が入力されたとき
        "0","1", "2", "3", "4", "5", "6", "7", "8", "9" ->
            if (num.length < 9) {
                num += input
                num = NumberFormat(num)

                    //先頭に0が並ばないようにする処理と
                    //isminusでマイナスにするかの判定
                    var w = if (isMinus) {
                        num.toDouble() * (-1)
                    } else {
                        num.toDouble()
                    }
                        dispry = num
            }
        //「+」が入力されたとき
        "+" -> if (operator == Status.NONE && dispry != "") {
            operator = Status.ADD
            total = dispry.toDouble()
            num = ""
        } else if (operator != Status.NONE) {
            Calculation(operator)
            operator = Status.ADD
            dispry = total.toString()
            num = ""
        }
        //「-」が入力されたとき
        "-" -> if (operator == Status.NONE && dispry != "") {
            operator = Status.SUB
            total = dispry.toDouble()
            num = ""
        } else if (operator != Status.NONE) {
            Calculation(operator)
            operator = Status.SUB
            dispry = total.toString()
            num = "";
        }
        //「÷」が入力されたとき
        "/" -> if (operator == Status.NONE && dispry != "") {
            operator = Status.DIV
            total = dispry.toDouble()
            num = ""
        } else if (operator != Status.NONE) {
            Calculation(operator)
            operator = Status.DIV
            dispry = total.toString()
            num = "";
        }
        //「×」が入力されたとき
        "*" -> if (operator == Status.NONE && dispry != "") {
            operator = Status.MUL
            total = dispry.toDouble()
            num = ""
        } else if (operator != Status.NONE) {
            Calculation(operator)
            operator = Status.MUL
            dispry = total.toString()
            num = "";
        }
        //「=」が入力されたとき
        "=" -> {
            Calculation(operator)
            dispry = total.toString()
            operator = Status.NONE
            total = 0.0
            num = ""
        }
        //「AC」が入力されたとき
        "C" -> {
            num = "0"
            dispry = "0"
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
            dispry = (dispry.toDouble() * (-1)).toString()
        }
        "." -> if(num.indexOf(".") == -1){
            num += "."
            dispry = num
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
    fun Calculation(status: Status) {
        when (status) {
            Status.ADD -> {
                total +=  dispry.toDouble()
            }
            Status.SUB -> {
                total -= dispry.toDouble()
            }
            Status.DIV -> {
                total /=  dispry.toDouble()
            }
            Status.MUL -> {
                total *= dispry.toDouble()
            }
        }
        isMinus = false
        isPoint = false
    }

    fun NumberFormat(number: String) :String{
        val doubleNum :Double = number.toDouble()
        val intNum :Int= doubleNum.toInt()
        var formatstr = ""
        if(isPoint) {
            formatstr = number
        }
        else{
            //「.0」になる場合、整数にする　例）12.0→12
            if (doubleNum - intNum.toDouble() == 0.0 && intNum.toString().length < 10) {
                formatstr = String.format("%,d", intNum)
            }
            //少数の場合
            else {
                formatstr = doubleNum.toString()
            }
        }
        if(formatstr.length > 13){
            formatstr = String.format("%9e" , doubleNum)
        }
        return formatstr
    }
}
