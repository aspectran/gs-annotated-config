package sample;

import javax.servlet.http.HttpServletRequest;

import com.aspectran.core.activity.Translet;

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
