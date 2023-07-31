package co.com.auth_back.auth_back.service;

import co.com.auth_back.auth_back.models.Credential;
import co.com.auth_back.auth_back.repositories.CredentialRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("CredentialService")
public class CredentialService extends GeneralService<Credential>{

    private final CredentialRepository credentialRepository;

    public CredentialService(CredentialRepository credentialRepository) {
        super(credentialRepository);
        this.credentialRepository = credentialRepository;
    }

    public Optional<Credential> findByUserAndMail(String id){
        return credentialRepository.findByUserNameOrMail(id,id);
    }

    @Override
    public long countByFileter(Credential credential) {
        return 0;
    }

}
