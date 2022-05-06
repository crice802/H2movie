package model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ActorRepository {
	private final EntityManager entityManager;

	public ActorRepository(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Actors save(final Actors actor) {
		EntityTransaction transaction = null;
		try {
			transaction = entityManager.getTransaction();
			if (!transaction.isActive()) {
				transaction.begin();
			}

			entityManager.persist(actor);
			transaction.commit();
			return actor;
		} catch (final Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			return null;
		}
	}

	public Optional<Actors> findById(final UUID id) {
		return Optional.ofNullable(entityManager.find(Actors.class, id));
	}

	public List<Actors> findAllBornAfter(final int lowerBoundExclusive) {
		return entityManager.createQuery("SELECT a FROM actors a WHERE a.yearOfBirth > :year", Actors.class)
				.setParameter("year", lowerBoundExclusive).getResultList();
	}

	public List<Actors> findAllWithLastNameEndsWith(final String surnameEndsWith) {
		return entityManager.createQuery("SELECT a FROM actors a WHERE a.lastName LIKE :lastName", Actors.class)
				.setParameter("lastName", "%" + surnameEndsWith).getResultList();
	}
}
