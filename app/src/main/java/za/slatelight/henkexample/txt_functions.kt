package za.slatelight.henkexample

import android.content.Context
import org.json.JSONObject
import java.io.*

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


//  Retrieves data from specified file
fun retrieveFrom_txt(_fileName:String, _ctx:Context):String{
    var jsonStringObject:String

    if(_fileName.toString().trim()!=""){
        var fileInputStream: FileInputStream? = null
        try {
            fileInputStream = _ctx.openFileInput(_fileName)
            val inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder: StringBuilder = StringBuilder()
            var text: String? = null
            while ({ text = bufferedReader.readLine(); text }() != null) {
                stringBuilder.append(text)
            }
            jsonStringObject = stringBuilder.toString()
        }catch (e:java.io.FileNotFoundException){
            //  For some reason if we get here the following procedures will solve the problem,
            //  but will close the app. When app is open again the files will exist.
            print(e)
            jsonStringObject = e.toString()
//            if (_fileName == "config.txt") { createConfigFile(_fileName, _ctx) }
//            if (_fileName == "main_menu.txt") { createMainMenuFile(_fileName, _ctx) }
//            if (_fileName == "DeviceTransactionCount.txt") { createDeviceTransactionCountFile(_fileName, _ctx) }

            // Re Execute's function
            retrieveFrom_txt(_fileName, _ctx)
        }
    }else{
//        jsonString = "file name cannot be blank"
        alert(_ctx, "file name cannot be blank")
        jsonStringObject = "failed"
    }

    return jsonStringObject
}


fun get_jwt(_ctx: Context):String{
    val raw_data = JSONObject(retrieveFrom_txt("login_details.txt", _ctx))
    val data = raw_data.getJSONObject("OnlineSettings")
    val jwt = data.getString("JWT_Token")

    return jwt.toString()
}


fun get_SessionData(_ctx: Context): JSONObject {
    val raw_data = JSONObject(retrieveFrom_txt("login_details.txt", _ctx))
    val data = raw_data.getJSONObject("OnlineSettings")
    val sessionData = data.getJSONObject("SessionData")

    return sessionData
}