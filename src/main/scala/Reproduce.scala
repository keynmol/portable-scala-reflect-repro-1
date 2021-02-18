import org.portablescala.reflect.annotation.EnableReflectiveInstantiation
import scala.util.control.NoStackTrace

import org.portablescala.reflect.Reflect

// Just a non-parameterized marker trait to help SBT's test detection logic.
@EnableReflectiveInstantiation
trait BaseSuiteClass {}

object Howdy extends BaseSuiteClass {
  def test: Int = 25
}


object Main extends App {

  println(loadModule("Howdy", getClass.getClassLoader))

  def loadModule(qualifiedName: String, loader: ClassLoader): Any = {
    val moduleName = qualifiedName + "$"
    Reflect.lookupLoadableModuleClass(moduleName, loader) match {
      case None =>
        throw new Exception(s"Could not load object $moduleName")
          with NoStackTrace
      case Some(cls) => cls.loadModule()
    }
  }
}
