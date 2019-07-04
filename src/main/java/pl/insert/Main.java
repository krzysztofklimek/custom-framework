package pl.insert;//http://zacznijprogramowac.net/stream-i-uzycia-map-oraz-flatmap/
//http://tutorials.jenkov.com/java-generics/class-objects-as-type-literals.html
//https://nofluffjobs.com/blog/dependency-injection-w-springu/


import pl.insert.ApplicationContext;
import pl.insert.configuration.Configuration;

import java.lang.reflect.InvocationTargetException;

public class Main {


    public static void main(String [] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {



        String nazwaAdnotacji = "serviceImpl";



        ApplicationContext context = new ApplicationContext(Configuration.class);
        //System.out.println(context.getBean(nazwaAdnotacji));

        System.out.println(context.getMap().size());
        System.out.println(context.getBean(nazwaAdnotacji));
        System.out.println(context.getMap().size());
        System.out.println(context.getBean("serviceImpl2"));
        System.out.println(context.getMap().size());
        System.out.println(context.getBean(nazwaAdnotacji));
        System.out.println(context.getMap().size());


    }


}
