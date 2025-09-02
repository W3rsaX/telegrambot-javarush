package com.github.javarushcommunity.tbjr.command;


import static com.github.javarushcommunity.tbjr.command.CommandName.HELP;
import static com.github.javarushcommunity.tbjr.command.CommandName.NO;
import static com.github.javarushcommunity.tbjr.command.CommandName.START;
import static com.github.javarushcommunity.tbjr.command.CommandName.STOP;

import com.github.javarushcommunity.tbjr.service.SendBotMessageService;
import com.google.common.collect.ImmutableMap;

public class CommandContainer {

  private final ImmutableMap<String, Command> commandMap;
  private final Command unknownCommand;

  public CommandContainer(SendBotMessageService sendBotMessageService) {

    commandMap = new ImmutableMap.Builder<String, Command>()
        .put(START.getCommandName(), new StartCommand(sendBotMessageService))
        .put(STOP.getCommandName(), new StopCommand(sendBotMessageService))
        .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
        .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
        .build();

    unknownCommand = new UnknownCommand(sendBotMessageService);
  }

  public Command retrieveCommand(String commandIdentifier) {
    return commandMap.getOrDefault(commandIdentifier, unknownCommand);
  }
}
