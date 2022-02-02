package com.fundamentos.springboot.fundamentos.service;

import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UseService {
    private final Log LOG = LogFactory.getLog(UseService.class);
    private UserRepository userRepository;

    public UseService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional
    public void saveTransactional(List<User> users){
        users.stream()
                .peek(user -> LOG.info("Usuario insertado: "+user))
                .forEach(userRepository::save);
                //.forEach(user -> userRepository.save((user)));
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

}
