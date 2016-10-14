logback-stackdriver-logging
=====

## Example Configuration

logback.xml

```
<configuration>

    <appender name="AppLogAppender" class="ch.qos.logback.core.FileAppender">
        <file>logs/log-for-error-reporting.log</file>
        <append>true</append>
        <encoder class="ch.qos.logback.core.encoder.LayoutWrappingEncoder">
            <layout class="org.kurochan.logback_stackdriver_logging.StackdriverErrorReportingLayout" />
        </encoder>
    </appender>

    <appender name="RawLogAppender" class="ch.qos.logback.core.FileAppender">
        <file>logs/log-for-stackdriver-logging.log</file>
        <append>true</append>
        <encoder class="ch.qos.logback.core.encoder.LayoutWrappingEncoder">
            <layout class="org.kurochan.logback_stackdriver_logging.StackdriverLoggingLayout" />
        </encoder>
    </appender>

    <logger name="com.example.my_app" additivity="false">
         <level value="DEBUG"/>
         <appender-ref ref="RawLogAppender"/>
    </logger>

    <root level="DEBUG">
        <appender-ref ref="AppLogAppender" />
    </root>
</configuration>
```
