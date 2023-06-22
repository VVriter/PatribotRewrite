package org.patriot;

import org.patriot.repository.UsersRepository;
import org.patriot.repository.exception.UserNotFoundException;

public class Test implements Constants{
    public static void main(String[] args) throws Exception, UserNotFoundException {
        UsersRepository repository = new UsersRepository();
        repository.addUserMoney("testID", 123);
        System.out.println("Moneys: " + repository.getUser("testID").getMoney());
    }
}
