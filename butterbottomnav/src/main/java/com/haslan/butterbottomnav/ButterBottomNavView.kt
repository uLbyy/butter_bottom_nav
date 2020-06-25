package com.haslan.butterbottomnav

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatImageButton
import kotlinx.android.synthetic.main.view_butter_bottom_nav.view.*


class ButterBottomNavView(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {

    private lateinit var butterBottomNavListener: ButterBottomNavListener

    private var selectedTab = ButterBottomNavUtils.UNDEFINED

    init {
        init(context, attrs)
    }

    @SuppressLint("Recycle", "ClickableViewAccessibility")
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

        image_button_center.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    v.startAnimation(AnimationUtils.loadAnimation(context, R.anim.bounce))
                    val porterDuffColorFilter =
                        PorterDuffColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP)
                    v.background.colorFilter = porterDuffColorFilter
                    v.invalidate()
                }
                MotionEvent.ACTION_UP -> {
                    v.startAnimation(AnimationUtils.loadAnimation(context, R.anim.bounce_reverse))
                    v.background.clearColorFilter()
                    v.invalidate()
                }
            }
            false
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
        val centerImageButtonBackground =
            attributes.getColor(
                R.styleable.ButterBottomNavView_butter_bottom_nav_center_button_background,
                Color.CYAN
            )

        image_tab_one.background = tabOneBackground
        image_tab_two.background = tabTwoBackground
        image_tab_three.background = tabThreeBackground
        image_tab_four.background = tabFourBackground

        layout_bottom_nav.background = bottomNavBackground

        val shape = GradientDrawable()
        shape.shape = GradientDrawable.OVAL
        shape.setColor(centerImageButtonBackground)
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
                tabSelected(image_tab_one, image_tab_two, image_tab_three, image_tab_four)
                selectedTab = ButterBottomNavUtils.TAB_ONE
            }
            ButterBottomNavUtils.TAB_TWO -> {
                tabSelected(image_tab_two, image_tab_one, image_tab_three, image_tab_four)
                selectedTab = ButterBottomNavUtils.TAB_TWO
            }
            ButterBottomNavUtils.TAB_THREE -> {
                tabSelected(image_tab_three, image_tab_two, image_tab_one, image_tab_four)
                selectedTab = ButterBottomNavUtils.TAB_THREE
            }
            ButterBottomNavUtils.TAB_FOUR -> {
                tabSelected(image_tab_four, image_tab_two, image_tab_three, image_tab_one)
                selectedTab = ButterBottomNavUtils.TAB_FOUR
            }
        }
    }

    private fun tabSelected(
        imageTabOne: AppCompatImageButton, imageTabTwo: AppCompatImageButton,
        imageTabThree: AppCompatImageButton, imageTabFour: AppCompatImageButton
    ) {
        imageTabOne.isActivated = true
        imageTabTwo.isActivated = false
        imageTabThree.isActivated = false
        imageTabFour.isActivated = false
    }
}