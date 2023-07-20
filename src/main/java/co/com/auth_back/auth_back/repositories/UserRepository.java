package co.com.auth_back.auth_back.repositories;

import co.com.auth_back.auth_back.models.Credential;
import co.com.auth_back.auth_back.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends GeneralRepository<User> {

    @Query("SELECT U FROM User as U where U.role NOT IN('A') AND U.active NOT IN('N')"+
            "AND U.name LIKE  %:#{#user.name}% " +
            "AND U.role LIKE %:#{#user.role}% ")
    List<User> findByFilter(
            @Param("user") User user,
            Pageable page
    );

    @Query("SELECT COUNT(U) FROM User AS U WHERE U.role NOT IN('A') AND U.active NOT IN ('N')" +
            "AND U.name LIKE  %:#{#user.name}% " +
            "AND U.role LIKE %:#{#user.role}% ")
    long countByFilter(
            @Param("user") User user
    );
    @Query("SELECT u FROM User u WHERE u.credential = :credential")
public User findByCredential(@Param("credential")Credential credential);
}
