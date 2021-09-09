package sda.cars.carrental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sda.cars.carrental.entity.Branches;
import sda.cars.carrental.error.CustomException;
import sda.cars.carrental.repository.BranchesJpaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService {

    private BranchesJpaRepository branchesJpaRepository;

    @Autowired
    public BranchServiceImpl(BranchesJpaRepository branchJpaRepo) {
        branchesJpaRepository = branchJpaRepo;
    }

    @Override
    public Branches findById(long theId) {

        Optional<Branches> result = branchesJpaRepository.findById(theId);
        Branches theBranch;
        if (result.isPresent()){
            theBranch = result.get();
        } else {
            throw new CustomException("branch ID=" + theId + " Not found");
        }
        return theBranch;
    }

    @Override
    public List<Branches> findAll() {
        return branchesJpaRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Branches theBranch) {
        branchesJpaRepository.save(theBranch);
    }

    @Override
    @Transactional
    public void deleteById(long theId) {
        branchesJpaRepository.deleteById(theId);
    }


}
