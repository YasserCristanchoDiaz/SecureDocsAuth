package co.com.auth_back.auth_back.repositories;

import co.com.auth_back.auth_back.models.Credential;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialRepository extends GeneralRepository<Credential>{

    //public List<Credential> findAll(Pageable page);
    public Optional<Credential> findByUserNameOrMail(String user, String mail);
    //public void deleteById(String id);

}
