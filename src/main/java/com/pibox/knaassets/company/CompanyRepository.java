package com.pibox.knaassets.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findCompanyById(Long id);

    Company findCompanyByVatNumber(String vatNumber);

    @Query("SELECT c FROM Company c where lower(c.title) like lower(concat('%', ?1, '%'))")
    List<Company> findCompaniesByTitleContains(String keyword);
}
