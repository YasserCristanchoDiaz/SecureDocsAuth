package co.com.auth_back.auth_back.service;

import co.com.auth_back.auth_back.models.AccessHours;
import co.com.auth_back.auth_back.repositories.AccessHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AccessHoursService")
public class AccessHoursService extends GeneralService<AccessHours> {
    private final AccessHoursRepository accessHoursRepository;

    @Autowired
    public AccessHoursService(AccessHoursRepository accessHoursRepository) {
        super(accessHoursRepository);
        this.accessHoursRepository = accessHoursRepository;
    }

    public AccessHours getAccessHoursByRol(String rol) {
        return accessHoursRepository.findByRol(rol);
    }

    @Override
    public long countByFileter(AccessHours accessHours) {
        return 0;
    }
}
