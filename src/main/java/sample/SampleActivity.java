/*
 * Copyright (c) 2016-2024 The Aspectran Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sample;

import com.aspectran.core.activity.Translet;
import com.aspectran.core.component.bean.annotation.Action;
import com.aspectran.core.component.bean.annotation.Bean;
import com.aspectran.core.component.bean.annotation.Component;
import com.aspectran.core.component.bean.annotation.Description;
import com.aspectran.core.component.bean.annotation.Dispatch;
import com.aspectran.core.component.bean.annotation.RequestToGet;
import com.aspectran.core.component.bean.annotation.Transform;
import com.aspectran.core.context.rule.type.FormatType;
import com.aspectran.utils.annotation.jsr305.NonNull;
import com.aspectran.utils.logging.Logger;
import com.aspectran.utils.logging.LoggerFactory;

import java.util.Map;

@Component
@Bean("sampleActivity")
public class SampleActivity {

    private static final Logger logger = LoggerFactory.getLogger(SampleActivity.class);

    /**
     * http://localhost:8080/helloWorld
     */
    @RequestToGet("/helloWorld")
    @Transform(FormatType.XML)
    @Action("message")
    @Description("Defines a translet for printing the sentence \"Hello, World.\"")
    public String helloWorld() {
        String msg = "Hello, World.";

        logger.info("The message generated by my first action is: " + msg);

        return msg;
    }

    /**
     * http://localhost:8080/front/helloWorld
     */
    @RequestToGet("/front/${templateName}")
    @Dispatch(name = "${templateName}")
    @Action("title")
    public String restHelloWorld() {
        return "The message generated by my first action is: ";
    }

    /**
     * http://localhost:8080/echo/this-is-a-message
     */
    @RequestToGet("/echo/${echoMsg}")
    @Transform(FormatType.JSON)
    @Action("message")
    public String restEchoMessage(String echoMsg) {
        logger.info("The message generated by my third action is: " + echoMsg);
        return echoMsg;
    }

    /**
     * http://localhost:8080/echoParams?param1=1&param2=2
     */
    @RequestToGet("/echoParams")
    @Transform(FormatType.JSON)
    @Action("params")
    public Map<String, Object> echoParams(@NonNull Translet translet) {
        Map<String, Object> params = translet.getAllParameters();

        logger.info("params: " + params);

        return params;
    }

}
