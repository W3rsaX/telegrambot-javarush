package com.github.javarushcommunity.tbjr.command;

public enum CommandName {

  START("/start"),
  HELP("/help"),
  NO(""),
  STAT("/stat"),
  STOP("/stop");

  private final String commandName;

  CommandName(String commandName) {
    this.commandName = commandName;
  }

  public String getCommandName() {
    return commandName;
  }
}
