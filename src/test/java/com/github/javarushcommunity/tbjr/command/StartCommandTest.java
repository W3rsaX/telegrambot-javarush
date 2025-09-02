package com.github.javarushcommunity.tbjr.command;

import static com.github.javarushcommunity.tbjr.command.CommandName.START;
import static com.github.javarushcommunity.tbjr.command.StartCommand.START_MESSAGE;

import org.junit.jupiter.api.DisplayName;

@DisplayName("Unit-level testing for StartCommand")
class StartCommandTest extends AbstractCommandTest {

  @Override
  String getCommandName() {
    return START.getCommandName();
  }

  @Override
  String getCommandMessage() {
    return START_MESSAGE;
  }

  @Override
  Command getCommand() {
    return new StartCommand(sendBotMessageService);
  }
}
