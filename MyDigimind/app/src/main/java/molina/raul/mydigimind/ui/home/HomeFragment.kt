package molina.raul.mydigimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import molina.raul.mydigimind.R
import molina.raul.mydigimind.ui.notifications.Recordatorio


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var adaptador: AdaptardorTareas? = null

    companion object{
        var tasks = ArrayList<Recordatorio>()
        var first = true
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        if(first){
            fullTasks()
            first = false
        }

        homeViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        adaptador = AdaptardorTareas(root.context, tasks)
        root.gridview.adapter = adaptador
        return root
    }
    fun fullTasks(){
        tasks.add(Recordatorio(arrayListOf("Tuesday"), "17:30","Practice 1", ))
        tasks.add(Recordatorio(arrayListOf("Monday","Sunday"), "17:00","Practice 2"))
        tasks.add(Recordatorio(arrayListOf("Wednesday"), "14:00","Practice 3"))
        tasks.add(Recordatorio(arrayListOf("Saturday"), "11:00","Practice 4"))
        tasks.add(Recordatorio(arrayListOf("Friday"), "13:00","Practice 5"))
        tasks.add(Recordatorio(arrayListOf("Thursday"), "10:40","Practice 6"))
        tasks.add(Recordatorio(arrayListOf("Monday"), "12:00", "Practice 7" ))
    }

    private class AdaptardorTareas: BaseAdapter {
        var tasks = ArrayList<Recordatorio>()
        var contexto: Context? = null

        constructor(contexto: Context, tasks: ArrayList<Recordatorio>) {
            this.contexto = contexto
            this.tasks = tasks
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View{
            var task = tasks[p0]
            var inflador = LayoutInflater.from(contexto)
            var vista = inflador.inflate(R.layout.recordatorio, null)

            vista.textNombreRecordatorio.setText(task.nombre)
            vista.textDiasRecordatorio.setText(task.tiempo)
            vista.textTiempoRecordatorio.setText(task.dias.toString())

            return vista
        }

        override fun getItem(p0: Int): Any{
            return tasks[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return tasks.size
        }

    }
}