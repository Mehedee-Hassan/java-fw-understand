package framework.jpamock;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

class User {
    int id;
    String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

interface UserRepository {
    User findById(int id);
    List<User> findAll();
}


class UserRepositoryHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("findById")) {
            System.out.println("Intercepted findById: Fetching user with id " + args[0]);
            // Here you would add your logic to fetch a user from a data source
            return new User((Integer) args[0], "Sample User");
        } else if (method.getName().equals("findAll")) {
            System.out.println("Intercepted findAll: Fetching all users");
            // Here you would add your logic to fetch all users
            return Arrays.asList(new User(1, "User1"), new User(2, "User2"));
        }
        return null;
    }
}

public class TestJPAMock {
    public static void main(String[] args) {
        UserRepository userRepositoryProxy = (UserRepository) Proxy.newProxyInstance(
                UserRepository.class.getClassLoader(),
                new Class<?>[]{UserRepository.class},
                new UserRepositoryHandler()
        );

        // Using the proxy
        User user = userRepositoryProxy.findById(1);
        System.out.println(user.toString());
        List<User> users = userRepositoryProxy.findAll();
        users.forEach( x->
                System.out.println(x.toString())
        );

    }
}

