package uz.bakhromjon;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Cat cat = new Cat("Stella");
        if (cat.getClass().isAnnotationPresent(VeryImportant.class)) {
            System.out.println("This thing very important");
        }
        for (Method method : cat.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(RunImmediately.class)) {
                RunImmediately annotation = method.getAnnotation(RunImmediately.class);
                for (int i = 0; i < annotation.times(); i++) {
                    method.invoke(cat);
                }
            }
        }

        for (Field field : cat.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(ImportantString.class)) {
                field.setAccessible(true);
                Object objectValue = field.get(cat);
                if (objectValue instanceof String) {
                    field.set(cat, ((String) objectValue).toUpperCase());
                }
            }
        }
        System.out.println(cat.getName());
    }
}