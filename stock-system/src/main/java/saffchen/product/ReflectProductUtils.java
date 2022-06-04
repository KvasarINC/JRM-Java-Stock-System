package saffchen.product;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ReflectProductUtils {
    public void invokeSetter(Product product, String fieldName, Object value) {
        try {
            PropertyDescriptor pd = new PropertyDescriptor(fieldName, product.getClass());
            pd.getWriteMethod().invoke(product, value);
        } catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            System.err.println("Error: Can't invoke the setter");
        }
    }

    public void invokeSetter(RawProduct product, String fieldName, Object value) {
        try {
            PropertyDescriptor pd = new PropertyDescriptor(fieldName, product.getClass());
            pd.getWriteMethod().invoke(product, value);
        } catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            System.err.println("Error: Can't invoke the setter");
        }
    }

    public void invokeGetter(Product product, String fieldName) {
        PropertyDescriptor pd;
        try {
            pd = new PropertyDescriptor(fieldName, product.getClass());
            System.out.println(pd.getDisplayName() + "- " + pd.getReadMethod().invoke(product));
        } catch (IntrospectionException | ReflectiveOperationException | IllegalArgumentException e) {
            System.err.println("Error: Can't invoke the getter");
        }
    }

    public List<String> getFieldsFromClass(Product product){
        List<String> fields = new ArrayList<>();

        try {
            Field[] productFields = product.getClass().getDeclaredFields();
            for(Field f : productFields){
                f.setAccessible(true);
                fields.add(f.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error: Can't get the field names");
        }
        return fields;
    }

    public List<String> getFieldsFromClass(RawProduct product){
        List<String> fields = new ArrayList<>();

        try {
            Field[] productFields = product.getClass().getDeclaredFields();
            for(Field f : productFields){
                f.setAccessible(true);
                fields.add(f.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error: Can't get the field names");
        }
        return fields;
    }
}
