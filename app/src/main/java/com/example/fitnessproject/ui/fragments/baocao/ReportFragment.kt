package com.example.fitnessproject.ui.fragments.baocao

import com.applandeo.materialcalendarview.EventDay
import com.example.fitnessproject.R
import com.example.fitnessproject.ui.BaseFragment
import com.example.fitnessproject.ui.fragments.baocao.dialog.DialogAddWeight
import kotlinx.android.synthetic.main.fragment_report.*
import java.util.*


class ReportFragment : BaseFragment<ReportViewModel>() {
    override fun getLayoutId() = R.layout.fragment_report

    private var dialogAddWeight: DialogAddWeight? = null

    override fun initScreen() {
        val events: MutableList<EventDay> = ArrayList()

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, 2)
        events.add(EventDay(calendar, R.drawable.ic_female))
        calendarView?.setEvents(events)

        btnAddWeight?.setOnClickListener {
            activity?.let { a ->
                dialogAddWeight?.dismiss()
                (dialogAddWeight ?: DialogAddWeight.getInstance(viewModel.month!!)).apply {
                    onSaveWeightClicked = { day, weight ->
                        val time = Calendar.getInstance()
                        time.set(Calendar.DAY_OF_MONTH, day)
                        viewModel.insertOrUpdateWeight(weight, time.time)
                    }
                    show(a.supportFragmentManager, "")
                }
            }

        }
        calendarView?.setOnForwardPageChangeListener {
            changeMonthCalendar()
        }
        calendarView?.setOnPreviousPageChangeListener {
            changeMonthCalendar()
        }

        calendarView?.setOnDayClickListener { eventDay ->
        }
    }

    private fun changeMonthCalendar() {
        val currentMonth = calendarView.currentPageDate.get(Calendar.MONTH)
        val currentYear = calendarView.currentPageDate.get(Calendar.YEAR)
        viewModel.year = currentYear
        viewModel.month = currentMonth
        viewModel.getAllWeightInfoInTime(viewModel.year, viewModel.month)
    }

    override fun bindData() {
        super.bindData()
        viewModel.weightListLiveData.observe(this) { list ->
        }

        viewModel.isAddWeightLiveData.observe(this) { isSuccess ->
            if (isSuccess) {
                viewModel.getAllWeightInfoInTime(viewModel.year, viewModel.month)
            } else {

            }
        }

    }
}