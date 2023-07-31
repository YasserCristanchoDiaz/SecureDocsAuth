package co.com.auth_back.auth_back.repositories;

import co.com.auth_back.auth_back.models.Attempts;
import co.com.auth_back.auth_back.models.Credential;
import org.springframework.stereotype.Repository;

@Repository
public interface AttemptsRepository extends GeneralRepository<Attempts> {

    public Attempts findByCredential(Credential credential);
}
