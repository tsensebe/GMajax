# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table loot (
  id                        bigint not null,
  description               varchar(255),
  cat                       integer,
  str                       integer,
  dex                       integer,
  intel                     integer,
  vita                      integer,
  bonus_life                decimal(38),
  armor                     integer,
  dmg_min                   integer,
  dmg_max                   integer,
  att_spd                   decimal(38),
  att_spd_bonus             decimal(38),
  resist_phys               integer,
  resist_all                integer,
  resist_arcane             integer,
  resist_cold               integer,
  resist_fire               integer,
  resist_lightning          integer,
  resist_poison             integer,
  constraint pk_loot primary key (id))
;

create table perso (
  id                        bigint not null,
  lvl                       integer,
  str                       integer,
  dex                       integer,
  intel                     integer,
  vita                      integer,
  main_carac                integer,
  classe                    varchar(255),
  resist_bonus              decimal(38),
  crit                      decimal(38),
  dmg_crit                  decimal(38),
  constraint pk_perso primary key (id))
;

create table spell (
  id                        bigint not null,
  cat                       integer,
  rune                      integer,
  description               varchar(255),
  tool_kit_link             varchar(255),
  dmg_multiplicator         decimal(38),
  dot_time                  decimal(38),
  constraint pk_spell primary key (id))
;

create sequence loot_seq;

create sequence perso_seq;

create sequence spell_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists loot;

drop table if exists perso;

drop table if exists spell;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists loot_seq;

drop sequence if exists perso_seq;

drop sequence if exists spell_seq;

