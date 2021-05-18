package za.slatelight.henkexample

import android.content.Context
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

//  Saves data to specified file
fun saveTo_txt(_fileName:String, _jsonString:String, _msg:String,_ctx: Context){
    val fileOutputStream: FileOutputStream
    try {
        fileOutputStream = _ctx.openFileOutput(_fileName, Context.MODE_PRIVATE)
        fileOutputStream.write(_jsonString.toByteArray())
    } catch (e: FileNotFoundException){
        print(e)
        //  For some reason if we get here the following procedures will solve the problem,
        //  but will close the app. When app is open again the files will exist.
//        if (_fileName == "config.txt") { createConfigFile(_fileName, _ctx) }
//        if (_fileName == "DeviceTransactionCount.txt") { createDeviceTransactionCountFile(_fileName, _ctx) }
//        if (_fileName == "onlineSettings.txt") { createDeviceTransactionCountFile(_fileName, _ctx) }

        // Re Execute's function
        saveTo_txt(_fileName, _jsonString, "", _ctx)

    }catch (e: NumberFormatException){
        e.printStackTrace()
    }catch (e: IOException){
        e.printStackTrace()
    }catch (e: Exception){
        e.printStackTrace()
    }
//    alert(_ctx, "Saved")
    if (_msg == ""){ print("$_fileName: Saved") }
    else { alert(_ctx, _msg) }

}