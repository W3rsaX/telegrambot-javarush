package com.github.javarushcommunity.tbjr.command;


import static com.github.javarushcommunity.tbjr.command.CommandName.ADD_GROUP_SUB;
import static com.github.javarushcommunity.tbjr.command.CommandName.DELETE_GROUP_SUB;
import static com.github.javarushcommunity.tbjr.command.CommandName.HELP;
import static com.github.javarushcommunity.tbjr.command.CommandName.LIST_GROUP_SUB;
import static com.github.javarushcommunity.tbjr.command.CommandName.NO;
import static com.github.javarushcommunity.tbjr.command.CommandName.START;
import static com.github.javarushcommunity.tbjr.command.CommandName.STAT;
import static com.github.javarushcommunity.tbjr.command.CommandName.STOP;

import com.github.javarushcommunity.tbjr.javarushclient.JavaRushGroupClient;
import com.github.javarushcommunity.tbjr.service.GroupSubService;
import com.github.javarushcommunity.tbjr.service.SendBotMessageService;
import com.github.javarushcommunity.tbjr.service.TelegramUserService;
import com.google.common.collect.ImmutableMap;

public class CommandContainer {

  private final ImmutableMap<String, Command> commandMap;
  private final Command unknownCommand;

  public CommandContainer(SendBotMessageService sendBotMessageService,
      TelegramUserService telegramUserService, JavaRushGroupClient javaRushGroupClient,
      GroupSubService groupSubService) {

    commandMap = new ImmutableMap.Builder<String, Command>()
        .put(START.getCommandName(), new StartCommand(sendBotMessageService, telegramUserService))
        .put(STOP.getCommandName(), new StopCommand(sendBotMessageService, telegramUserService))
        .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
        .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
        .put(STAT.getCommandName(), new StatCommand(sendBotMessageService, telegramUserService))
        .put(ADD_GROUP_SUB.getCommandName(),
            new AddGroupSubCommand(sendBotMessageService, javaRushGroupClient, groupSubService))
        .put(LIST_GROUP_SUB.getCommandName(), new ListGroupSubCommand(sendBotMessageService, telegramUserService))
        .put(DELETE_GROUP_SUB.getCommandName(),
            new DeleteGroupSubCommand(sendBotMessageService, groupSubService, telegramUserService))
        .build();

    unknownCommand = new UnknownCommand(sendBotMessageService);
  }

  public Command retrieveCommand(String commandIdentifier) {
    return commandMap.getOrDefault(commandIdentifier, unknownCommand);
  }
}
