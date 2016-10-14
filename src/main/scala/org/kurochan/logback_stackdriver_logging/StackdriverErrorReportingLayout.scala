package org.kurochan.logback_stackdriver_logging
import java.io.{PrintWriter, StringWriter}

import ch.qos.logback.classic.spi.{ILoggingEvent, ThrowableProxy}
import ch.qos.logback.core.{CoreConstants, LayoutBase}

class StackdriverErrorReportingLayout extends LayoutBase[ILoggingEvent] {

  def doLayout(event: ILoggingEvent): String = {
    val proxy = Option(event.getThrowableProxy)

    val log = proxy match {
      case Some(p: ThrowableProxy) => {
        val ex = p.getThrowable
        val exceptionWriter = new StringWriter()
        val printWriter = new PrintWriter(exceptionWriter)

        ex.printStackTrace(printWriter)
        exceptionWriter.toString
      }
      case _ => "empty"
    }

    s"########################### ${event.getTimeStamp} ${event.getLevel} ${event.getFormattedMessage} ||${log}|| ${CoreConstants.LINE_SEPARATOR}"
  }
}