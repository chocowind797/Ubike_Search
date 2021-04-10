package com.applab.ubike_search

import org.json.JSONObject
import java.net.URL
import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

val jsonPath =
    """https://data.tycg.gov.tw/api/v1/rest/datastore/0daad6e6-0632-44f5-bd25-5e1de1e9146f?format=json&limit=101"""

fun main() {
    val url = URL(jsonPath)
    val jsonText = url.readText()

    val root = JSONObject(jsonText)
    val result = root.getJSONObject("result")
    val records = result.getJSONArray("records")

    for (j in 0 until records.length()) {
        val jo = records[j]
        jo as JSONObject
        val distance = getDistance(121.3120108551101, 24.990056759076385, jo.getDouble("wgsX"), jo.getDouble("wgsY"))
        if (distance <= 500 && jo.getString("surplusSpace").toInt() > 0) {
            println(
                "${jo.getString("parkName")} : ${jo.getString("surplusSpace")} / ${jo.getInt("totalSpace")}  %.2f".format(
                    distance
                )
            )
        }
    }
}

fun getDistance(long1: Double, lat1: Double, long2: Double, lat2: Double): Double {
    val R = 6378137.0 // 地球半径

    val a = (lat1 - lat2) * Math.PI / 180.0
    val b = (long1 - long2) * Math.PI / 180.0

    val sa2 = sin(a / 2.0)
    val sb2 = sin(b / 2.0)
    return (2 * R * asin(sqrt(sa2 * sa2 + (cos(lat1) * cos(lat2) * sb2 * sb2))))
}