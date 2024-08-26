package aws.lambda.sample.app.services;

import aws.lambda.sample.app.Todo;

public interface TodoService {

    Todo addTodo(String todo);
}
