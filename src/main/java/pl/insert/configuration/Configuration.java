package pl.insert.configuration;


import pl.insert.annotation.Bean;

import pl.insert.daoProxy.EntityManagerProxy;
import pl.insert.daoProxy.InterfaceUserDao;
import pl.insert.daoProxy.RealUserDao;
import pl.insert.service.Service;
import pl.insert.service.ServiceImpl;
import pl.insert.service.ServiceImpl2;

import javax.persistence.EntityManager;

public class Configuration {

    @Bean(name="serviceImpl")
    public Service metoda1(){
        return new ServiceImpl();
        //System.out.println("metoda1");
    }

    @Bean(name="serviceImpl2")
    public Service metoda2(){
        return new ServiceImpl2();
    }

//    @Bean(name="companyDAO")
//    public CompanyDAO metoda3(){
//        return new CompanyDAOImpl();
//    }
//
//    @Bean(name="accountDAO")
//    public AccountDAO accountDAO(){
//        return new AccountDAOImpl();
//    }


    @Bean(name="entityManager")
    public EntityManager entityManager(){
        return new EntityManagerProxy();
    }

    @Bean(name="userDao")
    public InterfaceUserDao userDao(){
        return new RealUserDao();
    }
}


