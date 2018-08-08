package system.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import system.domain.Users;
import system.exception.BankException;
import system.repository.UserRepository;
import system.vo.ListUsersVO;
import system.vo.RegistrationUserVO;
import system.vo.UsersVO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class UserService  {


    @Autowired
    UserRepository userRepository;

    @Transactional
    public Boolean userRegistration(RegistrationUserVO vo){
        log.info("userRegistration");
        Users username = userRepository.findByUserName(vo.userName);
        if(username != null){
            if(username.getUserName().equals(vo.getUserName())){
                throw new BankException("username sudah terdaftar");
            }
        }

        String generatePassword = password(vo.getPassword());
        Users user = new Users();
        user.setCreatedBy("SYSTEM");
        user.setLastModifiedDate(new Date());
        user.setLastModifiedBy("SYSTEM");
        user.setFullName(vo.getFullName());
        user.setUserName(vo.getUserName());
        user.setEmail(vo.getEmail());
        user.setPassword(generatePassword);
        userRepository.save(user);

        return  Boolean.TRUE;

    }

    @Transactional
    public Boolean deleteUser(String secureId){
        log.info("deleteUser");
        Users users = userRepository.findBySecureId(secureId);
        if(users == null) throw new BankException("secureId tidak ditemukan");
        userRepository.delete(users);
        return Boolean.TRUE;
    }

    @Transactional
    public Boolean updateUser(String secureId , RegistrationUserVO vo){
        log.info("updateUser");
        Users users = userRepository.findBySecureId(secureId);
        if(users == null) throw new BankException("secureId tidak ditemukan");
        String generatePassword = password(vo.getPassword());
        users.setPassword(generatePassword);
        users.setEmail(vo.getEmail());
        users.setUserName(vo.userName);
        users.setFullName(vo.getFullName());
        userRepository.save(users);
        return Boolean.TRUE;
    }

    public List<UsersVO> listUser(){
        log.info("listUser");
        List<Users> user = userRepository.findAll();
        ListUsersVO usersVO = new ListUsersVO();
        List<UsersVO> listUser = new ArrayList<>();
        for(Users users : user){
            UsersVO vo =  new UsersVO();
            vo.setUsername(users.getUserName());
            vo.setFullname(users.getFullName());
            vo.setEmail(users.getEmail());
            listUser.add(vo);
        }
        usersVO.setUsersVOList(listUser);
        return listUser;
    }


    private String password(String password){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(password.getBytes());
        byte byteData[] = md.digest();
        StringBuffer passwords = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            passwords.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return String.valueOf(passwords);
    }

    private String randomKey(){
        Random rand = new Random();
        int  n = rand.nextInt(10000) + 1;
        return String.valueOf(n);
    }

}
