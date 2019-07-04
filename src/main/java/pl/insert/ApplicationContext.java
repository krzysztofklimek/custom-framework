package pl.insert;

import pl.insert.annotation.Autowired;
import pl.insert.annotation.Bean;
import pl.insert.annotation.Qualifier;
import pl.insert.configuration.Configuration;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationContext {


    //get bean wybiera metoda którą ma wykonać


    private Class cls;
    private Map<String, Object> beansMap;


    public ApplicationContext(Class<?> cls) {
        this.cls = cls;
        beansMap = new HashMap<String, Object>();
    }

    public Map getMap() {
        return beansMap;
    }


//    public <R> R getBean(String nazwa, Class<R> ccc) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
//
//
//        Constructor<?> ctor = cls.getConstructor();
//        Object object = ctor.newInstance(new Object[] { });
//
//
//        Method[] methods = pl.Configuration.class.getMethods();
//        for (Method m : methods) {
//            if (m.isAnnotationPresent(pl.Bean.class)) {
//                pl.Bean ta = m.getAnnotation(pl.Bean.class);
//                //System.out.println(ta.name());
//
//                if(ta.name().equals(nazwa))
//                    return (R) m.invoke(object);
//
//            }
//        }
//        return null;
//    }


    public Object getBean(String annotationName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {


        if (beansMap.containsKey(annotationName))
            return beansMap.get(annotationName);

        else {
            Constructor<?> ctor = cls.getConstructor();
            Object object = ctor.newInstance(new Object[]{});


            Method m = getMethodWithAnnotationName(annotationName);
            if (m != null) {

                //DOSTAJE OBJEKT ZWRACANY Z METODY Z BEANEM
                Object obj = m.invoke(object);

                //DOSTAJĘ LISTE PÓL Z ADNOTACJA AUTOWIRED
                List <Field> fields = getAutowiredField(obj);

                if(fields != null) {
                    for (Field f : fields) {

                        if (f != null) {
                            //System.out.println(f.getName());
                            //System.out.println(getQualifierName(f));
                            f.setAccessible(true);
                            f.set(obj, getBean(getQualifierName(f)));
                        }
                    }
                }
                beansMap.put(annotationName, obj);
                return obj;
            }
            return null;
        }
    }


    private Method getMethodWithAnnotationName(String annotationName) {
        Method[] methodsArray = Configuration.class.getMethods();
        for (Method m : methodsArray) {
            if (m.isAnnotationPresent(Bean.class)) {
                Bean ta = m.getAnnotation(Bean.class);
                if (ta.name().equals(annotationName))
                    return m;
            }
        }
        return null;
    }

    private List<Field> getAutowiredField(Object obj) {
        Field[] fieldsArray = obj.getClass().getDeclaredFields();
        List<Field> resultList = new ArrayList<Field>();
        for (Field f : fieldsArray) {
            if (f.isAnnotationPresent(Autowired.class))
                resultList.add(f);
        }
        if(resultList.size() != 0)
            return resultList;
        else
            return null;
    }

    private String getQualifierName(Field field) {

        if (field.isAnnotationPresent(Qualifier.class)) {
            Qualifier qualifier = field.getAnnotation(Qualifier.class);
            return qualifier.name();
        }
        return null;
    }
}