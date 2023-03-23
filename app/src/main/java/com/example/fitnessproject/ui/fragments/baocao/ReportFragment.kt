package com.example.fitnessproject.ui.fragments.baocao

import com.applandeo.materialcalendarview.EventDay
import com.example.fitnessproject.R
import com.example.fitnessproject.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_report.*
import java.util.*
import kotlin.collections.ArrayList


class ReportFragment : BaseFragment<ReportViewModel>() {
    override fun getLayoutId() = R.layout.fragment_report

    override fun initScreen() {
        val events: MutableList<EventDay> = ArrayList()

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, 2)
        events.add(EventDay(calendar, R.drawable.ic_female))
        calendarView?.setEvents(events)

        calendarView?.setOnForwardPageChangeListener {

        }
        calendarView?.setOnPreviousPageChangeListener {
        }

        calendarView?.setOnDayClickListener { eventDay ->
        }
    }

    override fun bindData() {
        super.bindData()
    }
}