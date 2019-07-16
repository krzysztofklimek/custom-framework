package pl.insert;

import pl.insert.annotation.Autowired;
import pl.insert.annotation.Bean;
import pl.insert.annotation.Qualifier;
import pl.insert.annotation.Transactional;
import pl.insert.configuration.Configuration;
import pl.insert.daoProxy.DynamicInvocationHandler;
import pl.insert.daoProxy.InterfaceUserDao;

import java.lang.reflect.*;
import java.util.*;

public class ApplicationContext {


    //get bean wybiera metoda którą ma wykonać


    private Class cls;
    private Map<String, Object> beansMap;

    public ApplicationContext(Class<?> cls) {
        this.cls = cls;
        beansMap = new HashMap<String, Object>();
    }


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
                List<Field> fields = getAutowiredField(obj);

                if (fields != null) {
                    for (Field f : fields) {

                        if (f != null) {
                            f.setAccessible(true);
                            f.set(obj, getBean(getQualifierName(f)));
                        }
                    }
                }

                // zamien obj na proxy(obj) jezeli ma @Transactional
                if (isTranscational(obj))
                    obj = makeObjectProxy(obj, m);


                beansMap.put(annotationName, obj);
                return obj;
            }
            return null;
        }
    }

    private boolean isTranscational(Object object) {
        return Arrays.asList(object.getClass().getMethods()).stream().anyMatch(m -> m.isAnnotationPresent(Transactional.class));
    }

    private Object makeObjectProxy(Object obj, Method m) {

        DynamicInvocationHandler dynamicInvocationHandler = new DynamicInvocationHandler(obj);

        obj = Proxy.newProxyInstance(
                m.getReturnType().getClassLoader(),
                new Class[]{m.getReturnType()},
                dynamicInvocationHandler);

        return obj;
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
        if (resultList.size() != 0)
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

    public Map getMap() {
        return beansMap;
    }
}