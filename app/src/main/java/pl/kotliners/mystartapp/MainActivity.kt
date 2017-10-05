package pl.kotliners.mystartapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
//https://query.yahooapis.com/v1/public/yql?q=select%20item.condition.text%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D"dallas%2C%20tx")&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys
    fun getSunset(view: View) {
        val city = etCityName.text.toString()
        val oneWordURL = "https://query.yahooapis.com/v1/public/yql?q=select%20item" +
                ".condition.text%20from%20weather.forecast%20where%20woeid%20in%20(" +
                "select%20woeid%20from%20geo.places(1)%20where%20text%3D%22$city%22)" +
                "&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys"
        doAsync {
            val result = URL(oneWordURL).readText()
            uiThread {
                parseJson(result)
            }
        }
    }

    private fun parseJson(s: String) {
        val gson = GsonBuilder().create().fromJson(s, Response::class.java)
        tv.text = "One word weather condition ${gson.query.results.channel.item.condition.text}"
    }
}