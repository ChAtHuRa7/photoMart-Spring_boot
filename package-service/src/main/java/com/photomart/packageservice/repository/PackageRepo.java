package com.photomart.packageservice.repository;


import com.photomart.packageservice.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface PackageRepo extends JpaRepository<Package,Long> {
    @Transactional
    long deleteByPackageId(@NonNull long packageId);
    @Transactional
    @Modifying
    @Query("""
            update Package p set p.packageTittle = ?1, p.packageDescription = ?2, p.price = ?3, p.packageStatus = ?4, p.activeStatus = ?5
            where p.packageId = ?6""")
    int updatePackageByPackageId(@NonNull String packageTittle, @NonNull String packageDescription,
                                 @NonNull double price, @NonNull String packageStatus, @NonNull boolean activeStatus,
                                 @NonNull long packageId);
    long countByPhotographerId(@NonNull long photographerId);

    List<Package> findByPhotographerId(@NonNull long photographerId);

}
