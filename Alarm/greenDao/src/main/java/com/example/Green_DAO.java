package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;


public class Green_DAO {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "poiuyt.alarm.model");

        Entity alarm = schema.addEntity("Alarm");
        alarm.addIdProperty();
        alarm.addDateProperty("time");
        alarm.addBooleanProperty("active");
        alarm.addLongProperty("durationTime");
        alarm.addStringProperty("repeatDay");
        alarm.addStringProperty("tonePath");
        alarm.addBooleanProperty("vibrate");
        alarm.addStringProperty("label");

        new DaoGenerator().generateAll(schema, "../app/src/main/java");

    }
}
