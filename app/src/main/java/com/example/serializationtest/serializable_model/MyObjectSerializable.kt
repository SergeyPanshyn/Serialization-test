package com.example.serializationtest.serializable_model

import java.io.Serializable

/**
 * Created by Sergey Panshyn on 30.01.2018.
 */

class MyObjectSerializable(var age: Int, var name: String?, var list: List<DataSerializable>) : Serializable
