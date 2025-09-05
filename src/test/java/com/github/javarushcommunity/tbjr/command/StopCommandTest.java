package com.github.javarushcommunity.tbjr.command;

import static com.github.javarushcommunity.tbjr.command.CommandName.STOP;
import static com.github.javarushcommunity.tbjr.command.StopCommand.STOP_MESSAGE;

import org.junit.jupiter.api.DisplayName;

@DisplayName("Unit-level testing for StopCommand")
public class StopCommandTest extends AbstractCommandTest {

  @Override
  String getCommandName() {
    return STOP.getCommandName();
  }

  @Override
  String getCommandMessage() {
    return STOP_MESSAGE;
  }

  @Override
  Command getCommand() {
    return new StopCommand(sendBotMessageService, telegramUserService);
  }
}
