package org.kurochan.logback_stackdriver_logging.model

case class ServiceContext(
  service: String,
  version: String
)

case class StackdriverErrorReportingLog(
  severity: String,
  serviceContext: ServiceContext,
  message: String
)
