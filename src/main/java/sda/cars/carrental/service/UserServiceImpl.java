package sda.cars.carrental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sda.cars.carrental.entity.Users;
import sda.cars.carrental.error.CustomException;
import sda.cars.carrental.repository.UsersJpaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UsersJpaRepository userJpaRepository;

    @Autowired
    public UserServiceImpl(UsersJpaRepository userJpaRepo) {
        userJpaRepository = userJpaRepo;
    }

    @Override
    public Users findById(long theId) {
        Optional<Users> result = userJpaRepository.findById(theId);
        Users theUser;
        if (result.isPresent()){
            theUser = result.get();
        } else {
            throw new CustomException("User with ID=" + theId + " Not found");
        }
        return theUser;
    }

    @Override
    public List<Users> findAll() {
        return userJpaRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Users users) {
        userJpaRepository.save(users);
    }

    @Override
    @Transactional
    public void deleteById(long Id) {
        userJpaRepository.deleteById(Id);
    }

    @Override
    public Users findByEmail(String email) {
        return userJpaRepository.findByEmail(email);
    }

    @Override
    public Optional<Users> findOptionalByEmail(String email) {
        Users user = new Users();
        user.setEmail(email);
        Example<Users> userExample = Example.of(user);
        return userJpaRepository.findOne(userExample);
    }
}
