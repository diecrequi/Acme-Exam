/*
 * InventorToolKitRepository.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.inventor.toolKit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.artifact.PartOf;
import acme.artifact.ToolKit;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;


@Repository
public interface InventorToolKitRepository extends AbstractRepository {

	@Query("select t from ToolKit t where t.id = :id")
	ToolKit findOneToolKitById(int id);
	
	@Query("select t from ToolKit t where t.inventor.id=:id")
	Collection<ToolKit> findManyToolKitsByInventorId(int id);

	@Query("select i from Inventor i where i.id=:id")
	Inventor findOneInventorById(int id);

	@Query("select p from PartOf p where p.toolkit.id=:id")
	Collection<PartOf> findAllPartoOfByToolKit(int id);

	@Query("select t from ToolKit t where t.code=:code")
	ToolKit findAnyToolKitByCode(String code);

}
