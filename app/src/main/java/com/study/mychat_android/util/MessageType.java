package com.study.mychat_android.util;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/*
 *
 * Author: jinguang
 * Create: 2019/11/14 18:35
 * Description:
 */
@IntDef({MessageType.TYPE_TXT,MessageType.TYPE_PIC,MessageType.TYPE_VIDEO,MessageType.TYPE_AUDIO})
@Retention(RetentionPolicy.SOURCE)
public @interface MessageType {
    int TYPE_TXT = 1;//文字信息
    int TYPE_PIC = 2;//图片信息
    int TYPE_VIDEO = 3;//视频信息
    int TYPE_AUDIO = 4;//音频信息


}
