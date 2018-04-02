/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rntree;

/**
 *
 * @author Karila
 */

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MakePublicHelper {
    
    /*
    public Object makeAllPublic(Object instance){
        Class<?> cls = instance.getClass();
        
        Method methods[] = cls.getDeclaredMethods();
        for (Method method : methods) {
            method.setAccessible(true);
         }
        Field fields[] = cls.getDeclaredFields();
     
        for (Field field : fields) {
            field.setAccessible(true);
        }
    return instance;
    }
    */
    
    private Field getProcessedField(Object obj, String fieldName) throws NoSuchFieldException{
        Class<?> cls = obj.getClass();
        Field f = cls.getDeclaredField(fieldName);
        f.setAccessible(true);
        return f;
    }
    
    public Object get(Object obj, String fieldName) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
        return this.getProcessedField(obj, fieldName).get(obj);        
    }
    
    public void set(Object obj, String fieldName, Object value) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
        this.getProcessedField(obj, fieldName).set(obj, value);        
    }
    
    public Object invoke(Object obj, String methodName, Object... args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
        Class[] argsClasses = new Class[args.length];
        for (int i = 0; i < args.length; i++){
            argsClasses[i] = args[i].getClass();
        }
        Class<?> cls = obj.getClass();
        Method m = cls.getDeclaredMethod(methodName, argsClasses);
        m.setAccessible(true);
        return m.invoke(obj, args);        
    } 
}
