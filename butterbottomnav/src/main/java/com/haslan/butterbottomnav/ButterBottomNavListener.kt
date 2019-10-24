package com.haslan.butterbottomnav

interface ButterBottomNavListener {

    fun tabClicked(tab: Int)

    fun sameTabClicked(tab: Int) {}

    fun centerTabClicked()
}