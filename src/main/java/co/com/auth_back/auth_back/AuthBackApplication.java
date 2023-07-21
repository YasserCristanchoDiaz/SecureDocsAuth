package co.com.auth_back.auth_back;

import co.com.auth_back.auth_back.models.triggers.TriggerCreator;
import co.com.auth_back.auth_back.utils.EmailUtil;
import co.com.auth_back.auth_back.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthBackApplication implements CommandLineRunner {

    private final TriggerCreator triggerCreator;

    @Autowired
    public AuthBackApplication(TriggerCreator triggerCreator) {
        this.triggerCreator = triggerCreator;
    }
    public static void main(String[] args) {
        SpringApplication.run(AuthBackApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        triggerCreator.initializeTrigger();
        initializeValues();
    }

    private void initializeValues() {
        TokenUtil.initializeTokenUtil();
        EmailUtil.initializeEmailUtil();
    }

}
