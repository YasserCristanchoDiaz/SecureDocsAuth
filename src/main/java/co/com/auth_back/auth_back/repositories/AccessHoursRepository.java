package co.com.auth_back.auth_back.repositories;

import co.com.auth_back.auth_back.models.AccessHours;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessHoursRepository extends GeneralRepository<AccessHours> {
   public AccessHours findByRol(String rol);
}
