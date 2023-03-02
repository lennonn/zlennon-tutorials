module spring.cloud.task {
    requires org.apache.logging.log4j.slf4j;
    requires spring.batch.core;
    requires spring.batch.infrastructure;
    requires spring.beans;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.cloud.task.core;
    requires spring.cloud.task.stream;
    requires spring.context;
}