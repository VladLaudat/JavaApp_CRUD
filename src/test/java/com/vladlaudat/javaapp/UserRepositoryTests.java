package com.vladlaudat.javaapp;

import com.vladlaudat.javaapp.user.User;
import com.vladlaudat.javaapp.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.cache.support.NullValue;
import org.springframework.test.annotation.Rollback;

import java.util.Collection;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired private UserRepository repo;

    @Test
    public void TestAddNew(){
        User user=new User();
        user.setEmail("other@yahoo.com");
        user.setFirstname("other");
        user.setLastname("Laudat");
        user.setPassword("pass2003");

        System.out.println(user);

        User savedUser = repo.save(user);

        Assertions.assertNotNull(savedUser);
        Assertions.assertTrue(savedUser.getId()>0);
    }

    @Test
    public void testListAll(){
        Iterable<User> users = repo.findAll();
        Assertions.assertTrue(users.iterator().hasNext());
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate(){
        Integer UsedId=11;
        Optional<User> OptionalUser = repo.findById(UsedId);
        User user=OptionalUser.get();
        user.setPassword("aaaa");
        repo.save(user);

        User updatedUser = repo.findById(UsedId).get();
        Assertions.assertTrue(updatedUser.getPassword().equals("aaaa"));
    }

    @Test
    public void testDelete(){
        Integer userId = 12;
        Optional<User> user= repo.findById(userId);
        Assertions.assertTrue(user.isPresent());

        repo.deleteById(userId);
        user= repo.findById(userId);
        Assertions.assertTrue(user.isEmpty());

    }
}
