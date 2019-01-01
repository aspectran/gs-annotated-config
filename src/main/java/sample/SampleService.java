package sample;

import com.aspectran.core.activity.Translet;
import com.aspectran.core.component.bean.annotation.Bean;
import com.aspectran.core.component.bean.annotation.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Bean("sampleService")
public class SampleService {

    public String getWelcomeMessage(Translet translet) {
        HttpServletRequest req = translet.getRequestAdaptee();
        String ip = req.getRemoteAddr();
        return "Welcome to Aspectran! (" + ip + ")";
    }

    public String getGoodbyeMessage() {
        return "Goodbye!";
    }

}
