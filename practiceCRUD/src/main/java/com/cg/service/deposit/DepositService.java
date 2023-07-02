package com.cg.service.deposit;

import com.cg.model.Deposit;
import com.cg.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DepositService implements IDeposit {
    @Autowired
    private DepositRepository depositRepository;

    @Override
    public List<Deposit> findAll() {
        return depositRepository.findAll();
    }

    @Override
    public Optional<Deposit> findById(Integer id) {
        return depositRepository.findById(id);
    }

//    public List<Deposit> findListById(Integer id) {
//        List<Deposit> deposits = new ArrayList<>();
//        Optional<Deposit> depositOptional = depositRepository.findById(id);
//        if (depositOptional.isPresent()) {
//            deposits.add(depositOptional.get());
//        }
//        return deposits;
//    }
    @Override
    public Deposit save(Deposit deposit) {
        return depositRepository.save(deposit);
    }

    @Override
    public void delete(Deposit deposit) {
        depositRepository.delete(deposit);
    }

    @Override
    public void deleteById(Integer id) {
        depositRepository.deleteById(id);
    }
}
