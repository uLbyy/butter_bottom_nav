package com.haslan.butterbottomnav

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatCheckBox
import kotlinx.android.synthetic.main.view_butter_bottom_nav.view.*

class ButterBottomNavView(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {

    private lateinit var butterBottomNavListener: ButterBottomNavListener

    private var selectedTab = ButterBottomNavUtils.UNDEFINED

    init {
        init(context, attrs)
    }

    @SuppressLint("Recycle")
    private fun init(context: Context, attrs: AttributeSet) {
        View.inflate(context, R.layout.view_butter_bottom_nav, this)

        layout_tab_one.setOnClickListener {
            if (selectedTab == ButterBottomNavUtils.TAB_ONE) {
                sameTabClicked(ButterBottomNavUtils.TAB_ONE)
            } else {
                tabOneClicked()
            }
        }

        layout_tab_two.setOnClickListener {
            if (selectedTab == ButterBottomNavUtils.TAB_TWO) {
                sameTabClicked(ButterBottomNavUtils.TAB_TWO)
            } else {
                tabTwoClicked()
            }
        }

        layout_tab_three.setOnClickListener {
            if (selectedTab == ButterBottomNavUtils.TAB_THREE) {
                sameTabClicked(ButterBottomNavUtils.TAB_THREE)
            } else {
                tabThreeClicked()
            }
        }

        layout_tab_four.setOnClickListener {
            if (selectedTab == ButterBottomNavUtils.TAB_FOUR) {
                sameTabClicked(ButterBottomNavUtils.TAB_FOUR)
            } else {
                tabFourClicked()
            }
        }

        image_button_center.setOnClickListener {
            centerTabClicked()
        }

        val attributes: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.ButterBottomNavView)

        val tabOneBackground =
            attributes.getDrawable(R.styleable.ButterBottomNavView_butter_bottom_nav_tab_one_selector)
        val tabTwoBackground =
            attributes.getDrawable(R.styleable.ButterBottomNavView_butter_bottom_nav_tab_two_selector)
        val tabThreeBackground =
            attributes.getDrawable(R.styleable.ButterBottomNavView_butter_bottom_nav_tab_three_selector)
        val tabFourBackground =
            attributes.getDrawable(R.styleable.ButterBottomNavView_butter_bottom_nav_tab_four_selector)

        val bottomNavBackground =
            attributes.getDrawable(R.styleable.ButterBottomNavView_butter_bottom_nav_background)

        val centerIcon =
            attributes.getResourceId(
                R.styleable.ButterBottomNavView_butter_bottom_nav_center_icon,
                ButterBottomNavUtils.UNDEFINED
            )
        val imageButtonBackground =
            attributes.getColor(
                R.styleable.ButterBottomNavView_butter_bottom_nav_center_button_background,
                Color.CYAN
            )

        check_tab_one.background = tabOneBackground
        check_tab_two.background = tabTwoBackground
        check_tab_three.background = tabThreeBackground
        check_tab_four.background = tabFourBackground

        layout_bottom_nav.background = bottomNavBackground

        val shape = GradientDrawable()
        shape.shape = GradientDrawable.OVAL
        shape.setColor(imageButtonBackground)
        image_button_center.background = shape

        if (centerIcon != ButterBottomNavUtils.UNDEFINED) {
            image_button_center.setImageResource(centerIcon)
        }

        attributes.recycle()
    }

    fun initListener(butterBottomNavListener: ButterBottomNavListener) {
        this@ButterBottomNavView.butterBottomNavListener = butterBottomNavListener
    }

    private fun tabOneClicked() {
        butterBottomNavListener.tabClicked(ButterBottomNavUtils.TAB_ONE)
    }

    private fun tabTwoClicked() {
        butterBottomNavListener.tabClicked(ButterBottomNavUtils.TAB_TWO)
    }

    private fun tabThreeClicked() {
        butterBottomNavListener.tabClicked(ButterBottomNavUtils.TAB_THREE)
    }

    private fun tabFourClicked() {
        butterBottomNavListener.tabClicked(ButterBottomNavUtils.TAB_FOUR)
    }

    private fun sameTabClicked(tab: Int) {
        butterBottomNavListener.sameTabClicked(tab)
    }

    private fun centerTabClicked() {
        butterBottomNavListener.centerTabClicked()
    }

    fun tabPositionChanged(tab: Int) {
        when (tab) {
            ButterBottomNavUtils.TAB_ONE -> {
                tabSelected(check_tab_one, check_tab_two, check_tab_three, check_tab_four)
                selectedTab = ButterBottomNavUtils.TAB_ONE
            }
            ButterBottomNavUtils.TAB_TWO -> {
                tabSelected(check_tab_two, check_tab_one, check_tab_three, check_tab_four)
                selectedTab = ButterBottomNavUtils.TAB_TWO
            }
            ButterBottomNavUtils.TAB_THREE -> {
                tabSelected(check_tab_three, check_tab_two, check_tab_one, check_tab_four)
                selectedTab = ButterBottomNavUtils.TAB_THREE
            }
            ButterBottomNavUtils.TAB_FOUR -> {
                tabSelected(check_tab_four, check_tab_two, check_tab_three, check_tab_one)
                selectedTab = ButterBottomNavUtils.TAB_FOUR
            }
        }
    }

    private fun tabSelected(
        checkBoxTabOne: AppCompatCheckBox, checkBoxTabTwo: AppCompatCheckBox,
        checkBoxTabThree: AppCompatCheckBox, checkBoxTabFour: AppCompatCheckBox
    ) {
        checkBoxTabOne.isChecked = true
        checkBoxTabTwo.isChecked = false
        checkBoxTabThree.isChecked = false
        checkBoxTabFour.isChecked = false
    }
}