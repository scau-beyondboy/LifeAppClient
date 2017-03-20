package scau.com.lifeappclient.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

import scau.com.lifeappclient.constants.NetWorkConstants;
import scau.com.lifeappclient.model.ResponseObject;

/**
 * Author:beyondboy
 * Gmail:xuguoli.scau@gmail.com
 * Date: 2015-10-25
 * Time: 00:36
 * 解析json工具类
 */
public class ParseJsonUtils
{
    //private static final String TAG = ParseJsonUtils.class.getName();

    @SuppressWarnings("UnnecessaryReturnStatement")
    public static <T> void parseDataJson(ResponseObject<T> responseObject, String successMessage)
  {
      Gson gson=new Gson();
      String data=gson.toJson(responseObject.getData());
      if(responseObject.getCode()==NetWorkConstants.SUCCESS_CODE&&successMessage!=null)
      {
//          ToaskUtils.displayToast(successMessage);
      }
      else if(responseObject.getCode()==NetWorkConstants.SUCCESS_CODE)
      {
          return;
      }
      else
      {
          ToaskUtils.displayToast(data);
      }
  }

    public static <T> T parseDataJson(ResponseObject<Object> responseObject,Class<T> classOfT)
    {
        Gson gson=new Gson();
        String data=gson.toJson(responseObject.getData());
        if(!StringUtils.isEmpty(responseObject.getMsg())){
//            ToaskUtils.showToast(responseObject.getMsg());
        }
        if(responseObject.getCode()== NetWorkConstants.SUCCESS_CODE)
        {
            return  gson.fromJson(data,classOfT);
        }
        else
        {
//            if(!StringUtils.isEmpty(responseObject.getMsg())){
//                ToaskUtils.showToast(responseObject.getMsg());
//            }
            return null;
        }
    }

    public static <T> T parseDataJson(ResponseObject<Object> responseObject,Class<T> classOfT,final boolean isShowMsg)
    {
        if(responseObject==null||responseObject.getData()==null){
            return null;
        }
        Gson gson=new Gson();
        String data=gson.toJson(responseObject.getData());
        if(!StringUtils.isEmpty(responseObject.getMsg())&&isShowMsg){
//            ToaskUtils.showToast(responseObject.getMsg());
        }
        if(responseObject.getCode()== NetWorkConstants.SUCCESS_CODE)
        {
            return  gson.fromJson(data,classOfT);
        }
        else
        {
//            if(!StringUtils.isEmpty(responseObject.getMsg())){
//                ToaskUtils.showToast(responseObject.getMsg());
//            }
            return null;
        }
    }
    /**返回list集合*/
    public static <T> List<T> paresListDataJson(ResponseObject<Object> responseObject,Type type)
    {
        Gson gson=new Gson();
        String data=gson.toJson(responseObject.getData());
        if(responseObject.getCode()==NetWorkConstants.SUCCESS_CODE)
        {
            return  gson.fromJson(data,type);
        }
        else
        {
            ToaskUtils.displayToast(data);
            return null;
        }
    }
    /**返回list集合*/
    public static <T> T parseDataJson(ResponseObject<Object> responseObject,final Type type,final boolean isShowMsg)
    {
        if(responseObject==null||responseObject.getData()==null){
            return null;
        }
        Gson gson=new Gson();
        String data=gson.toJson(responseObject.getData());
        if(!StringUtils.isEmpty(responseObject.getMsg())&&isShowMsg){
            //ToaskUtils.showToast(responseObject.getMsg());
        }
        if(responseObject.getCode()==NetWorkConstants.SUCCESS_CODE)
        {
            return  gson.fromJson(data,type);
        }
        else
        {
           // ToaskUtils.displayToast(data);
            return null;
        }
    }
}
