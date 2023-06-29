package controller;

import model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.CustomerService;
import service.ICustomer;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    ICustomer customerService = new CustomerService();

    @GetMapping
    public ModelAndView showListPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/list");
        List<Customer> customers = customerService.findAll();
        if (customers != null) {
            for (Customer customer : customers) {
                System.out.println(customer.toString());
            }
        }
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customer/create";

    }

    @PostMapping("/create")
    public String create(@ModelAttribute Customer customer, Model model) {
        customerService.add(customer);
        model.addAttribute("customer", customer);
        return "customer/create";
    }

    @GetMapping ("/{id}")
    public String showEditPage(@PathVariable String id, Model model) {
        Customer customer = customerService.getCustomerById(Integer.parseInt(id));
        model.addAttribute("customer", customer);
        return "customer/edit";
    }

    @PostMapping("/{id}")
    public String edit(@PathVariable int id, @ModelAttribute Customer customer, Model model) {
        customer.setId(id);
        customerService.update(customer);
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "customer/list";
    }
    @GetMapping("delete/{id}")
    public String showDeletePage(@PathVariable String id, Model model) {
        Customer customer = customerService.getCustomerById(Integer.parseInt(id));
        model.addAttribute("customer", customer);
        return "customer/delete";
    }
    @PostMapping ("delete/{id}")
    public String delete(@PathVariable int id,Model model) {
        customerService.remove(id);
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "customer/list";
    }

}
