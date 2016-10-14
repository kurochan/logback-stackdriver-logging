package org.kurochan.logback_stackdriver_logging

import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.{CoreConstants, LayoutBase}
import org.json4s.DefaultFormats
import org.json4s.native.Serialization.write
import org.kurochan.logback_stackdriver_logging.model.StackdriverLoggingLog

class StackdriverLoggingLayout extends LayoutBase[ILoggingEvent] {

  implicit val formats = DefaultFormats

  def doLayout(event: ILoggingEvent): String = {
    val log = StackdriverLoggingLog(
      severity = event.getLevel.levelStr,
      message = event.getFormattedMessage
    )

    write(log) + CoreConstants.LINE_SEPARATOR
  }
}