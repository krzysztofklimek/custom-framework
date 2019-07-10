//https://www.javatpoint.com/proxy-pattern
//https://www.teamten.com/lawrence/programming/avoid-fields-for-communication-between-methods.html
//https://blog.appoptics.com/introduction-to-java-threadlocal-storage/

package pl.insert.proxy;

public class Main {

    public static void main(String [] args){

        String name = "12345";

        Dog realDog = new RealDog("aaa");

        Dog dogProxy = new TransactionalDog(realDog);


        Dog dogRroxyProxy = new TransactionalDog(dogProxy);

        //realDog.execute();
        dogProxy.execute();
    }
}
