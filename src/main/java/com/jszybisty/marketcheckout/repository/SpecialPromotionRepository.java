package com.jszybisty.marketcheckout.repository;

import com.jszybisty.marketcheckout.model.SpecialPromotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecialPromotionRepository extends JpaRepository<SpecialPromotion, Long> {
    List<SpecialPromotion> findByIsActiveTrue();
}
