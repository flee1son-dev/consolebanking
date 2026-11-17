package flee1son.dev.operations;

public interface OperationCommand {
    void execute();

    ConsoleOperationType getOperationType();
}
