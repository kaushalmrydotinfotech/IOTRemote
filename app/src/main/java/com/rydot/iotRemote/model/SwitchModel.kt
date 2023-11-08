package com.rydot.iotRemote.model

import java.util.Objects

data class SwitchModel(var isOn:Boolean = false, var img:Int = 0,var number:String = "")


class SwitchModel1 {
    var isOn: Boolean
    var img: Int
    var number: String

    constructor() {
        isOn = false
        img = 0
        number = ""
    }

    constructor(isOn: Boolean, img: Int, number: String) {
        this.isOn = isOn
        this.img = img
        this.number = number
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val (isOn1, img1, number1) = o as SwitchModel
        return isOn == isOn1 && img == img1 && number == number1
    }

    override fun hashCode(): Int {
        return Objects.hash(isOn, img, number)
    }

    override fun toString(): String {
        return "SwitchModel{" +
                "isOn=" + isOn +
                ", img=" + img +
                ", number='" + number + '\'' +
                '}'
    }
}

