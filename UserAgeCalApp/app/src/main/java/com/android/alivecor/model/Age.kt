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
        return "$years Years, $months Months, $days Days"
    }
}