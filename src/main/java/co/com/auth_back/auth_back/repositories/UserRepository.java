package co.com.auth_back.auth_back.repositories;

import co.com.auth_back.auth_back.models.Credential;
import co.com.auth_back.auth_back.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GeneralRepository<User> {
@Query("SELECT u FROM User u WHERE u.credential = :credential")
public User findByCredential(@Param("credential")Credential credential);
}
