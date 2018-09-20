package io.github.chenfh5.common

import java.text.{DateFormat, SimpleDateFormat}

object OwnUtils {

  // time util
  private val sdfHiveFull = new ThreadLocal[DateFormat]() {
    override protected def initialValue(): DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E") // e.g., 2018-08-20 17:04:33 星期一
  }

  private val sdfHiveFullConcat = new ThreadLocal[DateFormat]() {
    override protected def initialValue(): DateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-S") // e.g., 2018-08-20 17:04:33 星期一
  }

  def getTimeNow(concat: Boolean = false): String = {
    if (concat) sdfHiveFullConcat.get().format(System.currentTimeMillis()) else sdfHiveFull.get().format(System.currentTimeMillis())
  }

  // path util
  private val currentDir: String = new java.io.File(getClass.getProtectionDomain.getCodeSource.getLocation.toURI.getPath).getParentFile.getParent

  def getCurrentDir: String = currentDir

  def getDirSep: String = scala.reflect.io.File.separator

  def makeFile[T](param: T*): String = param.mkString(getDirSep)

}
