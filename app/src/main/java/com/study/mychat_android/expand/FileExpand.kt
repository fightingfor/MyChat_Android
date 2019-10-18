package com.study.mychat_android.expand

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.core.content.FileProvider
import com.study.mychat_android.MainApplication
import java.io.File

/**
 * Created by jinguang on 2019/10/18.
 */


/**
 * 获取文件uri
 */
fun getUriFile(context: Context, file: File): Uri? {
    var fileUri: Uri?
    if (Build.VERSION.SDK_INT >= 24) {
        fileUri = getUriForFile24(context, file)
    } else {
        fileUri = Uri.fromFile(file)
    }
    return fileUri
}

/**
 * 获取 sdk 24以及以上文件uri
 */
fun getUriForFile24(context: Context, file: File): Uri? {
    try {
        return FileProvider.getUriForFile(
            context,
            "${MainApplication.getAppContext().packageName}.provider",
            file
        )
    } catch (e: Exception) {

    }

    return null
}


/**
 * 获取应用缓存文件路径
 */
fun getCacheFile(): File {
    val file = File("${MainApplication.getAppContext().filesDir.absoluteFile}")
    if (!file.exists()) {
        file.mkdirs()
    }
    return file
}

/**
 * 获取存储卡缓存文件路径
 */
fun getExternalCacheFile(): File {
    val file = File("${MainApplication.getAppContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.absoluteFile}")
    if (!file.exists()) {
        file.mkdirs()
    }
    return file
}

///**
// * 获取照相机拍照temp缓存
// */
//fun getCameraCacheTemp(): File {
//    var path = SharedPref.getString(SharedPrefKeyManager.KEY_CACHE_FILE_PATH, null)
//    if (path.isNullOrEmpty()) {
//        path = getExternalCacheFile().absolutePath
//        SharedPref.setString(SharedPrefKeyManager.KEY_CACHE_FILE_PATH, path)
//    }
//    val file = File("$path${File.separator}cameraTemp")
//    if (!file.exists()) {
//        file.mkdirs()
//    }
//    return file
//}

/**
 * 获取应用内照相机拍照缓存
 */
fun getCameraCache(): File {
    val file = File("${getCacheFile().absolutePath}${File.separator}camera")
    if (!file.exists()) {
        file.mkdirs()
    }
    return file
}

/**
 *
 * @param fileName 文件名
 * @return 返回路径
 */
fun getPicCacheFilePath(fileName: String): String = File(getCameraCache(), fileName).absolutePath


///**
// * 删除照相机拍照temp缓存
// */
//fun deleteCameraExternalCache() {
//    ThreadPoolUtil.executor("删除照相机拍照临时缓存") {
//        deleteDir(getCameraCacheTemp())
//    }
//}

///**
// * 删除应用内拍照图片
// */
//fun deleteCameraAppCache() {
//    ThreadPoolUtil.executor("删除证件信息图片缓存") {
//        deleteDir(getCameraCache())
//    }
//}


/**
 * 删除目录下的所有文件
 */
fun deleteDir(dir: File) {
    if (dir.isDirectory) {
        val children = dir.listFiles()
        if (children != null && !children.isEmpty()) {
            children.forEach {
                if (it.isFile) {
                    it.delete()
                }
            }
        }
    }
}

fun deleteFiles(file: File?) {
    try {
        if (file!!.exists()) {
            file.delete()
        }
    } catch (e: Exception) {

    }


}

fun deleteFiles(filePath: String?) {
    try {
        val file = File(filePath)
        deleteFiles(file)
    } catch (e: Exception) {

    }
}


