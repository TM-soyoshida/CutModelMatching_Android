package training20.tcmobile.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.hairdresser_activity_salon_matching_calendar_day.*
import training20.tcmobile.R

class HairdresserSalonMatchingCalendarDayActivity : AppCompatActivity() {

    private class Recruitment(val startHour: Int, val startMinute: Int, val endHour: Int, val endMinute: Int) {
    }

    private class Hairdresser(val recruitmentArray: Array<Recruitment>) {
    }

    private val hairdressers = arrayOf(
        Hairdresser(
            arrayOf(
                Recruitment(13, 0, 14, 0),
                Recruitment(16, 0, 17, 0),
                Recruitment(19, 0, 21, 0)
            )
        ),
        Hairdresser(
            arrayOf(
                Recruitment(6, 0, 7, 0),
                Recruitment(9, 0, 10, 0),
                Recruitment(13, 0, 14, 0)
            )
        ),
        Hairdresser(
            arrayOf(
                Recruitment(4, 0, 5, 0),
                Recruitment(15, 0, 16, 0)
            )
        ),
        Hairdresser(
            arrayOf(
                Recruitment(18, 0, 19, 0)
            )
        ),
        Hairdresser(
            arrayOf(
                Recruitment(21, 0, 22, 0)
            )
        ),
        Hairdresser(
            arrayOf(
                Recruitment(5, 0, 6, 0),
                Recruitment(19, 0, 22, 0)
            )
        ),
        Hairdresser(
            arrayOf(
                Recruitment(8, 0, 10, 0)
            )
        ),
        Hairdresser(
            arrayOf(
                Recruitment(10, 0, 13, 0)
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hairdresser_activity_salon_matching_calendar_day)
        setupViews()
    }

    private fun setupViews() {
        setupHairdresserIconsScrollView()
        setupMatchingScrollView()
        setupTimeScrollView()
    }

    private fun setupHairdresserIconsScrollView() {
        hairdressers.forEach {
            val linearLayout = LinearLayout(this)
            linearLayout.gravity = Gravity.CENTER
            val linearLayoutWidth = resources.getDimension(R.dimen.hairdresser_activity_salon_matching_calendar_day_hairdresser_icon_layout_width)
            val linearLayoutHeight = resources.getDimension(R.dimen.hairdresser_activity_salon_matching_calendar_day_hairdresser_icon_layout_height)
            val iconImageView = CircleImageView(this)
            val iconSize = linearLayoutWidth * 0.8
            iconImageView.setImageDrawable(getDrawable(R.drawable.user_icon))
            linearLayout.addView(iconImageView,
                LinearLayout.LayoutParams(
                    iconSize.toInt(),
                    iconSize.toInt()
                ))
            hairdresserIconsLayout.addView(linearLayout,
                LinearLayout.LayoutParams(
                    linearLayoutWidth.toInt(),
                    linearLayoutHeight.toInt()
                )
            )
        }
        hairdresserIconsScrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            matchingHorizontalScrollView.scrollTo(scrollX, 0)
        }
    }

    private fun setupMatchingScrollView() {
        hairdressers.forEach { hairdresser ->
            val layout = RelativeLayout(this)
            hairdresser.recruitmentArray.forEach { recruitment ->
                // TODO: 分数も考慮
                val hours = recruitment.endHour - recruitment.startHour
                val topMargin = recruitment.startHour * resources.getDimension(R.dimen.hairdresser_activity_salon_matching_calendar_day_time_text_view_height)
                val barView = View(this)
                barView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
                val layoutParams = RelativeLayout.LayoutParams(
                    (resources.getDimension(R.dimen.hairdresser_activity_salon_matching_calendar_day_hairdresser_icon_layout_width) * 0.6F).toInt(),
                    (resources.getDimension(R.dimen.hairdresser_activity_salon_matching_calendar_day_time_text_view_height) * hours).toInt()
                )
                layoutParams.topMargin = topMargin.toInt()
                layoutParams.leftMargin = ((resources.getDimension(R.dimen.hairdresser_activity_salon_matching_calendar_day_hairdresser_icon_layout_width) - layoutParams.width) * 0.5F).toInt()
                layout.addView(barView, layoutParams)
            }
            matchingLayout.addView(layout,
                LinearLayout.LayoutParams(
                    resources.getDimension(R.dimen.hairdresser_activity_salon_matching_calendar_day_hairdresser_icon_layout_width).toInt(),
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
            )
        }
        matchingHorizontalScrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            hairdresserIconsScrollView.scrollTo(scrollX, 0)
        }
        matchingVerticalScrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            timeScrollView.scrollTo(0, scrollY)
        }
    }

    private fun setupTimeScrollView() {
        timeScrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            matchingVerticalScrollView.scrollTo(0, scrollY)
        }
    }

}