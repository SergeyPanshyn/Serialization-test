package com.example.serializationtest

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.example.serializationtest.parcelable_model.DataParcelable
import com.example.serializationtest.parcelable_model.MyObjectParcelable
import com.example.serializationtest.serializable_model.DataSerializable
import com.example.serializationtest.serializable_model.MyObjectSerializable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        val PARCELABLE_STRING = "Parcelable"
        val SERIALIZABLE_STRING = "Serializable"
        var currentState = "Serializable"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFab()

        initRadioButtons()
    }

    private fun initRadioButtons() {
        serializable_radio_button.setOnCheckedChangeListener({_, isChecked ->
            if(isChecked) {
                currentState = SERIALIZABLE_STRING
                parcelable_radio_button.isChecked = false
            } else {
                currentState = PARCELABLE_STRING
                parcelable_radio_button.isChecked = true
            }
        })

        parcelable_radio_button.setOnCheckedChangeListener({_, isChecked ->
            if (isChecked) {
                currentState = PARCELABLE_STRING
                serializable_radio_button.isChecked = false
            } else {
                currentState = SERIALIZABLE_STRING
                serializable_radio_button.isChecked = true
            }
        })
    }

    private fun initFab() {
        fab.setOnClickListener { view ->
            if (edit_elements_count.text.isEmpty()) {
                Snackbar.make(view, "Enter element number, please.", Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val intent: Intent
            if (currentState == SERIALIZABLE_STRING) {
                intent = useSerializable()
            } else {
                intent = useParcelable()
            }
            try {
                startActivity(intent)
            }catch (e: Exception) {
                Snackbar.make(view, "Failed binder transaction($currentState). Unreasonably large binder buffer.", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun useParcelable(): Intent {
        val list = generateParcelableList()
        val intent = Intent(this@MainActivity, SecondActivity::class.java)
        intent.putExtra("start_time", System.currentTimeMillis())
        val obj = MyObjectParcelable(100, "name", list)
        intent.putExtra("my_object", obj)
        return intent
    }

    private fun generateParcelableList(): List<DataParcelable> {
        val list = ArrayList<DataParcelable>()
        (0..edit_elements_count.text.toString().toInt()).mapTo(list) { DataParcelable(it, "$it", it %2 == 0, System.currentTimeMillis()) }
        return list
    }


    private fun useSerializable(): Intent {
        val list = generateSerializableList()
        val intent = Intent(this@MainActivity, SecondActivity::class.java)
        intent.putExtra("start_time", System.currentTimeMillis())
        val obj = MyObjectSerializable(100, "name", list)
        intent.putExtra("my_object", obj)
        return intent
    }

    private fun generateSerializableList(): List<DataSerializable> {
        val list = ArrayList<DataSerializable>()
        (0..edit_elements_count.text.toString().toInt()).mapTo(list) { DataSerializable(it, "$it", it %2 == 0, System.currentTimeMillis()) }
        return list
    }
}
