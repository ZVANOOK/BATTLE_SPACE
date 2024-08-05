package battlespace.gameserver;

public interface Command {
    void execute() throws Exception;
    int getRetryCount();
    void incrementRetryCount();
}
