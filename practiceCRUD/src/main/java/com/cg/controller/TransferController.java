package com.cg.controller;

import com.cg.model.Customer;
import com.cg.model.Deposit;
import com.cg.model.Transfer;
import com.cg.service.customer.ICustomer;
import com.cg.service.deposit.IDeposit;
import com.cg.service.transfer.ITransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/transfers")
public class TransferController {
    @Autowired
    private ICustomer customerService;
    @Autowired
    private ITransfer transferService;

    @GetMapping("/{id}")
    public String showTransferPage(@PathVariable String id, Model model) {
        Optional<Customer> customerOptional = customerService.findById(Integer.parseInt(id));
        if (customerOptional.isEmpty()) {
            return "redirect:/errors/404";
        }
        Customer sender = customerOptional.get();
        model.addAttribute("sender", sender);
        List<Customer> customers = new ArrayList<>(customerService.findAll());
        customers.removeIf(customer -> customer.getId() == sender.getId());
        model.addAttribute("customers", customers);
        Transfer transfer = new Transfer();
        model.addAttribute("transfer", transfer);
        return "transfer/transfer";
    }

    @PostMapping("/{id}")
    public String transfer(@PathVariable int id, @RequestParam("idRecipient") int idRecipient, @ModelAttribute Transfer transfer, Model model) {
        Optional<Customer> senderOptional = customerService.findById(id);
        Optional<Customer> recipientOptional = customerService.findById(idRecipient);
        if (senderOptional.isEmpty() || recipientOptional.isEmpty()) {
            return "redirect:/errors/404";
        }
        Customer sender = senderOptional.get();
        Customer recipient = recipientOptional.get();
        if (sender.getBalance().compareTo(transfer.getTransactionAmount()) < 0) {
            model.addAttribute("success", true);
            model.addAttribute("message", "Số dư của bạn không đủ để thực hiện chuyển khoản!");
            model.addAttribute("sender", sender);
            List<Customer> customers = new ArrayList<>(customerService.findAll());
            customers.removeIf(customer -> customer.getId() == sender.getId());
            model.addAttribute("customers", customers);
            model.addAttribute("tempIDRecipient",idRecipient);
            model.addAttribute("tempNameRecipient",recipient.getName());
            model.addAttribute("transfer", new Transfer());
            return "transfer/transfer";
        } else {
            sender.setBalance(sender.getBalance().subtract(transfer.getTransactionAmount()));
            recipient.setBalance(recipient.getBalance().add(transfer.getTransferAmount()));
            customerService.save(sender);
            customerService.save(recipient);
            model.addAttribute("sender", sender);
//        transfer.setSender(sender);
//        transfer.setNameSender(sender.getName());
//        transfer.setRecipinet(recipient);
//        transfer.setNameRecipinet(recipient.getName());
            Transfer transfer1 = new Transfer(sender, sender.getName(), recipient, recipient.getName(), transfer.getTransferAmount(), transfer.getTransactionAmount());
            transferService.save(transfer1);
            model.addAttribute("transfer", new Transfer());
            List<Customer> customers = new ArrayList<>(customerService.findAll());
            customers.removeIf(customer -> customer.getId() == sender.getId());
            model.addAttribute("customers", customers);
            model.addAttribute("success", true);
            model.addAttribute("tempIDRecipient",idRecipient);
            model.addAttribute("tempNameRecipient",recipient.getName());
            model.addAttribute("message", "Chuyển tiền thành công");
            return "transfer/transfer";
        }
    }
}
