package com.t3.bertalt.kttsoft.model;

import android.graphics.Color;

/**
 * Created by bertalt on 24.07.16.
 */
public enum Departments {

    DEVELOPMENT(0), LOGISTIC(1), MANAGEMENT(2);

    private String departmentStr;

    Departments(int id) {
        this.id = id;
    }

    private int id;

    public int getId() {
        return id;
    }

    public static String getClassName() {
        return Departments.class.getName();
    }



    public static Departments getDepartment(String s) {
        for (Departments status : values()) {
            if (status.departmentStr.equals(s)) {
                return status;
            }
        }
        return null;
    }

    public static Departments getDevelopmentById(int id){

        switch (id){
            case 0:return DEVELOPMENT;

            case 1:return LOGISTIC;

            case 2:return MANAGEMENT;

        }
        return null;
    }

}
