package com.example.spring2.services;

import com.example.spring2.Exceptions.EtBadRequestException;
import com.example.spring2.Exceptions.EtResourceNotFoundException;
import com.example.spring2.domain.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> fetchAllTransactions(Integer userId, Integer categoryId);

    Transaction fetchTransactionById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException;

    Transaction addTransaction(Integer userId, Integer categoryId, Double amount, String note, Long transactionDate) throws EtBadRequestException;

    void updateTransaction(Integer userId, Integer categoryId, Integer transactionId, Transaction transaction) throws EtBadRequestException;

    void removeTransaction(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException;
}
