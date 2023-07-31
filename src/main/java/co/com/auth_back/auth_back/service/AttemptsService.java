package co.com.auth_back.auth_back.service;

import co.com.auth_back.auth_back.models.Attempts;
import co.com.auth_back.auth_back.models.Credential;
import co.com.auth_back.auth_back.repositories.AttemptsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttemptsService extends GeneralService<Attempts> {
    private final AttemptsRepository attemptsRepository;

    @Autowired
    public AttemptsService(AttemptsRepository attemptsRepository) {
        super(attemptsRepository);
        this.attemptsRepository = attemptsRepository;
    }

    public Attempts findByCredential(Credential credential) {
        return attemptsRepository.findByCredential(credential);
    }

    @Override
    public long countByFileter(Attempts attempts) {
        return 0;
    }
}
