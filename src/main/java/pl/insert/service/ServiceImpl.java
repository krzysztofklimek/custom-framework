package pl.insert.service;


import pl.insert.annotation.Autowired;
import pl.insert.annotation.Qualifier;
import pl.insert.dao.AccountDAO;
import pl.insert.dao.CompanyDAO;

public class ServiceImpl implements Service {

    @Autowired
    @Qualifier(name="companyDAO")
    private CompanyDAO dao;

    @Autowired
    @Qualifier(name="accountDAO")
    private AccountDAO accountDAO;
}
