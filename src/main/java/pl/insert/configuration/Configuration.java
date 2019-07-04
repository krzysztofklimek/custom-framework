package pl.insert.configuration;


import pl.insert.annotation.Bean;
import pl.insert.dao.AccountDAO;
import pl.insert.dao.AccountDAOImpl;
import pl.insert.dao.CompanyDAO;
import pl.insert.dao.CompanyDAOImpl;
import pl.insert.service.Service;
import pl.insert.service.ServiceImpl;
import pl.insert.service.ServiceImpl2;

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

    @Bean(name="companyDAO")
    public CompanyDAO metoda3(){
        return new CompanyDAOImpl();
    }

    @Bean(name="accountDAO")
    public AccountDAO accountDAO(){
        return new AccountDAOImpl();
    }
}
