package com.example.data.type_adapter

import com.example.domain.entities.Weather
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter

//class WeatherAdapter : TypeAdapter<Weather>() {
//
//    override fun write(out: JsonWriter?, value: Weather?) {
//
//        out?.apply {
//            beginObject()
//
//            value?.let {
//                name("title").value(
//                    when (it.tempC) {
//                        null -> "empty"
//                        else -> it.tempC
//                    }
//                )
//            }
//
//            endObject()
//        }
//    }
//
//    override fun read(`in`: JsonReader?): Weather {
//
//        var temp: Float? = null
//
//        `in`?.beginObject()
//        while (`in`?.hasNext() == true) {
//            val name = `in`.nextName()
//
//            if (`in`.peek() == JsonToken.NULL) {
//                `in`.nextNull()
//                continue
//            }
//
//            when (name) {
//                "title" -> title = `in`.nextString()
//            }
//        }
//        `in`?.endObject()
//
//        return when (title) {
//            null -> Weather("empty", "empty", 0, 0, 0, 0, 0)
//            else -> Weather(title, "empty", 0, 0, 0, 0, 0)
//        }
//    }
//}