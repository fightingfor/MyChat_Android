package com.study.mychat_android.expand

import com.study.mychat_android.util.SharedPref
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by jinguang on 2019/10/18.
 */

class TimerUtil {
    companion object {
        const val ONE_DAY_MILLISECONDS = (24 * 3600 * 1000).toLong()//一天的毫秒
        const val ONE_HOUR_MILLISECONDS = (3600 * 1000).toLong()//一个小时的毫秒
        const val ONE_MIN_MILLISECONDS = (60 * 1000).toLong()//一个分钟的毫秒
        const val ONE_SECCOND_MILLISECONDS = (1 * 1000).toLong()//一个秒钟的毫秒
        const val COUNTDOWNTIMER_IV = 999.toLong()//CountDownTimer 有误差

        const val DAY_7 = 7 * ONE_DAY_MILLISECONDS//7天毫秒数
        const val DAY_15 = 15 * ONE_DAY_MILLISECONDS //15天毫秒数
        const val DAY_30 = 30 * ONE_DAY_MILLISECONDS //30天毫秒数
        const val DAY_45 = 45 * ONE_DAY_MILLISECONDS //45天毫秒数
    }
}


/**
 * 判断 多少天之内是否可以再次请求
 * @param sharePerfkey 保存的key
 * @param timers       间隔时间
 * @param tag          log tag
 */
fun checkCanRequest(sharePerfkey: String, timers: Long, tag: String): Boolean {
    var canRequest = false
    val lastTime = SharedPref.getLong(sharePerfkey, -1L)
    logger_i(tag, "sharekey = $sharePerfkey lastTime = $lastTime")
    if (lastTime == -1L) {
        canRequest = true
        logger_i(tag, "lastTime is init")
    } else {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastTime >= timers) {
            canRequest = true
            logger_i(tag, "lastTime >= $timers")
        } else {
            logger_i(tag, "lastTime < $timers")
        }
    }
    return canRequest
}


/**
 * 是否是同一天
 */
fun isSameDay(time: Long): Boolean {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = time
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH) + 1
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    logger_v("debug_Time", "year: $year month: $month day: $day")
    calendar.timeInMillis = System.currentTimeMillis()

    val currentYear = calendar.get(Calendar.YEAR)
    val currentMonth = calendar.get(Calendar.MONTH) + 1
    val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
    logger_v("debug_Time", "currentYear: $currentYear currentMonth: $currentMonth currentDay: $currentDay")
    return year == currentYear && month == currentMonth && currentDay == day
}

/**
 * @param time 时间毫秒值
 * @param regex 日期格式 例：yyyy-MM-dd HH:mm:ss
 * @param locale 根据区域，返回格式化的语言类型
 */
fun time2Str(time: Long, regex: String = "yyyy-MM-dd HH:mm:ss", locale: Locale? = null): String {
    return (if (locale == null) SimpleDateFormat(regex) else SimpleDateFormat(regex, locale)).format(Date(time))
}

fun utcTimeToStr(time: Long): String {
    return time2Str(time - TimeZone.getDefault().rawOffset)
}

/**
 * 时间转换格式：00:00:00,精确到秒，时：分：秒
 */
fun toTimeStr(timeMilliseconds: Long): String {
    if (timeMilliseconds <= 0)
        return "00:00:00"
    val hour = timeMilliseconds / TimerUtil.ONE_HOUR_MILLISECONDS
    val minute = (timeMilliseconds - hour * TimerUtil.ONE_HOUR_MILLISECONDS) / TimerUtil.ONE_MIN_MILLISECONDS
    val second =
        (timeMilliseconds - hour * TimerUtil.ONE_HOUR_MILLISECONDS - minute * TimerUtil.ONE_MIN_MILLISECONDS) / TimerUtil.ONE_SECCOND_MILLISECONDS
    var str = StringBuilder()
    if (hour < 10) {
        str.append("0")
    }
    str.append(hour).append(":")
    if (minute < 10) {
        str.append("0")
    }
    str.append(minute).append(":")
    if (second < 10) {
        str.append("0")
    }
    str.append(second)
    return str.toString()
}

