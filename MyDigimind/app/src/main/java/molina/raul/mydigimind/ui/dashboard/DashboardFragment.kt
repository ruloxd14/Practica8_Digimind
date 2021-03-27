package molina.raul.mydigimind.ui.dashboard

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import molina.raul.mydigimind.R
import molina.raul.mydigimind.ui.home.HomeFragment
import molina.raul.mydigimind.ui.notifications.Recordatorio
import java.text.SimpleDateFormat
import java.util.*


class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    val btn_set: Button = findViewById(R.id.btn_set)
    val btn_done: Button = findViewById(R.id.btn_done)
    val et_take: Button = findViewById(R.id.et_take)


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        root.btn_set.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)

                btn_set.text = SimpleDateFormat("HH:mm").format(cal.time)
            }

            TimePickerDialog(
                root.context,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()

            dashboardViewModel.text.observe(viewLifecycleOwner, Observer {

            })
            root.btn_done.setOnClickListener {
                var title = et_take.text.toString()
                var time = btn_set.text.toString()
                var days = ArrayList<String>()

                if (checkMonday.isChecked)
                    days.add("Monday")
                if (checkTuesday.isChecked)
                    days.add("Tuesday")
                if (checkWednesday.isChecked)
                    days.add("Wednesday")
                if (checkThursday.isChecked)
                    days.add("Thursday")
                if (checkFriday.isChecked)
                    days.add("Friday")
                if (checkSatuday.isChecked)
                    days.add("Saturday")
                if (checkSunday.isChecked)
                    days.add("Sunday")

                var reco = Recordatorio(days, time, title)

                HomeFragment.tasks.add(reco)

                Toast.makeText(root.context, "new task added", Toast.LENGTH_SHORT).show()
            }

            return root
        }
    }