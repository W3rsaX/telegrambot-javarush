package com.github.javarushcommunity.tbjr.command;

public enum CommandName {

  START("/start"),
  HELP("/help"),
  NO("nocommand"),
  STAT("/stat"),
  ADD_GROUP_SUB("/addgroupsub"),
  LIST_GROUP_SUB("/listgroupsub"),
  DELETE_GROUP_SUB("/deletegroupsub"),
  ADMIN_HELP("/ahelp"),
  STOP("/stop");

  private final String commandName;

  CommandName(String commandName) {
    this.commandName = commandName;
  }

  public String getCommandName() {
    return commandName;
  }
}
