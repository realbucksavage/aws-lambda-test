package aws.lambda.sample.app;

import java.util.Date;

public record Todo(
        String id,
        String task,
        Date createdAt
) {
}
