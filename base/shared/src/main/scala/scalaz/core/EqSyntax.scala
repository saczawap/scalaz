package scalaz
package core

import scala.language.experimental.macros

trait EqSyntax {
  implicit final class ToEqOps[A](a: A) {
    private[core] type equal
    def ===(f: A)(implicit ev: Eq[A]): Boolean = macro meta.Ops.nia_1[equal]
  }
}
