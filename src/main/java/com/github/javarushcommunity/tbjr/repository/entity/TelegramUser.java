package com.github.javarushcommunity.tbjr.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "tg_user")
@EqualsAndHashCode(exclude = "groupSubs")
public class TelegramUser {
  @Id
  @Column(name = "chat_id")
  private String chatId;

  @Column(name = "active")
  private boolean active;

  @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
  private List<GroupSub> groupSubs;
}
