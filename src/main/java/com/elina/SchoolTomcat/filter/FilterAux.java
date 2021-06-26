package com.elina.SchoolTomcat.filter;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.math.NumberUtils;

@Getter
public class FilterAux {
    private String path;
    private String method;
    private int id;

    public FilterAux(String path) {
        this.path = path;
        if (path==null) method = "Method 1";
        else if(NumberUtils.isNumber(path.substring(1))) {
            method = "Method 2";
            id = Integer.parseInt(path.substring(1));
        }
        else method = "error";
    }

}
