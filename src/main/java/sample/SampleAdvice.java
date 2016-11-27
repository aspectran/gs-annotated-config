package sample;

import com.aspectran.core.activity.Translet;
import com.aspectran.core.context.bean.annotation.Autowired;
import com.aspectran.core.util.logging.Log;
import com.aspectran.core.util.logging.LogFactory;

public class SimplestAdvice {
	
	private final Log log = LogFactory.getLog(SimplestAdvice.class);

	@Autowired
	private SampleService simplestService;

	public String welcome(Translet translet) {
		String msg = simplestService.getWelcomeMessage(translet);

		log.info(msg);
		
		return msg;
	}
	
	public String goodbye(Translet translet) {
		log.info("activityDataMap " + translet.getActivityDataMap(true));

		String msg = simplestService.getGoodbyeMessage();
		
		log.info(msg);
		
		return msg;
	}
	
}
