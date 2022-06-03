package acme.features.authenticated.systemsetting;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;
import acme.systemSetting.SystemSetting;

@Repository
public interface AuthenticatedSystemSettingRepository extends AbstractRepository {

	@Query("select st from SystemSetting st")
	SystemSetting findOneSystemSetting();
}
