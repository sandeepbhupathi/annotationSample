package com.sample.annotation;

import sun.reflect.generics.tree.Tree;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.joining;

public class JsonSerializer {
    public String serialize(Object object) throws JsonSerializeException {
        try {
            Class<?> objectClass = requireNonNull(object).getClass();
            TreeMap<String, Object> jsonElements = new TreeMap<>();
            for (Field field: objectClass.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(JsonField.class)) {
                    jsonElements.put(getSerializedKey(field), field.get(object));
                }
            }
            return toJsonString(jsonElements);
        }
        catch (IllegalAccessException e) {
            throw new JsonSerializeException(e.getMessage());
        }
    }
    private String toJsonString(TreeMap<String, Object> jsonMap) {
        String elementsString = jsonMap.descendingMap().entrySet()
                .stream()
                .map(entry -> { if(entry.getValue() instanceof String)
                                    return "\""  + entry.getKey() + "\":\"" + entry.getValue() + "\"";
                        return "\""  + entry.getKey() + "\":" + entry.getValue()  ;
                            })
                .collect(joining(","));
        return "{" + elementsString + "}";
    }
    private static String getSerializedKey(Field field) {
        String annotationValue = field.getAnnotation(JsonField.class).value();
        if (annotationValue.isEmpty()) {
            return field.getName();
        }
        else {
            return annotationValue;
        }
    }
}
