package pl.insert.service;

import pl.insert.annotation.Autowired;
import pl.insert.annotation.Qualifier;

public class ServiceImpl2 implements Service {

    @Autowired
    @Qualifier(name="adnotacja1")
    private Service service;
}
