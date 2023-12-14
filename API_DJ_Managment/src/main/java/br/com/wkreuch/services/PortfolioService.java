package br.com.wkreuch.services;

import br.com.wkreuch.exceptions.ErrorCode;
import br.com.wkreuch.exceptions.RequiredObjectIsNullException;
import br.com.wkreuch.exceptions.ResourceNotFoundException;
import br.com.wkreuch.models.Portfolio;
import br.com.wkreuch.repositories.PortfolioRepository;
import br.com.wkreuch.utils.mapper.PortfolioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Transactional
    public Portfolio create(Portfolio portfolio) {
        if (portfolio == null) throw new RequiredObjectIsNullException();

        return portfolioRepository.save(portfolio);
    }

    public Portfolio findById(Long id) {
        return portfolioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ErrorCode.NOT_FOUND.getMessage()));
    }

    public List<Portfolio> findAll() {
        return portfolioRepository.findAll();
    }

    @Transactional
    public Portfolio update(Long id, Portfolio portfolio) {
        if (portfolio == null) throw new RequiredObjectIsNullException();

        Portfolio portfolioPersisted = portfolioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ErrorCode.NOT_FOUND.getMessage()));

        PortfolioMapper.copyProperties(portfolio, portfolioPersisted, "idPortfolio");

        portfolioPersisted = portfolioRepository.save(portfolioPersisted);
        return portfolioPersisted;
    }

    @Transactional
    public void delete(Long id){
        Portfolio portfolioPersisted = portfolioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ErrorCode.NOT_FOUND.getMessage()));
        portfolioRepository.delete(portfolioPersisted);
    }
}
