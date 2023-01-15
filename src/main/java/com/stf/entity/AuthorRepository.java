package com.stf.entity;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findDistinctByNickNameIgnoreCaseAndPhoneNumberOrderBySignDateDesc(String nickName, String phoneNumber);

    List<Author> findByNickNameLike(String nickName);

    @Query("SELECT a FROM Author a WHERE a.phoneNumber = ?1")
    List<Author> findByPhone(String phoneNumber);

    @Query("SELECT a FROM Author a WHERE a.nickName LIKE %?1%")
    List<Author> findBySQL(String nickName);

    @Transactional
    @Modifying
    @Query("UPDATE Author a SET a.nickName = ?1 WHERE a.phoneNumber = ?2")
    int setNickName(String nickName, String phoneNumber);

    Page<Author> findAll(Pageable pageable);
}
