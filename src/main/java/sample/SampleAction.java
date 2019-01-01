package sample;

import com.aspectran.core.activity.Translet;
import com.aspectran.core.component.bean.annotation.Action;
import com.aspectran.core.component.bean.annotation.Bean;
import com.aspectran.core.component.bean.annotation.Component;
import com.aspectran.core.component.bean.annotation.Description;
import com.aspectran.core.component.bean.annotation.Dispatch;
import com.aspectran.core.component.bean.annotation.RequestAsGet;
import com.aspectran.core.component.bean.annotation.Transform;
import com.aspectran.core.context.rule.type.TransformType;
import com.aspectran.core.util.logging.Log;
import com.aspectran.core.util.logging.LogFactory;

import java.util.Map;

@Component
@Bean("sampleAction")
public class SampleAction {

    private final Log log = LogFactory.getLog(SampleAction.class);

    /**
     * http://localhost:8080/helloWorld
     */
    @RequestAsGet("/helloWorld")
    @Transform(type = TransformType.XML)
    @Action(id = "message")
    @Description("Defines a translet for printing the sentence \"Hello, World.\"")
    public String helloWorld() {
        String msg = "Hello, World.";

        log.info("The message generated by my first action is: " + msg);

        return msg;
    }

    /**
     * http://localhost:8080/front/helloWorld
     */
    @RequestAsGet("/front/${templateName}")
    @Dispatch(name = "${templateName}")
    @Action(id = "title")
    public String restHelloWorld() {
        return "The message generated by my first action is: ";
    }

    /**
     * http://localhost:8080/echo/this-is-a-message
     */
    @RequestAsGet("/echo/${echoMsg}")
    @Transform(type = TransformType.JSON)
    @Action(id = "message")
    public String restEchoMessage(Translet translet) {
        String echoMsg = translet.getParameter("echoMsg");

        log.info("The message generated by my third action is: " + echoMsg);

        return echoMsg;
    }

    /**
     * http://localhost:8080/echoParams?param1=1&param2=2
     */
    @RequestAsGet("/echoParams")
    @Transform(type = TransformType.JSON)
    @Action(id = "params")
    public Map<String, Object> echoParams(Translet translet) {
        Map<String, Object> params = translet.getAllParameters();

        log.info("params: " + params);

        return params;
    }

}
