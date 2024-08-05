package battlespace.gameserver;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

public class BattleSpaceEngine {
    private static final Logger logger = Logger.getLogger(BattleSpaceEngine.class.getName());
    private ConcurrentLinkedQueue<Command> commandQueue = new ConcurrentLinkedQueue<>();

    public void processCommands() {
        while (!commandQueue.isEmpty()) {
            Command command = commandQueue.poll();
            try {
                command.execute();
            } catch (Exception e) {
                handleException(e, command);
            }
        }
    }

    private void handleException(Exception e, Command failedCommand) {
        if (failedCommand.getRetryCount() < 2) {
            failedCommand.incrementRetryCount();
            commandQueue.offer(new RepeatCommand(failedCommand)); // Повторить команду
        } else {
            commandQueue.offer(new LogCommand(e)); // Записать в лог
        }
    }

    public void addCommand(Command command) {
        commandQueue.offer(command);
    }

    class LogCommand implements Command {
        private Exception exception;

        LogCommand(Exception exception) {
            this.exception = exception;
        }

        @Override
        public void execute() {
            String message = "Произошло исключение: " + exception.getMessage();
            logger.severe(message);
            System.out.print(message); // Вывод в консоль
        }

        @Override
        public int getRetryCount() {
            return 0; // Эта команда не требует повторов
        }

        @Override
        public void incrementRetryCount() {
            // ничего не делаем
        }
    }

    class RepeatCommand implements Command {
        private Command originalCommand;

        RepeatCommand(Command command) {
            this.originalCommand = command;
        }

        @Override
        public void execute() throws Exception {
            originalCommand.execute();
        }

        @Override
        public int getRetryCount() {
            return originalCommand.getRetryCount();
        }

        @Override
        public void incrementRetryCount() {
            originalCommand.incrementRetryCount();
        }
    }
}
