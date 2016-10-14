package org.kurochan.logback_stackdriver_logging
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.{CoreConstants, LayoutBase}

class StackdriverLoggingLayout extends LayoutBase[ILoggingEvent] {

  def doLayout(event: ILoggingEvent): String = {
    s"########################### ${event.getTimeStamp} ${event.getLevel} ${event.getFormattedMessage} ${CoreConstants.LINE_SEPARATOR}"
  }
}