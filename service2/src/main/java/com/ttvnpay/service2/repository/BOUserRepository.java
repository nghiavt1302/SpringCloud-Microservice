package com.ttvnpay.service2.repository;

import com.ttvnpay.service2.model.entity.BOUserEntity;
import com.ttvnpay.service2.model.response.BOUserBranchNameRp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BOUserRepository extends JpaRepository<BOUserEntity, Integer> {

    List<BOUserEntity> findByUserId(Integer userId);

    @Query(value = "SELECT " +
            "u.username AS username, u.fullname AS fullname, u.branch_code AS branch_code, " +
            "b.branch_name AS branch_name, b.branch_address AS branch_address " +
            "FROM bo_user u LEFT JOIN cm_branches b " +
            "ON u.branch_code = b.branch_code", nativeQuery = true)
    List<BOUserBranchNameRp> getBOUserBranches();
}
