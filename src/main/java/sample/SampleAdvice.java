package sample;

import com.aspectran.core.activity.Translet;
import com.aspectran.core.component.bean.annotation.After;
import com.aspectran.core.component.bean.annotation.Aspect;
import com.aspectran.core.component.bean.annotation.Autowired;
import com.aspectran.core.component.bean.annotation.Bean;
import com.aspectran.core.component.bean.annotation.Before;
import com.aspectran.core.component.bean.annotation.Component;
import com.aspectran.core.component.bean.annotation.Joinpoint;
import com.aspectran.core.context.rule.type.JoinpointTargetType;
import com.aspectran.core.util.logging.Log;
import com.aspectran.core.util.logging.LogFactory;

/**
 * This Aspect will:
 * If the request URI is "/ helloWorld",
 * execute the method welcome() of the sampleAdvice bean before executing the Translet
 * and execute the method goodbye() of the sampleAdvice bean after executing the Translet.
 */
@Component
@Bean("sampleAdvice")
@Aspect("sampleAspect")
@Joinpoint(
        target = JoinpointTargetType.TRANSLET,
        pointcut = {
            "+: /helloWorld@sampleAction"
        }
)
public class SampleAdvice {

    private final Log log = LogFactory.getLog(SampleAdvice.class);

    private final SampleService sampleService;

    @Autowired
    public SampleAdvice(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @Before
    public String welcome(Translet translet) {
        String msg = sampleService.getWelcomeMessage(translet);

        log.info(msg);

        return msg;
    }

    @After
    public String goodbye(Translet translet) {
        log.info("activityDataMap " + translet.getActivityDataMap());

        String msg = sampleService.getGoodbyeMessage();

        log.info(msg);

        return msg;
    }

}
