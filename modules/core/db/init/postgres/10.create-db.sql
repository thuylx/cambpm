-- begin CAMBPM_BPM_PROCESS_DEFINITION
create table CAMBPM_BPM_PROCESS_DEFINITION (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    KEY_ varchar(255) not null,
    NAME varchar(255),
    DESCRIPTION text,
    BPM_PROCESS_DEFINITION_GROUP_ID uuid,
    ACTIVE boolean,
    --
    primary key (ID)
)^
-- end CAMBPM_BPM_PROCESS_DEFINITION
-- begin CAMBPM_BPM_PROCESS_DEFINITION_ENTITY
create table CAMBPM_BPM_PROCESS_DEFINITION_ENTITY (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    BPM_PROCESS_DEFINITION_ID uuid,
    ENTITY_NAME varchar(255),
    --
    primary key (ID)
)^
-- end CAMBPM_BPM_PROCESS_DEFINITION_ENTITY
-- begin CAMBPM_BPM_TASK_LIST
create table CAMBPM_BPM_TASK_LIST (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    DESCTIPTION varchar(255),
    ORDER_ integer,
    --
    primary key (ID)
)^
-- end CAMBPM_BPM_TASK_LIST
-- begin CAMBPM_BPM_TASK_LIST_FILTER
create table CAMBPM_BPM_TASK_LIST_FILTER (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    PARAMETER_ varchar(50) not null,
    VALUE_ varchar(255) not null,
    ORDER_ integer,
    BPM_TASK_LIST_ID uuid,
    --
    primary key (ID)
)^
-- end CAMBPM_BPM_TASK_LIST_FILTER
-- begin CAMBPM_BPM_PROCESS_DEFINITION_GROUP
create table CAMBPM_BPM_PROCESS_DEFINITION_GROUP (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    --
    primary key (ID)
)^
-- end CAMBPM_BPM_PROCESS_DEFINITION_GROUP
-- begin CAMBPM_NEW_ENTITY
create table CAMBPM_NEW_ENTITY (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    REASON varchar(255),
    --
    primary key (ID)
)^
-- end CAMBPM_NEW_ENTITY
