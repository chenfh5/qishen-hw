package common

object OwnUtils {

  private val currentDir: String = new java.io.File("").getAbsolutePath

  def getCurrentDir: String = currentDir

  def getDirSep: String = scala.reflect.io.File.separator

  def makeFile[T](param: T*): String = param.mkString(getDirSep)

}
