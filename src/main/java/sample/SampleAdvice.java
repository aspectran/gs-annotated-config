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
import com.aspectran.core.component.bean.annotation.After;
import com.aspectran.core.component.bean.annotation.Aspect;
import com.aspectran.core.component.bean.annotation.Autowired;
import com.aspectran.core.component.bean.annotation.Bean;
import com.aspectran.core.component.bean.annotation.Before;
import com.aspectran.core.component.bean.annotation.Component;
import com.aspectran.core.component.bean.annotation.Joinpoint;
import com.aspectran.utils.annotation.jsr305.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        pointcut = {
            "+: /helloWorld@sampleActivity"
        }
)
public class SampleAdvice {

    private final Logger logger = LoggerFactory.getLogger(SampleAdvice.class);

    private final SampleService sampleService;

    @Autowired
    public SampleAdvice(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @Before
    public String welcome(Translet translet) {
        String msg = sampleService.getWelcomeMessage(translet);

        logger.info(msg);

        return msg;
    }

    @After
    public String goodbye(@NonNull Translet translet) {
        logger.info("activityData " + translet.getActivityData());

        String msg = sampleService.getGoodbyeMessage();

        logger.info(msg);

        return msg;
    }

}
