package com.kevinkan.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* A transaction is possibly invalid if:
* - the amount exceeds $1000, or;
* - if it occurs within (and including) 60 minutes of another transaction with the same name in a different city.
* You are given an array of strings transaction where transactions[i] consists of comma-separated values representing the name, time (in minutes), amount, and city of the transaction.
* Return a list of transactions that are possibly invalid. You may return the answer in any order.
* 
* Constraints:
* 1 <= transactions.length <= 1000
* Each transactions[i] takes the form "{name},{time},{amount},{city}"
* Each {name} and {city} consist of lowercase English letters, and have lengths between 1 and 10.
* Each {time} consists of digits representing an integer between 0 and 1000.
* Each {amount} consists of digits representing an integer between 0 and 2000.
*/
public class InvalidTransactions {

    public List<String> invalidTransactions(String[] transactions) {
        List<String> invalids = new ArrayList<>();
        Map<String, List<Transaction>> personTxns = new HashMap<>();

        for (String transaction : transactions) {
            Transaction txn = new Transaction(transaction);

            // Add this new transaction to the storage map
            personTxns.putIfAbsent(txn.name, new ArrayList<>());
            personTxns.get(txn.name).add(txn);
        }

        for (String transaction : transactions) {
            Transaction txn = new Transaction(transaction);

            if (txn.isInvalid(personTxns.get(txn.name))) {
                invalids.add(transaction);
            }
        }

        return invalids;
    }

    public class Transaction {
        String name;
        int time;
        int amount;
        String city;

        public Transaction(String transaction) {
            String[] split = transaction.split(",");
            this.name = split[0];
            this.time = Integer.parseInt(split[1]);
            this.amount = Integer.parseInt(split[2]);
            this.city = split[3];
        }

        public boolean isInvalid(List<Transaction> personalTransactions) {
            return isInvalidAmount() || isInvalidCityTime(personalTransactions);
        }

        private boolean isInvalidAmount() {
            return this.amount > 1000;
        }

        private boolean isInvalidCityTime(List<Transaction> personalTransactions) {
            for (Transaction t : personalTransactions) {
                if (!this.city.equals(t.city) && Math.abs(this.time - t.time) <= 60) {
                    return true;
                }
            }
            return false;
        }
    }
}
