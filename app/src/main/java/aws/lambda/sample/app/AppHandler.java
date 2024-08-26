package aws.lambda.sample.app;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.UUID;

public class AppHandler implements RequestHandler<String, Todo> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppHandler.class);

    @Override
    public Todo handleRequest(String s, Context context) {
        LOGGER.debug("handling todo request for string {}", s);
        return new Todo(UUID.randomUUID().toString(), s, new Date());
    }
}
