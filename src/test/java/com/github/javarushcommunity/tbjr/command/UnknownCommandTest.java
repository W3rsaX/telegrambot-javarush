package com.github.javarushcommunity.tbjr.command;

import static com.github.javarushcommunity.tbjr.command.UnknownCommand.UNKNOWN_MESSAGE;

import org.junit.jupiter.api.DisplayName;

@DisplayName("Unit-level testing for UnknownCommand")
public class UnknownCommandTest extends AbstractCommandTest {

  @Override
  String getCommandName() {
    return "/fdgdfgdfgdbd";
  }

  @Override
  String getCommandMessage() {
    return UNKNOWN_MESSAGE;
  }

  @Override
  Command getCommand() {
    return new UnknownCommand(sendBotMessageService);
  }
}