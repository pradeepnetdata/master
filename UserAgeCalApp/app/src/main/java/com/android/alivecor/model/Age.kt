package com.android.alivecor.model

import java.lang.StringBuilder

class Age {
    var days = 0
        private set
    var months = 0
        private set
    var years = 0
        private set

    private constructor() {
        //Prevent default constructor
    }

    constructor(days: Int, months: Int, years: Int) {
        this.days = days
        this.months = months
        this.years = years
    }

    override fun toString(): String {
        var sb :StringBuilder = StringBuilder();
        if(years ==1){
            sb.append("$years Year,");
        }else if (years > 1){
            sb.append("$years Years,");
        }
        if(months ==1){
            sb.append("$months Month,");
        }else if (months > 1){
            sb.append("$months Months,");
        }
        if(days ==1){
            sb.append("$days Day");
        }else if (days > 1){
            sb.append("$days Days");
        }
        return sb.toString()
    }
}