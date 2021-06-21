package com.elina.SchoolTomcat.util;

import com.elina.SchoolTomcat.model.Course;
import com.elina.SchoolTomcat.model.Department;
import com.elina.SchoolTomcat.model.Student;
import com.elina.SchoolTomcat.model.Teacher;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Utility {

    public static <T> T getObject (HttpServletRequest request, ObjectMapper objectMapper, Class<T> type) throws IOException {
        String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        return objectMapper.readValue(json, type);
    }

    public static <T> List<T> getListOfObjects(HttpServletRequest request, ObjectMapper objectMapper, Class<T[]> type) throws IOException {
        String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        return Arrays.asList(objectMapper.readValue(json, type));

    }
}
