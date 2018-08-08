package system.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import system.domain.Users;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {

    Users findByUserName(String username);
    Users findBySecureId(String secureId);
    List<Users> findAll();

}
