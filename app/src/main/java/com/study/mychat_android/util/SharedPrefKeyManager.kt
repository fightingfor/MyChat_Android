package com.study.mychat_android.util

/**
 * Created by jinguang on 2019/10/18.
 */

class SharedPrefKeyManager {
    companion object {

        const val CASH_KEY_REQUEST_CICY_TIMER: String = "request_cicy_timer"
        const val CASH_KEY_REQUEST_LOCATION_TIMER: String = "request_location_timer"
        const val CASH_KEY_LOCATION_INFO: String = "location_info"

        const val CASH_KEY_POST_MCLC: String = "post_mclc"
        const val KEY_CID = "key_cid"

        /**
         * 是否允许获取IMEI
         */
        const val KEY_AGREE_GET_IMEI = "key_agree_get_imei"

        /**
         * IMEI弹窗点击拒绝的次数
         */
        const val KEY_DIALOG_IMEI_DISAGREE_TIMES = "key_dialog_imei_disagree_times"

        /**
         * IMEI是否勾选“不再弹出”
         */
        const val KEY_DIALOG_IMEI_DONT_SHOW_AGAIN = "key_dialog_imei_dont_show_again"

        const val KEY_CACHE_FILE_PATH: String = "sd_file_path"


        const val KEY_TOKEN = "key_token"
        const val KEY_USER_ID = "key_user_id"
    }
}