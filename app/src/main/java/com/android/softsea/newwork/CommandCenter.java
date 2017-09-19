package com.android.softsea.newwork;


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

;

/**
 * Created by softsea on 17/9/1.
 * 主要封装请求命令，整合在一起方便调用
 */

public class CommandCenter {


    private JSONObject jsonObj;
    private JSONObject jsonObjArr;


    /**
     * 登录
     *
     * @param phone
     * @param pwd
     * @return
     */
    public String Login(String phone, String pwd) {

        jsonObj = new JSONObject();
        addData(jsonObj, "sjhm", phone);
        addData(jsonObj, "pwd", pwd);
        addData(jsonObj, "cmd", "login");


        Log.e("Conker", "-=-=-=-=" + jsonObj.toString());
        return jsonObj.toString();  //mCrypt.encrypt(JSONUtils.mapToJSON(map).toString());

    }


    /**
     * 获取日志列表
     *
     * @return
     */
    public String getWorkingDynamics() {
        jsonObj = new JSONObject();
        addData(jsonObj, "cmd", "getWorkingDynamics");
        return jsonObj.toString();  //mCrypt.encrypt(JSONUtils.mapToJSON(map).toString());

    }

    /**
     * 获取最近项目
     *
     * @return
     */
    public String getLastProjectList() {
        jsonObj = new JSONObject();
        addData(jsonObj, "cmd", "getLastProjectList");
        return jsonObj.toString();

    }
    /**
     * 获取项目
     *
     * @return
     */
    public String projectGetList() {
        jsonObj = new JSONObject();
        addData(jsonObj, "cmd", "projectGetList");
        return jsonObj.toString();

    }

    /**
     * 获取人员信息
     *
     * @return
     */
    public String getEmpList() {
        jsonObj = new JSONObject();
        addData(jsonObj, "cmd", "getEmpList");
        return jsonObj.toString();

    }

    /**
     * 个人信息
     *
     * @return
     */
    public String getMyInfo() {
        jsonObj = new JSONObject();
        addData(jsonObj, "cmd", "getMyInfo");
        return jsonObj.toString();

    }

    /**
     * 项目详情
     *
     * @return
     */
    public String project(String proId) {
        jsonObj = new JSONObject();
        addData(jsonObj, "cmd", "project");
        addData(jsonObj, "project_id", proId);
        return jsonObj.toString();
    }

    /**
     * 添加日志
     *
     * @param workContent
     * @param workdata
     * @param workLong
     * @param workPro
     * @return
     */
    public String addWorkingLog(String workContent, String workdata, String workLong, String workPro) {
        jsonObj = new JSONObject();
        addData(jsonObj, "cmd", "addWorkingLog");
        addData(jsonObj, "xmbh", workPro);
        addData(jsonObj, "khbh", "");
        addData(jsonObj, "gzrq", workdata);
        addData(jsonObj, "gznr", workContent);
        addData(jsonObj, "gs", workLong);
        addData(jsonObj, "cbfl", 0);
        return jsonObj.toString();
    }



    /////////////////////////////////////去try catch
    private JSONObject addData(JSONObject ob, String name, Object vlaue) {
        try {
            ob.put(name, vlaue);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ob;

    }

    private JSONObject addCmd(JSONObject cmd, String cmdName, JSONObject data) {

        try {
            cmd.put("cmd", cmdName);
            cmd.put("data", data);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cmd;
    }


}
