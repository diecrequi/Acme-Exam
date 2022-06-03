package acme.features.any.chirp;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.chirp.Chirp;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChirpRepository extends AbstractRepository{
	
	@Query("select a from Chirp a where a.id = :id")
	Chirp findOneChirpById(int id);

	@Query("select a from Chirp a")
	Collection<Chirp> findManyChirp();

}
