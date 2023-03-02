module apache.kafka {
    requires kafka.clients;
    requires kafka.streams;
    requires org.apache.logging.log4j.slf4j;
    requires spring.beans;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.core;
    requires spring.kafka;
    requires spring.messaging;
    requires spring.web;
    requires common.entity;
}