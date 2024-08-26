package aws.lambda.sample.app;

import aws.lambda.sample.app.services.TodoService;
import aws.lambda.sample.app.services.impl.DefaultTodoService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppHandler implements RequestHandler<String, Todo> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppHandler.class);

    private final TodoService todoService;

    public AppHandler() {
        this.todoService = new DefaultTodoService();
    }

    @Override
    public Todo handleRequest(String s, Context context) {
        LOGGER.debug("handling todo request for string {}", s);
        return this.todoService.addTodo(s);
    }
}
