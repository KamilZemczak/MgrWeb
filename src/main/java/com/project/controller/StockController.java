package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

import com.project.model.User;
import com.project.model.Stock;
import com.project.model.Wallet;
import com.project.searcher.StockSearcher;
import com.project.searcher.WalletSearcher;
import com.project.service.UserService;
import com.project.service.StockService;
import com.project.service.WalletService;
import com.project.validator.UniValidator;

@Controller
public class StockController {

    @Autowired
    private UserService userService;

    @Autowired
    private StockService stockService;

    @Autowired
    private WalletService walletService;

    @Autowired
    private StockSearcher stockSearcher;

    @Autowired
    private WalletSearcher walletSearcher;

    @Autowired
    private UniValidator uniValidator;

    @RequestMapping(value = "/buy-stock", method = RequestMethod.GET)
    public String buy(@RequestParam int id, HttpServletRequest request) {
        Stock stock = stockSearcher.findOne(id);
        stockService.roundPrice(stock);
        request.setAttribute("mode", "MODE_BUY");
        request.setAttribute("stock", stock);
        request.setAttribute("user", userService.getCurrentUser());
        return "index";
    }

    @Transactional
    @RequestMapping(value = "/buy-stock", method = RequestMethod.POST)
    public String buyConfirm(@RequestParam int id, HttpServletRequest request) {
        Stock stock = stockSearcher.findOne(id);
        User user = userService.getCurrentUser();
        stockService.roundPrice(stock);
        if (!uniValidator.validateBuyStockData(request, user, stock)) {
            Integer amount = Integer.valueOf(request.getParameter("amount"));
            walletService.createOrUpdate(id, amount, user, stock);
            stockService.subtractAmount(stock, amount);
            userService.updateMoney(user, stock, amount);
        } else {
            return "index";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/sell-stock", method = RequestMethod.GET)
    public String sell(@RequestParam int id, HttpServletRequest request) {
        Stock stock = stockSearcher.findOne(id);
        stockService.roundPrice(stock);
        request.setAttribute("mode", "MODE_SELL");
        request.setAttribute("stock", stock);
        request.setAttribute("wallet", walletSearcher.findOneByUserAndStock(userService.getCurrentUser(), stockSearcher.findOne(id)));
        request.setAttribute("user", userService.getCurrentUser());
        return "index";
    }

    @Transactional
    @RequestMapping(value = "/sell-stock", method = RequestMethod.POST)
    public String sellConfirm(@RequestParam int id, HttpServletRequest request) {
        Stock stock = stockSearcher.findOne(id);
        User user = userService.getCurrentUser();
        stockService.roundPrice(stock);
        Wallet wallet = walletSearcher.findOneByUserAndStock(user, stock);
        if (!uniValidator.validateSellStockData(request, stock, wallet)) {
            Integer amount = Integer.valueOf(request.getParameter("amount"));
            userService.addMoney(stock, amount, user);
            walletService.deleteOrUpdate(wallet, amount);
            stockService.addAmount(stock, amount);
        } else {
            return "index";
        }
        return "redirect:/";
    }
}
