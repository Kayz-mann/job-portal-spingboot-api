package com.kayzmann.job.app.review.impl;

import com.kayzmann.job.app.company.Company;
import com.kayzmann.job.app.company.CompanyService;
import com.kayzmann.job.app.review.Review;
import com.kayzmann.job.app.review.ReviewRepository;
import com.kayzmann.job.app.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews =  reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company =  companyService.getCompanyById(companyId);
        if(company != null){
            review.setCompany(company);
             reviewRepository.save(review);
             return  true;
        }else {
            return false;
        }

    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
       List<Review> reviews =  reviewRepository.findByCompanyId(companyId);
        return (Review) reviews.stream().filter(review -> review.getId().equals((reviewId)));
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview){
        if(companyService.getCompanyById(companyId) != null){
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if(companyService.getCompanyById(companyId) != null && reviewRepository.existsById(reviewId)){
            Review review =  reviewRepository.findById(reviewId).orElse(null);
            Company company =  review.getCompany();
            companyService.updateCompany(company, companyId);
            reviewRepository.deleteById(reviewId);
            return true;
        }else{
            return false;
        }
    }



}
