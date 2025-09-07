package com.github.javarushcommunity.tbjr.command;

import com.github.javarushcommunity.tbjr.javarushclient.JavaRushGroupClient;
import com.github.javarushcommunity.tbjr.service.GroupSubService;
import com.github.javarushcommunity.tbjr.service.SendBotMessageService;
import com.github.javarushcommunity.tbjr.service.TelegramUserService;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@DisplayName("Unit-level testing for CommandContainer")
public class CommandContainerTest {

  private CommandContainer commandContainer;

  @BeforeEach
  public void init() {
    SendBotMessageService sendBotMessageService = Mockito.mock(SendBotMessageService.class);
    TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
    JavaRushGroupClient groupClient = Mockito.mock(JavaRushGroupClient.class);
    GroupSubService groupSubService = Mockito.mock(GroupSubService.class);
    commandContainer = new CommandContainer(sendBotMessageService, telegramUserService, groupClient, groupSubService);
  }

  @Test
  public void shouldGetAllTheExistingCommands() {
    Arrays.stream(CommandName.values()).forEach(commandName -> {
      Command command = commandContainer.retrieveCommand(commandName.getCommandName());
      Assertions.assertNotEquals(UnknownCommand.class, command.getClass());
    });
  }

  @Test
  public void shouldReturnUnknownCommand() {
    String unknownCommand = "/fgjhdfgdfg";

    Command command = commandContainer.retrieveCommand(unknownCommand);

    Assertions.assertEquals(UnknownCommand.class, command.getClass());
  }
}
