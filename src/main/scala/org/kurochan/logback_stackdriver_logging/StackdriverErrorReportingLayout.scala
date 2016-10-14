package org.kurochan.logback_stackdriver_logging

import java.io.{PrintWriter, StringWriter}

import ch.qos.logback.classic.spi.{ILoggingEvent, ThrowableProxy}
import ch.qos.logback.core.{CoreConstants, LayoutBase}
import org.json4s.DefaultFormats
import org.json4s.native.Serialization.write
import org.kurochan.logback_stackdriver_logging.model.{ServiceContext, StackdriverErrorReportingLog}

class StackdriverErrorReportingLayout extends LayoutBase[ILoggingEvent] {

  implicit val formats = DefaultFormats

  def doLayout(event: ILoggingEvent): String = {
    val proxy = Option(event.getThrowableProxy)

    val message = proxy match {
      case Some(p: ThrowableProxy) => {
        val ex = p.getThrowable
        val exceptionWriter = new StringWriter()
        val printWriter = new PrintWriter(exceptionWriter)

        ex.printStackTrace(printWriter)
        exceptionWriter.toString
      }
      case _ => event.getFormattedMessage
    }

    val context = ServiceContext("my-service", "version-1.0")

    val log = StackdriverErrorReportingLog(
      severity = event.getLevel.levelStr,
      serviceContext = context,
      message = message
    )

    write(log) + CoreConstants.LINE_SEPARATOR
  }
}