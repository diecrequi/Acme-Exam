package acme.features.administrator.systemSetting;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;
import acme.systemSetting.SystemSetting;

@Repository
public interface AdministratorSystemSettingRepository extends AbstractRepository {
	@Query("select st from SystemSetting st")
	SystemSetting findOneSytemSetting();
	
	
	@Query("select st.weakSpam from SystemSetting st")
	String findWeakSpam();
	
	@Query("select st.strongSpam from SystemSetting st")
	String findStrongSpam();
	
	@Query("select st.weakSpamThreshold from SystemSetting st")
	Integer findWeakSpamThreshold();
	
	@Query("select st.strongSpamThreshold from SystemSetting st")
	Integer findStrongSpamThreshold();
	
}
