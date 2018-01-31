package com.example.serializationtest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.serializationtest.parcelable_model.MyObjectParcelable
import com.example.serializationtest.serializable_model.MyObjectSerializable
import kotlinx.android.synthetic.main.second_activity.*

/**
 * Created by Sergey Panshyn on 30.01.2018.
 */

class SecondActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        deserialize(MainActivity.currentState == MainActivity.SERIALIZABLE_STRING)
    }

    private fun deserialize(useSerializable: Boolean) {
        val endTime: Long

        if (useSerializable) {
            val obj = intent.getSerializableExtra("my_object") as MyObjectSerializable
            endTime = System.currentTimeMillis()
            text_list_size.text = getString(R.string.list_size, obj.list.size)
        } else {
            val obj = intent.getParcelableExtra<MyObjectParcelable>("my_object")
            endTime = System.currentTimeMillis()
            text_list_size.text = getString(R.string.list_size, obj.list.size)
        }

        val startTime = intent.getLongExtra("start_time", 0)
        text_time.text = getString(R.string.time, endTime-startTime)
        text_type.text = getString(R.string.type, MainActivity.currentState)
    }
}