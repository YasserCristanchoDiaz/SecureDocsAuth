package co.com.auth_back.auth_back.repositories;

import co.com.auth_back.auth_back.models.Document;
import co.com.auth_back.auth_back.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends GeneralRepository<Document> {
    @Query("SELECT d FROM Document d WHERE d.user =:user")
    public Document findByUser(@Param("user") User user);

}
