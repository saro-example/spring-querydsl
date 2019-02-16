package me.saro.example.qdsl.account;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import me.saro.commons.Converter;
import me.saro.commons.converter.HashAlgorithm;

@RestController
public class AccountController {
    
    @Autowired AccountRepository accountRepository;
    
    @GetMapping("/account/{page}")
    public List<Account> account(@PathVariable("page") int page) {
        return accountRepository.findAll(PageRequest.of(page, 10));
    }
    
    @GetMapping("/account/save/ac/{ac}/pw/{pw}")
    public Account save(@PathVariable("ac") String ac, @PathVariable("pw") String pw) {
        Account account = new Account();
        account.setAccount(ac);
        account.setPassword(Converter.toHashHex(HashAlgorithm.SHA3_512, pw));
        accountRepository.insert(account);
        return account;
    }
    
    @GetMapping("/account/find/{ac}")
    public List<Account> accountName(@PathVariable("ac") String ac) {
        return accountRepository.findLikeAccount(ac);
    }
}
