package molina.raul.mydigimind.ui.dashboard

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        root.btn_time.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY,hour)
                cal.set(Calendar.MINUTE,minute)

                btn_time.text = SimpleDateFormat("HH:mm").format(cal.time)
            }

            TimePickerDialog(root.context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),true).show()

        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {

        })
            root.btn_save.setOnClickListener {
                var title = et_task.text.toString()
                var time = btn_time.text.toString()
                var days = ArrayList<String>()

                if (checkMonday.isChecked)
                    days.add("Monday")
                if (checkTuesday.isChecked)
                    days.add("Tuesday")
                if (checkWednesday.isChecked)
                    days.add("Wednesday")
                if(checkThursday.isChecked)
                    days.add("Thursday")
                if(checkFriday.isChecked)
                    days.add("Friday")
                if (checkSatuday.isChecked)
                    days.add("Saturday")
                if (checkSunday.isChecked)
                    days.add("Sunday")

                var reco = Recordatorio(title, days, time)

                HomeFragment.tasks.add(reco)

                Toast.makeText(root.context,"new task added", Toast.LENGTH_SHORT).show()
            }

        return root
    }
}