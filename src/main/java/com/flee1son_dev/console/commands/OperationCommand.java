package com.flee1son_dev.console.commands;

import com.flee1son_dev.console.ConsoleOperationType;

public interface OperationCommand {
    void execute();

    ConsoleOperationType getOperationType();

    
}
