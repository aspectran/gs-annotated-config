package sample;

import com.aspectran.core.activity.Translet;
import com.aspectran.core.component.bean.annotation.Autowired;
import com.aspectran.core.util.logging.Log;
import com.aspectran.core.util.logging.LogFactory;

public class SampleAdvice {

    private final Log log = LogFactory.getLog(SampleAdvice.class);

    @Autowired
    private SampleService simplestService;

    public String welcome(Translet translet) {
        String msg = simplestService.getWelcomeMessage(translet);

        log.info(msg);

        return msg;
    }

    public String goodbye(Translet translet) {
        log.info("activityDataMap " + translet.getActivityDataMap());

        String msg = simplestService.getGoodbyeMessage();

        log.info(msg);

        return msg;
    }

}
