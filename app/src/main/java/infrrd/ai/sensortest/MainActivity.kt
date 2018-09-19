package infrrd.ai.sensortest

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity(), SensorEventListener {

    private val TAG = MainActivity::class.java.simpleName
    private lateinit var sensorManager: SensorManager
    private var stepCounter: Sensor? = null
    private var stepCount: Int = 0
    override fun onAccuracyChanged(sensor: Sensor?, variation: Int) {
        Log.d(TAG, "onAccuracyChanged " + sensor?.type.toString())

    }

    override fun onSensorChanged(sensorEvent: SensorEvent?) {
        Log.d(TAG, "OnSensorChanged " + sensorEvent.toString())
        if (sensorEvent?.sensor?.type == Sensor.TYPE_STEP_DETECTOR) {
            stepCount += 1
        }
        stepValue.text = stepCount.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        stepValue.text = "hello"
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
    }

    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(this)
    }
}