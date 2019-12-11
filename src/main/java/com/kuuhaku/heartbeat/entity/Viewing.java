package com.kuuhaku.heartbeat.entity;

import java.util.Date;

public class Viewing{
    private String id         = "";  //id
    private String projectId ="";   //项目ID
    private String teamCode   = "";  //*机组编号    TEAM_CODE   VARCHAR2(40)
    private String process    = "";  //*工艺规程    PROCESS VARCHAR2(100)
    private String equipCode  = "";  //*设备编号    EQUIP_CODE  VARCHAR2(200)
    private String hjProcess  = "";  //*焊接工艺    HJ_PROCESS  VARCHAR2(100)
    private String personCode = "";  //*人员编号    PERSON_CODE VARCHAR2(200)
    private String weldCode   = "";  //*焊口编号    WELD_CODE   VARCHAR2(400)
    private String position   = "";  //*焊接位置    POSITION    VARCHAR2(40)
    private String layer      = "";  //*焊层    LAYER   VARCHAR2(40)
    private String current    = "";  //*焊接电流    CURRENT VARCHAR2(40)
    private String voltage    = "";  //*焊枪电压    VOLTAGE VARCHAR2(40)
    private String ssSpeed    = "";  //*送丝速度    SS_SPEED    VARCHAR2(40)
    private String temp       = "";  //*环境温度    TEMP    VARCHAR2(40)
    private String humidity   = "";  //*环境湿度    HUMIDITY    VARCHAR2(40)
    private String hjSpeed    = "";  //*焊接速度    HJ_SPEED    VARCHAR2(40)
    private float layerTemp   = 0F; //层间温度    4   32bit-float ℃   焊道温度
    private String workExcep   = "无异常"; //异常    4   32bit-float     工况参数超限报警
    private String datetime   = ""; //时间    20  datetime    -   数据采集时间（精确至秒）
    private Date createTime = null; //服务器存储时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getEquipCode() {
        return equipCode;
    }

    public void setEquipCode(String equipCode) {
        this.equipCode = equipCode;
    }

    public String getHjProcess() {
        return hjProcess;
    }

    public void setHjProcess(String hjProcess) {
        this.hjProcess = hjProcess;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public String getWeldCode() {
        return weldCode;
    }

    public void setWeldCode(String weldCode) {
        this.weldCode = weldCode;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String getSsSpeed() {
        return ssSpeed;
    }

    public void setSsSpeed(String ssSpeed) {
        this.ssSpeed = ssSpeed;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getHjSpeed() {
        return hjSpeed;
    }

    public void setHjSpeed(String hjSpeed) {
        this.hjSpeed = hjSpeed;
    }

    public float getLayerTemp() {
        return layerTemp;
    }

    public void setLayerTemp(float layerTemp) {
        this.layerTemp = layerTemp;
    }

    public String getWorkExcep() {
        return workExcep;
    }

    public void setWorkExcep(String workExcep) {
        this.workExcep = workExcep;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
