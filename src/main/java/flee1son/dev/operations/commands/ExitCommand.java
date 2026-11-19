package flee1son.dev.operations.commands;

import flee1son.dev.operations.ConsoleOperationType;
import flee1son.dev.operations.OperationCommand;
import flee1son.dev.utils.ShutdownManager;
import org.springframework.stereotype.Component;

@Component
public class ExitCommand implements OperationCommand {
    private final ShutdownManager shutdownManager;

    public ExitCommand(ShutdownManager shutdownManager) {
        this.shutdownManager = shutdownManager;
    }

    @Override
    public void execute() {
        shutdownManager.shutdown();
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.EXIT;
    }
}
