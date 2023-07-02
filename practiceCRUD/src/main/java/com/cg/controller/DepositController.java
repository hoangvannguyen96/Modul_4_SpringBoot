package com.cg.controller;

import com.cg.model.Customer;
import com.cg.model.Deposit;
import com.cg.service.customer.ICustomer;
import com.cg.service.deposit.IDeposit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/deposits")
public class DepositController {
    @Autowired
    private IDeposit depositService;
    @Autowired
    private ICustomer customerService;

    @GetMapping("/{id}")
    public String showDepositPage(@PathVariable String id, Model model) {
        Optional<Customer> customerOptional = customerService.findById(Integer.parseInt(id));
        if (customerOptional.isEmpty()) {
            return "redirect:/errors/404";
        }
        Customer customer = customerOptional.get();
        model.addAttribute("customer", customer);
        Deposit deposit = new Deposit();
        model.addAttribute("deposit", deposit);
        return "deposit/deposit";
    }

    @PostMapping("/{id}")
    public String edit(@PathVariable int id, @ModelAttribute Deposit deposit, Model model) {
        Optional<Customer> customerOptional = customerService.findById(id);
        if (customerOptional.isEmpty()) {
            return "redirect:/errors/404";
        }
        Customer customer = customerOptional.get();
        customer.setBalance(customer.getBalance().add(deposit.getTransactionAmount()));
        customerService.save(customer);
        model.addAttribute("customer", customer);
//        deposit.setCustomer(customer);
        Deposit deposit1=new Deposit(customer,deposit.getTransactionAmount());
        depositService.save(deposit1);
        model.addAttribute("deposit", new Deposit());
        model.addAttribute("success", true);
        model.addAttribute("message", "Nạp tiền thành công");
        return "deposit/deposit";
    }
}
