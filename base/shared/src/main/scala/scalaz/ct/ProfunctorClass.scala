package scalaz
package ct

trait ProfunctorClass[F[_, _]] {
  def lmap[A, B, C](fab: F[A, B])(ca: C => A): F[C, B]
  def rmap[A, B, C](fab: F[A, B])(bc: B => C): F[A, C]
  def dimap[A, B, C, D](fab: F[A, B])(ca: C => A)(bd: B => D): F[C, D]
}

object ProfunctorClass {

  trait DeriveDimap[F[_, _]] extends ProfunctorClass[F] with Alt[DeriveDimap[F]] {
    final override def dimap[A, B, C, D](fab: F[A, B])(ca: C => A)(bd: B => D): F[C, D] = rmap(lmap(fab)(ca))(bd)
  }

  trait DeriveLRMap[F[_, _]] extends ProfunctorClass[F] with Alt[DeriveLRMap[F]] {
    final override def lmap[A, B, C](fab: F[A, B])(ca: C => A): F[C, B] = dimap[A, B, C, B](fab)(ca)(identity)
    final override def rmap[A, B, C](fab: F[A, B])(bc: B => C): F[A, C] = dimap[A, B, A, C](fab)(identity)(bc)
  }

  trait Alt[D <: Alt[D]]
}
