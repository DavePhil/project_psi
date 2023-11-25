package com.psi.project_psi.utils;

public class Utils {

    public static Object concatObjects(Object o1, Object o2) {
        StringBuffer concatenatedObjects = new StringBuffer();
        concatenatedObjects.append(o1);
        concatenatedObjects.append(o2);
        return concatenatedObjects ;
    }

}
