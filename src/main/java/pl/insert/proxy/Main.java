//https://www.javatpoint.com/proxy-pattern
//https://www.teamten.com/lawrence/programming/avoid-fields-for-communication-between-methods.html

package pl.insert.proxy;

public class Main {

    public static void main(String [] args){

        String name = "12345";

        Dog realDog = new RealDog("aaa");

        Dog dogRroxy = new TransactionalDog(realDog);


        Dog dogRroxyProxy = new TransactionalDog(dogRroxy);

        realDog.execute();

    }
}
