package molina.raul.mydigimind.ui.notifications

import java.io.Serializable

data class Recordatorio(var dias: ArrayList<String>,
                        var tiempo: String,
                        var nombre: String) : Serializable
