package co.com.auth_back.auth_back.models.triggers;

import co.com.auth_back.auth_back.utils.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TriggerCreator {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TriggerCreator(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private void createCheckRoleTrigger(){
        try{
            String sql="CREATE TRIGGER  `check_role` BEFORE INSERT ON user FOR EACH ROW IF NEW.role NOT IN ('L','E','A') THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT ='El valor del role debe ser L, E o A'; END IF;";
            jdbcTemplate.execute(sql);
        }catch(Exception e){
            LoggerUtil.info("Trigger role validator alredy exist");
        }
    }

    private void createCheckStatusTrigger(){
        try{
            String sql="CREATE TRIGGER `check_status` BEFORE INSERT ON user FOR EACH ROW IF NEW.active  NOT IN('S','N') THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='El valor del estado debe ser S, N'; END IF";
            jdbcTemplate.execute(sql);
        }catch(Exception e){
            LoggerUtil.info("Trigger status validator already exist ");
        }
    }

    public void initializeTrigger() {
        createCheckRoleTrigger();
        createCheckStatusTrigger();
    }

}
